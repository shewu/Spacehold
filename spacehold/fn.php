<?php
/**
 * The request page.
 *
 * We need to deal with locking somehow.
 */

$DB_NAME = "shewu+spacehold";
$DB_SERVER = "mysql.mit.edu";
$DB_LOGIN = "shewu";

$SPACES_TBL = "spaces";
$PEOPLE_TBL = "people";
$PASSWD_FILE = "password.txt";

function getPassword() {
    global $PASSWD_FILE;

    return trim(file_get_contents($PASSWD_FILE));
}

function addSpace($spaceName) {
    global $DB_NAME, $DB_SERVER, $DB_LOGIN;
    global $SPACES_TBL, $PEOPLE_TBL;

    $con = mysql_connect($DB_SERVER, $DB_LOGIN, getPassword());
    if (!$con) {
        echo "Failed to connect to database.";
        die();
    }
    if (!mysql_select_db($DB_NAME)) {
        echo "Database " . $DB_NAME . " not found!";
        die();
    }

    // if space exists, don't add it
    $exists = false;
    while ($row = mysql_fetch_assoc($result)) {
        foreach ($row as $val) {
            if (strcmp($val, $spaceName) == 0) {
                $exists = true;
                break;
            }
        }
    }

    if (!$exists) {
        $cmd = sprintf("INSERT INTO %s VALUES ('%s')", $SPACES_TBL, $spaceName);
        $result = mysql_query($cmd);
        if (!$result) {
            echo "ERROR: command " . $cmd . " failed!";
            die();
        }
        echo "0";
    } else {
        echo "1";
    }

    mysql_free_result($result);
    mysql_close($con);
}

function addPersonToSpace($person, $space) {
    global $DB_NAME, $DB_SERVER, $DB_LOGIN;
    global $SPACES_TBL, $PEOPLE_TBL;

    $con = mysql_connect($DB_SERVER, $DB_LOGIN, getPassword());
    if (!$con) {
        echo "Failed to connect to database.";
        die();
    }
    if (!mysql_select_db($DB_NAME)) {
        echo "Database " . $DB_NAME . " not found!";
        die();
    }
    $cmd = sprintf("INSERT INTO %s VALUES ('%s', '%s')", $PEOPLE_TBL, $person, $space);
    $result = mysql_query($cmd);
    if (!$result) {
        echo "ERROR: command " . $cmd . " failed!";
        die();
    }

    mysql_free_result($result);
    mysql_close($con);

    echo "0";
}

function getSpaces() {
    global $DB_NAME, $DB_SERVER, $DB_LOGIN;
    global $SPACES_TBL, $PEOPLE_TBL;

    $out = array();

    $con = mysql_connect($DB_SERVER, $DB_LOGIN, getPassword());
    if (!$con) {
        echo "Failed to connect to database.";
        die();
    }
    if (!mysql_select_db($DB_NAME)) {
        echo "Database " . $DB_NAME . " not found!";
        die();
    }
    $cmd = sprintf("SELECT * FROM %s", $SPACES_TBL);
    $result = mysql_query($cmd);
    if (!$result) {
        echo "ERROR: command " . $cmd . " failed!";
        die();
    }

    while ($row = mysql_fetch_assoc($result)) {
        foreach ($row as $val) {
            echo $val . "\n";
            $out[] = $val;
        }
    }

    mysql_free_result($result);
    mysql_close($con);
}

function getPeopleAndSpaces() {
    global $DB_NAME, $DB_SERVER, $DB_LOGIN;
    global $SPACES_TBL, $PEOPLE_TBL;

    $out = array();

    $con = mysql_connect($DB_SERVER, $DB_LOGIN, getPassword());
    if (!$con) {
        echo "Failed to connect to database.";
        die();
    }
    if (!mysql_select_db($DB_NAME)) {
        echo "Database " . $DB_NAME . " not found!";
        die();
    }
    $cmd = sprintf("SELECT * from %s", $SPACES_TBL);
    $result = mysql_query($cmd);
    if (!$result) {
        echo "ERROR: command " . $cmd . " failed!";
        die();
    }

    while ($row = mysql_fetch_assoc($result)) {
        $inner = array();
        foreach ($row as $val) {
            $inner[] = $val;
        }
        $out[] = $inner;
    }

    mysql_free_result($result);
    mysql_close($con);

    return $out;
}

function getPeopleAtSpace($space) {
    $pairs = getPeopleAndSpaces();
    foreach ($pairs as $pair) {
        if (strcmp($pair[1], $space) == 0) {
            echo $pair[0] . "; " . $pair[1] . "\n";
        }
    }
}
?>

