<?
/**
 * The request page.
 *
 * We need to deal with locking somehow.
 */

function testAndSet() {

}

function release() {

}

function addPerson($p) {
	$ret = 0;
	testAndSet();
	$s = file_get_contents("miters.txt");
	$s = $s . $p . "\n";
	if (file_put_contents("miters.txt", $s) == FALSE) {
		$ret = -1;
	}
	release();
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
	return $ret;
}

function getPeople() {
	$s = file_get_contents("miters.txt");
	return $s;
}

if (isset($_GET["addPerson"])) {
	addPerson($_GET["addPerson"]);
} else if (isset($_GET("leave"))) {
	removePerson($_GET("leave"));
} else if (isset($_GET("getPeople"))) {
	getPeople();
}
?>

