<%-- 
    Document   : mybids
    Created on : Nov 18, 2015, 3:08:59 PM
    Author     : Vicmc12
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/newTable.css">
        <link rel="stylesheet" type="text/css" href="css/title.css">
        <link rel="stylesheet" type="text/css" href="css/login.css">
        <link rel="stylesheet" type="text/css" href="css/background.css">
        <link rel="stylesheet" type="text/css" href="css/table.css">
        <link rel="stylesheet" type="text/css" href="css/submit.css">
        <link rel="stylesheet" type="text/css" href="css/menu.css">





        <title>Meus posts</title>
    </head>
    <body style="background-image:url(css/backproj.jpg)">

        <c:if test="${user != null}">
            <c:if test="${posts == null}">
                <c:redirect url="FrontController?command=post.myposts.read"></c:redirect>
            </c:if>
            <c:if test="${allUsers == null}">
                <c:redirect url="FrontController?command=user.myposts.read"></c:redirect>
            </c:if>
            <c:if test="${bids == null}">
                <c:redirect url="FrontController?command=bid.myposts.read"></c:redirect>
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
                    </ul>
                </div>
            </c:if>

            <section> 
                <div class="container">
                    <h1>MEUS POSTS: ${user.username}</h1>
                </div>


                <table>
                    <thead>
                        <tr>
                            <th>Descrição</th>
                            <th>Status</th>
                            <th>Jogo(s)</th>
                            <th>Deletar</th>
                            <th>Leilão</th>
                        </tr>
                    </thead>
                    <c:forEach items="${posts}" var="post">
                        <c:if test="${post.user_id==user.user_id}">
                            <tbody>
                                <tr>


                                    <td>${post.description}</td>
                                    <td>Status: ${post.status}</td>
                                    <td>
                                        <c:forEach items="${post.games}" var="game">
                                            <img src="gamesImg\game${game.id_game}.jpg" alt="game${game.id_game}">
                                        </c:forEach>
                                    </td>
                                    <c:if test="${post.status!='terminado'}">
                                <form action="FrontController" method="POST">

                                    <input type="hidden" name="command" value="post.delete" />
                                    <input type="hidden" name="postToDelete" value="${post.id_post}"/>
                                    <td><input type="submit" value="Deletar" id="submit"/></td>

                                </form>
                                <td><a href="FrontController?post=${post.id_post}&command=user.auction">Ver leilão</a></td>
                            </c:if> 
                            <c:if test="${post.status=='terminado'}">
                                <p>Bid accepted</p> 
                                <c:forEach items="${bids}" var="bid">
                                    <c:if test="${bid.status=='aceito'}">
                                        <c:if test="${bid.id_post==post.id_post}">
                                            <p>User: ${bid.id_user}</p>
                                            <p>Games: 
                                                <c:forEach items="${bid.games}" var="game">
                                                <td><img src="gamesImg\game${game.id_game}.jpg" alt="game${game.id_game}"></td> 
                                                </c:forEach>
                                            </p>
                                        </c:if> 
                                    </c:if>
                                </c:forEach>
                            </c:if>  
                            </tr>
                        </c:if>

                    </c:forEach>
                </c:if>
                </tbody>
            </table>

            <p class="footer3"> © All rights reserved.  </p>
    </body>
</html>
