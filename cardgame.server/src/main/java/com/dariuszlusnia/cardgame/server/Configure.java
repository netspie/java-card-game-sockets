/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dariuszlusnia.cardgame.server;

/**
 *
 * @author dariu
 */
public class Configure {

    public static final String IP = "localhost";
    public static final int PORT = 2002;

    public enum MessageType {
        CLOSE_CONNECTION,
        
        SIGN_IN_ADMIN,
        SIGN_IN_PLAYER,

        GET_CARDS,
        ADD_CARD,
        UPDATE_CARD,
        REMOVE_CARD,
        
        JOIN_GAME,
        DRAW_CARDS,

        EVENT
    }
}
