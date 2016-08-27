<?php include 'template/send_email_top_php.html' ?>
<?php include 'template/doctype_section.html' ?>
<?php include 'template/open_html_tag_section.html' ?>
<?php include 'template/top_head_section.html' ?>
<title>HTMLPress - Uploaded File Results</title>
<?php include 'template/bottom_head_section.html' ?>
<?php include 'template/open_body_tag_section.html' ?>
<?php include 'template/open_page_container_section.html' ?>
<?php include 'template/banner_section.html' ?>
<?php include 'template/nav_upload_section.html' ?>
<?php include 'template/open_page_content_section.html' ?>
<div id="inner_page_content">
    <h3>Your Uploaded File Contained:</h3>
<?php
$storagename=$_GET['storagename'];

if ( $file = fopen( "upload/" . $storagename , "r" ) ) 
{      
	$firstline = fgets ($file, 4096 );         
	
	//Gets the number of fields, in CSV-files the names of the fields are mostly given in the first line     
	
	$num = strlen($firstline) - strlen(str_replace(";", "", $firstline));          
	
	//save the different fields of the firstline in an array called fields     
	
	$fields = array();     
	$fields = explode( ";", $firstline, ($num+1) );      
	$line = array();     $i = 0;          
	
	//CSV: one line is one record and the cells/fields are seperated by ";"         
	//so $dsatz is an two dimensional array saving the records like this: $dsatz[number of record][number of cell]     
	
	while ( $line[$i] = fgets ($file, 4096) ) 
	{          
		$dsatz[$i] = array();         
		$dsatz[$i] = explode( ";", $line[$i], ($num+1) );          
		$i++;     
	}          
	
	echo "<table>";         
	echo "<tr>";     
	
	for ( $k = 0; $k != ($num+1); $k++ ) 
	{         
		echo "<td>" . $fields[$k] . "</td>";     
	}         
	
	echo "</tr>";      
	
	foreach ($dsatz as $key => $number) 
	{                 
		//new table row for every record         
		echo "<tr>";         
		foreach ($number as $k => $content) 
		{                         
			//new table cell for every field of the record             
			echo "<td>" . $content . "</td>";         
		}     
	}      
	
	echo "</table>"; 

}
else
{
    $this->RedirectURL("index.php");
}
    
?>
</div>
<?php include 'template/close_page_content_section.html' ?>
<?php include 'template/page_footer_section.html' ?>
<?php include 'template/close_page_container_section.html' ?>
<?php include 'template/send_email_form.html' ?>
<?php include 'template/error_dialog.html' ?>
<?php include 'template/scripts_section.html' ?>
<?php include 'template/close_body_tag_section.html' ?>
<?php include 'template/close_html_tag_section.html' ?>