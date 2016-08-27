<?php defined("true-access") or die;

include_once("model/common.php");
include_once("model/schema.php");
include_once("model/user.php");
include_once("model/course.php");
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
            return "onCourseList";
        case "detail":
            return "onCourseDetail";
        case "enroll":
            return "onEnroll";
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
 * CourseList Event Handler. Invoke
 * the course view() function.
 *
 */
function onCourseList() {
    include_once('view/course.php');
    $data = array();
    $data['mode'] = 'list';
    view($data);
}

/**
 * Course Detail Event Handler.
 */
function onCourseDetail() {
    if (empty($_GET["courseid"])) {
        error('400');
    } else {
        include_once('view/course.php');
        $data = array();
        $data['mode'] = 'detail';
        $data['courseid'] = $_GET["courseid"];
        view($data);
    }
}

/**
 * Enroll Event Handler.
 */
function onEnroll() {
    if (empty($_GET["courseid"])) {
        error('400');
    } else {
        enrollUserInCourse($_SESSION['login'], $_GET['courseid']);
        onCourseDetail();
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
    header('Location: index.php?option=course&view=list');
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
    renderError("courselist", $data);
}

?>