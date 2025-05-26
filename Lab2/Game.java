import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
public class Game {
    private boolean finishGame;
    private int roundCount;
    private boolean finishRound;
    private ArrayList<Word> chain;
    public Game() throws FileNotFoundException, IOException {
        this.finishGame = false;
        this.finishRound = false;
        this.roundCount = 1;
        this.chain = new ArrayList<Word>();
    }

    public void GameRound() throws FileNotFoundException, IOException {
        Scanner sc = new Scanner(System.in);
        Chain newChain = new Chain();
        while(!this.finishRound){
            this.RoundInfo();
            this.chain = newChain.randomChain(3);
            int wordCount = 0;
            for(Word word : this.chain){
                wordCount++;
                if (wordCount == 2){
                    System.out.print("-?-");
                }
                else{
                    System.out.print(word.toString());
                }
                if (word != this.chain.get(this.chain.size()-1)){
                    System.out.print(" -> ");
                }
            }
            int tryCount = 0;
            System.out.print("\nEnter the word in the middle: ");
            String inputWord = sc.next().trim().toUpperCase();
            while (tryCount < 3 && !this.finishRound){
                if (inputWord.trim().toUpperCase().equals(this.chain.get(1).getName())){
                    System.out.println("Correct!");
                    this.roundCount++;
                    this.finishRound = true;
                    tryCount = 0;
                }
                else{
                    System.out.println("Wrong! Try again.");
                    inputWord = sc.next().trim().toUpperCase();
                    tryCount++;
                }    
            }
            if (tryCount == 3 && !this.finishRound){
                System.out.println("Wrong! The correct word is: " + this.chain.get(1).getName());
                this.finishRound = true;
                this.finishGame = true;
                System.out.println("Game Over!");
                System.out.println("Score: " + this.roundCount);
                System.out.println("*******************************************************");
            }
        }
        this.finishRound = false;
    }

    public int getRoundCount() {
        return this.roundCount;
    }
    public void RoundInfo() throws FileNotFoundException, IOException {
        System.out.println("*******************************************************");
        System.out.println("Round Info");
        System.out.println("Round: " + this.roundCount);

    }

    public void initializeGame() throws FileNotFoundException, IOException {
        while (!this.finishGame) {
            this.GameRound();
        }
    }
        
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Game game = new Game();
        game.initializeGame();
    }
}
