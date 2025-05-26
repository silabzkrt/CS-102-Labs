
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;

public class Word {  
    private String name;
    private boolean inChain;
    private ArrayList<Word> possibleChains;
    private boolean canChain;
    private int chainNumber;

    public Word(String text) {
        this.name = text.toUpperCase();
        this.inChain = false;
        this.possibleChains = new ArrayList<Word>();
        this.canChain = false;
        this.chainNumber = 0;
    }

    public void addToPossibleChain(Word chainWord) {
        this.possibleChains.add(chainWord);
        this.chainNumber++;
    }
    public String getName() {
        return this.name;
    }
    public ArrayList <Word> getPossibleChains() throws FileNotFoundException, IOException {
        return this.possibleChains;
    }

    public int getChainNumber() {
        return this.chainNumber;
    }

    public void setInChain(boolean inChain) {
        this.inChain = inChain;
    }
    public boolean inChain(){
        return this.inChain;
    }

    boolean canChain(Word otherWord){
        int thisLength = this.getName().length();
        int otherWordLength = otherWord.getName().length();
        if (Math.abs(thisLength - otherWordLength) > 1) {
            return false;
        }

        int difference = 0; 
        int i = 0, j = 0;

        while (i < thisLength && j < otherWordLength) {
            if (this.getName().charAt(i) != otherWord.getName().charAt(j)) {
                difference++;
                if (difference > 1) {
                    return false; // More than 1 difference
                }
                
                // If one word is longer, try skipping a character
                if (thisLength > otherWordLength) {
                    i++;
                } else if (thisLength < otherWordLength) {
                    j++;
                } else {
                    // If equal length, move both pointers
                    i++;j++;
                }
            } else {
                i++;
                j++;
            }
        }

        // If there's one remaining letter in the longer word, count it as a difference
        if (i < thisLength || j < otherWordLength) {
            difference++;
            
        }
        this.addToPossibleChain(otherWord);
        return difference == 1; // Only 1 difference allowed
    }

    public void makeChainable(){
        this.canChain = true;
    }

    public void makePossibleChains(File file) throws FileNotFoundException, IOException {
        file = new File("C:\\Users\\Sıla\\Documents\\GitHub\\labs102\\Lab2\\words.txt");
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()){
            Word word = new Word(sc.nextLine().toUpperCase());
            if(this.canChain(word)){
                this.addToPossibleChain(word);
            }
        }
        sc.close();
    }

    public String toString(){
        return this.name;
    }

    
}
