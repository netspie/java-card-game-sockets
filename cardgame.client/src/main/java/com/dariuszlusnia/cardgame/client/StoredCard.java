/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dariuszlusnia.cardgame.client;

import com.dariuszlusnia.cardgame.client.common.CollectionFunctions;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

/**
 *
 * @author dariu
 */

public record StoredCard(
    String Id,
    String Name,
    int Attack,
    int Speed,
    int Health) {

    public static StoredCard fromString(String str) {
        var map = CollectionFunctions.parseToMap(str);
        
        return new StoredCard(
            map.get("id"), 
            map.get("name"),
            Integer.parseInt(map.get("attack")),
            Integer.parseInt(map.get("speed")),
            Integer.parseInt(map.get("health")));
    }
    
    public static List<StoredCard> arrayFromString(String str) {
        if (str.isEmpty())
            return Collections.emptyList();
        
        if (str.contains(";"))
            return Arrays.stream(str.split(";")).map(StoredCard::fromString).toList();
        
        return List.of(fromString(str));
    }
    
    public String toString() {
        return new StringJoiner("&")
                .add("id=" + Id)
                .add("name=" + Name)
                .add("attack=" + Attack)
                .add("speed=" + Speed)
                .add("health=" + Health)
                .toString();
    }
}