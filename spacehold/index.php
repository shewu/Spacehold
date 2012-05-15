<?
if (!isset($_POST["submit"])) {
include 'static/header.php';
?>
<body>
<form method=post action="<?php echo $_SERVER['PHP_SELF'];?>">
<p>Sign in with your OpenID:</p>
<input type=text name=id size=30 /> <input type=submit name=submit value="Log in" />
</form>
</body>
<?
include 'static/footer.php';
} else {
}
?>

