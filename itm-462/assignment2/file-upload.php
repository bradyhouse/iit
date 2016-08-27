<?php

function to_epoch_time($date_str){
    $hours = date('H', strtotime($date_str));
    $minutes = date('i', strtotime($date_str));
    $seconds = date('s', strtotime($date_str));
    $month = date('n', strtotime($date_str));
    $day = date('j', strtotime($date_str));
    $year = date('Y', strtotime($date_str));
    $is_dst = 0;
    return mktime($hours, $minutes, $seconds, $month, $day, $year, $is_dst);
}

if ( isset($_POST["uploadFile"]) )
{
    date_default_timezone_set('US/Central');
    if ( isset($_FILES["file"]))
    {
        $now = date('Y-m-d H:i:s');
        $ext = pathinfo($_FILES["file"]["name"], PATHINFO_EXTENSION);
        if ($ext == 'txt') {
            $name = strval(to_epoch_time($now)).'_'.$_FILES["file"]["name"];
            //if there was an error uploading the file
            if ($_FILES["file"]["error"] > 0) {
                header("Location: index.php");
            }
            else {
                $rawFile = fopen( $_FILES["file"]["tmp_name"] , "r" );
                $fh = fopen("content/".$name, 'x') or die("can't open file");
                $postTitle = $_POST['postTitle']."\n";
                fwrite($fh, $postTitle);
                $postline = array();
                $i = 0;
                while ( $postline[$i] = fgets ($rawFile, 4096) )
                {
                    fwrite($fh, $postline[$i]);
                    $i++;
                }
                fclose($fh);

                //Redirect the User to the Upload Page to display the results.
                $url="admin.php";
                header("Location: $url");
            }
        }
        else {
            header("Location: admin.php");
        }
    }
    else
    {
        header("Location: index.php");
    }
}


?>