<?php defined("true-access") or die;
/**
 * Collection of methods used to perform
 * CRUD operations against the stu_user_course_link
 * table using the associated (stu_user_course_link_*)
 * stored procedures.
 */

include_once("common.php");

/**
 * Wrapper function for the
 * stu_user_course_link_s stored
 * procedure
 *
 * @return array
 */
/**
 * Wrapper function for the
 * stu_user_course_link_s stored
 * procedure
 * @param $login
 * @param $courseId
 * @return bool
 */
function isUserEnrolledInCourse($login, $courseId) {
    $result = false;
    list($dbc, $error) = connect_to_database();
    $sql = 'CALL `stu_user_course_link_s`("'.$login.'", "'.$courseId.'");';
    if ($dbc) {
        $queryResult = mysqli_query($dbc, $sql);
        while ($record = mysqli_fetch_array($queryResult,MYSQLI_ASSOC)):
            if ($record['ENROLLED'] == 1) {
                $result = true;
            }
        endwhile;
    }
    return $result;
}

/**
 * Wrapper function for the
 * stu_user_course_link_i stored
 * procedure
 *
 * @param $login
 * @param $courseId
 * @return bool
 */
function enrollUserInCourse($login, $courseId) {
    $result = true;
    list($dbc, $error) = connect_to_database();
    $sql = 'CALL `stu_user_course_link_i`("'.$login.'", "'.$courseId.'");';
    if ($dbc) {
        try {
            $resultSet = mysqli_query($dbc, $sql);
        } catch(Exception $e) {
            $result = false;
        }
    }
    return $result;
}

?>