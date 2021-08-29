
<?php

$EMAIL_ID = 304513639; // 9-digit integer value (i.e., 123456789)
$API_KEY = "16f5d89d"; // API key (string) provided by Open Movie DataBase (i.e., "ab123456")

session_start(); // Connect to the existing session

require_once '/home/common/php/dbInterface.php'; // Add database functionality
require_once '/home/common/php/mail.php'; // Add email functionality
require_once '/home/common/php/p4Functions.php'; // Add Project 4 base functions

processPageRequest(); // Call the processPageRequest() function

// DO NOT REMOVE OR MODIFY THE CODE OR PLACE YOUR CODE ABOVE THIS LINE

function addMovieToCart($imdbID)
{	
    $val = movieExistsInDB($imdbID);
    if(!empty($val)){
        $result= file_get_contents('http://www.omdbapi.com/?apikey='.$GLOBALS['API_KEY'].'&i='.$imdbID.'&type=movie&r=json');
        $movieInfo = json_decode($result, true);
        $imdbId = $movieInfo['imdbID'];
        $title = $movieInfo['Title'];
        $year = $movieInfo['Year'];
        $rating = $movieInfo['Rated'];
        $runtime = $movieInfo['Runtime'];
        $genre = $movieInfo['Genre'];
        $actors = $movieInfo['Actors'];
        $director = $movieInfo['Director'];
        $writer = $movieInfo['Writer'];
        $plot = $movieInfo['Plot'];
        $poster = $movieInfo['Poster'];
        addMovie($imdbId, $title, $year, $rating, $runtime, $genre, $actors, $director, $writer, $plot, $poster);
    }else{}
    addMovieToShoppingCart($_SESSION['userId'], $val);
    displayCart();
}

function displayCart()
{
    $movies = getMoviesInCart($_SESSION['userId']);
    require_once("./templates/cart_form.html");
}

function processPageRequest()
{
    if(isset($_SESSION['displayName'])){
        if(isset($_GET['action'])){
            if($_GET['action'] == 'add'){
                addMovieToCart($_GET['imdb_id']);
                displayCart();
            }
            if($_GET['action'] == 'remove'){
                removeMovieFromCart($_GET['movie_id']);
                displayCart();
            }
        }   
        else{
            displayCart();
        }
    }
    else{
        header('Location: ./logon.php');
    }
}

function removeMovieFromCart($movieID)
{	
	removeMovieFromShoppingCart($_SESSION['userId'],$movieID);
    displayCart();
}

?>