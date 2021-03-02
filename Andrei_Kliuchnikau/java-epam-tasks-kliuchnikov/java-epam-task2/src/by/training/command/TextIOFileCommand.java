package by.training.command;

import by.training.domain.Sentence;
import by.training.domain.Text;
import by.training.model.LookService;
import by.training.model.io.FileIO;

import java.util.ArrayList;
import java.util.List;

public class TextIOFileCommand {
    private FileIO fileIO;

    public Text read(String relativePath) {
        System.out.println("Попытка загрузки текста по пути - " + relativePath);
        fileIO = new FileIO();
        Text text = fileIO.read(relativePath);
        System.out.println("Текст успешно загружен");
        return text;
    }

    public void writeDuplicate(String relativePath, Text text) {
        LookService lookService = new LookService();
        List<Sentence> sentences = lookService.DuplicateWordsSentence(text);
        fileIO = new FileIO();
        System.out.println("Попытка записи предложений максимального колличества предложений текста с повторяющимися словами - "
        + relativePath);
        fileIO.write(relativePath, sentences, true);
        System.out.println("Текст успешно записан!");
    }

    public void writeParsedText(String relativePath, Text text) {
        List<Sentence> sentences = (ArrayList)text.getComponentsList();
        fileIO = new FileIO();
        System.out.println("Попытка записи текста, обработанного по языковым единицам в файл - "
                + relativePath);
        fileIO.write(relativePath, sentences, false);
        System.out.println("Текст успешно записан!");
    }
}