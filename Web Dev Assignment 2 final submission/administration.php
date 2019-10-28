<?php
	//DB connection and Query Data
	require_once ("settings.php");
	$sqlTable = "cab_bookings";	
	$conn = @mysqli_connect($host, $user, $pswd, $dbnm) or die("Unable to connect to DB server");	
	mysql_select_db($dbnm, $conn);
		
	$query = $conn->query("SELECT * FROM $sqlTable WHERE booking_assignment_status = 'UNASSIGNED'"); 
?>
	<!Table field references>
	<table border="2">
	<tr>
		<td>Booking Id</td>
		<td>Booking Reference</td>
		<td>Name</td>
		<td>Phone</td>
		<td>Pick Up Address</td>
		<td>Destination Suburb</td>
		<td>Pick Up Date</td>
		<td>Pick Up Time</td>
		<td>Status</td>
	</tr>

<?php	
	if($query->num_rows > 0) //if unassigned bookings exist
		while($row = $query->fetch_assoc())
		{?>		
			<!Pull results from DB and insert into table>
			<tr>
				<td><?php echo $row['booking_id']?></td>
				<td><?php echo $row['booking_number']?></td>
				<td><?php echo $row['customer_name']?></td>
				<td><?php echo $row['customer_phone']?></td>
				<td><?php echo $row['customer_pickup_address']?></td>
				<td><?php echo $row['customer_destination']?></td>
				<td><?php echo $row['customer_pickup_date']?></td>
				<td><?php echo $row['customer_pickup_time']?></td>
				<td><?php echo $row['booking_assignment_status']?></td>
			</tr><?php		
		}?>
