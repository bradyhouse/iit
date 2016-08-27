<?php defined("true-access") or die;

/**
 * Collection of methods used to perform
 * CRUD operations against the stu_site
 * table using the associated (stu_site_*)
 * stored procedures.
 */

include_once("common.php");


/**
 * Wrapper function for the stu_user_auth_s
 * store procedure.
 *
 * @param $login
 * @param $password
 * @return bool
 */
function testUserPassword($login, $password) {
    $result = false;
    list($dbc,$error) = connect_to_database();
    $sql = 'CALL `stu_user_auth_s`("'.$login.'","'.$password.'", "'.SALT.'");';
    if ($dbc) {
        $queryResult = mysqli_query($dbc, $sql);
        while ($record = mysqli_fetch_array($queryResult,MYSQLI_ASSOC)):
            if (intval($record['AUTHENTICATED']) == 1) {
                $result = true;
            }
        endwhile;
    }
    return $result;
}

/**
 * Wrapper function for the
 * stu_user_auth_s stored procedure
 *
 * @param $login
 * @param string $field
 * @return string
 */
function getUserInfo($login, $field="*") {
    $result = '';
    list($dbc,$error) = connect_to_database();
    $sql = 'CALL `stu_user_s`("'.$login.'");';
    if ($dbc) {
        $queryResult = mysqli_query($dbc, $sql);
        while ($record = mysqli_fetch_array($queryResult,MYSQLI_ASSOC)):
            switch(strtoupper($field)) {
                case "USERID":
                    $result = $record['UserId'];
                    break;
                case "NAME":
                    $result = $record['Name'];
                    break;
                case "LOGIN":
                    $result = $record['Login'];
                    break;
                case "EMAIL":
                    $result = $record['Email'];
                    break;
                case "LOCKED":
                    $result = $record['Locked'];
                    break;
                default:
                    $result = $record['UserId'].", ".$record['Name'].", ".$record['Login'].", ".$record['Email'].", ".$record['Locked'];
            }
        endwhile;
    }
    return $result;
}

/**
 * Wrapper function for the
 * stu_user_summary_s stored
 * procedure
 *
 * @return array
 */
function getUserList() {
    $result = array();
    list($dbc, $error) = connect_to_database();
    $sql = 'CALL `stu_user_summary_s`();';
    if ($dbc) {
        array_push($result, '<table border="1">');
        array_push($result, '<tr><th>#</th><th>User Name</th><th>Email</th><th></th></tr>');
        $resultSet = mysqli_query($dbc, $sql);
        while ($record = mysqli_fetch_array($resultSet,MYSQLI_ASSOC)):
            array_push($result,'<tr><td>'.$record['UserId'].'</td>');
            array_push($result,'<td>'.$record['Name'].'</td>');
            array_push($result,'<td>'.$record['Email'].'</td>');
            array_push($result,'<td><a href="index.php?option=user&view=enrollment&userid='.$record['UserId'].'">Enrollment</a></td></tr>');
        endwhile;
        array_push($result,'</table>');
    }
    return $result;
}

/**
 * Wrapper function for the
 * stu_user_detail_s stored
 * procedure
 *
 * @param $courseId
 * @return array
 */
function getUserEnrollment($userId) {
    $result = array();
    list($dbc, $error) = connect_to_database();
    $sql = 'CALL `stu_user_detail_s`("'.$userId.'");';
    if ($dbc) {
        $resultSet = mysqli_query($dbc, $sql);
        array_push($result, '<table border="1">');
        array_push($result, '<tr><th>CourseNumber</th><th>Description</th><th>Credits</th><th>Cost</th><th>Specialization</th></tr>');
        while ($record = mysqli_fetch_array($resultSet,MYSQLI_ASSOC)):
            array_push($result,'<tr><td>'.$record['CourseNumber'].'</td><td>'.$record['CourseDescription'].'</td><td>'.$record['Credits'].'</td><td>'.$record['Cost'].'</td><td>'.$record['Specialization'].'</td></tr>');
        endwhile;
        array_push($result,'</table>');
    }
    return $result;
}

function isUserAuthenticated() {
    return isset($_SESSION['user']) && !empty($_SESSION['user']) ? true : false;
}

function logout() {
    session_destroy();
    if (isset($_SESSION['user'])) {
        session_unset($_SESSION['user']);
    }
    if (isset($_SESSION['userid'])) {
        session_unset($_SESSION['userid']);
    }
    if (isset($_SESSION['login'])) {
        session_unset($_SESSION['login']);
    }
}

?>