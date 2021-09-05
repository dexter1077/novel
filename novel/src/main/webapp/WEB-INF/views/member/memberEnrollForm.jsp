<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<!-- jQuery library -->
<script
    src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- Popper JS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<style>
    .content-area{
        width: 1200px;
        height: 800px;
        border: 1px solid lightgray;
        margin: auto;
    }

    .content-area>form>div:first-child{
        font-size: 24px;
        margin:30px 0px 30px 0px;
    }

    form>table{
        margin-top: 20px;
    }
    
</style>
</head>
<body>
    <jsp:include page="../common/header.jsp"/>

	<div class="content-area" align="center">
        <form action="enroll.me" method="post">
            <div>회원가입</div>
            <table>
                <tr>
                    <th>아이디</th>
                    <td><input type="text" class="form-control" name="userId"></td>
                </tr>
                <tr>
                    <th>비밀번호</th>
                    <td><input type="text" class="form-control" name="userPwd"></td>
                </tr>
                <tr>
                    <th>비밀번호 확인</th>
                    <td><input type="text" class="form-control" name="pwdCheck"></td>
                </tr>
                <tr>
                    <th>이름</th>
                    <td><input type="text" class="form-control" name="userName"></td>
                </tr>
                <tr>
                    <th>닉네임</th>
                    <td><input type="text" class="form-control" name="nickname"></td>
                </tr>
                <tr>
                    <th>성별</th>
                    <td>남 : <input type="radio" name="gender" value="M" checked> 여 : <input type="radio" name="gender" id="W"></td>
                </tr>
                <tr>
                    <th>주소</th>
                    <td><input type="text" class="form-control" name="address"></td>
                </tr>
                <tr>
                    <th>생년월일</th>
                    <td><input type="text" class="form-control" name="birthday"></td>
                </tr>
                <tr>
                    <th>전화번호</th>
                    <td><input type="text" class="form-control" name="phone"></td>
                </tr>
                <tr>
                    <th>이메일</th>
                    <td><input type="email" class="form-control" name="email"></td>
                </tr>
            </table>
            <br>
            <button type="submit" class="btn btn-success">회원가입</button>
        </form>
            
    </div>
</body>
</html>