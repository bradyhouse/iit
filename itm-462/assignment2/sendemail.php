<?php
//step 3 include the email class / objects
include_once('includes/class.phpmailer.php');
include("includes/class.smtp.php"); 

//step 1 - check if human pushed submit button
if(isset($_POST['name']))
{
    //step 2: make variables always make variables
    //out of stuff humans typed in html forms
    $user_name=$_POST['name'];
    $user_email=$_POST['email'];
    $user_message=$_POST['message'];
    
    //step 4 send the email 
    
    $mail = new PHPMailer();

    $mail->IsSMTP();               // set mailer to use SMTP
    $mail->Host = "mail.iit.edu";  // specify main and backup server

    $mail->From = $user_email;
    $mail->FromName = $user_name;
    $mail->AddAddress("bhousekn@hawk.iit.edu", "Brady Houseknecht");
    $mail->AddReplyTo($user_email, $user_name);

    $mail->WordWrap = 60;                                 // set word wrap to 50 characters
    $mail->IsHTML(true);                                  // set email format to HTML

    $mail->Subject = "Some website is sending you an email";

    $mail->Body = "Name $user_name  
            <br> Email  $user_email
            <br> Message $user_message";

    if (!$mail->Send()) {
        echo "Message could not be sent. <p>";
        echo "Mailer Error: " . $mail->ErrorInfo;
        exit;
    }
}
else{
    
}





?>
