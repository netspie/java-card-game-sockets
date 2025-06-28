/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.dariuszlusnia.cardgame.server;

import com.dariuszlusnia.cardgame.server.features.cards.CardRepository;
import com.dariuszlusnia.cardgame.server.features.combat.entities.CombatRepository;

import java.io.IOException;

/**
 *
 * @author dariu
 */
public class CardGameServer {
    public static void main(String[] args) {
        var combatRepository = new CombatRepository();
        var cardRepository = new CardRepository("data/cards");

        try {
            var server = new CardGameServerThread(combatRepository, cardRepository);
            server.start();
        } catch (IOException ex) {
            System.out.println("Could not start the server");
        }
    }
}