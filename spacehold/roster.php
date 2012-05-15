<?
include 'static/header.php';
include 'fn.php';
?>
<body>
<form method=post action="<?echo $_SERVER['PHP_SELF'];?>">
<select>
<?
$spaces = file_get_contents('http://shewu.scripts.mit.edu/Spacehold/spacehold/r.php?cmd=getspaces&tok=21W.789');
$spaces = preg_split('/[\s,]+/', trim($spaces));
print_r($spaces);
foreach ($spaces as $space) {
    echo "<option value=$space>$space</option>\n";
}
?>
</select>
</form>
</body>
<?
include 'static/footer.php';
?>

