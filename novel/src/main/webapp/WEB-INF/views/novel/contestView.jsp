<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공모전</title>
<style>
    div{
        border:lightgray 1px solid;
        box-sizing: border-box;
    }

    #container{
        margin: auto;
        width: 1200px;
        height: 1800px;
        display: flex;
        flex-direction: column;
        flex-wrap: wrap;
        background-color: yellowgreen;
        align-items: center;
    }
    .item{
        width: 1200px;
        height: 200px;
    }

    .item1{
        background-color: lightgray;
    }
    .item2{background-color: red;}
    .item3{background-color: green;}
    .item4{background-color: blue;}
    .item5{background-color: orange;}
    .item6{background-color: purple;}
</style>
</head>
<body>
	<jsp:include page="../common/header.jsp"/>
	
    <div id="container">
        <div class="item item1">
            1
        </div>
        <div class="item item2">
            2
        </div>
        <div class="item item3">
            3
        </div>
        <div class="item item4">
            4
        </div>
        <div class="item item5">
            5
        </div>
        <div class="item item6">
            6
        </div>
    </div>

</body>
</html>