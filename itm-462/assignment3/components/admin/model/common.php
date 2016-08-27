<?php defined("true-access") or die;
/**
 * Methods common (re-used) across model methods (files).
 */

/**
 * Open a database connection using
 * the specified database.
 *
 * @return array
 */
function connect_to_database($database = DB_NAME)
{
    $dbc = mysqli_connect(DB_HOST,DB_USER,DB_PASSWORD,$database);
    $error = "";

    if ($dbc)
    {
        //good news everyone!
        mysqli_set_charset($dbc,"utf8");
    }
    else
    {
        $error = mysqli_connect_error();
    }

    return array($dbc,$error);
}


?>