<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
</head>
<body>
<h2 style="text-align: center; margin-top: 100px">ids : ${ids}</h2>
<%--<c:forEach var="id" items="${ids}">
    <p>${id}</p>

</c:forEach>--%>

<script>
$(function(){
    let json = {
        user:{
            name:"문태환",
            age:"22",
            address:"대한민국"
        }
    }

$.ajax({
    url: "/example/parameter/example6/saveData",
    type:'post',
    data:JSON.stringify(json),
    contentType:'application/json',
    dataType:'json',
    success: function(data){
        console.log(data);
    }
})

});
</script>
</body>
</html>