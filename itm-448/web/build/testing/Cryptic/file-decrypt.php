<?php
    set_include_path('resources/phpseclib');
    include('Crypt/AES.php');
    $target_path = $_POST['hiddenFilePath'];
    $form_data = array();
    $password = $_POST['decrypt-key'];
    $file_contents = file_get_contents($target_path);
    $decrypted_file = $target_path;
    $cipher = new Crypt_AES;
    $cipher->setKey($password);
    $decrypted = $cipher->decrypt($file_contents);
    if (@file_put_contents($decrypted_file, $decrypted)) {
        $form_data['success'] = true;
        $form_data['responseText'] = $target_path;
    } else {
        $form_data['success'] = false;
        $form_data['errors']  = $errors;
    }
    header('Content-type: application/json');
    echo json_encode($form_data);
?>