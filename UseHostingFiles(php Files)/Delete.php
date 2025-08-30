<?php
$con = mysqli_connect("localhost", "seq0914", "whrudrhs1!", "seq0914");
mysqli_query($con, 'SET NAMES utf8');

$userID = $_POST["userID"];

// userID와 userPassword 모두 확인
$statement = mysqli_prepare($con, "DELETE FROM USER WHERE userID = ?");
mysqli_stmt_bind_param($statement, "s", $userID);
mysqli_stmt_execute($statement);

$response = array();
$response["success"] = true;


echo json_encode($response);
?>
