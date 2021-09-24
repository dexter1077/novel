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
                    <td><input type="password" id="enrollpwd1" class="form-control" name="userPwd"></td>
                </tr>
                <tr>
                    <th>비밀번호 확인</th>
                    <td><input type="password" id="enrollpwd2" class="form-control" name="pwdCheck"></td>
                </tr>
                <tr>
                    <th id="check-message" colspan="2" style="color:red; display:none;">사용가능한비밀번호</th>
                </tr>
                <tr>
                    <th>이름</th>
                    <td><input type="text" class="form-control" name="username"></td>
                </tr>
                <tr>
                    <th>닉네임</th>
                    <td><input type="text" class="form-control" name="nickname"></td>
                </tr>
                <tr>
                    <th>성별</th>
                    <td>남 : <input type="radio" name="gender" value="M" checked> 여 : <input type="radio" name="gender" value="W"></td>
                </tr>
                <tr>
                    <th>주소</th>
                    <td><input id="address_kakao" type="text" class="form-control" name="address"></td>
                </tr>
                <tr>
                    <th>상세주소</th>
                    <td><input type="text" class="form-control" name="addressDetail"></td>
                </tr>
                <tr>
                    <th>생년월일</th>
                    <td><input type="text" class="form-control" name="birthday"></td>
                </tr>
                <tr>
                    <th>전화번호</th>
                    <td><input type="text" class="form-control" name="phone" maxlength="11"></td>
                </tr>
                <tr>
                    <th>이메일</th>
                    <td><input type="email" class="form-control" name="email"></td>
                </tr>
            </table>
            <br>
            <button id="submit-btn" type="submit" class="btn btn-success" disabled>회원가입</button>
        </form>
    </div>

    <script>
        // 비밀번호 재확인
        $(document).on('keyup', 'input[type=password]', function(){

            let pwd1 = $('#enrollpwd1').val();
            let pwd2 = $('#enrollpwd2').val();

            let $checkMsg = $('#check-message');

            if(pwd1 == pwd2){    
                $checkMsg.css('display', 'block')
                        .css('color','blue')
                        .text('비밀번호 사용가능');
                $('#submit-btn').removeAttr('disabled');
            }else{
                $checkMsg.css('display', 'block')
                        .css('color','red')
                        .text('비밀번호 불일치');
                $('#submit-btn').attr('disabled');
            }

        });
    </script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script>
    window.onload = function(){
        document.getElementById("address_kakao").addEventListener("click", function(){ //주소입력칸을 클릭하면
            let width = 500;
            let height = 600;
            //카카오 지도 발생
            new daum.Postcode({
                width:width,
                height:height,
                oncomplete: function(data) { //선택시 입력값 세팅
                    document.getElementById("address_kakao").value = data.address; // 주소 넣기
                    document.querySelector("input[name=addressDetail]").focus(); //상세입력 포커싱
                }
            }).open({
                left: (window.screen.width / 2) - (width / 2),
                top: (window.screen.height / 2) - (height / 2)
            });
        });
    }
    </script>
</body>
</html>