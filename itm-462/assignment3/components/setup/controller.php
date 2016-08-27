<?php defined("true-access") or die;

include_once("model/common.php");
include_once("model/component.php");
include_once("model/schema.php");
include_once("model/site.php");

/**
 *
 *
 * @param $view
 * @return bool|string
 */
function getListener($event) {
	switch($event) {
        case "install":
            return "onSetup";
        case "reset":
            return "onReset";
        default:
            return '';
    }
}

/**
 * Invoke the listener defined for the requested
 * event.
 *
 * @param $event
 */
function controllerInvokeListener($event) {
    $gateway = isMySQLRunning();
    if (intval($gateway['ping']) > 0) {
        $listener = getListener($event);
        if (!empty($listener)) {
            $listener();
        } else {
            onError('404');
        }
    } else {
        onError($gateway['httpcode'],$gateway['responsetext']);
    }
}

/**
 * setup event handler.  Redirect the
 * the browser to the setup/index.php.
 */
function onSetup() {
    include_once("view/setup.php");
    $data = array();
    $data["reset"] = false;
    view($data);
}

/**
 * Reset Event Handler.
 */
function onReset()
{
    include_once("view/setup.php");
    $data = array();
    $data["reset"] = true;
    view($data);
}

/**
 * Error event handler.
 *
 * @param $code
 */
function onError($code, $msg = '') {
    $data = array();
    switch($code) {
        case '400':
            $data['header'] = '400';
            $data['subheader'] = "Bad request.";
            break;
        case '401';
            $data['header'] = '401';
            $data['subheader'] = "Not authorized";
            break;
        case '404':
            $data['header'] = '404';
            $data['subheader'] = "Page not found.";
            break;
        case '502':
            $data['header'] = '502';
            $data['subheader'] = "Bad Gateway";
        default:
            $data['header'] = '501';
            $data['subheader'] = "Internal server error has occurred.";
            break;
    }
    if(!empty($msg)) {
        $data['message'] = $msg;
    }
    renderError("setup", $data);
}

?>