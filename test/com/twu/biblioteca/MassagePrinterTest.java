package com.twu.biblioteca;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class MassagePrinterTest {
    @Test
    public void ShouldPrintMessageInBoxFormat() {
        //given
        String[] messages = new String[2];
        messages[0] = "test line 1";
        messages[1] = "test long line 2";
        //when
        String boxMessage = MassagePrinter.printMessageWithBox(messages);
        //then
        assertThat(227, is(boxMessage.length()));
        assertThat("********************************************************" +
                "\n*test line 1                                           *" +
                "\n*test long line 2                                      *" +
                "\n********************************************************", is(boxMessage));
    }

    @Test
    public void ShouldPrintBooksInfoInBoxFormat() {
        //given
        String[] messages = new String[]{"Tittle: Water Margin1","Tittle: Water Margin2"};
        //when
        String boxMessage = MassagePrinter.getBoxFormatMessageForArrayList(messages);
        //then
        assertThat("********************************************************\n" +
                        "*Tittle: Water Margin1                                 *\n" +
                        "*-----                                                 *\n" +
                        "*Tittle: Water Margin2                                 *\n" +
                        "********************************************************\n"
                , is(boxMessage));
    }
}
