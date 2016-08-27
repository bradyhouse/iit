<?php
    set_include_path('resources/phpseclib');
    $target_path = $_GET['f'];
    header("Content-disposition: attachment; filename=" . $target_path);
    readfile($target_path);
    unlink($target_path);
?>