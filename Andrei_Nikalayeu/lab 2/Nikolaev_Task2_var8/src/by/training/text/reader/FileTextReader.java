package by.training.text.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileTextReader implements Reader {
    private String path;

    public FileTextReader(String path) {
        this.path = path;
    }

    @Override
    public String read() throws IOException{
        StringBuilder stringBuilder = new StringBuilder();

        BufferedReader reader = new BufferedReader(new FileReader(new File(path)));

        String line = reader.readLine();

        while (line != null) {
            stringBuilder.append(line);
            line = reader.readLine();
        }

        reader.close();
        return stringBuilder.toString();
    }
}
