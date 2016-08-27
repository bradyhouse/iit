using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Org.BouncyCastle.Bcpg.OpenPgp;
using System.IO;

namespace iPie
{
    public class Keys
    {
        /// <summary>
        /// long representing the key ID we want to target.
        /// </summary>
        private long _keyID;
        /// <summary>
        /// string to represent the path to the private key file.
        /// </summary>
        private string _privKeyPath;
        /// <summary>
        /// string to represent the path to the public key file.
        /// </summary>
        private string _pubKeyPath;
        /// <summary>
        /// string to represent the passphrase used with PGP.
        /// </summary>
        private string _password;

        public PgpPublicKey PGPPublicKey { get; private set; }
        public PgpPrivateKey PGPPrivateKey { get; private set; }
        public PgpSecretKey PGPSecretKey { get; private set; }

        #region Class Constructor
        
        /// <summary>
        /// Constructor to manage variables for us.
        /// </summary>
        /// <param name="pubKeyPath">string representing the public key path</param>
        /// <param name="privKeyPath">string representing the private key path</param>
        /// <param name="password">string representing the passphrase</param>
        /// <param name="keyID">long representing the key ID we want to use</param>
        public Keys(string pubKeyPath, string privKeyPath, string password, long keyID)
        {
            if (!File.Exists(pubKeyPath))
            {
                throw new ArgumentNullException("Could not find the public key at " + pubKeyPath);
            }
            else
            {
                _pubKeyPath = pubKeyPath;
            }
            if (!File.Exists(privKeyPath))
            {
                throw new ArgumentNullException("Could not find the private key at " + privKeyPath);
            }
            else
            {
                _privKeyPath = privKeyPath;
            }
            if (String.IsNullOrEmpty(password))
            {
                throw new ArgumentNullException("The password must not be null");
            }
            else
            {
                _password = password;
            }
            if (keyID == 0)
            {
                throw new ArgumentNullException("The key ID must not be null");
            }
            else
            {
                _keyID = keyID;
            }
            PGPPublicKey = getPublicKey(_pubKeyPath);
            PGPSecretKey = getSecretKey(_privKeyPath);
            PGPPrivateKey = getPrivateKey(_password);
        }
        
        #endregion
       
        /// <summary>
        /// private method to get the public key value.
        /// </summary>
        /// <param name="_pubKeyPath">string representing the public key path</param>
        /// <returns>a PGPPublicKey</returns>
        private PgpPublicKey getPublicKey(string _pubKeyPath)
        {
            PgpPublicKey pubKey;
            using (Stream keyin = File.OpenRead(_pubKeyPath))
            using (Stream s = PgpUtilities.GetDecoderStream(keyin))
            {
                PgpPublicKeyRingBundle pubKeyBundle = new PgpPublicKeyRingBundle(s);
                pubKey = pubKeyBundle.GetPublicKey(_keyID);
                if (pubKey == null)
                    throw new Exception("The public key value is null!");
            }
            return pubKey;
        }
        /// <summary>
        /// private method to get the secret key value.
        /// </summary>
        /// <param name="_privKeyPath">string representing the private key path</param>
        /// <returns>a PGPSecretKey</returns>
        private PgpSecretKey getSecretKey(string _privKeyPath)
        {
            PgpSecretKey secKey;
            using (Stream keyin = File.OpenRead(_privKeyPath))
            using (Stream s = PgpUtilities.GetDecoderStream(keyin))
            {
                PgpSecretKeyRingBundle secKeyBundle = new PgpSecretKeyRingBundle(s);
                secKey = secKeyBundle.GetSecretKey(_keyID);
                if (secKey == null)
                    throw new Exception("The secret key value is null!");
            }
            return secKey;
        }
        /// <summary>
        /// private method to get the private key value.
        /// </summary>
        /// <returns>a PGPPrivateKey</returns>
        private PgpPrivateKey getPrivateKey(string _password)
        {
            PgpPrivateKey privKey = PGPSecretKey.ExtractPrivateKey(_password.ToCharArray());
            if (privKey == null)
            {
                return null;
            }
            else
            {
                return privKey;
            }
        }

    }
}
