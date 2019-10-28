<html>
<body>
<h1>Displaying Current Bookings</h1><br>
<?php
	require_once ("settings.php");			
	$sqlTable = "cab_bookings";
	$conn = @mysqli_connect($host, $user, $pswd, $dbnm) or die("Unable to connect to DB server");
	$query = $conn->query("SELECT * FROM $sqlTable");
?>
	<table border="2">
	<tr>
		<td>Booking ID</td>
		<td>Reference No</td>
		<td>Name</td>
		<td>Phone</td>
		<td>Pick Up Address</td>
		<td>Destination Suburb</td>
		<td>Pick Up Date</td>
		<td>Pick Up Time</td>
		<td>Assignment Status</td>
	</tr>

	<?php	
		if($query->num_rows > 0)
		{
			while($row = $query->fetch_assoc())
			{
	?>
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
			</tr>
	<?php
			}		
		}
	?>
</table>
<form>
<input type = "button" onclick = "location.href='http://wdg7741.cmslamp14.aut.ac.nz/assign2/OnlineTaxi.php';" value = "Home"/>
</form>
</body>
</html>