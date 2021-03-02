package by.epam.jvd.vitebsk.task2.reader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//Создать программу обработки текста учебника по программированию с использованием классов (по необходимости) 
//для представления: символа, слова, предложения, знака препинания и др. Во всех задачах с формированием текста 
//заменять табуляции и последовательности пробелов одним пробелом. Программа должна обрабатывать адреса электронной
//почты и номера телефонов в формате +XXX(XX)XXX-XX-XX как отдельные слова.
//Вариант 2
//Вывести все предложения заданного текста в порядке возрастания количества слов в каждом из них.

public class TextReader {
    private String pathToFile;

    public TextReader(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    public TextReader() {
    }

    public List<String> readReturnList() {
        List<String> textData;
        textData = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(pathToFile)))) {
            String str;
            while ((str = br.readLine()) != null) {
                textData.add(str);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return textData;
    }

    public String readReturnString() {
        String textData, temp;
        textData = "";
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(pathToFile)))) {
            while ((temp = br.readLine()) != null) {
                textData = textData + " " + temp;
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return textData;
    }

    public String getPathToFile() {
        return pathToFile;
    }

    public void setPathToFile(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((pathToFile == null) ? 0 : pathToFile.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TextReader other = (TextReader) obj;
        if (pathToFile == null) {
            if (other.pathToFile != null)
                return false;
        } else if (!pathToFile.equals(other.pathToFile))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TextReader [pathToFile=" + pathToFile + "]";
    }

}
