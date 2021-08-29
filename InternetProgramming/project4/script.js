// DO NOT REMOVE OR MODIFY THE CODE OR PLACE YOUR CODE BELOW THIS LINE

function displayMovieInformation(movie_id)
{
	var request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		document.getElementById("modalWindowContent").innerHTML = this.responseText;
		showModalWindow();
		};
	request.open("GET", "./movieinfo.php?movie_id=" + movie_id, true);
	request.send();
}

function forgotPassword()
{
	window.location.replace("./logon.php?action=forgot");
}

function showModalWindow()
{
    var modal = document.getElementById('modalWindow');
    var span = document.getElementsByClassName("_close")[0];

    span.onclick = function() 
    { 
        modal.style.display = "none";
    }

    window.onclick = function(event) 
    {
        if (event.target == modal) 
        {
            modal.style.display = "none";
        }
    }
 
    modal.style.display = "block";
}

// DO NOT REMOVE OR MODIFY THE CODE OR PLACE YOUR CODE ABOVE THIS LINE


// DO NOT REMOVE OR MODIFY THE SIGNATURE OF THE FUNCTIONS BELOW THIS LINE

function addMovie(imdbID)
{
    window.location.replace("./index.php?action=add&imdb_id=" + imdbID);
    return true;

}

function confirmCancel()
{
	var select = window.confirm("Cancel?");
    if(select){
        window.location.replace("./index.php");
    }
    return select;
}
function confirmCheckout()
{
	var select = window.confirm("Checkout?");
    if(select){
        window.location.replace("./index.php?action=checkout");
    }
    return select;
}
function confirmLogout()
{
	var select = window.confirm("Do you really want to logout?");
    if(select){
        window.location.replace("./logon.php?action=logoff");
    }
    return select;
}
function confirmRemove(title, movieID)
{
    var select = window.confirm("Remove selected movie? " + title);
    if(select){
        window.location.replace("./index.php?action=remove&movie_id=" + movieID);
    }
    return select;
}