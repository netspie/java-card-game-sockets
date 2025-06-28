package com.dariuszlusnia.cardgame.server.features.combat.useCases;

import com.dariuszlusnia.cardgame.server.Configure;
import com.dariuszlusnia.cardgame.server.ServerClient;
import com.dariuszlusnia.cardgame.server.features.combat.entities.Combat;
import com.dariuszlusnia.cardgame.server.features.combat.events.CombatEvent;

import java.util.List;
import java.util.Map;

public class CombatEventsPublisher {
    private final Map<String, ServerClient> clients;

    public CombatEventsPublisher(Map<String, ServerClient> clients) {
        this.clients = clients;
    }

    public void publish(Combat combat, List<CombatEvent> events) {
        var clients = this.clients.entrySet()
            .stream()
            .filter(kv -> combat.players.stream().anyMatch(x -> x.Id.equals(kv.getKey())))
            .map(Map.Entry::getValue)
            .toList();

        var eventsString = String.valueOf(Configure.MessageType.EVENT) + "#" + CombatEvent.eventsToString(events);
        for (ServerClient client: clients) {
            client.getWriter().println(eventsString);
            client.getWriter().flush();
        }
    }
}