package labs102.Lab1;

public class Player {
    private String name;
    private int coins;
    private int house;
    private boolean isComputer;
    private int currentCellIndex;
    private boolean skip;
    private boolean finishTurn;

    public Player(String aName) {
        this.name = aName;
        this.coins = 10;
        this.house = 0;
        this.isComputer = true;
        this.currentCellIndex = 0;
        this.skip = false;
        this.finishTurn = false;
    }

    public String getName() {
        return this.name;
    }

    public String getInitial() {
        return this.name.substring(0, 1);
    }

    public int getHouse(){
        return this.house;
    }

    public void makeUser(){
        this.isComputer = false;
    }

    public int getBalance(){
        return this.coins;
    }

    public int getCurrentCell(){
        return this.currentCellIndex;
    }

    public boolean Computer(){
        return this.isComputer;
    }

    public boolean checkForCompletition(int index){
        if (this.getCurrentCell() + index >= 16){
            this.coins += 3;
            return true;
        }
        return false;
    }

    public void movePlayer(int index) {
        this.currentCellIndex = (currentCellIndex + index) % 16;
    }
    
    public void loseCoin(int value){
        this.coins -= value;
    }

    public void earnCoin (int value){
        this.coins += value;
    }

    public void skipTurn(){
        this.skip = true;
    }
    public boolean skipping(){
        return this.skip;
    }
    public void enableFinish(){
        this.finishTurn = true;
    }
    public boolean checkForFinish(){
        return this.finishTurn;
    }
    @Override
    public String toString() {
        return "Name: " + this.getName() + "|| Balance: " + this.getBalance() + "|| Number of Houses: " + this.house; 
    }
}