package textBookProject.by.epam.textbook.text;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import textBookProject.by.epam.textbook.exceptions.WrongFileFormatException;

public class Text {
    private String text;

    public Text() {
    }
    
    public String getText() {
        return text;
    }
    
    public String addFile(String s) throws IOException {
        if(!s.matches("(.+)\\.(txt)$")) {
            throw new WrongFileFormatException();
        };
        FileInputStream fis = new FileInputStream(s);
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
           sb.append(line);
        }
        String str = new String(sb);
        str = str.replaceAll("[\\p{C}]", ""); //removes all the spare newline characters
        str = str.replaceAll("[\s]{1,}", "\s"); //removes all the spare whitespace characters 
        br.close();
        return this.text = str;
    }
}
