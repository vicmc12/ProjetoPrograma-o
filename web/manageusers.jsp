<%-- 
    Document   : manageusers
    Created on : Nov 25, 2015, 11:18:04 AM
    Author     : Vicmc12
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrar usuários</title>
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
            <c:choose>
            <c:when test="${user.permission<2}">
                    <div class="container">
                    <h1>Você não tem permissão suficiente para acessar essa pagina</h1>
                    <form action="index.jsp">
                <input type="submit" class ="botao" value="Voltar">
            </form>
                    </div>
            </c:when>
            <c:otherwise>
        <c:if test="${user != null}">
            <c:if test="${user.permission==2}">
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
        <div class="container">
    <h1>Lista de usuários</h1>
</div>
<table>
    <thead>
        <tr>
            <th>Nome do usuário</th>
            <th>Permissão*</th>
            <th>Mudar permissão</th>
        </tr>
    </thead>


    <c:forEach items="${allUsers}" var="user">
        <c:if test="${post.status!='terminado'}">
            
            <tbody>
                <tr> 
                    <td>${user.username}</td>  
                    <td>${user.permission}</td>
                    <td> <form action="FrontController" method="POST" >
                       <select name="permission">
                            <option value="0">Normal</option>
                            <option value="1">Admin</option>
                            <option value="2">Super Admin</option>
                        </select>
                    <div class="form-bottom">
                        <input type="submit" id="submit" value="MUDAR"/>
                        <input type="hidden" name="userPermission" value="${user.user_id}"/>
                        <input type="hidden" name="command" value="user.permission"/>
                    </div>
                  </form>
                    </td>
                </tr>

            </c:if>
        </c:forEach>
    </tbody>
</table>
        <div class="container">
            <h1>0 = Normal, 1 = Admin, 2 = Super Admin</h1>
        </div>
        <div class="container">
            <h1>As mudanças em você serão efetivadas ao relogar</h1>
        </div>
                </c:if>
            </c:if>
        </c:otherwise>
            </c:choose>
                
               
    </body>
</html>
