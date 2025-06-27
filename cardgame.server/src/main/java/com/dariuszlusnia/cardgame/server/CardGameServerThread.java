/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.dariuszlusnia.cardgame.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dariu
 */
public class CardGameServerThread extends Thread {
    private ServerSocket serverSocket;
    public static List<ServerClient> clients;

    public CardGameServerThread() throws IOException {
        this.clients = new ArrayList<>();
        this.serverSocket = new ServerSocket(Configure.PORT);
    }

    @Override
    public void run() {
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                ServerClient client = new ServerClient(socket);
                client.start();
                clients.add(client);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            try {
                serverSocket.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

    }
}
