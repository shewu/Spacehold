<?
include 'static/header.php';
include 'fn.php';
?>
<body>
<form method=post action="<?echo $_SERVER['PHP_SELF'];?>">
<select name='space'>
<?
$spaces = file_get_contents('http://shewu.scripts.mit.edu/Spacehold/spacehold/r.php?cmd=getspaces&tok=21W.789');
$spaces = preg_split('/[\s,]+/', trim($spaces));
foreach ($spaces as $space) {
    echo "<option value=$space>$space</option>\n";
}
?>
</select>
<input type=submit value=Submit name=submit />
</form>
<?
if (isset($_POST['submit'])) {
    $leSpace = $_POST['space'];
    $people = file_get_contents("http://shewu.scripts.mit.edu/Spacehold/spacehold/r.php?cmd=spaceinfo&space=$leSpace&tok=21W.789");
    $people = preg_split('/[\s,]+/', trim($people));
    echo "<table border=1>";
    echo "<tr><th>$leSpace</th></tr>";
    foreach ($people as $person) {
        echo "<tr>";
        echo "<td>$person</td>";
        echo "</tr>";
    }
    echo "</table>";
}
?>
</body>
<?
include 'static/footer.php';
?>

