package com.dariuszlusnia.cardgame.server.requests;

public enum MessageType {
    CLOSE_CONNECTION,

    SIGN_IN_ADMIN,
    SIGN_IN_PLAYER,

    GET_CARDS,
    ADD_CARD,
    UPDATE_CARD,
    REMOVE_CARD,

    DRAW_CARDS,

    EVENT
}
