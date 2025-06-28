/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dariuszlusnia.cardgame.server.common;

import java.util.Map;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 *
 * @author dariu
 */
public class CollectionFunctions {
    public static Map<String, String> parseToMap(String str) {
        return Arrays.stream(str.split("&"))
            .map(s -> s.split("=", 2))
            .collect(Collectors.toMap(
                arr -> arr[0],
                arr -> arr.length > 1 ? arr[1] : ""));
    }
}
