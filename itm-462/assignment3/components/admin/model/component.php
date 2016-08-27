<?php defined("true-access") or die;

/**
 * Collection of methods used to perform
 * CRUD operations against the stu_component
 * table using the associated stored procedures.
 */

include_once("common.php");

/**
 * Wrapper function for the
 * stu_component_s stored
 * procedure
 *
 * @return array
 */
function getComList() {
    $result = array();
    list($dbc, $error) = connect_to_database();
    $sql = 'CALL `stu_component_s`();';
    if ($dbc) {
        array_push($result, '<table border="1">');
        array_push($result, '<tr><th>#</th><th>Option</th><th>View</th><th>Enabled</th><th></th></tr>');
        $resultSet = mysqli_query($dbc, $sql);
        while ($record = mysqli_fetch_array($resultSet,MYSQLI_ASSOC)):
            $line = '<tr><td nowrap>'.$record['Id'].'</td><td nowrap>'.$record['Opt'].'</td><td nowrap>'.$record['Vw'].'</td>';
            if (intval($record['State'])==1) {
                $line.='<td>Y</td>';
                $line.='<td nowrap><a href="index.php?option=admin&view=comdisable&componentid='.$record['Id'].'">Disable</a></td>';
             } else {
                $line.='<td>N</td><td nowrap><a href="index.php?option=admin&view=comenable&componentid='.$record['Id'].'">Enable</a></td>';
            }
            $line.='</tr>';
        array_push($result,$line);
        endwhile;
        array_push($result,'</table>');
    }
    return $result;
}

/**
 * Wrapper function for the
 * stu_component_u stored
 * procedure.
 *
 * @param $componentId
 * @param $enabled
 * @return bool
 */
function updateComponent($componentId, $enabled) {
    $result = true;
    list($dbc, $error) = connect_to_database();
    $sql = 'CALL `stu_component_u`("'.$componentId.'", '.$enabled.');';
    if ($dbc) {
        try {
            $resultSet = mysqli_query($dbc, $sql);
        } catch(Exception $e) {
            $result = false;
        }
    }
    return $result;
}

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