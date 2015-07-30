/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chatbot;

import org.alicebot.ab.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author k3-sears
 */
/**
 * @author javatutorials.co.in
 */
public class messageServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    String botname;
    String path;
    Bot bot;
    Chat chatSession;
    Servlet_Utils utilities;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        botname = "alice2";
        path = getServletContext().getInitParameter("botFolder");
        bot = new Bot(botname, path);
        utilities = new Servlet_Utils();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Set response header
        response.setContentType("text/html;UTF-8");
        HttpSession session = request.getSession(true);

        // Set response body content. response body is returned as Ajax ResponseText   
        if (session.isNew()) {

            chatSession = new Chat(bot, false, session.getId());
            session.setAttribute(session.getId(), chatSession);
            System.out.println("new chat session created " + session.getId());
        } else {
            chatSession = (Chat) session.getAttribute(session.getId());
        }

        if (chatSession == null) {
            System.out.println("session null " + session.getId());
        }

        // process and return response to message
        String message = request.getParameter("message");
        String output = chatSession.multisentenceRespond(message);
        PrintWriter writer = response.getWriter();
        writer.write(chatSession.customerId + ": " + message + "\nBot: " + output);
    }

}
