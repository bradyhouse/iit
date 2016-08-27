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