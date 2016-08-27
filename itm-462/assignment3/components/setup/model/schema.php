<?php defined("true-access") or die;

/**
 * Collection of methods used to interact with the schema
 * of the database.  These methods are used not to connect
 * to a specific database but rather to test the state
 * of a specific database or the MySQL service itself.
 */

include_once("common.php");

/**
 * Test whether MySQL is up and
 * running.
 *
 * @return array
 */
function isMySQLRunning() {
    $result = array();
    list($dbc,$error) = connect_to_database(SETUP_DB_NAME);
    if (mysqli_connect_errno($dbc)) {
        $result['ping'] = '0';
        $result['httpcode'] = '502';
        $result['responsetext'] = "Failed to  connect to MySQL: " . mysqli_connect_error();
    } else {
        $result['ping'] = '1';
        $result['httpcode'] = '200';
        $result['responsetext'] = 'Success';
    }
    return $result;
}

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

/**
 * Import SQL from file.
 *
 * NOTE - Adapted from post #3 of the following
 * StackOverflow article--
 * http://stackoverflow.com/questions/147821/loading-sql-files-from-within-php/149456#149456
 *
 * @param string path to sql file
 */
function sqlImport($file) {

    $delimiter = ';';
    $file = fopen($file, 'r');
    $isFirstRow = true;
    $isMultiLineComment = false;
    $sql = '';

    while (!feof($file)) {

        $row = fgets($file);

        // remove BOM for utf-8 encoded file
        if ($isFirstRow) {
            $row = preg_replace('/^\x{EF}\x{BB}\x{BF}/', '', $row);
            $isFirstRow = false;
        }

        // 1. ignore empty string and comment row
        if (trim($row) == '' || preg_match('/^\s*(#|--\s)/sUi', $row)) {
            continue;
        }

        // 2. clear comments
        $row = trim(clearSQL($row, $isMultiLineComment));

        // 3. parse delimiter row
        if (preg_match('/^DELIMITER\s+[^ ]+/sUi', $row)) {
            $delimiter = preg_replace('/^DELIMITER\s+([^ ]+)$/sUi', '$1', $row);
            continue;
        }

        // 4. separate sql queries by delimiter
        $offset = 0;
        while (strpos($row, $delimiter, $offset) !== false) {
            $delimiterOffset = strpos($row, $delimiter, $offset);
            if (isQuoted($delimiterOffset, $row)) {
                $offset = $delimiterOffset + strlen($delimiter);
            } else {
                $sql = trim($sql . ' ' . trim(substr($row, 0, $delimiterOffset)));
                query($sql);

                $row = substr($row, $delimiterOffset + strlen($delimiter));
                $offset = 0;
                $sql = '';
            }
        }
        $sql = trim($sql . ' ' . $row);
    }
    if (strlen($sql) > 0) {
        query($row);
    }

    fclose($file);
}

/**
 * Remove comments from sql
 *
 * NOTE - Adapted from post #3 of the following
 * StackOverflow article--
 * http://stackoverflow.com/questions/147821/loading-sql-files-from-within-php/149456#149456
 *
 * @param string sql
 * @param boolean is multicomment line
 * @return string
 */
function clearSQL($sql, &$isMultiComment)
{
    if ($isMultiComment) {
        if (preg_match('#\*/#sUi', $sql)) {
            $sql = preg_replace('#^.*\*/\s*#sUi', '', $sql);
            $isMultiComment = false;
        } else {
            $sql = '';
        }
        if(trim($sql) == ''){
            return $sql;
        }
    }

    $offset = 0;
    while (preg_match('{--\s|#|/\*[^!]}sUi', $sql, $matched, PREG_OFFSET_CAPTURE, $offset)) {
        list($comment, $foundOn) = $matched[0];
        if (isQuoted($foundOn, $sql)) {
            $offset = $foundOn + strlen($comment);
        } else {
            if (substr($comment, 0, 2) == '/*') {
                $closedOn = strpos($sql, '*/', $foundOn);
                if ($closedOn !== false) {
                    $sql = substr($sql, 0, $foundOn) . substr($sql, $closedOn + 2);
                } else {
                    $sql = substr($sql, 0, $foundOn);
                    $isMultiComment = true;
                }
            } else {
                $sql = substr($sql, 0, $foundOn);
                break;
            }
        }
    }
    return $sql;
}

/**
 * Check if "offset" position is quoted
 *
 * NOTE - Adapted from post #3 of the following
 * StackOverflow article--
 * http://stackoverflow.com/questions/147821/loading-sql-files-from-within-php/149456#149456
 *
 * @param int $offset
 * @param string $text
 * @return boolean
 */
function isQuoted($offset, $text) {
    if ($offset > strlen($text))
        $offset = strlen($text);
    $isQuoted = false;
    for ($i = 0; $i < $offset; $i++) {
        if ($text[$i] == "'")
            $isQuoted = !$isQuoted;
        if ($text[$i] == "\\" && $isQuoted)
            $i++;
    }
    return $isQuoted;
}

/**
 * Execute a given sql statement.
 *
 * @param $sql
 * @throws Exception
 */
function query($sql) {
    list($dbc,$error) = connect_to_database(SETUP_DB_NAME);
    if ($dbc) {
        try {
            $queryResult = mysqli_query($dbc, $sql);
            while ($record = mysqli_fetch_array($queryResult,MYSQLI_ASSOC)):
                print_r($record);
            endwhile;
        } catch (Exception $e) {
            throw $e;
        }
    }
}

?>