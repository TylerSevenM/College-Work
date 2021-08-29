<?php

$API_KEY = "16f5d89d"; // API key (string) provided by Open Movie DataBase (i.e., "ab123456")

session_start(); // Connect to the existing session
//  http://www.omdbapi.com/?i=tt3896198&apikey=16f5d89d  ====> API Link
processPageRequest(); // Call the processPageRequest() function

// DO NOT REMOVE OR MODIFY THE CODE OR PLACE YOUR CODE ABOVE THIS LINE

function displaySearchForm()
{
	require_once("./templates/search_form.html");
}

function displaySearchResults($searchString)
{	
    $results = file_get_contents('http://www.omdbapi.com/?apikey='.$GLOBALS['API_KEY'].'&s='.urlencode($searchString).'&type=movie&r=json');
    $resultsArray = json_decode($results, true)["Search"];
    require_once("./templates/results_form.html");
}

function processPageRequest()
{
    if(isset($_SESSION['displayName'])){
        if(isset($_POST['keyword'])){
            displaySearchResults($_POST['keyword']);
        }
        else{
            displaySearchForm();
        }
    }
    else{
        header('Location: ./logon.php');
    }
}

?>