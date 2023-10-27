package com.shane.example.core.rpc.message;

import java.util.stream.Collectors;

/**
 * @author: shane
 * @date: 2023-10-20 15:04:09
 * @version: 1.0
 */
public class Encoder {

    public static void main(String[] args) {
        String input = "camelCasing";
        String string = input.chars().mapToObj(x -> !Character.isUpperCase(x) ? String.valueOf((char) x) : " " + (char) x).collect(Collectors.joining()).toString();
        System.out.println(string);

    }
}
