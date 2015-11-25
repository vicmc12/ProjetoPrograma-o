/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.projetolp2.controller;

import com.br.projetolp2.model.Bid;
import com.br.projetolp2.model.Game;
import com.br.projetolp2.model.Post;
import com.br.projetolp2.model.User;
import com.br.projetolp2.model.dao.BidDAO;
import com.br.projetolp2.model.dao.GameDAO;
import com.br.projetolp2.model.dao.PostDAO;
import com.br.projetolp2.model.dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 31381243
 */
public class FrontController extends HttpServlet {
    private String command;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String page = "index.jsp";
            int code = 1;
            String msg = "";
            RequestDispatcher rd;
            PostDAO postdao = new PostDAO();
            BidDAO biddao = new BidDAO();
            GameDAO gamedao = new GameDAO();
            UserDAO userdao = new UserDAO();
            //todas as operações de user
            if(command.startsWith("post")){
                if(command.endsWith("read")){
                   
                  
                   List<Post> posts = postdao.read();
                   request.getSession().setAttribute("posts", posts);
                   if(command.contains("myposts")){
                       page = "myposts.jsp";
                   }
                }else if(command.endsWith("addpost")){
                    Post post = new Post();
                    List<Game> allGames = gamedao.read();
                    List<Game> games = new ArrayList<>();
                    User user = (User)request.getSession().getAttribute("user");
                    String description = request.getParameter("description");
                    List<Integer> gamesID =(List<Integer>) request.getSession().getAttribute("gamesID");
                    for (int i = 0; i < gamesID.size(); i++) {
                        if(gamesID.get(i)==2){
                            games.add(allGames.get(i));
                        }
                    }
                    
                    post.setDescription(description);
                    post.setGames(games);
                    post.setUser_id(user.getUser_id());
                    postdao.insert(post);
                    List<Post>posts = postdao.read();
                    request.getSession().setAttribute("posts", posts);
                }else if(command.endsWith("userID")){
                    int userID =  Integer.parseInt(request.getParameter("postUserID"));
                    User user = userdao.readById(userID);
                    request.getSession().setAttribute("postUsername", user.getUsername());
                    page="auction.jsp";
                }
            }
            if(command.startsWith("bid")){
                if(command.endsWith("read")){
                   List<Bid> bids = biddao.read();
                   request.getSession().setAttribute("bids", bids);
                   page = "auction.jsp";
                   if(command.contains("mybids")){
                   page = "mybids.jsp";  
                   }
                   if(command.contains("myposts")){
                   page = "myposts.jsp";  
                   }
                
                }else if(command.endsWith("bids")){
                    page = "mybids.jsp";
                }else if(command.endsWith("delete")){
                    int bidID = Integer.parseInt(request.getParameter("bidToDelete"));
                    biddao.deleteBidById(bidID);
                    request.getSession().setAttribute("bids", biddao.read());
                    page = "mybids.jsp";
                }else if(command.endsWith("games")){
                   List<Game> allGamesBid = gamedao.read();
                   request.getSession().setAttribute("allGamesBid", allGamesBid);
                   page = "auction.jsp";
                }else if(command.endsWith("gamesID")){
                    List<Integer> gamesIDBid = new ArrayList<>();
                    for (int i = 0; i < gamedao.read().size(); i++) {
                        gamesIDBid.add(1);
                    }
                    request.getSession().setAttribute("gamesIDBid", gamesIDBid);
                    page = "auction.jsp";
                }else if(command.endsWith("select")){
                    int game = Integer.parseInt(request.getParameter("gameBid"));
                    List<Integer> games = (List<Integer>)request.getSession().getAttribute("gamesIDBid");
                    if(games.get(game)==1){
                        games.set(game, 2);
                    }else{
                        games.set(game, 1);
                    }
                    request.getSession().setAttribute("gamesIDBid", games);
                    page = "auction.jsp";
                }else if(command.endsWith("addbid")){
                    Bid bid = new Bid();
                    List<Game> allGames = gamedao.read();
                    List<Game> games = new ArrayList<>();
                    User user = (User)request.getSession().getAttribute("user");
                    List<Integer> gamesID =(List<Integer>) request.getSession().getAttribute("gamesIDBid");
                    for (int i = 0; i < gamesID.size(); i++) {
                        if(gamesID.get(i)==2){
                            games.add(allGames.get(i));
                        }
                    }
                    bid.setGames(games);
                    Post post = (Post)request.getSession().getAttribute("post");
                    bid.setId_post(post.getId_post());
                    bid.setId_user(user.getUser_id());
                    biddao.insert(bid);
                    List<Bid>bids = biddao.read();
                    request.getSession().setAttribute("bids", bids);
                    page="auction.jsp";
                }else if(command.endsWith("accept")){
                    List<Bid> bids = biddao.read();
                    Bid bid = new Bid();
                    int bidId = Integer.parseInt(request.getParameter("bidAccepted"));
                    for (int i = 0; i < bids.size(); i++) {
                        if(bids.get(i).getId_bid()==bidId){
                            bid = bids.get(i);
                        }
                    }
                    bid.setStatus("aceito");
                    biddao.update(bid);
                    Post post = (Post) request.getSession().getAttribute("post");
                    post.setStatus("terminado");
                    for (int i = 0; i < bids.size(); i++) {
                        if(bids.get(i).getId_post() == bid.getId_post()){
                            bids.get(i).setStatus("Leilão terminado, outro lance foi aceito");
                            biddao.update(bids.get(i));
                            
                        }
                    }
                    postdao.update(post);
                }
        }
            
            
            if(command.startsWith("user")){
                if(command.endsWith("login")){
                    //LOGIN
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                    code = UserManager.authorize(username, password);
                    request.getSession().setAttribute("user", UserManager.getUser());
                    if(command.contains("auction")){page = "auction.jsp";}else
                    if(command.contains("newpost")){page = "newpost.jsp";}
                }else if(command.endsWith("insert")){ 
                    //INSERT
                    String username2 = request.getParameter("username");
                    String pwd = request.getParameter("password");
                    String pwd2 = request.getParameter("password2");
                    User user = new User();
                    user.setUsername(username2);
                    user.setPassword(pwd);
                    code = UserManager.insert(user,pwd2);
                } else if(command.endsWith("logout")){
                    request.getSession().invalidate(); 
                    page="index.jsp";
                } else if(command.endsWith("read")){
                     
                   List<User> allUsers = userdao.read();
                   request.getSession().setAttribute("allUsers", allUsers);
                   if(command.contains("myposts")){
                       page = "myposts.jsp";
                   }else if(command.contains("auction")){
                       page = "auction.jsp";
                   }
                } else if(command.endsWith("auction")){
                    List<Post> posts = postdao.read();
                    for (Post post: posts) {
                        if(post.getId_post()==Integer.parseInt(request.getParameter("post"))){
                            request.getSession().setAttribute("post", post);
                        }
                    }
                    
                    page = "auction.jsp";
                } else if(command.endsWith("newpost")){
                    page = "newpost.jsp";
                } else if(command.endsWith("permission")){
                    int userID = Integer.parseInt(request.getParameter("userPermission"));
                    int permission = Integer.parseInt(request.getParameter("permission"));
                    User user = userdao.readById(userID);
                    user.setPermission(permission);
                    userdao.update(user);
                    page="manageusers.jsp";
                    request.getSession().setAttribute("allUsers", userdao.read());
                }
                
                
            } 
            if(command.startsWith("post")){
                if(command.endsWith("fail")){
                    code = -6;
                }else if(command.endsWith("games")){
                   List<Game> allGames = gamedao.read();
                   request.getSession().setAttribute("allGames", allGames);
                   page = "newpost.jsp";
                }else if(command.endsWith("myposts")){
                   page = "myposts.jsp";
                }else if(command.endsWith("delete")){
                    int postID = Integer.parseInt(request.getParameter("postToDelete"));
                    postdao.deletePostById(postID);
                    request.getSession().setAttribute("posts", postdao.read());
                    page="myposts.jsp";
                }else if(command.endsWith("gamesID")){
                    List<Integer> gamesID = new ArrayList<>();
                    for (int i = 0; i < gamedao.read().size(); i++) {
                        gamesID.add(1);
                    }
                    request.getSession().setAttribute("gamesID", gamesID);
                    page = "newpost.jsp";
                }else if(command.endsWith("select")){
                    int game = Integer.parseInt(request.getParameter("game"));
                    List<Integer> games = (List<Integer>)request.getSession().getAttribute("gamesID");
                    if(games.get(game)==1){
                        games.set(game, 2);
                    }else{
                        games.set(game, 1);
                    }
                    request.getSession().setAttribute("gamesID", games);
                    page = "newpost.jsp";
                }
            }
                
            if( code == 1){
                    String username = "";
                    String pwd = "";
                    if(request.getParameter("lembrar") != null){
                        username = UserManager.getUser().getUsername();
                        pwd = UserManager.getUser().getPassword();
                    } 
                    Cookie cookie = new Cookie("name", username);
                    cookie.setMaxAge(60*60*24*7);
                    response.addCookie(cookie);
                    Cookie cookie2 = new Cookie("pwd", pwd);
                    cookie2.setMaxAge(60*60*24*7);
                    response.addCookie(cookie2);
            }
            if(code!=1){
                   page = "error.jsp";
                    switch(code){
                        case -1:
                            msg = "User not found!";
                            break;
                        case -2:
                            msg = "Wrong Password!";
                            break;
                        case -3:
                            msg = "User already exist!";
                            break;
                        case -4:
                            msg = "Password doesn't match!";
                            break;
                        case -5:
                            msg = "Error on database. Try again!";
                            break;
                        case -6:
                            msg = "Post not found";
                            break;
                    }
                }
            request.getSession().setAttribute("code", msg);
            response.sendRedirect(page);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        command = request.getParameter("command");
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        command = request.getParameter("command");
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
