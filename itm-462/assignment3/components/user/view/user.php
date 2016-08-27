<?php defined("true-access") or die;

/*
* Main function
*/
function view($data) {
    switch($data['mode']) {
        case 'list':
            renderPage('userlist','renderUserList');
            break;
        case 'enrollment':
            renderPage('userenrollment', 'renderUserEnrollment', $data['userid']);
            break;
    }
}

/**
 * Output the course list HTML.
 */
function renderUserList() {
    $summaryHTML = getUserList();
    foreach($summaryHTML as $line) {
        echo $line.PHP_EOL;
    }
}

/**
 * Output the user enrollment
 * detail list HTML.
 *
 * @param $userid
 */
function renderUserEnrollment($userid) {
    echo userListLink('< Back').PHP_EOL;
    br();
    $detailHTML = getUserEnrollment($userid);
    foreach($detailHTML as $line) {
        echo $line.PHP_EOL;
    }
}






