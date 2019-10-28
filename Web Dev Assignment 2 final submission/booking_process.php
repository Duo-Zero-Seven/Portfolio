<?php
	//Receive data from XHR booking.js passthrough
	$customerName = $_POST["cName"];
	$customerPhone = $_POST["cPhone"];
	$streetNumber = $_POST["stNum"];
	$streetName = $_POST["stName"];
	$suburb = $_POST["suburb"];
	$destinationSuburb = $_POST["destSuburb"];
	$pickUpDate = $_POST["puDate"];
	$pickUpTime = $_POST["puTime"];
	sleep(1); //slow server response time
	
	//Check if any fields are empty
	if(empty($customerName)||empty($customerPhone)||empty($streetNumber)||
	   empty($streetName)||empty($suburb)||empty($destinationSuburb)||
	   empty($pickUpDate)||empty($pickUpTime)) 
	{
		echo "Please fill out all fields before clicking submit!";
	}
	else
	{
		//Save address using string concatenation
		$pickupAddress = "$streetNumber $streetName"; 
		$pickupAddress = $pickupAddress.", ";
		$pickupAddress = $pickupAddress.$suburb;
		
		//save date in dmY format in a new variable for DB storage
		$today = date("dmY"); 
		//generate a random booking reference using date and a random number between 1k and 5k
		$bookingRef = $today . rand(1000, 5000); 
	}
	
	//Database connection settings
	require_once("settings.php");
	$sqlTable = "cab_bookings";
	$conn = @mysqli_connect($host, $user, $pswd, $dbnm) or die("Unable to connect to DB server");   
	mysql_select_db($sqlTable);
	
	//DATABASE CREATION CODE GOES HERE
	$table = "CREATE TABLE IF NOT EXISTS cab_bookings
	(
		booking_id int AUTO_INCREMENT, 
		booking_number VARCHAR(12), 
		customer_name VARCHAR(20), 
		customer_phone VARCHAR(15), 
		customer_pickup_address VARCHAR (40), 
		customer_destination VARCHAR(30), 
		customer_pickup_date VARCHAR(10), 
		customer_pickup_time VARCHAR(5), 
		booking_assignment_status VARCHAR(10), 
		PRIMARY KEY(booking_id)
	)";
	
	// Checks if above statement fails.
	if(!@mysqli_query($conn, $table))
	{ 
		echo "Request invalid, Table not created."; 
	} 
	else
	{
		//Insert data into cab_bookins DB
		$insertQuery = 	"insert into $sqlTable"
				."(booking_number, customer_name, customer_phone, 
				customer_pickup_address, customer_destination, customer_pickup_date, 
				customer_pickup_time, booking_assignment_status)"
				." values "
				."('$bookingRef', '$customerName', '$customerPhone', '$pickupAddress', 
				'$destinationSuburb', '$pickUpDate', '$pickUpTime', 'UNASSIGNED')";
		//Check if process completed correctly
		if($conn->query($insertQuery) == true)
		{
			echo "Thank you for booking your taxi with Online Taxi! </br>";
			echo "Your reference number is: $bookingRef.</br>"; 
			echo "You will be collected from $pickupAddress @ $pickupTime on $pickupDate.</br>";
			echo "Please be ready 5 minutes before the scheduled pick up time.";
		}
		else echo "Error: ".$insertQuery.$conn->error; //throw error if process fails
	}
	mysqli_close($conn); 
?>
