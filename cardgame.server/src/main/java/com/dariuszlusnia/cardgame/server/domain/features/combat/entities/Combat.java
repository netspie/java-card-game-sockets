/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dariuszlusnia.cardgame.server.domain.features.combat.entities;

import com.dariuszlusnia.cardgame.server.combat.events.CombatEvent;
import com.dariuszlusnia.cardgame.server.combat.events.FirstPlayerDecidedEvent;
import com.dariuszlusnia.cardgame.server.combat.events.PlayerAttackedEvent;
import com.dariuszlusnia.cardgame.server.combat.events.PlayerJoinedEvent;

import java.util.*;

/**
 *
 * @author dariu
 */
public class Combat {
    public final int MaxPlayerCount = 2;

    public final List<Player> players = new ArrayList<>();
    public Optional<String> nextTurnPlayerId = Optional.empty();
    
    public Combat() {}
    
    public List<CombatEvent> AddPlayer(Player player) {
        if (this.players.size() >= MaxPlayerCount)
            return Collections.emptyList();
        
        this.players.add(player);

        return List.of(new PlayerJoinedEvent(player.Id));
    }

    public List<CombatEvent> DrawStartingPlayer() {
        var player1 = this.players.getFirst();
        var player2 = this.players.get(1);

        var player1CardsSpeedSum = player1.getCardsSpeedSum();
        var player2CardsSpeedSum = player2.getCardsSpeedSum();

        if (player1CardsSpeedSum > player2CardsSpeedSum)
            return List.of(new FirstPlayerDecidedEvent(player1.Id));

        if (player1CardsSpeedSum < player2CardsSpeedSum)
            return List.of(new FirstPlayerDecidedEvent(player2.Id));

        var random = new Random();
        int index = random.nextInt(MaxPlayerCount);
        return List.of(new FirstPlayerDecidedEvent(this.players.get(index).Id));
    }

    public List<CombatEvent> Attack(
        String attackingPlayerId,
        String attackingCardName) {

        if (nextTurnPlayerId.isEmpty() || !attackingPlayerId.equals(nextTurnPlayerId.get()))
            return Collections.emptyList();

        var attackingPlayer = getPlayerOfId(attackingPlayerId);
        var defendingPlayer = getPlayerNotOfId(attackingPlayerId);

        if (attackingPlayer.isEmpty() || defendingPlayer.isEmpty())
            return Collections.emptyList();

        return attackingPlayer.get().Attack(attackingCardName, defendingPlayer.get());
    }
    
    private Optional<Player> getPlayerOfId(String id) {
        return this.players.stream()
            .filter(x -> x.Id.equals(id))
            .findFirst();
    }
    
    private Optional<Player> getPlayerNotOfId(String id) {
        return this.players.stream()
            .filter(x -> !x.Id.equals(id))
            .findFirst();
    }
}
