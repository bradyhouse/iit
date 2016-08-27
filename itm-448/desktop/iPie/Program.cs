using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;
using System.IO.Compression;
using Org.BouncyCastle.Bcpg;
using Org.BouncyCastle.Bcpg.OpenPgp;
using Org.BouncyCastle.Security;
using System.Reflection;



namespace iPie
{
    public class Program
    {
        #region Encapsulation 

        private const int BufferSize = 0x10000;

        private static string m_str_targetDirectory = string.Empty,
            m_str_workingDirectory = string.Empty,
            m_str_publicKeyPath = string.Empty,
            m_str_privateKeyPath = string.Empty,
            m_str_password = string.Empty,
            m_str_upassword = string.Empty,
            m_str_logon = "Administrator";

        private static Boolean m_boo_decrypt = false;

        private static long m_lng_key;

        private static Keys m_obj_pgpKeys;

        private static Dictionary<string, string> m_dic_Files;

        #endregion
        #region Main

        public static void Main(string[] args)
        {
            /// Print Header
            #region ...

            Header_Display();

            #endregion

            /// Process required input parameters
            #region ...
            
            Arguments _args = new Arguments(args);
            
            if (_args["d"] != null && Directory.Exists(_args["d"]))
                m_str_targetDirectory = _args["d"];
            else
                throw new Exception("No directory parameter, \"-d\", was passed or the directory does not exist.");

            if (_args["puk"] != null && File.Exists(_args["puk"]))
            {
                m_str_publicKeyPath = _args["puk"];
            }
            else
                throw new Exception("No PGP public key file path parameter, \"-puk\", was passed or the parameter value is invalid.");
            
            if (_args["prk"] != null && File.Exists(_args["prk"]))
            {
                m_str_privateKeyPath = _args["prk"];
            }
            else
                throw new Exception("No PGP private key file path parameter, \"-prk\", was passed or the parameter value is invalid.");
            
            if (_args["ps"] != null)
            {
                m_str_password = _args["ps"];
            }
            else
                throw new Exception("No PGP password parameter, \"-ps\", was passed or the parameter value is invalid.");

            if (_args["ap"] != null)
            {
                m_str_upassword = _args["ap"];
            }
            else
                throw new Exception("No Administrator password parameter, \"-ap\", was passed or the parameter value is invalid.");

            if (_args["ki"] != null)
            {
                m_lng_key = long.Parse(_args["ki"], System.Globalization.NumberStyles.HexNumber);
            }
            else
                throw new Exception("No PGP key id parameter, \"-ki\", was passed or the parameter value is invalid.");

            Header_DisplayParameters();

            #endregion

            /// Process optional parameters
            #region ...

            if (_args["decrypt"] != null || _args["DECRYPT"] != null)
                m_boo_decrypt = true;

            #endregion

            /// Initialize PGP Keys
            #region ...

            m_obj_pgpKeys = new Keys(m_str_publicKeyPath, m_str_privateKeyPath, m_str_password, m_lng_key);

            #endregion

            /// Create a working directory
            #region ...

            m_str_workingDirectory = m_boo_decrypt ? System.IO.Path.GetDirectoryName(System.Reflection.Assembly.GetExecutingAssembly().Location.ToString()) +
                "\\Pie_decrypt" : System.IO.Path.GetDirectoryName(System.Reflection.Assembly.GetExecutingAssembly().Location.ToString()) +
                "\\Pie_encrypt"; 

            if (Directory.Exists(m_str_workingDirectory))
                Directory.Delete(m_str_workingDirectory,true);

            Directory.CreateDirectory(m_str_workingDirectory);

            #endregion

            /// Recursively parse the target directory
            #region ...

            m_dic_Files = new Dictionary<string, string>();
         
            Directory_RecursivelyParse(m_str_targetDirectory);
            
            #endregion

            /// Encrypt or decrypt files
            #region ...

            if (m_boo_decrypt)
                Process_Decrypt();
            else
                Process_Encrypt();
           
            #endregion

            string _domain = System.Environment.UserDomainName;
          
            using (Impersonation _imp = new Impersonation(_domain, m_str_logon, m_str_upassword))
            {
                /// Move files
                #region ...
           
                foreach (string _fkey in m_dic_Files.Keys)
                {
                    File.SetAttributes(_fkey,FileAttributes.Normal);
                    File.Delete(_fkey);
                    File.Copy(m_dic_Files[_fkey], _fkey, true);
                    File.SetAttributes(_fkey, FileAttributes.Normal);
                }

                #endregion

                /// Delete working directory
                Directory.Delete(m_str_workingDirectory, true);
            }
        }

        #endregion
        #region Process

        private static void Process_Encrypt()
        {
            foreach (string _fKey in m_dic_Files.Keys)
            {
                Console.WriteLine(_fKey);

                Encryptor _encryptor = new Encryptor(m_obj_pgpKeys);

                FileInfo _oFileInfo = new FileInfo(_fKey);

                using (FileStream _outfile = new FileStream(m_dic_Files[_fKey], FileMode.Create, System.IO.FileAccess.Write))
                {
                    _encryptor.EncryptAndSign(_outfile, _oFileInfo);
                }
            }
        }
        private static void Process_Decrypt()
        {
            foreach (string _fKey in m_dic_Files.Keys)
            {
                Console.WriteLine(_fKey);
                Decryptor _decryptor = new Decryptor(m_obj_pgpKeys);
                using (FileStream _outfile = new FileStream(_fKey, FileMode.OpenOrCreate, System.IO.FileAccess.Read))
                {
                    _decryptor.DecryptAndVerify(_outfile, m_dic_Files[_fKey]);
                }
            }
        }

        #endregion
        #region Directory Functions

        public static void Directory_RecursivelyParse(string dir)
        {
            DirectoryInfo _rootFolder = new DirectoryInfo(dir);
            DirectoryInfo[] _subFolders = _rootFolder.GetDirectories();
            FileInfo[] _files = _rootFolder.GetFiles();

            if (_files.Length > 0)
            {
                foreach (FileInfo _f in _files)
                {
                    m_dic_Files.Add(_f.FullName, _f.FullName.Replace(m_str_targetDirectory, m_str_workingDirectory));
                }
            }

            if (_subFolders.Length > 0)
            {
                foreach (DirectoryInfo _sf in _subFolders)
                {
                    string _workingSubFolder = _sf.FullName.Replace(m_str_targetDirectory, m_str_workingDirectory);

                    if (!Directory.Exists(_workingSubFolder))
                        Directory.CreateDirectory(_workingSubFolder);

                    Directory_RecursivelyParse(_sf.FullName);
                }
            }
        }
        
        #endregion
        #region Display Header
        
        private static void Header_Display()
        {
            ConsoleColor _defaultColor = Console.ForegroundColor;
            Console.ForegroundColor = System.ConsoleColor.Cyan;
            Console.WriteLine("==================================");
            Console.WriteLine(" __     ______   __     ______    ");
            Console.WriteLine("/\\ \\   /\\  == \\ /\\ \\   /\\  ___\\   ");
            Console.WriteLine("\\ \\ \\  \\ \\  _-/ \\ \\ \\  \\ \\  __\\   ");
            Console.WriteLine(" \\ \\_\\  \\ \\_\\    \\ \\_\\  \\ \\_____\\ ");
            Console.WriteLine("  \\/_/   \\/_/     \\/_/   \\/_____/ ");
            Console.WriteLine(" ");
            Console.WriteLine("==================================");
            Console.ForegroundColor = System.ConsoleColor.Green;
            AssemblyName _assemblyName = Assembly.GetExecutingAssembly().GetName();
            Console.WriteLine("iPie.exe Version " + _assemblyName.Version.Major + "." + _assemblyName.Version.Minor + "." +
                _assemblyName.Version.Build + "." + _assemblyName.Version.Revision);
            Console.ForegroundColor = _defaultColor;

        }

        private static void Header_DisplayParameters()
        {
            Console.WriteLine("-d        :   " + m_str_targetDirectory);
            Console.WriteLine("-ki       :   " + m_lng_key.ToString());
            Console.WriteLine("-puk      :   " + m_str_publicKeyPath);
            Console.WriteLine("-prk      :   " + m_str_privateKeyPath);
            Console.WriteLine("-ps       :   " + m_str_password);
            Console.WriteLine("-decrypt  :   " + (m_boo_decrypt ? "true" : "false"));
            Console.WriteLine("");
        }

        #endregion
    } 
} 
