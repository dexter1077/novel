<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
    .head-area{
        width: 100%;
        height: 50px;
        border-bottom: 1px solid lightgray;
    }
    .icon-box, .menu-box{
        width: 1200px;
        height: 50%;
        margin: auto;
        border:1px solid red;
    }

    .icon-box>span{
        font-size: 24px;
        line-height: 50px;
    }
    #login-box:hover{
        cursor: pointer;
        font-weight: 600;
    }


    .menu-box>a{
        font-size: 24px;
        margin-right: 50px;
        line-height: 50px;
        color: black;
        text-decoration: none;
        font-weight: 600;
    }
</style>
</head>
<body>
	<div class="head-area">
        <div class="icon-box" align="right">
            <span id="login-box" data-toggle="modal" data-target="#loginModal">Login</span>
        </div>
    </div>
    <div class="head-area">
        <div class="menu-box">
            <a href="/novel">홈</a>
            <a href="">공모전</a>
            <a href="">웹소설</a>
            <a href="">작품올리기</a>
            <span align="right">선호장르</span>
        </div>
    </div>

    <div class="modal" id="loginModal">
        <div class="modal-dialog">
            <div class="modal-content">
      
            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">로그인</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <form action="login.me" method="post">
                <!-- Modal body -->
                <div class="modal-body" align="center">
                    <input type="text" class="form-control" style="width: 200px;" placeholder="id" name="userId">
                    <input type="password" class="form-control" style="width : 200px" placeholder="pwd" name="userPwd"><br>
                    <button type="submit" class="btn btn-info">로그인</button><br>
                    <button class="btn btn-success">네이버</button><button class="btn btn-warning">카카오</button><br>
                    <a href="enrollForm.me">일반 회원가입</a>
                </div>
            </form>
                
            <!-- Modal footer -->
            <div class="modal-footer">
              <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
            </div>
      
          </div>
        </div>
      </div>

</body>
</html>