/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.dariuszlusnia.cardgame.server;

import java.io.IOException;

/**
 *
 * @author dariu
 */
public class CardGameServer {
    public static void main(String[] args) {
        try {
            var server = new CardGameServerThread();
            server.start();
        } catch (IOException ex) {
            System.out.println("Could not start the server");
        }
    }
}