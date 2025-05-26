package labs102.Lab1;
import java.util.ArrayList;

public class Cell {
    private String name;
    private ArrayList <Player> playersOnTheCell;
    private Player owner;
    private int house;
    private boolean isSpecialCase;

    public Cell(String aName){
        this.name = aName;
        this.playersOnTheCell = new ArrayList<>();
        this.owner = null;
        this.house = 0;
        this.isSpecialCase = false;
    }

    public String getName() {
        return this.name;
    }

    public void makeSpecial(){
        this.isSpecialCase = true;
    }
    public boolean checkForSpecial() {
        return this.isSpecialCase;
    }
    
    public ArrayList <Player> getPlayersOnTheCell(){
        return this.playersOnTheCell;
    }
    
    public void addPlayer(Player player){
        this.playersOnTheCell.add(player);
    }
    
    public Player getOwner() {
        return this.owner;
    }

    public void setOwner (Player player) {
        this.owner = player;
        System.out.println("The owner of the Cell " + this.getName() + " is " + this.getOwner().getName());
    }
   
    public int getHouseNumber() {
        return this.house;
    }

    public void addHouse(int value){
        this.house += value;
    }
}