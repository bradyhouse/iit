<?php defined("true-access") or die;

/**
 * Collection of methods used to perform
 * CRUD operations against the stu_component
 * table using the associated stored procedures.
 */

include_once("common.php");

/**
 * Wrapper function for the stu_component_enabled_s
 * stored procedure.
 *
 * @param $option
 * @param $view
 * @return bool
 */
function isComponentEnabled($option, $view) {
    $result = false;
    list($dbc,$error) = connect_to_database();
    $sql = 'CALL `stu_component_enabled_s`("'.$option.'","'.$view.'");';
    if ($dbc) {
        $queryResult = mysqli_query($dbc, $sql);
        while ($record = mysqli_fetch_array($queryResult,MYSQLI_ASSOC)):
            if ($record['ENABLED'] == 1) {
                $result = true;
            }
        endwhile;
    }
    return $result;
}

?>