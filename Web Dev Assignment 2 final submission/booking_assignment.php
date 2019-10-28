<?php
	require_once ("settings.php");
	$sqlTable = "cab_bookings";
	$conn = @mysqli_connect($host, $user, $pswd, $dbnm) or die("Unable to connect to DB server");
	$searchRef = $_POST['bookingRef']; 
	$QueryOne = $conn->query("SELECT * FROM $sqlTable WHERE booking_number = '$searchRef'");
	
	if($QueryOne->num_rows > 0) 
	{
		$QueryTwo = $conn->query("SELECT * FROM $sqlTable WHERE booking_number = '$searchRef' AND booking_assignment_status = 'UNASSIGNED'");
		if($QueryTwo->num_rows > 0) 
		{
			$QueryThree = $conn->query("UPDATE $sqlTable SET booking_assignment_status = 'ASSIGNED' WHERE booking_number = '$searchRef'");
			echo "A driver has been assigned to job ref: $searchRef";
		}
		else echo "A driver has already been assigned to this job.";
	} 
	else echo "Incorrect booking Number";
?>