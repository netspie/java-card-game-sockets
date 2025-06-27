/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dariuszlusnia.cardgame.server;

/**
 *
 * @author dariu
 */
public class Player {
    private final String id;
    
    public Player(String id) {
        this.id = id;
    }
    
    public String getId() {
        return this.id;
    }
}
