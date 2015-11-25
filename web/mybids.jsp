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
        <link rel="stylesheet" type="text/css" href="css/title.css">
        <link rel="stylesheet" type="text/css" href="css/table.css">
        <link rel="stylesheet" type="text/css" href="css/background.css">
        <link rel="stylesheet" type="text/css" href="css/newTable.css">
        <link rel="stylesheet" type="text/css" href="css/submit.css">
        <link rel="stylesheet" type="text/css" href="css/menu.css">





        <title>Meus lances</title>
    </head>
    <body style="background-image:url(css/backproj.jpg)">
        <c:if test="${user != null}">
            <c:if test="${bids == null}">
                <c:redirect url="FrontController?command=bid.mybids.read"></c:redirect>
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
            <div class="container">
                <c:if test="${user != null}">
                    <h1>Bem-vindo ${user.username} </h1>
                    <ul id="nav">

                        <li class="bars">
                            <div class="bar1"></div><div class="bar2"></div><div class="bar3"></div><div class="bar3"></div><div class="bar4"></div>
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
            <div class="container">
                <h1>MEUS LANCES: ${user.username}</h1>
            </div>

            <table>
                <thead>
                    <tr>
                        <th>Status</th>
                        <th>Jogo(s)</th>

                    </tr>
                </thead>
                <c:forEach items="${bids}" var="bid">

                    <c:if test="${bid.id_user == user.user_id}">
                        <tbody>
                            <tr>
                                <td>Status: ${bid.status}</ul>
                                <td><c:forEach items="${bid.games}" var="game">
                                        <img src="gamesImg\game${game.id_game}.jpg" alt="game${game.id_game}">
                                    </c:forEach> 
                                </td>

                                <c:if test="${bid.status=='pendente'}">
                            <form action="FrontController" method="POST">

                                <input type="hidden" name="command" value="bid.delete" />
                                <input type="hidden" name="bidToDelete" value="${bid.id_bid}"/>
                                <td> <input type="submit" value="Deletar" /></td>

                            </form>
                            <td><a href="FrontController?post=${bid.id_post}&command=user.auction">Ver leilão</a></td>
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
