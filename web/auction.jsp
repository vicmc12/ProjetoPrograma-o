<%-- 
    Document   : auction
    Created on : Nov 17, 2015, 3:06:23 PM
    Author     : Vicmc12
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/background.css">
        <link rel="stylesheet" type="text/css" href="css/title.css">
        <link rel="stylesheet" type="text/css" href="css/login.css">
        <link rel="stylesheet" type="text/css" href="css/table.css">
        <link rel="stylesheet" type="text/css" href="css/newTable.css">
        <link rel="stylesheet" type="text/css" href="css/submit.css">





        <title>Leilão</title>
    </head>
    <body style="background-image:url(css/backproj.jpg)">
        <c:if test="${post == null}">
            <c:redirect url="FrontController?command=post.fail"></c:redirect>
        </c:if>
        <c:if test="${bids == null}">
            <c:redirect url="FrontController?command=bid.read"></c:redirect>
        </c:if>
        <c:if test="${allGamesBid == null}">
            <c:redirect url="FrontController?command=bid.games"></c:redirect>
        </c:if>
        <c:if test="${gamesIDBid == null}">
            <c:redirect url="FrontController?command=bid.gamesID"></c:redirect>
        </c:if>
        <c:if test="${allUsers == null}">
            <c:redirect url="FrontController?command=user.auction.read"></c:redirect>
        </c:if>
        <c:if test="${user == null}">
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
            <div class="form">
                <form action="FrontController" method="POST">
                    <div class="text-input">
                        <label for="username">Username</label>
                        <input type="text" name="username" id="username"  required=""/>              
                        <span class="separator"> </span>
                    </div>
                    <div class="text-input">
                        <label for="password">Password</label>
                        <input type="password" name="password" id="password"  required=""/>
                        <span class="separator"> </span>
                    </div>
                    <div class="form-bottom">
                        <input type="submit" id="submit" value="LOGIN"/></p>
                        <a href="sign.jsp" class="original-src">SIGN-IN</a>
                        <input type="hidden" name="command" value="user.auction.login"/>           
                    </div>
            </div>
        </form>
    </c:if>
    <c:if test="${post.status=='terminado'}">
        <div class="container">
        <h1>Este leitão ja terminou</h1>
        </div>
    </c:if>
    <c:if test="${post.status!='terminado'}">


        <c:if test="${user != null && post.user_id!=user.user_id}">
            <form action="FrontController" method="POST">
                <input type="hidden" name="command" value="bid.addbid" />

                <div class="container">
                    <h1>Novo lance</h1>
                </div>


                <table>

                    
                        <td>Selecione os jogos</td>
                        <c:forEach var="i" begin="0" end="${(allGamesBid.size())-1}">
                            <c:if test="${i % 10 == 0}">
                            </c:if>
                            <c:if test="${gamesIDBid.get(i)==2}">
                                <td><a href="FrontController?gameBid=${i}&command=bid.select"><img id="game${i}" alt="game" style="border-style: dashed" src="gamesImg\game${allGamesBid.get(i).id_game}.jpg"/></a></td>
                                    </c:if>
                                    <c:if test="${gamesIDBid.get(i)==1}">
                                <td><a href="FrontController?gameBid=${i}&command=bid.select"><img id="game${i}" alt="game" src="gamesImg\game${allGamesBid.get(i).id_game}.jpg"/></a></td>
                                    </c:if>

                        </c:forEach>
                           
                                
                    
                </table>
              <input type="submit" class="botao" value="Adicionar" />       
            </form>
            
        </c:if>

        <div class="container">
            <h1>Informações do post</h1>
        </div>

        <table>
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Nome do usuário</th>
                    <th>Descrição</th>
                    <th> </th>
                    <th>Jogo(s)</th>
                </tr>
            </thead>
            <tbody>
                <tr>

                    <td>${post.id_post}</td>
                    <c:forEach items="${allUsers}" var="user">
                        <c:if test="${user.user_id == post.user_id}">
                    <td>${user.username}</td>
                        </c:if>
                    </c:forEach>
                    <td>${post.description}</td>
                    <td>
                        <c:forEach items="${post.games}" var="game">
                            <img src="gamesImg\game${game.id_game}.jpg" alt="game${game.id_game}">
                        </c:forEach>
                    </td>
                </tr>
            </tbody>
        </table>
        <div class="container">

            <h1>Lista de lance</h1>
        </div>

        <table>

            <c:forEach items="${bids}" var="bid">

                <c:if test="${bid.id_post == post.id_post}">
                    <tbody>
                        <tr> 

                            <td>Id: ${bid.id_bid}</td>
                            <c:forEach items="${allUsers}" var="user">
                                <c:if test="${user.user_id == bid.id_user}">
                            <td>Nome do usuário: ${user.username}</td>
                            
                                </c:if>
                            </c:forEach>
                            <td>Jogos: <c:forEach items="${bid.games}" var="game">
                                <td><img src="gamesImg\game${game.id_game}.jpg" alt="game${game.id_game}"></td>
                                </c:forEach> 


                            <c:if test="${post.user_id==user.user_id}">
                        <form action="FrontController" method="POST">

                            <td><input type="hidden" name="command" value="bid.accept" /></td>
                            <td><input type="hidden" name="bidAccepted" value="${bid.id_bid}"/></td>
                            <td> <input type="submit" value="Aceitar lance" /></td>

                        </form>
                        
                    </c:if>
                        
                </c:if>  

            </c:forEach>
        </c:if>
    </tbody>
</table>

            <form action="index.jsp">
                <input type="submit" class ="botao" value="Voltar">
            </form>
</body>
</html>
