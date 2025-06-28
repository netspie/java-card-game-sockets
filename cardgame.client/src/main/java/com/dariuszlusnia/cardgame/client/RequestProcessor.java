/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dariuszlusnia.cardgame.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author dariu
 */
public class RequestProcessor {
    
    private final PrintWriter writer;
    private final BufferedReader reader;

    public RequestProcessor(PrintWriter writer, BufferedReader reader) {
        this.writer = writer;
        this.reader = reader;
    }
    
    public void write(MessageType type) {
        var message = String.valueOf(type) + ";" + "_";
        writer.println(message);
        writer.flush();
    }
    
    public void write(MessageType type, String line) {
        var message = String.valueOf(type) + ";" + line;
        writer.println(message);
        writer.flush();
    }
    
    public String read() {
        try {
            return reader.readLine();
        } catch (IOException ex) {
            return "";
        }
    }
    
     public String readData() {
        var string = read();
        if (!string.contains("#"))
            return "";
        
        var data = string.split("#");
        var key = data[0];
        var value = data[1];
        
        if (value.equals("_"))
            return "";
        
        return data[1];
    }
    
    public boolean isSuccess() {
        try {
            var line = this.reader.readLine();
            return isSuccess(line);
        } catch (Exception ex) {
            return false;
        }
    }
    
    private static boolean isSuccess(String response) {
        int code = Integer.parseInt(response);
        return code == 1;
    }
}
