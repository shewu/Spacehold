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
        echo "Space " . $spaceName . " already exists";
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

    $cmd = sprintf("SELECT * FROM %s", $PEOPLE_TBL);
    $result = mysql_query($cmd);

    $exists = false;
    while ($row = mysql_fetch_assoc($result)) {
        //echo "looking at ".$row['space']." ".$row['handle']."\n";
        if (strcmp($row['space'], $space) == 0 && strcmp($row['handle'], $person) == 0) {
            $exists = true;
            break;
        }
    }

    if (!$exists) {
        $cmd = sprintf("INSERT INTO %s VALUES ('%s', '%s')", $PEOPLE_TBL, $person, $space);
        $result = mysql_query($cmd);
        if (!$result) {
            echo "ERROR: command " . $cmd . " failed!";
            die();
        }
        echo "0";
    } else {
        echo "Person " . $person . " is already in space " . $space;
    }

    mysql_free_result($result);
    mysql_close($con);
}

function getSpaces() {
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
    $cmd = sprintf("SELECT * FROM %s", $SPACES_TBL);
    $result = mysql_query($cmd);
    if (!$result) {
        echo "ERROR: command " . $cmd . " failed!";
        die();
    }

    while ($row = mysql_fetch_assoc($result)) {
        foreach ($row as $val) {
            echo $val . "\n";
        }
    }

    mysql_free_result($result);
    mysql_close($con);
}

function getPeopleAndSpaces() {
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
    $cmd = sprintf("SELECT * from %s", $PEOPLE_TBL);
    $result = mysql_query($cmd);
    if (!$result) {
        echo "ERROR: command " . $cmd . " failed!";
        die();
    }

    while ($row = mysql_fetch_assoc($result)) {
        foreach ($row as $val) {
            echo $row['handle'] . "; " . $row['space'] . "\n";
        }
    }

    mysql_free_result($result);
    mysql_close($con);
}

function getPeopleAtSpace($space) {
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
    $cmd = sprintf("SELECT * from %s", $PEOPLE_TBL);
    $result = mysql_query($cmd);
    if (!$result) {
        echo "ERROR: command " . $cmd . " failed!";
        die();
    }

    while ($row = mysql_fetch_assoc($result)) {
        foreach ($row as $val) {
            if (strcmp($space, $row['space']) == 0) {
                echo $row['handle'] . "\n";
            }
        }
    }

    mysql_free_result($result);
    mysql_close($con);
}
?>

