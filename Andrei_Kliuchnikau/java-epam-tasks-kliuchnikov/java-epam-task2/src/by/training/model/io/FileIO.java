package by.training.model.io;

import by.training.console.ComplexUnitInfo;
import by.training.domain.Sentence;
import by.training.domain.Text;

import java.io.*;
import java.util.List;

public class FileIO {
    public Text read(String path) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            StringBuilder stringBuilder = new StringBuilder();
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                stringBuilder.append(str).append(System.getProperty("line.separator"));
            }
            return new Text(stringBuilder.toString());
        } catch (IOException e) {
            System.out.println("Во время загрузки текста произошла ошибка!");
            e.printStackTrace();
            return null;
        }
    }

    public void write(String path, List<Sentence> sentences, boolean isDuplicate) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path))) {
            if (isDuplicate) {
                bufferedWriter.write("Предложения текста, в которых есть одинаковые слова (наибольшее колличество): " + System.lineSeparator());
                for (Sentence unit : sentences) {
                    bufferedWriter.write(unit.getUnitString() + System.lineSeparator());
                }
            }
            else {
                bufferedWriter.write("Разбор предложения по языковым единицам: " + System.lineSeparator());
                for (Sentence unit : sentences) {
                    bufferedWriter.write(ComplexUnitInfo.printFullInfo(unit));
                }
            }
        } catch (IOException e) {
            System.out.println("Во время записи текста в файл произошла ошибка!");
            e.printStackTrace();
        }
    }
}