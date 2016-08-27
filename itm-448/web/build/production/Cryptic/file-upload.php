<?php
    $destination_path = getcwd().DIRECTORY_SEPARATOR;
    $target_path = $destination_path . basename( $_FILES['local-file-path']['name']);
    $form_data = array();
    if (@move_uploaded_file($_FILES['local-file-path']['tmp_name'], $target_path)) {
        $form_data['success'] = true;
        $form_data['responseText'] = $_FILES['local-file-path']['name'];
    } else {
        $form_data['success'] = false;
        $form_data['errors']  = $errors;
    }
    header('Content-type: application/json');
    //header("Content-Type: application/octet-stream; ");
    //header("Content-Transfer-Encoding: binary");
    echo json_encode($form_data);
?>
