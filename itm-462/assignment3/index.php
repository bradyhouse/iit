<?php

define("true-access", true);

include_once('configuration.php');
include_once('components/base.php');
if (version_compare(PHP_VERSION, '5.3.10', '<'))
{
    die('Your host needs to use PHP 5.3.10 or higher to run this version of '.SITE_TITLE);
}

session_start();
ob_start();

/**
 * Map the specified option to
 * the configured controller.
 *
 * @param $option
 * @return bool|string
 */
function mapController($option) {
    switch($option) {
        case "admin":
            return 'admin/controller.php';
        case "setup":
            return 'setup/controller.php';
        case "user":
            return 'user/controller.php';
        default:
            return 'course/controller.php';
    }
}

/**
 * Basic Router Function. It requires
 * registering components and their
 * router files.
 */
function route() {
    $option = empty($_GET["option"]) ? "store" : $_GET["option"];
    $view = empty($_GET["view"]) ? "list" : $_GET["view"];
    if ($controller = mapController($option)) {
        include_once('components/'.$controller);
        controllerInvokeListener($view);
    } else {
        die("404 - No Controller for specified option");
    }
}

route();
ob_end_flush();


?>