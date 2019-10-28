//Using POST method pass through data from new booking to booking process.
var xhr = createRequest();
function booking(dataSource, divID, customerName, customerPhone, pickUpDate, 
				pickUpTime, streetNumber, streetName, pickUpSuburb, destinationSuburb) 
{
	if(xhr) 
	{
		var obj = document.getElementById(divID);
		var requestbody =
		"cName=" 		+ encodeURIComponent(customerName)	+
		"&cPhone=" 		+ encodeURIComponent(customerPhone)	+
		"&puDate=" 		+ encodeURIComponent(pickUpDate)	+
		"&puTime=" 		+ encodeURIComponent(pickUpTime)	+
		"&stNum=" 		+ encodeURIComponent(streetNumber)	+
		"&stName=" 		+ encodeURIComponent(streetName)	+
		"&suburb=" 		+ encodeURIComponent(pickUpSuburb)	+
		"&destSuburb=" 	+ encodeURIComponent(destinationSuburb);
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