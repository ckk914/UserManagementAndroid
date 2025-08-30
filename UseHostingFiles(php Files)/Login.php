<?php
$con = mysqli_connect("localhost", "seq0914", "whrudrhs1!", "seq0914");
mysqli_query($con, 'SET NAMES utf8');

$userID = $_POST["userID"];
$userPassword = $_POST["userPassword"];

// userID와 userPassword 모두 확인
$statement = mysqli_prepare($con, "SELECT userID, userPassword, userName, userAge FROM USER WHERE userID = ? AND userPassword = ?");
mysqli_stmt_bind_param($statement, "ss", $userID, $userPassword);
mysqli_stmt_execute($statement);

mysqli_stmt_store_result($statement);
mysqli_stmt_bind_result($statement, $resUserID, $resUserPassword, $resUserName, $resUserAge);

$response = array();
$response["success"] = false;

if (mysqli_stmt_fetch($statement)) {
    $response["success"] = true;
    $response["userID"] = $resUserID;
    $response["userPassword"] = $resUserPassword;
    $response["userName"] = $resUserName;
    $response["userAge"] = $resUserAge;
}

echo json_encode($response, JSON_UNESCAPED_UNICODE);
?>
