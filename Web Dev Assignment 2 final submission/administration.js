//Using POST method pass through data to the booking assignment page for booking allocation
var xhr = createRequest();
function bookingassignment(dataSource, divID, request) 
{
	if(xhr) //if XHR function compatible
	{
		var obj = document.getElementById(divID);
		var requestbody = "bookingRef=" + encodeURIComponent(request);
 		xhr.open("POST", dataSource, true);
		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		xhr.onreadystatechange = function() 
		{
			if (xhr.readyState == 4 && xhr.status == 200) 
			{ 
				obj.innerHTML = xhr.responseText;
			} 
		} 
 		xhr.send(requestbody);
	} 
}

//Using POST method pass through data to the booking review page
function administration(dataSource, divID, date) 
{
	if(xhr) //if XHR function compatible
	{
		var obj = document.getElementById(divID);
		var requestbody = "date=" + encodeURIComponent(date);
 		xhr.open("POST", dataSource, true);
		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		xhr.onreadystatechange = function() 
		{
			if (xhr.readyState == 4 && xhr.status == 200) 
			{ 
				obj.innerHTML = xhr.responseText;
			} 
		}
 		xhr.send(requestbody);
	}
}