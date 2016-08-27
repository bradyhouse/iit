<?php defined("true-access") or die;

/*
* Main function
*/
function view($data) {
    switch($data['mode']) {
        case 'list':
            renderPage('courselist', 'renderCourseList');
            break;
        case 'detail':
            renderPage('courselist', 'renderCourseDetails', $data['courseid']);
            break;
    }
}

/**
 * Output the course list HTML.
 */
function renderCourseList() {
    $summaryHTML = getCourseList();
    foreach($summaryHTML as $line) {
        echo $line.PHP_EOL;
    }
}

/**
 * Output the course detail list HTML.
 *
 * @param $courseid
 */
function renderCourseDetails($courseid) {
    echo courseListLink('< Back').PHP_EOL;
    br();
    if (isUserAuthenticated()) {
        $detailHTML = getCourseDetail($courseid,true,$_SESSION['login']);
    } else {
        $detailHTML = getCourseDetail($courseid);
    }
    foreach($detailHTML as $line) {
        echo $line.PHP_EOL;
    }
}






