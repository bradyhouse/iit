<!DOCTYPE html>
<?php
/**
* Data Section 2
*/
$daysOfTheWeekOpen = array(1=>"Mon",2=>"Tue",3=>"Wed",4=>"Thur",5=>"Fri");
$openingHours = array("Mon"=>9,"Tue"=>9,"Wed"=>9,"Thur"=>12,"Fri"=>9);
$closingHours = array("Mon"=>5,"Tue"=>5,"Wed"=>5,"Thur"=>4.5,"Fri"=>4.5);

?>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Reliable Repair</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="css/bootstrap.css" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 20px;
        padding-bottom: 40px;
        overflow-x: hidden;
      }
      /* Quote Form container - Shifts the form to the left */
      .dialog
      {
        border:1px solid black;
        margin-left: -100px;
        padding: 10px;
      }
      /* Quote Form Field container */
      #calcQuoteForm .container
      {
        margin-top:8px;
        margin-bottom: 10px;
      }
      #calcQuoteForm .short_explanation
      {
         font-family : Arial, sans-serif;
         font-size: 0.6em;
         color:#333;
      }
      #calcQuoteForm fieldset
      {
         width:320px;
         padding:20px;
         border:1px solid #ccc;
      -moz-border-radius: 10px;
      -webkit-border-radius: 10px;
      -khtml-border-radius: 10px;
      border-radius: 10px;
      }

      /* Custom container */
      .container-narrow {
        margin: 0 auto;
        max-width: 700px;
      }
      .container-narrow > hr {
        margin: 30px 0;
      }

      /* Main marketing messages */
      .jumbotron {
        margin: 60px 0;
        text-align: center;
		background: url(img/sue.jpg);
		color: white;
      }
      .jumbotron h1 {
        font-size: 72px;
        line-height: 1;
      }
      .jumbotron .btn {
        font-size: 21px;
        padding: 14px 24px;
      }

      /* Supporting marketing content */
      .marketing {
        margin: 60px 0;
      }
      .marketing p + h4 {
        margin-top: 28px;
      }
    </style>
    <link href="css/bootstrap-responsive.css" rel="stylesheet">

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="js/html5shiv.js"></script>
    <![endif]-->

    <!-- Fav and touch icons -->
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="ico/apple-touch-icon-57-precomposed.png">
    <link rel="shortcut icon" href="ico/favicon.png">
  </head>

  <body>

    <div class="container-narrow">

      <div class="masthead">
        <ul class="nav nav-pills pull-right">
          <li><a href="index.php">Home</a></li>
          <li class="active"><a href="quote.php">Quote</a></li>
          <li><a href="#">Contact</a></li>
        </ul>
        <h3 class="muted">Reliable Repair</h3>
        <?php include 'templates/location.html' ?>
      </div>

      <hr>

      <div class="row-fluid marketing">
		<h1>Reliable Repair</h1>
		<div class="marketing">
            <?php include 'templates/functions.html'; ?>
            <?php include 'templates/calcQuote.html'; ?>
        </div>
      </div>
	  <div class="row-fluid">
		<h2>Quote</h2>
		<?php 
		//section 7
		?>
	  </div>	  
   </div> <!-- /container -->

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <script src="js/bootstrap.js"></script>
  </body>
</html>
