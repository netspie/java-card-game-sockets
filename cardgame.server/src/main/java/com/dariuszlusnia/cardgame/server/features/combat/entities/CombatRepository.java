package com.dariuszlusnia.cardgame.server.features.combat.entities;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CombatRepository {
    private final Map<String, Combat> map = new HashMap<>();

    public Optional<Combat> getById(String combatId) {
        if (!this.map.containsKey(combatId))
            return Optional.empty();

        return Optional.of(map.get(combatId));
    }

    public Optional<Combat> getFree() {
        return this.map.values()
                .stream()
                .filter(x -> x.players.size() < Combat.MaxPlayerCount)
                .findFirst();
    }

    public boolean add(Combat combat) {
        if (this.map.containsKey(combat.Id) || combat.Id.isBlank())
            return false;

        this.map.put(combat.Id, combat);

        return true;
    }

    public boolean update(Combat combat) {
        if (!this.map.containsKey(combat.Id))
            return false;

        this.map.replace(combat.Id, combat);

        return true;
    }

    public boolean delete(String combatId) {
        if (!this.map.containsKey(combatId))
            return false;

        this.map.remove(combatId);

        return true;
    }

    public Optional<Combat> deleteWherePlayerId(String playerId) {
        var combatToDelete = this.map.values()
            .stream()
            .filter(x -> x.players.stream().anyMatch(p -> p.Id.equals(playerId)))
            .findFirst();

        if (combatToDelete.isEmpty())
            return Optional.empty();

        this.map.remove(combatToDelete.get().Id);

        return combatToDelete;
    }
}