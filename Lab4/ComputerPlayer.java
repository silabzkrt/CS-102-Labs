import java.util.*;

public class ComputerPlayer extends Player {
    public ComputerPlayer(String name, int Playernumber, Game game){
        super(name, Playernumber,game);
        this.setComputer();
    }
    Random random = new Random();
    @Override
public void buyCell() {
    if (this.getCurrentCell().getOwner() == null 
        && !(this.getCurrentCell() instanceof SpecialEventCell) 
        && this.getMoney() >= this.getCurrentCell().getPrice() 
        && random.nextInt(2) == 0) { 
        super.buyCell();
    }
}

    
    @Override
    public void sellHouse(Cell cell) {
        if (!this.getCells().isEmpty() && random.nextInt(2) == 0) {
            super.sellHouse(cell);
        }
    }
    
    @Override
    public void buildHouse(Cell cell) {
        if (this.getMoney() >= cell.getHouseCost() && cell.getOwner() == this && random.nextInt(2) == 0) {
            super.buildHouse(cell);
        }
    }
    
    @Override
    public ArrayList<Cell> getCells(){
        return super.getCells();
    }

    public Cell getRandomCell() {
        if (this.getCells().isEmpty()) {
            return null; // Return null if no cells are owned
        }
        return this.getCells().get(random.nextInt(this.getCells().size()));
    }
}
