package by.training;

import by.training.command.TextIOConsoleCommand;
import by.training.command.TextIOFileCommand;
import by.training.console.ComplexUnitInfo;
import by.training.domain.Text;
import by.training.domain.Word;

//test file use encoding windows-1251

public class WordProcessor {
    public static void main(String[] args) {
        TextIOFileCommand textIOFileCommand = new TextIOFileCommand();
        Text textFromFile = textIOFileCommand.read("data/test.txt");
        System.out.println(ComplexUnitInfo.printFullInfo(textFromFile));
        textIOFileCommand.writeParsedText("data/test_parser.txt", textFromFile);
        textIOFileCommand.writeDuplicate("data/test_find_sentences.txt", textFromFile);

         /* TextIOConsoleCommand textIOConsoleCommand = new TextIOConsoleCommand();                 //work with console
        Text textFromConsole = textIOConsoleCommand.read();
        ComplexUnitInfo.printFullInfo(textFromConsole);
        textIOConsoleCommand.writeParsedText(textFromConsole);
        textIOConsoleCommand.writeDuplicate(textFromConsole); */
    }
}