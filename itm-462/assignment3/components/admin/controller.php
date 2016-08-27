<?php defined("true-access") or die;

include_once("model/common.php");
include_once("model/schema.php");
include_once("model/component.php");
include_once("model/site.php");
include_once("model/user.php");

/**
 * Get the configured Event Listener.
 *
 * @param $view
 * @return bool|string
 */
function getListener($event) {
	switch($event) {
        case "comlist":
            return "onComponentList";
        case "comenable":
            return "onComponentEnable";
        case "comdisable":
            return "onComponentDisable";
        case "siteupdate":
            return "onSiteUpdate";
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
    if(databaseExists(DB_NAME)) {
        if (isUserAuthenticated()) {
            if (isUserAdmin()) {
                $listener = getListener($event);
                if (!empty($listener)) {
                    $listener();
                } else {
                    onError('404');
                }
            } else {
                onError('401', 'You do not have the necessary privileges to access the admin console.');
            }
        } else {
            onError('401', 'Please enter a valid username and password.');
        }
    } else {
        header('location: index.php?option=setup&view=install');
    }
}

/**
 * setup event handler.  Redirect the
 * the browser to the setup/index.php.
 */
function onComponentList() {
    include_once('view/component.php');
    $data = array();
    $data['mode'] = 'list';
    view($data);
}

/**
 * Com Enable Event Handler.
 */
function onComponentEnable() {
    if (empty($_GET["componentid"])) {
        error('400');
    } else {
        updateComponent($_GET["componentid"],"1");
        onComponentList();
    }
}

/**
 * Com Disable Event Handler.
 */
function onComponentDisable() {
    if (empty($_GET["componentid"])) {
        error('400');
    } else {
        updateComponent($_GET["componentid"],"0");
        onComponentList();
    }
}

/**
 * Site Update Event Handler.
 */
function onSiteUpdate() {
    $title = empty($_POST["title"]) ||
        $_POST["title"] == '' ?
        getSiteConfig('title') : $_POST["title"];
    $subtitle = empty($_POST["subtitle"]) ||
        $_POST["subtitle"] == '' ?
            getSiteConfig("subtitle") : $_POST["subtitle"];
    updateSite($title, $subtitle);
    onComponentList();
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
    renderError('admin', $data);
}

?>