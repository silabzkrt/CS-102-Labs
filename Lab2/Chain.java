import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Chain {
    //preapre all the words with their possible chains lists and how many chains they can form use filtered words list
    public boolean checkInChain(Word word, ArrayList<Word> chain){
        for (Word chainWord : chain){
            if (word.getName().equals(chainWord.getName())){
                return false;
            }
        }
        return true;
    }
    public boolean checkInDoc (String string) throws FileNotFoundException, IOException{
        Scanner sc = new Scanner(new File("C:\\Users\\Sıla\\Documents\\GitHub\\labs102\\Lab2\\words.txt"));
        while (sc.hasNextLine()){
            if(sc.nextLine().equals(string.trim())){
                return true;
            }
        }
        return false;
    }
    public ArrayList <Word> chain(int chainLength) throws FileNotFoundException, IOException {
        File file = new File("C:\\Users\\Sıla\\Documents\\GitHub\\labs102\\Lab2\\words.txt");
        ArrayList<Word> chain = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        System.out.print("Enter a word: ");
        if(!checkInDoc(sc.nextLine())){
            System.out.println("This word does not exist!");
            System.exit(0);
        }
        Word inputWord = new Word(sc.nextLine().trim().toUpperCase());
        inputWord.makePossibleChains(file);
        chain.add(inputWord);
        Word prevWord = inputWord;
        Word nextWord = inputWord;

        while (chain.size()< chainLength){
            prevWord = nextWord;
            while (prevWord == nextWord){
                prevWord.makePossibleChains(file);
                if(prevWord.getChainNumber() == 0 || prevWord.getChainNumber() == 1){
                    prevWord = this.getRandomWord();
                    prevWord.makePossibleChains(file);
                }
                nextWord = prevWord.getPossibleChains().get(random.nextInt(0,prevWord.getPossibleChains().size()));
                if (!nextWord.inChain() && checkInChain(nextWord,chain)){
                    chain.add(nextWord);
                    nextWord.setInChain(true);
                }
                else{
                    nextWord = prevWord;
                }

            }
        }
        sc.close();
        return chain;
    }

    public Word getRandomWord() throws FileNotFoundException, IOException {
        Random random = new Random();
        File file = new File("C:\\Users\\Sıla\\Documents\\GitHub\\labs102\\Lab2\\words.txt");
        Scanner sc = new Scanner(file);
        String randomLine = null;
        int lineNumber = 0;

        while (sc.hasNextLine()) {
            lineNumber++;
            String currentLine = sc.nextLine();
            
            if (random.nextInt(lineNumber) == 0) { // 1/lineNumber chance
                randomLine = currentLine;
            }
        }

        if (randomLine != null) {
            Word randomWord = new Word(randomLine.trim().toUpperCase());
            return randomWord;
        } 
        else {
            while (randomLine == null) {
                int randomLineNumber = random.nextInt(lineNumber);
                sc = new Scanner(file);
                for (int i = 0; i <= randomLineNumber; i++) {
                    randomLine = sc.nextLine();
                }
                Word randomWord = new Word(randomLine.trim().toUpperCase());
                randomWord.setInChain(true);
                return randomWord;
            }
        }
        sc.close();
        return null;
    }

    public ArrayList <Word> randomChain(int chainLength) throws FileNotFoundException, IOException {
        File file = new File("C:\\Users\\Sıla\\Documents\\GitHub\\labs102\\Lab2\\words.txt");
        ArrayList<Word> chain = new ArrayList<>();
        Random random = new Random();
        Word inputWord = this.getRandomWord();
        inputWord.makePossibleChains(file);
        chain.add(inputWord);
        Word prevWord = inputWord;
        Word nextWord = inputWord;

        while (chain.size()< chainLength){
            prevWord = nextWord;
            while (prevWord == nextWord){
                prevWord.makePossibleChains(file);
                prevWord.setInChain(true);
                if(prevWord.getChainNumber() == 0 || prevWord.getChainNumber() == 1){
                    System.out.println("No possible chain found. Exiting...");
                    System.exit(0);
                }
                nextWord = prevWord.getPossibleChains().get(random.nextInt(0,prevWord.getPossibleChains().size()));
                if (!nextWord.inChain() && checkInChain(nextWord,chain)){
                    chain.add(nextWord);
                    nextWord.setInChain(true);
                }
                else{
                    nextWord = prevWord;
                }

            }
        }
        return chain;
    }
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Chain newChain = new Chain();
        ArrayList<Word> chain = newChain.chain(10);
        for(Word word : chain){
            System.out.print(word.toString());
            if (word != chain.get(chain.size()-1)){
                System.out.print(" -> ");
            }
        }
    }
}
