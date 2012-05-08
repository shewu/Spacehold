<?php

include 'fn.php';

$cmd = $_GET["cmd"];

if (strcmp($cmd, "getspaces") == 0) {
    getSpaces();
}
/*
} else if (strcmp($cmd, "spaceinfo") == 0) {
    $which = $_GET["space"]
    getPeopleAtSpace($which);
} else if (strcmp($cmd, "addspace") == 0) {
    $which = $_GET["space"];
    addSpace($which);
} else if (strcmp($cmd, "addperson") == 0) {
    $space = $_GET["space"];
    $person = $_GET["person"];
    addPersonToSpace($person, $space);
} else {
    echo "-1\n";
}
*/
?>
