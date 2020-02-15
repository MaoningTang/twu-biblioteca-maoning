package com.twu.biblioteca;

import java.util.*;
import java.util.stream.Stream;

public class MassagePrinter {

    public static String printMessageWithBox(String[] messages){
        StringBuilder builder = new StringBuilder();
        int boxWidth = getBoxWidth(messages);
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

    public static String getBoxFormatMessageForArrayList(String[][] bookMatrix){
        StringBuilder builder = new StringBuilder();
        int[] columnsWidth = new int[bookMatrix[0].length];
        for(int i = 0; i<columnsWidth.length; i++){
            String[] column = getColumn(i,bookMatrix);
            columnsWidth[i] = getBoxWidth(column);
        }
        int lineWidth = Arrays.stream(columnsWidth).sum() + 2*columnsWidth.length + 2;
        constructBoxBoard(builder, lineWidth,"=");
        builder.append(System.lineSeparator());
        for(String[] messages:bookMatrix) {
            builder.append("|");
            for (int i = 0; i < messages.length; i++) {
                String message = getLineFillingWithMarksToLength(messages[i], columnsWidth[i], " ");
                message = getStringWithMarkOnEachSide(message, "|");
                builder.append(message);
            }
            builder.append("|");
            builder.append(System.lineSeparator());
            constructBoxBoard(builder, lineWidth,"=");
            builder.append(System.lineSeparator());
        }
        return builder.toString();
    }

    private static String getStringWithMarkOnEachSide(String message,String mark){
        StringBuilder builder=new StringBuilder();
        builder.append(mark);
        builder.append(message);
        builder.append(mark);
        return builder.toString();
    }

    private static String getLineFillingWithMarksToLength(String message,int length,String mark){
        StringBuilder builder=new StringBuilder();
        builder.append(mark);
        builder.append(message);
        String toAppend = String.join("", Collections.nCopies(length - message.length() - 1, mark));
        builder.append(toAppend);
        return builder.toString();
    }

    private static int getBoxWidth(String[] messages) {
        int boxWidth = Stream.of(messages).mapToInt(message -> message.length()).max().getAsInt() + 2;
        return boxWidth;
    }

    private static String[] getColumn(int address, String[][] matrix) {
        return Arrays.stream(matrix).map(x -> x[address]).toArray(String[]::new);
    }
}
