/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.dariuszlusnia.cardgame.server.requests;

import com.dariuszlusnia.cardgame.server.features.cards.CardRepository;
import com.dariuszlusnia.cardgame.server.features.combat.entities.CombatRepository;
import com.dariuszlusnia.cardgame.server.features.combat.useCases.CombatEventsPublisher;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author dariu
 */
public class CardGameServerThread extends Thread {
    private final CombatRepository combatRepository;
    private final CardRepository cardRepository;

    private ServerSocket serverSocket;
    private final Map<String, ServerClient> clients = new HashMap<>();
    private final CombatEventsPublisher combatEventsPublisher;

    public CardGameServerThread(CombatRepository combatRepository, CardRepository cardRepository) throws IOException {
        this.combatRepository = combatRepository;
        this.cardRepository = cardRepository;
        this.serverSocket = new ServerSocket(Configuration.PORT);
        this.combatEventsPublisher = new CombatEventsPublisher(this.clients);
    }

    @Override
    public void run() {
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                ServerClient client = new ServerClient(
                    socket, this.combatRepository, this.cardRepository, this.clients, this.combatEventsPublisher);
                client.start();

                String clientId = socket.getInetAddress().getHostAddress() + ":" + socket.getPort();
                clients.put(clientId, client);
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
