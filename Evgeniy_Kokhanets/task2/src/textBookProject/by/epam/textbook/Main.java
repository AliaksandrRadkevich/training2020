package textBookProject.by.epam.textbook;

import java.io.*;
import textBookProject.by.epam.textbook.exceptions.WrongFileFormatException;
import textBookProject.by.epam.textbook.text.Text;
import textBookProject.by.epam.textbook.text.words.FirstSentenceWords;
import textBookProject.by.epam.textbook.text.words.OtherWords;

public class Main{
    public static void main(String[] args) {
        Text txt = new Text();
        try {
            txt.addFile(".\\src\\textBookProject\\by\\epam\\textbook\\sampleText.txt");
        }
        catch(WrongFileFormatException wffe){
            wffe.printStackTrace();
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
        System.out.println("The uploaded text printed:");
        
        //prints each sentence of the text from the new line
        System.out.println(txt.getText().replaceAll("(?<=(\\.)|(\\!)|(\\?))\\s", "\n"));
        
        FirstSentenceWords fsw = new FirstSentenceWords();
        fsw.addText(txt);
        System.out.println("\nThe words from the first sentence:");
        fsw.getFirstSentenceWords().forEach(word -> {
            System.out.println(word);
        });
        
        OtherWords ow = new OtherWords();
        ow.addText(txt);
        System.out.println("\nThe words from the other text:");
        ow.getOtherWords().forEach(word -> {
            System.out.println(word);
        });
        
        System.out.println("\nUnique words from the first sentence:");
        System.out.println(fsw.getUniqueWords(ow));
    }
}