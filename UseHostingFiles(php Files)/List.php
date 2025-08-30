<?php
// DB 연결
$con = mysqli_connect("localhost", "seq0914", "whrudrhs1!", "seq0914");

// 연결 오류 체크
if (mysqli_connect_errno()) {
    echo json_encode(array("error" => "Failed to connect to MySQL: " . mysqli_connect_error()));
    exit();
}

// 한글 깨짐 방지
mysqli_query($con, 'SET NAMES utf8');

// 쿼리 실행
$result = mysqli_query($con, "SELECT userID, userPassword, userName, userAge FROM USER;");

$response = array();

// 결과 가져오기
while ($row = mysqli_fetch_array($result)) {
    array_push($response, array(
        "userID" => $row["userID"],
        "userPassword" => $row["userPassword"],
        "userName" => $row["userName"],
        "userAge" => $row["userAge"]
    ));
}

// JSON 출력
echo json_encode(array("response" => $response));

// 연결 종료
mysqli_close($con);
?>
