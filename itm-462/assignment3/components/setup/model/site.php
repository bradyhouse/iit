<?php defined("true-access") or die;
/**
 * Collection of methods used to perform
 * CRUD operations against the stu_site
 * table using the associated (stu_site_*)
 * stored procedures.
 */

include_once("common.php");
include_once("schema.php");

/**
 * Wrapper function for the
 * stu_site_s stored procedure.
 *
 * NOTE - IF the DB_NAME does not exist, then this
 * function returns the default values defined
 * in the configuration.php file.
 *
 * @param string $field
 * @return string
 */
function getSiteConfig($field="*") {
    $site = array();
    $result = '';
    $i = 0;
    if (databaseExists(DB_NAME)) {
        list($dbc,$error) = connect_to_database();
        $sql = 'CALL `stu_site_s`();';
        if ($dbc) {
            $resultSet = mysqli_query($dbc, $sql);
            while ($record = mysqli_fetch_array($resultSet,MYSQLI_ASSOC)):
                switch (strtoupper($field)) {
                    case "TITLE":
                        $result=$record['Title'];
                        break;
                    case "SUBTITLE":
                        $result=$record['SubTitle'];
                        break;
                    case "SALT":
                        $result=$record['Salt'];
                        break;
                    default:
                        $result=$record['Title'].", ".$record['SubTitle'].", ".$record['Salt'];
                        break;
                }
            endwhile;
        }
    } else {
        switch (strtoupper($field)) {
            case "TITLE":
                $result=SITE_TITLE;
                break;
            case "SUBTITLE":
                $result=SITE_SUBTITLE;
                break;
            case "SALT":
                $result=SALT;
                break;
            default:
                $result=SITE_TITLE.", ".SITE_SUBTITLE.", ".SALT;
                break;
        }
    }
    return $result;
}

/**
 * Wrapper function for the
 * stu_site_u stored
 * procedure
 *
 * @param $title
 * @param $subtitle
 * @return bool
 */
function updateSite($title, $subtitle) {
    $result = true;
    list($dbc, $error) = connect_to_database();
    $sql = 'CALL `stu_site_u`("'.$title.'", "'.$subtitle.'");';
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