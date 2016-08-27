gpg --gen-key
gpg --list-keys --with-colons blhouseknecht@hotmail.com
gpg --armor --export blhouseknecht@hotmail.com > publicKey.asc
gpg --export-secret-key -a "blhouseknecht@hotmail.com" > private.key
A0ECCFD9BABB782F


