import java.awt.Color;
import java.util.*;
public class Player{
    private String name;
    private ArrayList <Cell> cells;
    private int money;
    private Color color;
    private Cell currentCell;
    private int Playernumber;
    private boolean continueGame;
    private boolean isComputer;
    private boolean rolledDice;
    private Game game;
    private boolean boughtCell;
    private boolean soldHouse;
    private boolean builtHouse;

    public Player(String name, int number, Game game){
        this.name = name;
        this.money = 10;
        this.game = game;
        this.currentCell = game.getCells().get(0);
        this.continueGame = true;
        this.isComputer = false;
        this.rolledDice = false;
        this.boughtCell= false;
        this.soldHouse = false;
        this.builtHouse = false;
        this.cells = new ArrayList<>();
        switch (number) {
            case 1:
                this.color = Color.RED;
                break;
            case 2:
                this.color = Color.BLUE;
                break;
            case 3:
                this.color = Color.YELLOW;
                break;
            case 4:
                this.color = Color.GREEN;
                break;
            default:
                throw new AssertionError();
        }
    }

    public void rollDice() {
        Random random = new Random();
        int roll = random.nextInt(6) + 1;
        game.logMessage(this.getName() + " rolled a " + roll);
    
        int newPosition = (currentCell.getNumber() + roll) % game.getCells().size();
        if ((currentCell.getNumber() + roll) >= game.getCells().size()) {
            this.setMoney(this.getMoney() + 3);
            game.logMessage(this.getName() + " passed start and received 3 credits!");
        }
        
        movePlayer(newPosition);
        this.rolledDice = true;
    }
    
    public void buyCell(){
        if(this.rolledDice){
            if(this.getCurrentCell().getOwner() == null && !(this.getCurrentCell() instanceof SpecialEventCell)){
                if(this.currentCell.getPrice() <= this.getMoney()){
                    this.cells.add(this.currentCell);
                    System.out.println("The cell " + this.currentCell.getName() + " is purchased by " + this.getName() 
                        + " for " + this.getCurrentCell().getPrice());
                    game.logMessage("The cell " + this.currentCell.getName() + " is purchased by " + this.getName() 
                        + " for " + this.getCurrentCell().getPrice());
                    this.money -= this.currentCell.getPrice();
                    this.currentCell.setOwner(this);
                    this.currentCell.setColor(this.getColor());  
                    this.boughtCell = true;   
                }
                else{
                    System.out.println("Insufficent credits to buy house!");
                }
            }
        }
    }
    public void sellHouse(Cell cell){
        if(this.rolledDice && this.cells.size() != 0){
            this.cells.remove(cell);
            System.out.println("The cell " + cell.getName() + " is sold by " + this.getName() + " for " + cell.getPrice());
            game.logMessage("The cell " + cell.getName() + " is sold by " + this.getName() + " for " + cell.getPrice());
            this.money += cell.getPrice();
            cell.setOwner(null);
            cell.setColor(Cell.DEFAULT_COLOR);
            this.soldHouse = true;
        }
    }
    public void buildHouse(Cell cell){
        if(cell.getOwner() == this && cell.getHouseCost() <= this.getMoney()){
            System.out.println("A house has been built on " + cell.getName() + " for " + cell.getHouseCost());
            game.logMessage("A house has been built on " + cell.getName() + " for " + cell.getHouseCost());
            this.money = this.getMoney() - cell.getHouseCost();
            if(cell.canBuild()){
                cell.addHouse();
                this.builtHouse = true;
            }
            else{
                System.out.println(cell.getName() + " has reached maximum number of houses");
                game.logMessage(cell.getName() + " has reached maximum number of houses");
            }
        }
    }
    public void declareBankcrupcy(){
        this.continueGame = false;
        for (Cell cell : this.cells){
            cell.setOwner(null);
            cell.setColor(Cell.DEFAULT_COLOR);
        }
        System.out.println("The player " + this.getName() + " has lost the game");
        game.logMessage("The player " + this.getName() + " has lost the game");
    }
    public void endTurn(){
        this.rolledDice = false;
        this.boughtCell = false;
        this.soldHouse = false;
        this.builtHouse = false;
        this.game.getMapPanel().updateMap();
        this.game.nextPlayer();
        this.game.markUserTurnCompleted(false);
    }
    public void movePlayer(int move) {
        this.setCurrentCell(game.getCells().get(move));
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }

    public void setCells(ArrayList<Cell> cells) {
        this.cells = cells;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    public void setCurrentCell(Cell cell) {
        this.currentCell = cell;
    }

    public int getPlayernumber() {
        return Playernumber;
    }

    public void setPlayernumber(int playernumber) {
        Playernumber = playernumber;
    }

    public void setComputer(){
        this.isComputer = true;
    }
    public boolean isBuiltHouse() {
        return builtHouse;
    }

    public void setBuiltHouse(boolean builtHouse) {
        this.builtHouse = builtHouse;
    }

    public boolean isBoughtCell() {
        return boughtCell;
    }

    public void setBoughtCell(boolean boughtCell) {
        this.boughtCell = boughtCell;
    }

    public boolean isSoldHouse() {
        return soldHouse;
    }

    public void setSoldHouse(boolean soldHouse) {
        this.soldHouse = soldHouse;
    }
    public void payRent(){
        this.setMoney(this.getMoney() - this.getCurrentCell().getRent());
        this.getCurrentCell().getOwner().setMoney(this.getCurrentCell().getOwner().getMoney() + this.getCurrentCell().getRent());
        game.logMessage("The player paid " + this.getCurrentCell().getOwner().getName() + " rent of " + this.getCurrentCell().getName() );
    }
}