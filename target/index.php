<?php
	include_once "../internal/globals.php";
	$page = array('url' => "/impressum", 'title' => "Impressum & Datenschutzerklärung", 'description' => "Dies ist das Impressum sowie die Datenschutzerklärung von WarKing.de!", 'sitecategory' => "Imp");
	
	if(!isset($_GET['js'])){
		include_once "../internal/pageheader.php";
	}else{
		include_once "../internal/jsheader.php";
	}
?>
<section id="Impressum" class="slidein">
<h1>WarKing</h1>
<hr>
<?php
	$post = WDBone("SELECT * FROM Post WHERE Thread = ? ORDER BY PID DESC LIMIT 1",array($Impressumthread));
?>
<h2><?php echo $post['PTitle']; ?></h2>
<hr>
<?php echo eval('?>'.$post['Content'].'<?php '); ?>
<hr>
</section>
<?php
	if(!isset($_GET['js'])){
		include_once "../internal/pagefooter.php";
	}
?> 
