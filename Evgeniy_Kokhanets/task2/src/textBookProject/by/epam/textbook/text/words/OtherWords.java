package textBookProject.by.epam.textbook.text.words;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import textBookProject.by.epam.textbook.text.Text;

public class OtherWords implements Words{
    private List<String> otherWords;
    
    public OtherWords() {
        this.otherWords = new ArrayList<String>();
    }
    
    public List<String> getOtherWords() {
        return this.otherWords;
    }
    
    public int getSize() {
        return otherWords.size();
    }
    
    public String getWordByIndex(int index) {
        return otherWords.get(index);
    }
    
    @Override
    public void addText(Text text) {
        String s = text.getText();
        s = s.replaceAll("(?:(^.+?(\\.|\\?|\\!)(?=(\\p{Z})|(\"))))", ""); //removes the first sentence
        s = s.replaceAll("[\\p{P}*](?=(\\p{Z})|($))", ""); //removes all the punctuation characters
        s = s.replaceAll("[\\p{C}\"*]", ""); //removes other unused positions
        
        String[] arrStr = s.split("\s");
        otherWords.addAll(Arrays.asList(arrStr));
        for(int i = 0; i < otherWords.size(); i++) {
            otherWords.set(i, otherWords.get(i).toLowerCase());
        }
        
        Iterator<String> iter = otherWords.iterator();
        while(iter.hasNext()) {
            String iterStr = iter.next();
            if(iterStr.isEmpty()) {
                iter.remove();
            }
        }
    }
}