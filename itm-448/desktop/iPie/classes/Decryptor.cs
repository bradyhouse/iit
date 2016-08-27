using Org.BouncyCastle.Bcpg.OpenPgp;
using Org.BouncyCastle.Crypto;
using Org.BouncyCastle.Security;
using Org.BouncyCastle.Utilities.IO;
using Org.BouncyCastle.Bcpg;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace iPie
{
    /// <summary>
    /// Wrapper around Bouncy Castle OpenPGP library.
    /// Bouncy documentation can be found here: http://www.bouncycastle.org/docs/pgdocs1.6/index.html
    /// </summary>
    public class Decryptor
    {
        private Keys mEncryptionKeys;
        private const int bufferSize = 0x10000; // should always be power of 2 

        public Decryptor(Keys encryptionKeys)
        {
            if (encryptionKeys == null)
            {
                throw new ArgumentNullException("encryptionKeys", "encryptionKeys is null.");
            }

            mEncryptionKeys = encryptionKeys;
        }

        public void DecryptAndVerify(Stream inputStream, string outputFile)
        {
            if (inputStream == null)
            {
                throw new ArgumentNullException("inputStream", "inputStream is null.");
            }
            if (outputFile == null)
            {
                throw new ArgumentNullException("outputFile", "outputFile is null.");
            }

            decryptAndVerify(inputStream, outputFile);
        }
        
        private void decryptAndVerify(Stream inputStream, string outputFilePath)
        {
            PgpPublicKeyEncryptedData publicKeyED = extractPublicKeyEncryptedData(inputStream);
            PgpObject message = getClearCompressedMessage(publicKeyED);

            if (message is PgpCompressedData)
            {
                message = processCompressedMessage(message);
                PgpLiteralData literalData = (PgpLiteralData)message;
                using (Stream outputFile = File.Create(outputFilePath))
                {
                    using (Stream literalDataStream = literalData.GetInputStream())
                    {
                        Streams.PipeAll(literalDataStream, outputFile);
                    }
                }
            }

            return;
        }

        private static PgpObject processCompressedMessage(PgpObject message)
        {
            PgpCompressedData compressedData = (PgpCompressedData)message;
            Stream compressedDataStream = compressedData.GetDataStream();
            PgpObjectFactory compressedFactory = new PgpObjectFactory(compressedDataStream);
            message = checkforOnePassSignatureList(message, compressedFactory);
            return message;
        }

        private PgpObject getClearCompressedMessage(PgpPublicKeyEncryptedData publicKeyED)
        {
            PgpObjectFactory clearFactory = getClearDataStream(mEncryptionKeys.PGPPrivateKey, publicKeyED);
            PgpObject message = clearFactory.NextPgpObject();
            return message;
        }

        private static PgpPublicKeyEncryptedData extractPublicKeyEncryptedData(Stream inputStream)
        {
            Stream encodedFile = PgpUtilities.GetDecoderStream(inputStream);
            PgpEncryptedDataList encryptedDataList = getEncryptedDataList(encodedFile);
            PgpPublicKeyEncryptedData publicKeyED = extractPublicKey(encryptedDataList);
            return publicKeyED;
        }

        private static PgpObject checkforOnePassSignatureList(PgpObject message, PgpObjectFactory compressedFactory)
        {
            message = compressedFactory.NextPgpObject();
            if (message is PgpOnePassSignatureList)
            {
                message = compressedFactory.NextPgpObject();
            }
            return message;
        }

        private static PgpObjectFactory getClearDataStream(PgpPrivateKey privateKey, PgpPublicKeyEncryptedData publicKeyED)
        {
            Stream clearStream = publicKeyED.GetDataStream(privateKey);
            PgpObjectFactory clearFactory = new PgpObjectFactory(clearStream);
            return clearFactory;
        }

        private static PgpPublicKeyEncryptedData extractPublicKey(PgpEncryptedDataList encryptedDataList)
        {
            PgpPublicKeyEncryptedData publicKeyED = null;
            foreach (PgpPublicKeyEncryptedData privateKeyED in encryptedDataList.GetEncryptedDataObjects())
            {
                if (privateKeyED != null)
                {
                    publicKeyED = privateKeyED;
                    break;
                }
            }
            return publicKeyED;
        }

        private static PgpEncryptedDataList getEncryptedDataList(Stream encodedFile)
        {
            PgpObjectFactory factory = new PgpObjectFactory(encodedFile);
            PgpObject pgpObject = factory.NextPgpObject();

            PgpEncryptedDataList encryptedDataList;

            if (pgpObject is PgpEncryptedDataList)
            {
                encryptedDataList = (PgpEncryptedDataList)pgpObject;
            }
            else
            {
                encryptedDataList = (PgpEncryptedDataList)factory.NextPgpObject();
            }
            return encryptedDataList;
        }
   
    }
}