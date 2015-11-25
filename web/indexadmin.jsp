<%-- 
    Document   : indexadmin
    Created on : Nov 25, 2015, 9:48:44 AM
    Author     : Vicmc12
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index Admin</title>
        <link rel="stylesheet" type="text/css" href="css/background.css">
        <link rel="stylesheet" type="text/css" href="css/mainStyle.css"/>
        <link rel="stylesheet" type="text/css" href="css/login.css">
        <link rel="stylesheet" type="text/css" href="css/newTable.css">
        <link rel="stylesheet" type="text/css" href="css/title.css">
        <link rel="stylesheet" type="text/css" href="css/menu.css">
        <link rel="stylesheet" type="text/css" href="css/table.css">
        <link rel="stylesheet" type="text/css" href="css/submit.css">
    </head>
    <body style="background-image:url(css/backproj.jpg)">
        <!--        <img src="backproj.jpg" class="photo" alt="">-->
        <c:if test="${posts == null}">
            <c:redirect url="FrontController?command=post.read"></c:redirect>
        </c:if>
        <c:if test="${allUsers == null}">
            <c:redirect url="FrontController?command=user.read"></c:redirect>
        </c:if>
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

        <c:if test="${user == null}">
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
    </div>
</c:if>

<c:if test="${user != null}">

    <div class="container">
        <h1>Bem-vindo ${user.username} </h1>

        <ul id="nav">
            <li class="bars">
                <div class="bar1"></div><div class="bar2"></div><div class="bar3"></div><div class="bar3"></div><div class="bar3"></div>
            </li>
            <li><a href="FrontController?command=index">INDEX</a></li>
            <li><a href="FrontController?command=post.myposts">MEUS POSTS</a></li>
            <li><a href="FrontController?command=bid.bids">MEUS LANCES</a></li>
            <li><a href="FrontController?command=user.newpost">ADICIONAR POSTS</a></li>
            
        </ul>
    </div>
        <div class="container">
        <ul id="nav">

            <li class="bars">
                <div class="bar1"></div>
            </li>
            <li><a href="FrontController?command=user.logout">SAIR</a></li>
            <c:if test="${user.permission==2}">
            <li><a href="manageusers.jsp">ADMINISTRAR USUÁRIOS</a></li>
            </c:if>
        </ul>
    </div>

        <c:if test="${user.permission>0}">
    


<div class="container">
    <h1>Lista de posts</h1>
</div>
<table>
    <thead>
        <tr>
            <th>Nome do Vendedor</th>
            <th>Descrição</th>
            <th>Jogo(s)</th>
            <th>Leilão</th>
            <th>Deletar</th>
        </tr>
    </thead>


    <c:forEach items="${posts}" var="post">
        <c:if test="${post.status!='terminado'}">
            <tbody>
                <tr> 

                    <c:forEach items="${allUsers}" var="user">
                        <c:if test="${user.user_id == post.user_id}">
                            <td>${user.username}</td>  
                        </c:if>
                    </c:forEach>
                    <td>${post.description}</td>
                    <td> <c:forEach items="${post.games}" var="game">
                            <img src="gamesImg\game${game.id_game}.jpg" alt="game${game.id_game}">
                        </c:forEach>
                    </td>
                    <td><a href="FrontController?post=${post.id_post}&command=user.auction">Ver leilão</a></td>
                     <form action="FrontController" method="POST">
                        <input type="hidden" name="command" value="post.delete" />
                        <input type="hidden" name="postToDelete" value="${post.id_post}"/>
                        <td><input type="submit" value="Deletar" id="submit"/></td>
                    </form>
                </tr>

            </c:if>
        </c:forEach>
    </tbody>
</table>
            </c:if>
<c:if test="${user.permission<1}">
    <div class="container">
    <h1>Você não tem permissão suficiente para acessar essa pagina</h1>
    </div>
</c:if>
</c:if>
<p class="footer3"> © All rights reserved.  </p>
</body>
</html>