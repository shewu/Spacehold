<?php
/**
 * The request page.
 *
 * We need to deal with locking somehow.
 */

echo "hello";
function testAndSet() {

}

function release() {

}

/*
function addPerson($p) {
	$ret = 0;
	testAndSet();
	$s = file_get_contents("miters.txt");
	$s = $s . $p . "\n";
	if (file_put_contents("miters.txt", $s) == FALSE) {
		$ret = -1;
	}
	release();
	echo $ret;
	return $ret;
}

function removePerson($p) {
	testAndSet();
	$s = file_get_contents("miters.txt");
	$s = explode("\n", $s);
	for ($s as $a) {
		if (strcmp($p, $a) == 0) {
			unset($a);
		}
	}
	$s = implode("\n", $s);
	if (file_put_contents("miters.txt", $s) == FALSE) {
		$ret = -1;
	}
	release();
	echo $ret;
	return $ret;
}

*/
function getKeyholders() {
	$s = file_get_contents("miters.txt");
	echo $s;
	return $s;
}

if (isset($_GET["addPerson"])) {
	echo "addPerson";
	//addPerson($_GET["addPerson"]);
} else if (isset($_GET["leave"])) {
	echo "leave";
	//removePerson($_GET("leave"));
} else if (isset($_GET["getKeyholders"])) {
	echo "getKeyholders";
	getKeyholders();
} else {
	echo "no argument or invalid argument specified!";
}
?>

