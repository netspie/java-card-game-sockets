package com.dariuszlusnia.cardgame.client.ui;

import com.dariuszlusnia.cardgame.client.common.CollectionFunctions;
import com.dariuszlusnia.cardgame.client.ui.events.*;

import java.util.Optional;

public class EventConverter {
    public static <T extends CombatEvent> Optional<T> getEvent(String eventsString, Class<T> clazz) {
        var parts = new String[0];
        if (eventsString.contains(";"))
            parts = eventsString.split(";");
        else if (!eventsString.isEmpty())
            parts = new String[] { eventsString };

        for (var part : parts) {
            var map = CollectionFunctions.parseToMap(part);

            var eventId = map.get("event-id");

            CombatEvent event = null;
            switch (eventId) {
                case CombatCreatedEvent.EventId:
                    event = CombatCreatedEvent.fromString(part);
                    break;

                case PlayerJoinedEvent.EventId:
                    event = PlayerJoinedEvent.fromString(part);
                    break;

                case PlayerDisconnectedEvent.EventId:
                    event = PlayerDisconnectedEvent.fromString(part);
                    break;

                case FirstPlayerDecidedEvent.EventId:
                    event = FirstPlayerDecidedEvent.fromString(part);
                    break;

                case PlayerAttackedEvent.EventId:
                    event = PlayerAttackedEvent.fromString(part);
                    break;

            }

            if (clazz.isInstance(event)) {
                return Optional.of(clazz.cast(event));
            }
        }

        return Optional.empty();
    }
}
