<%-- 
    Document   : newPost
    Created on : Nov 17, 2015, 9:48:09 PM
    Author     : Vicmc12
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/login.css">
        <link rel="stylesheet" type="text/css" href="css/newTable.css">
        <link rel="stylesheet" type="text/css" href="css/title.css">
        <link rel="stylesheet" type="text/css" href="css/textbox.css">
        <link rel="stylesheet" type="text/css" href="css/background.css">
        <link rel="stylesheet" type="text/css" href="css/table.css">
        <link rel="stylesheet" type="text/css" href="css/submit.css">
        <link rel="stylesheet" type="text/css" href="css/menu.css">


        <title>New Post</title>
    </head>
    <body style="background-image:url(css/backproj.jpg)">
        <c:if test="${user == null}">
            <div class="form">
                <form action="FrontController" method="POST">
                    <div class="text-input">
                        <label for="username">Username</label>
                        <input type="text" name="username" id="username"  required=""/>
                        <span class="separator"> </span>
                    </div>
                    <div class="text-input">
                        <label for="password">Password</label>
                        <input type="password" name="password" id="password" required=""/>
                        <span class="separator"> </span>
                    </div>
                    <div class="form-bottom">
                        <input type="submit" id="submit" value="LOGIN"/>
                        <input type="button" value="SIGN-IN"  onclick="location.href = 'sign.jsp'"/>
                        <input type="hidden" name="command" value="user.newpost.login"/>           
                    </div>
            </div>
        </form>
        <h1>Faça login para poder criar um post</h1>
    </c:if>
    <c:if test="${allGames == null}">
        <c:redirect url="FrontController?command=post.games"></c:redirect>
    </c:if>
    <c:if test="${gamesID == null}">
        <c:redirect url="FrontController?command=post.gamesID"></c:redirect>
    </c:if>
    <c:if test="${user != null}">

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
        <form action="FrontController" method="POST">
            <input type="hidden" name="command" value="post.addpost" />

            <div class="container">
                <h1>ADICIONAR POST: ${user.username}</h1>
            </div>

            <table>

                
                <tr>
                    <td>Escolha o(s) jogo(s).</td>
                    <c:forEach var="i" begin="0" end="${(allGames.size())-1}">
                        <c:if test="${i % 10 == 0}">
                        <br>
                    </c:if>
                    <c:if test="${gamesID.get(i)==2}">
                        <td><a href="FrontController?game=${i}&command=post.select"><img id="game${i}" alt="game" style="border-style: dashed" src="gamesImg\game${allGames.get(i).id_game}.jpg"/></a></td>
                            </c:if>
                            <c:if test="${gamesID.get(i)==1}">
                        <td><a href="FrontController?game=${i}&command=post.select"><img id="game${i}" alt="game" src="gamesImg\game${allGames.get(i).id_game}.jpg"/></a></td>
                            </c:if>

                </c:forEach>
                <td> <input type="submit" id="login-button" value="Adicionar" />

                </td>
                </tr> 
                <td>Descrição: <input type="text" name="description" id="resizer" placeholder="descrição" required="" /> </td>
        </form>   
    </c:if>
</table>
<p class="footer3"> © All rights reserved.  </p>
</body>
</html>
