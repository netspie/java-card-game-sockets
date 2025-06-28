/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dariuszlusnia.cardgame.client.ui;

/**
 *
 * @author dariu
 */
public class CombatFrameThread extends Thread {
    private final CombatFrame frame;

    public CombatFrameThread(CombatFrame frame) {
        this.frame = frame;
    }
    
    @Override
    public void run() {
        while (true) {
            
        }
    }
}
