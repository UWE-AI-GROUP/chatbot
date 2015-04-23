/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.alicebot.ab;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.alicebot.ab.utils.IOUtils;

/**
 *
 * @author k3-sears
 */
public class Logs {
    
    
    public ArrayList<String[]> array = new ArrayList<>();
    
    public void Logs(){
    
    
    }
    
    
    public void addPredicate(String predicate, String value, String topic){
    String[] item = new String[3];
    item[0] = predicate;
    item[1] = value;
    item[2] = topic;
    
    array.add(item);
    
        System.out.println("Added : " + item[0]+ "; " + item[1]+ "; " + item[2]+ "; " + " to arrayList in Logs.class");
    }

    public void writeToFile() {
        
        
        try {
            BufferedWriter bw = null;
            String logFile = MagicStrings.root_path + "\\bots\\alice2\\log\\log.txt";
            System.out.println("size of the array = " + array.size());
            bw = new BufferedWriter(new FileWriter(logFile, true));

            while (array.iterator().hasNext()) {
                String[] item = array.iterator().next();
                bw.write(item[0] + ": " + item[1]);
                bw.newLine();
                bw.flush();
            }
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(Logs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
