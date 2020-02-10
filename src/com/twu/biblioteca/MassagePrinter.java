package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

public class MassagePrinter {

    public static String printMessageWithBox(String[] messages){
        StringBuilder builder = new StringBuilder();
        int boxWidth = Stream.of(messages).mapToInt(message -> message.length()).max().getAsInt() + 2;
        constructBoxBoard(builder, boxWidth,"*");
        for(String message:messages){
            builder.append(System.lineSeparator());
            builder.append("*");
            builder.append(message);
            for (int i = message.length()+1; i < boxWidth-1; i++) {
                builder.append(" ");
            }
            builder.append("*");
        }
        builder.append(System.lineSeparator());
        constructBoxBoard(builder, boxWidth,"*");
        return builder.toString();
    }

    private static void constructBoxBoard(StringBuilder builder, int boxWidth, String s) {
        for (int i = 0; i < boxWidth; i++) {
            builder.append(s);
        }
    }

    public static String getBoxFormatMessageForArrayList(ArrayList<String> arrayList){
        return "Todo";
    }
}
