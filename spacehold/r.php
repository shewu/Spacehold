<?php

include 'fn.php';

$tok = $_GET["tok"];
$cmd = $_GET["cmd"];

if (strcmp($tok, "21W.789") == 0) {
    if (strcmp($cmd, "getspaces") == 0) {
        getSpaces();
    } else if (strcmp($cmd, "spaceinfo") == 0) {
        $which = $_GET["space"];
        getPeopleAtSpace($which);
    } else if (strcmp($cmd, "addspace") == 0) {
        $which = $_GET["space"];
        addSpace($which);
    } else if (strcmp($cmd, "addperson") == 0) {
        $space = $_GET["space"];
        $person = $_GET["person"];
        addPersonToSpace($person, $space);
    } else if (strcmp($cmd, "removeperson") == 0) {
        $space = $_GET["space"];
        $person = $_GET["person"];
        removePersonFromSpace($person, $space);
    } else {
        echo "-1\n";
    }
} else {
    echo "-1\n";
}
?>
