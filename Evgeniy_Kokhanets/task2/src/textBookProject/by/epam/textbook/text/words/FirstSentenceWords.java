package textBookProject.by.epam.textbook.text.words;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import textBookProject.by.epam.textbook.text.Text;

public class FirstSentenceWords implements Words{
    private List<String> firstSentenceWords;
    
    public FirstSentenceWords() {
        this.firstSentenceWords = new ArrayList<String>();
        
    }
    
    @Override
    public void addText(Text text) {
        String s = text.getText();
        Matcher m = Pattern.compile("(^.+?(\\.|\\?|\\!)(?=(\\p{Z})|(\")))").matcher(s); //Gets first sentence from the text
        while(m.find()) {
            s = m.group();
        }
        s = s.replaceAll("[\\p{P}*](?=(\\p{Z})|(\\p{Pi})|($))", ""); //Removes all the punctuation characters
        s = s.replaceAll("[\\p{C}*]", ""); //Removes other unused positions
        s = s.replaceAll("\"", ""); //Removes all the quote characters
        
        String[] arrStr = s.split("\s");
        
        firstSentenceWords.addAll(Arrays.asList(arrStr));
        for(int i = 0; i < firstSentenceWords.size(); i++) {
            firstSentenceWords.set(i, firstSentenceWords.get(i).toLowerCase());
        }
        
        Iterator<String> iter = firstSentenceWords.iterator();
        while(iter.hasNext()) {
            String iterStr = iter.next();
            if(iterStr.isEmpty()) {
                iter.remove();
            }
        }
    }
    
    public int getSize() {
        return firstSentenceWords.size();
    }
    
    public List<String> getFirstSentenceWords() {
        return this.firstSentenceWords;
    }
    
    public String getWordByIndex(int index) {
        return firstSentenceWords.get(index);
    }
    
    public List<String> getUniqueWords(OtherWords otherWords) {
        List<String> uniqueWords = new ArrayList<>();
        for(String s : firstSentenceWords) {
            if(!otherWords.getOtherWords().contains(s)) {
                    uniqueWords.add(s);
            }
        }
        return uniqueWords;
    }
}
