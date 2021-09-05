<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>웹소설</title>
<style>
    .content-area{
        width: 1200px;
        margin: auto;
        height: 1200px;
    }
    .content-area>div{
        margin-top: 10px;
    }

    /*배너 영역*/
    .banner{
        width: 100%;
        height: 300px;
    }
    .banner>div{
        float: left;
        height: 100%;
    }
    .banner-box{
        width: 800px;
    }
    .new-novel{
        width: 380px;
        margin-left: 16px;
    }

    /*소설 랭킹 영역*/
    .ranking-area{
        width: 100%;
        height: 400px;
    }
    .list-head{
        width: 100%;
        height: 30px;
        font-weight: 600;
        margin-bottom : 10px;
    }

    .novel-content>div{
        width: 370px;
        margin-right: 25px;
        float: left;
        height: 350px;
    }

    div{box-sizing: border-box; border:1px solid red;}
</style>
</head>
<body>
	<jsp:include page="common/header.jsp"/>

    <div class="content-area">

        <div class="banner">
            <div class="banner-box">
                슬라이더 배너 만들기
            </div>
            <div class="new-novel">
                새로운 소설 소개
            </div>
        </div>

        <div class="ranking-area">
            <div class="list-head">
                웹소설 통합 랭킹
            </div>
            <div class="novel-content">
                <div></div>
                <div></div>
                <div></div>
            </div>
        </div>

        <div class="content-area">
            <div class="list-head">
                <span>리그별 일간 랭킹</span>
                <select name="" id="" style="margin-left: 900px;">
                    <option value="1">로맨스</option>
                </select>
            </div>
            <div class="novel-content">
                <div></div>
                <div></div>
                <div></div>
            </div>
        </div>

    </div>

    <jsp:include page="common/footer.jsp"/>
</body>
</html>