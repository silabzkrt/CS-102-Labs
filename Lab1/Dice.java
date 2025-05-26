package labs102.Lab1;
import java.util.*;

public class Dice {
    private String name;
    public Dice(String aName){
        this.name = aName;
    }

    Random random = new Random();

    public int rollDice () {
        Random random = new Random();
        int number = random.nextInt(1, 6);
        return number;
    }
}
