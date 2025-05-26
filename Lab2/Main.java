
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void printChoices(){
        System.out.println("1- Generate Chain");
        System.out.println("2- Guessing Game");
        System.out.println("3- Exit");
    }
    public static void main(String[] args) throws FileNotFoundException, IOException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the Game!!!");
        System.out.println("Choose game: ");
        printChoices();
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                Chain newChain = new Chain();
                ArrayList<Word> chain = newChain.chain(10);
                for(Word word : chain){
                    System.out.print(word.toString());
                    if (word != chain.get(chain.size()-1)){
                        System.out.print(" -> ");
                    }
                }
                break;
            case 2: 
                Game game = new Game();
                game.initializeGame();
            case 3: 
                System.exit(0);
        }
    }
}
