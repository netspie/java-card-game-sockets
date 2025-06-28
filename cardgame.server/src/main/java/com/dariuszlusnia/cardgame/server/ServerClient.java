/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dariuszlusnia.cardgame.server;

import com.dariuszlusnia.cardgame.server.features.cards.CardRepository;
import com.dariuszlusnia.cardgame.server.features.combat.entities.CombatRepository;
import com.dariuszlusnia.cardgame.server.features.combat.events.CombatEvent;
import com.dariuszlusnia.cardgame.server.features.combat.useCases.CombatEventsPublisher;
import com.dariuszlusnia.cardgame.server.features.combat.useCases.JoinRandomCombatCommand;
import com.dariuszlusnia.cardgame.server.features.combat.useCases.JoinRandomCombatCommandHandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author dariu
 */

public class ServerClient extends Thread {
    private final Socket socket;
    private final PrintWriter writer;
    private final BufferedReader reader;

    private final CombatRepository combatRepository;
    private final CardRepository cardRepository;

    private final Map<String, ServerClient> clients;
    private final CombatEventsPublisher combatEventsPublisher;

    public ServerClient(
        Socket socket,
        CombatRepository combatRepository,
        CardRepository cardRepository,
        Map<String, ServerClient> clients,
        CombatEventsPublisher combatEventsPublisher) {
        this.socket = socket;
        this.combatRepository = combatRepository;
        this.cardRepository = cardRepository;
        this.clients = clients;
        this.combatEventsPublisher = combatEventsPublisher;

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
            String clientId = socket.getInetAddress().getHostAddress() + ":" + socket.getPort();
            this.clients.remove(clientId);
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

                    case JOIN_GAME: {
                        var map = deserializeToMap(message);
                        var joinCommand = new JoinRandomCombatCommand(map.get("player-id"));
                        var handler = new JoinRandomCombatCommandHandler(this.combatRepository, this.cardRepository, this.combatEventsPublisher);
                        handler.handle(joinCommand);
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

    private static Map<String, String> deserializeToMap(Message message) {
        var fields = message.getContent().split("&");
        return Arrays.stream(fields)
            .map(x -> x.split("="))
            .collect(Collectors.toMap(
                x -> x[0],
                x -> x[1]
            ));
    }
}
