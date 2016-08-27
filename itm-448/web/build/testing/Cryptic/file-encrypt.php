<?php
    set_include_path('resources/phpseclib');
    include('Crypt/AES.php');
    $target_path = $_POST['hiddenFilePath'];
    $form_data = array();
    $password = $_POST['encrypt-key'];
    $encrypted_file = $target_path . '.pie';
    $file_contents = file_get_contents($target_path);
    $cipher = new Crypt_AES;
    $cipher->setKey($password);
    $encrypted = $cipher->encrypt($file_contents);
    if (@file_put_contents($encrypted_file, $encrypted)) {
        $form_data['success'] = true;
        $form_data['responseText'] = $encrypted_file;
    } else {
        $form_data['success'] = false;
        $form_data['errors']  = $errors;
    }
    header('Content-type: application/json');
    echo json_encode($form_data);
?>