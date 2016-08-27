<?php defined("true-access") or die;
/**
 * Collection of methods used to perform
 * CRUD operations against the stu_course
 * table using the associated (stu_course_*)
 * stored procedures.
 */

include_once("common.php");

/**
 * Wrapper function for the
 * stu_course_summary_s stored
 * procedure
 *
 * @return array
 */
function getCourseList() {
    $result = array();
    list($dbc, $error) = connect_to_database();
    $sql = 'CALL `stu_course_summary_s`();';
    if ($dbc) {
        array_push($result, '<table border="1">');
        array_push($result, '<tr><th>CourseNumber</th><th>Title</th><th></th></tr>');
        $resultSet = mysqli_query($dbc, $sql);
        while ($record = mysqli_fetch_array($resultSet,MYSQLI_ASSOC)):
            array_push($result,'<tr><td>'.$record['CourseNumber'].'</td>');
            array_push($result,'<td>'.$record['Title'].'</td>');
            array_push($result,'<td><a href="index.php?option=course&view=detail&courseid='.$record['CourseId'].'">Details</a></td></tr>');
        endwhile;
        array_push($result,'</table>');
    }
    return $result;
}

/**
 * Wrapper function for the
 * stu_course_detail_s stored
 * procedure
 *
 * @param $courseId
 * @return array
 */
function getCourseDetail($courseId,$auth=false,$login='') {
    $result = array();
    list($dbc, $error) = connect_to_database();
    $sql = 'CALL `stu_course_detail_s`("'.$courseId.'");';
    if ($dbc) {
        array_push($result, '<table border="1">');
        if ($auth && !empty($login)) {
            array_push($result, '<tr><th>CourseNumber</th><th>Description</th><th>Credits</th><th>Cost</th><th>Specialization</th><th></th></tr>');
        } else {
            array_push($result, '<tr><th>CourseNumber</th><th>Description</th><th>Credits</th><th>Cost</th><th>Specialization</th></tr>');
        }
        $resultSet = mysqli_query($dbc, $sql);
        while ($record = mysqli_fetch_array($resultSet,MYSQLI_ASSOC)):
            array_push($result,'<tr><td>'.$record['CourseNumber'].'</td>');
            array_push($result,'<td>'.$record['CourseDescription'].'</td>');
            array_push($result,'<td>'.$record['Credits'].'</td>');
            array_push($result,'<td>'.$record['Cost'].'</td>');
            array_push($result,'<td>'.$record['Specialization'].'</td>');
            if ($auth && !empty($login)) {
                if(isUserEnrolledInCourse($login, $courseId)) {
                    array_push($result,'<td>Enrolled</td></tr>');
                } else {
                    array_push($result,'<td><a href="index.php?option=store&view=enroll&courseid='.$record['CourseId'].'">Enroll</a></td></tr>');
                }
            } else {
                array_push($result,'</tr>');
            }
        endwhile;
        array_push($result,'</table>');
    }
    return $result;
}

?>