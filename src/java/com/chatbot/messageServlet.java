/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chatbot;

import org.alicebot.ab.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author k3-sears
 */
 
/**
 * @author javatutorials.co.in
 */    
public class messageServlet extends HttpServlet {
 
	private static final long serialVersionUID = 1L;
        String botname="alice2";
        String path=getServletContext().getInitParameter("botFolder");
        Bot bot = new Bot(botname, path);
        Chat chatSession = new Chat(bot, false, "Guest");;
        Servlet_Utils SU = new Servlet_Utils();
      
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Set response header
        response.setContentType("text/html;UTF-8");

        // Set response body content. response body is returned as Ajax ResponseText
        PrintWriter writer = response.getWriter();
        String name = request.getParameter("name");

        if (!name.equals(chatSession.customerId) && !name.isEmpty()) { // if not equal to the current chatSession name
            if (SU.checkForChat(name)) { 
                chatSession = SU.getChat(name); // if chat exists, set as current session
            } else { // else create new chat for new user
                chatSession = new Chat(bot, false, name);
                SU.addChat(chatSession);
            }
        }
        else {chatSession = new Chat(bot, false, "Guest");} // user hasnt entered a username

        // process and return response to message
        String message = request.getParameter("message");
        String output = chatSession.multisentenceRespond(message);
        writer.write(chatSession.customerId + ": " + message + "\nBot: " + output);
        writer.close();
    }

}