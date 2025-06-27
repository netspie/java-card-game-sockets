/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dariuszlusnia.cardgame.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author dariu
 */

public class ServerClient extends Thread {
    Socket socket;
    PrintWriter writer;
    BufferedReader reader;

    public ServerClient(Socket socket) {
        this.socket = socket;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        try {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void close() {
        writer.close();
        try {
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                String wiadomoscString = reader.readLine();
                Message message = Message.deserialize(wiadomoscString);
                switch (message.getMessageType()) {
                    case WIADOMOSC: {
                        for (ServerClient client: CardGameServerThread.clients) {
                            client.getWriter().println(wiadomoscString);
                        }
                        break;
                    }
                    
                    case CLOSE_CONNECTION: {
                        close();
                    }
                    
                    default:
                        break;
                }

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public PrintWriter getWriter() {
        return writer;
    }

    public BufferedReader getReader() {
        return reader;
    }
}
