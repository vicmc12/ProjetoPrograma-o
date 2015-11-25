<%-- 
    Document   : sign
    Created on : 21/10/2015, 10:05:06
    Author     : 31377467
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/login.css">
        <link rel="stylesheet" type="text/css" href="css/title.css">
        <link rel="stylesheet" type="text/css" href="css/background.css">
        <link rel="stylesheet" type="text/css" href="css/submit.css">



        <title>Sign in</title>
    </head>
    <body style="background-image:url(css/backproj.jpg)">

        <div class="container">
            <h1 class="row skew-title">  
                <span>T</span><span>R</span><span>O</span><span>C</span><span class="last">A</span>
                &nbsp; 
                <span>D</span><span class="last">E</span>
                &nbsp;
                <span class="alt">J</span><span class="alt">O</span><span class="alt">G</span><span class="alt">O</span><span class="alt last">S</span>
            </h1>
            <p class="row row--intro">O maior site de trocas de jogos do Brasil.</p>
        </div>


        <%--<form action="FrontController" method="POST"> --%>
        <div class="form">
            <form action="FrontController" method="POST" >
                <div class="text-input">
                    <label for="username">Username</label>
                    <input type="text" name="username" id="username" required=""/>
                    <span class="separator"> </span>
                </div>
                <div class="text-input">
                    <label for="password">Password</label>    
                    <input type="password" name="password" id="password" required=""/>
                    <span class="separator"> </span>
                </div> 
                <div class="text-input">
                    <label for="password">Password2</label> 
                    <input type="password" name="password2" id="password" required=""/>
                    <span class="separator"> </span>
                </div> 
                <div class="form-bottom">
                    <p> <input type="submit" id="submit" value="SIGN-IN"/></p>
                    <input type="hidden" name="command" value="user.insert"/>     
                    <a href="index.jsp" class="original-src">VOLTAR</a>
                </div> 
        </div>
    </form>
    <p class="footer3"> © All rights reserved.  </p>
</body>
</html>
