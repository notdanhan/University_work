function getTime()  {// Create new XMLHttpRequestObject
	var requester = new XMLHttpRequest;/* Monitor the onreadystatechangeevent for a change in the readyState.This function will be executed wheneverthe readyStateis changed so must be declared beforethe request is sent */
	requester.onreadystatechange = function() {
		if (requester.readyState== 4 && requester.status== 200) {// Put the response into the divdocument.
			getElementById("divTime").innerHTML= requester.responseText;
		}
	}// Open connection to time.php and send the request asynchronously
	requester.open("GET", "http://www.psychicsoftware.com/ct1113/time.php", true);
	requester.send();
}