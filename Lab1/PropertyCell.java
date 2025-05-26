package labs102.Lab1;

public class PropertyCell extends Cell {
    public PropertyCell(String name){
        super(name);
    }

     /*public Player getOwner() {
        return this.owner;
    }

    public void setOwner (Player player) {
        this.owner = player;
        System.out.println("The owner of the Cell " + this.getName() + " is " + this.getOwner().getName());
    }

    public int getHouseNumber() {
        return this.house;
    }

    @Override
    public String printCellInfo() {
        if(this.getOwner() != null){
            return this.getName() + "." + this.getOwner().getInitial() + this.getHouseNumber();
        }
        return this.getName()+ ".." + this.getHouseNumber();
    }

    public void addHouse(int value){
        this.house += value;
    } */
}
