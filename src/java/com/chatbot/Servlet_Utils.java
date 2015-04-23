/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chatbot;

import java.util.HashMap;
import java.util.Map;
import org.alicebot.ab.Chat;


public class Servlet_Utils {
    
    
    Map<String, Chat> chatStore = new HashMap<String, Chat>();
    
    
    
    public void addChat(Chat chat){
    chatStore.put(chat.customerId, chat);
    }
    
    public void removeChat(String name){
    chatStore.remove(name);
    }
    
    public Chat getChat(String name){
    Chat chat = chatStore.get(name);
        return chat;
    }
    
    public Boolean checkForChat(String name){
    Boolean isChat = false;
    if (chatStore.containsKey(name)){
    return isChat = true;
    }
        return isChat;
    }
  
    
}
