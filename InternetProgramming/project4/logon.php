<?php

$EMAIL_ID = 304513639; // 9-digit integer value (i.e., 123456789)

require_once '/home/common/php/dbInterface.php'; // Add database functionality
require_once '/home/common/php/mail.php'; // Add email functionality
require_once '/home/common/php/p4Functions.php'; // Add Project 4 base functions

processPageRequest(); // Call the processPageRequest() function

// DO NOT REMOVE OR MODIFY THE CODE OR PLACE YOUR CODE ABOVE THIS LINE

function authenticateUser($username, $password) 
{
	$user = validateUser($username, $password);
    if($user !== null){
        session_start();
        $_SESSION['userId'] = $user[0];
        $_SESSION['displayName'] = $user[1];
        $_SESSION['emailAddress'] = $user[2];
        return true; 
    } else{return false;}
}

function displayLoginForm($message = "")
{	
	require_once("./templates/logon_form.html");
}

function processPageRequest()
{
	// DO NOT REMOVE OR MODIFY THE CODE OR PLACE YOUR CODE BELOW THIS LINE
	if(session_status() == PHP_SESSION_ACTIVE)
	{
		session_destroy();
	}
	// DO NOT REMOVE OR MODIFY THE CODE OR PLACE YOUR CODE ABOVE THIS LINE
    if($_SERVER['REQUEST_METHOD'] == 'POST'){
        if(isset($_POST['action'])){
            if($_POST['action'] === 'login'){
                if(authenticateUser($_POST['username'], $_POST['password'])){
                    header('Location: ./index.php');
                }
                else{
                    displayLoginForm("Failed to authenticate user!");
                }
            }
        }
    }
    else{
        displayLoginForm();
    }
}

?>
