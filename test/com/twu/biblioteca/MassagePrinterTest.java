package com.twu.biblioteca;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

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

        new ArrayList<Book>(Arrays.asList(new Book("Water Margin","Naian Shi",1999),
                new Book("The Journey to the West","Chengen Wu",1992),
                new Book("A Dream of Red Mansions","Xueqin Cao",1990),
                new Book("The Three Kingdoms Era","Guanzhong Luo",1997)));

        String[] tittles = {"Water Margin","The Journey to the West"};
        String[] authors= {"Naian Shi","Chengen Wu"};
        String[] yearPublisheds= {"1992","1990"};

        String[][] messages = new String[][]{
            {"Tittle","Author","Year Published"},
            {tittles,authors,yearPublisheds}
        };

        HashMap<String,String[]> messages = new HashMap<String,String[]>(){{
            put("Tittle",tittles);
            put("Author",authors);
            put("Year Published",yearPublisheds);
        }};

        //when
        String boxMessage = MassagePrinter.getBoxFormatMessageForArrayList(messages);
        //then
        assertThat("*******************************************************************\n" +
                          "* Tittle                  | Author     | Year Published |         *\n" +
                          "*-----------------------------------------------------------------*\n" +
                          "* Water Margin            | Naian Shi  | 1992           |         *\n" +
                          "*-----------------------------------------------------------------*\n" +
                          "* The Journey to the West | Chengen Wu | 1990           |         *\n" +
                          "*******************************************************************\n"
                , is(boxMessage));
    }
}
