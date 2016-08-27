<?php
if(!isset($_SESSION)) {
    session_start();
}
$_SESSION['hits']=strval(0);
session_write_close();
header('Location: ' . $_SERVER['HTTP_REFERER']);

?>