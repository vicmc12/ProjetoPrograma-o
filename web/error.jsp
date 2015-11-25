<%-- 
    Document   : error
    Created on : 30/09/2015, 09:47:47
    Author     : 31381243
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/background.css">
        <link rel="stylesheet" type="text/css" href="css/mainStyle.css"/>
        <link rel="stylesheet" type="text/css" href="css/login.css">
        <link rel="stylesheet" type="text/css" href="css/newTable.css">
        <link rel="stylesheet" type="text/css" href="css/title.css">
        <link rel="stylesheet" type="text/css" href="css/menu.css">
        <link rel="stylesheet" type="text/css" href="css/table.css">
        <link rel="stylesheet" type="text/css" href="css/submit.css">
        <title>Erro</title>
    </head>
    <body style="background-image:url(css/backproj.jpg)">
        
        <%-- OBS COLOCAR O REDIRECT ANTES DISSE COMENTARIO --%>
        <div class="container">
            <h1 class="row skew-title">  
                <span>T</span><span>R</span><span>O</span><span>C</span><span class="last">A</span>
                &nbsp; 
                <span>D</span><span class="last">E</span>
                &nbsp;
                <span class="alt">J</span><span class="alt">O</span><span class="alt">G</span><span class="alt">O</span><span class="alt last">S</span>
            </h1>
            <p class="row row--intro">O maior site de trocas de jogos do Brasil.</p>
            <div class="form">
                <form action="FrontController" method="POST" >
                    <div class="text-input">
                        <label for="username">Username</label>
                        <input type="text" name="username" id="username"  required="true" />
                        <span class="separator"> </span>
                    </div>  
                    <div class="text-input">
                        <label for="password">Password</label>
                        <input type="password" name="password" id="password"  required="true"/>
                        <span class="separator"> </span>
                    </div>  
                    <div class="form-bottom">
                        <input type="submit" id="submit" value="LOGIN"/>

                        <input type="hidden" name="command" value="user.login"/>

                        <a href="sign.jsp" class="original-src">SIGN-IN</a>
                    </div>
            </div>
        </form>
            <c:if test="${code!=null}">
            <h1>${code}</h1>
            </c:if>
        </div>
             
        
    </body>
</html>
