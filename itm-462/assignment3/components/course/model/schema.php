<?php defined("true-access") or die;

/**
 * Collection of methods used to interact with the schema
 * of the database.  These methods are used not to connect
 * to a specific database but rather to test the state
 * of a specific database or the MySQL service itself.
 */

include_once("common.php");

/**
 * Test whether a given database
 * exists.
 *
 * @param $databaseName
 * @return bool
 */
function databaseExists($databaseName) {
    $result = false;
    list($dbc,$error) = connect_to_database(SETUP_DB_NAME);
    $sql = 'SELECT CASE WHEN COUNT(*) LIKE \'1\' THEN TRUE ELSE FALSE END AS \'EXISTS\''.
        'FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = \''.$databaseName.'\';';
    if ($dbc) {
        $queryResult = mysqli_query($dbc, $sql);
        while ($record = mysqli_fetch_array($queryResult,MYSQLI_ASSOC)):
            if ($record['EXISTS']) {
                $result = true;
            }
        endwhile;
    }
    return $result;
}

?>