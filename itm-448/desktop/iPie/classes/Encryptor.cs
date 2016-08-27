using Org.BouncyCastle.Bcpg;
using Org.BouncyCastle.Bcpg.OpenPgp;
using Org.BouncyCastle.Security;
using System;
using System.IO;

namespace iPie
{
    public class Encryptor
    {
        private Keys mEncryptionKeys;
        private const int bufferSize = 0x10000; // should always be power of 2 
   
        /// <summary>
        /// Instantiate a new PgpEncrypt class with initialized PgpEncryptionKeys.
        /// </summary>
        /// <param name="encryptionKeys"></param>
        /// <exception cref="ArgumentNullException">encryptionKeys is null</exception>
        public Encryptor(Keys encryptionKeys)
        {
            if (encryptionKeys == null)
            {
                throw new ArgumentNullException("encryptionKeys", "encryptionKeys is null.");
            }

            mEncryptionKeys = encryptionKeys;
        }

        /// <summary>
        /// Encrypt and sign the file pointed to by unencryptedFileInfo and
        /// write the encrypted content to outputStream.
        /// </summary>
        /// <param name="outputStream">
        ///     The stream that will contain the encrypted data when this method returns.
        /// </param>
        /// <param name="fileName">FileInfo of the file to encrypt</param>
        public void EncryptAndSign(Stream outputStream, FileInfo unencryptedFileInfo)
        {
            if (outputStream == null)
            {
                throw new ArgumentNullException("outputStream", "outputStream is null.");
            }
            if (unencryptedFileInfo == null)
            {
                throw new ArgumentNullException("unencryptedFileInfo", "unencryptedFileInfo is null.");
            }
            if (!File.Exists(unencryptedFileInfo.FullName))
            {
                throw new ArgumentException("File to encrypt not found.");
            }

            using (Stream encryptedOut = chainEncryptedOut(outputStream))
            {
                using (Stream compressedOut = chainCompressedOut(encryptedOut))
                {
                    PgpSignatureGenerator signatureGenerator = initSignatureGenerator(compressedOut);
                    using (Stream literalOut = chainLiteralOut(compressedOut, unencryptedFileInfo))
                    {
                        using (FileStream inputFile = unencryptedFileInfo.OpenRead())
                        {
                            writeOutputAndSign(compressedOut, literalOut, inputFile, signatureGenerator);
                        }
                    }
                }
            }
        }

        private static void writeOutputAndSign(Stream compressedOut, Stream literalOut, FileStream inputFile, PgpSignatureGenerator signatureGenerator)
        {
            int length = 0;
            byte[] buf = new byte[bufferSize];

            while ((length = inputFile.Read(buf, 0, buf.Length)) > 0)
            {
                literalOut.Write(buf, 0, length);
                signatureGenerator.Update(buf, 0, length);
            }

            signatureGenerator.Generate().Encode(compressedOut);
        }

        private Stream chainEncryptedOut(Stream outputStream)
        {
            PgpEncryptedDataGenerator encryptedDataGenerator;
            encryptedDataGenerator = new PgpEncryptedDataGenerator(SymmetricKeyAlgorithmTag.TripleDes, new SecureRandom());
            encryptedDataGenerator.AddMethod(mEncryptionKeys.PGPPublicKey);

            return encryptedDataGenerator.Open(outputStream, new byte[bufferSize]);
        }

        private static Stream chainCompressedOut(Stream encryptedOut)
        {
            PgpCompressedDataGenerator compressedDataGenerator = new PgpCompressedDataGenerator(CompressionAlgorithmTag.Zip);
            return compressedDataGenerator.Open(encryptedOut);
        }

        private static Stream chainLiteralOut(Stream compressedOut, FileInfo file)
        {
            PgpLiteralDataGenerator pgpLiteralDataGenerator = new PgpLiteralDataGenerator();
            return pgpLiteralDataGenerator.Open(compressedOut, PgpLiteralData.Binary, file);
        }

        private PgpSignatureGenerator initSignatureGenerator(Stream compressedOut)
        {
            const bool IsCritical = false;
            const bool IsNested = false;

            PublicKeyAlgorithmTag tag = mEncryptionKeys.PGPSecretKey.PublicKey.Algorithm;
            PgpSignatureGenerator pgpSignatureGenerator = new PgpSignatureGenerator(tag, HashAlgorithmTag.Sha1);
            pgpSignatureGenerator.InitSign(PgpSignature.BinaryDocument, mEncryptionKeys.PGPPrivateKey);

            foreach (string userId in mEncryptionKeys.PGPSecretKey.PublicKey.GetUserIds())
            {
                PgpSignatureSubpacketGenerator subPacketGenerator = new PgpSignatureSubpacketGenerator();
                subPacketGenerator.SetSignerUserId(IsCritical, userId);
                pgpSignatureGenerator.SetHashedSubpackets(subPacketGenerator.Generate());

                // Just the first one!
                break;
            }

            pgpSignatureGenerator.GenerateOnePassVersion(IsNested).Encode(compressedOut);
            return pgpSignatureGenerator;
        }

    }
}
