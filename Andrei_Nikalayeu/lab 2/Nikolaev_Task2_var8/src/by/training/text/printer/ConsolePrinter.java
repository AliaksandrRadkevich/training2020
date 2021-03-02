package by.training.text.printer;

public class ConsolePrinter implements Printer{
    @Override
    public void print(String string) {
        System.out.print(string.toString());
    }

    @Override
    public void println(String string) {
        System.out.println(string.toString());
    }
}
