<?php defined("true-access") or die;

include_once("model/common.php");
include_once("model/schema.php");
include_once("model/user.php");
include_once("model/site.php");
include_once("model/userCourseLink.php");
include_once("model/component.php");

/**
 *
 *
 * @param $view
 * @return bool|string
 */
function getListener($event) {
	switch($event) {
        case "list":
            return "onUserList";
        case "enrollment":
            return "onUserEnrollment";
        case "login":
            return "onLogin";
        case "logout":
            return "onLogout";
        default:
            return false;
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
        $listener = getListener($event);
        if (!empty($listener)) {
            $listener();
        } else {
            onError('404');
        }
    } else {
        header('location: index.php?option=setup&view=install');
    }
}

/**
 * Login Event Handler.
 */
function onLogin() {

    if(!empty($_POST['username']) && !empty($_POST['password'])) {
        $authenticated = testUserPassword($_POST['username'], $_POST['password']);
        if ($authenticated == 1) {
            $_SESSION['user'] = getUserInfo($_POST['username'],'Name');
            $_SESSION['login'] = getUserInfo($_POST['username'],'Login');
            $_SESSION['userid'] = getUserInfo($_POST['username'],'UserId');
            onCourseList();
        } else {
            onError('401');
        }
    } else {
        onError('401');
    }

}

/**
 * Logout Event Handler.
 */
function onLogout() {
    logout();
    header('Location: index.php?option=user&view=list');
}

/**
 * Error event handler.
 *
 * @param $code
 */
function onError($code) {
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
        default:
            $data['header'] = '501';
            $data['subheader'] = "Internal server error has occurred.";
            break;
    }
    renderError("userlist", $data);
}

/**
 * User list event handler.
 */
function onUserList() {
    include_once('view/user.php');
    $data = array();
    $data['mode'] = 'list';
    view($data);

}

/**
 * User Enrollment event handler
 */
function onUserEnrollment() {
    if (empty($_GET["userid"])) {
        onError('400');
    } else {
        include_once('view/user.php');
        $data = array();
        $data['mode'] = 'enrollment';
        $data['userid'] = $_GET["userid"];
        view($data);
    }
}

?>