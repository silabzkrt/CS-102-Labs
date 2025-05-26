import java.awt.Color;

public class Cell {
    final static Color DEFAULT_COLOR = Color.WHITE;
    private Player owner;
    private Color color;
    private int HouseNumber;
    private boolean canBuild;
    private String name;
    private int price;
    private int cellNumber;
    private int rent;
    private  boolean canBuy;
    private int cost;

    public Cell(String name, int number){
        this.name = name;
        this.cellNumber = number;
        if (number == 1 || number == 2 || number == 3) {
            this.price = 2;
            this.rent = 1;
        } else if (number == 5 || number == 6 || number == 7) {
            this.price = 4;
            this.rent = 3;
        } else if (number == 9 || number == 10 || number == 11) {
            this.price = 6;
            this.rent = 1;
        } else if (number == 13 || number == 14 || number == 15) {
            this.price = 8;
            this.rent = 3;
        } else {
            this.price = 0;
            this.rent = 0;
        }
        this.canBuy = true;
    }
    public boolean canBuy(){
        return this.canBuy;
    }
    public Player getOwner(){
        return this.owner;
    }
    public void setOwner(Player owner){
        this.owner = owner;
    }
    public void setColor(Color color){
        this.color = color;
    }
    public boolean canBuild() {
        return this.getHouseNumber() < 4;
    }
    public void addHouse(){
        if(canBuild){
            this.HouseNumber++;
        }
    }
    public String getName(){
        return this.name;
    }
    public int getPrice(){
        return this.price;
    }
    public int getNumber(){
        return this.cellNumber;
    }
    public int getHouseNumber(){
        return this.HouseNumber;
    }
    public int getHouseCost(){
        if (this.getNumber() == 1 || this.getNumber() == 2 || this.getNumber() == 3) {
            this.cost = 1;
        } else if (this.getNumber() == 5 || this.getNumber() == 6 || this.getNumber() == 7) {
            this.cost = 1;
        } else if (this.getNumber() == 9 || this.getNumber() == 10 || this.getNumber() == 11) {
            this.cost = 2;
        } else if (this.getNumber() == 13 || this.getNumber() == 14 || this.getNumber() == 15) {
            this.cost = 3;
        }
        return this.cost;
    }
    public Color getColor(){
        return this.color;
    }
    public int getRent() {
        if (this.getNumber() == 1 || this.getNumber() == 2 || this.getNumber() == 3) {
            switch (this.getHouseNumber()) {
                case 0:
                    this.rent = 1;
                    break;
                case 1:
                    this.rent = 2;
                    break;
                case 2:
                    this.rent = 3;
                    break;
                case 3:
                    this.rent = 4;
                    break;
                case 4:
                    this.rent = 6;
                    break;
                default:
                    throw new AssertionError();
            }
        } else if (this.getNumber() == 5 || this.getNumber() == 6 || this.getNumber() == 7) {
            switch (this.getHouseNumber()) {
                case 0:
                    this.rent = 2;
                    break;
                case 1:
                    this.rent = 2;
                    break;
                case 2:
                    this.rent = 3;
                    break;
                case 3:
                    this.rent = 3;
                    break;
                case 4:
                    this.rent = 7;
                    break;
                default:
                    throw new AssertionError();
            }
        } else if (this.getNumber() == 9 || this.getNumber() == 10 || this.getNumber() == 11) {
            switch (this.getHouseNumber()) {
                case 0:
                    this.rent = 1;
                    break;
                case 1:
                    this.rent = 3;
                    break;
                case 2:
                    this.rent = 4;
                    break;
                case 3:
                    this.rent = 6;
                    break;
                case 4:
                    this.rent = 7;
                    break;
                default:
                    throw new AssertionError();
            }
        } else if (this.getNumber() == 13 || this.getNumber() == 14 || this.getNumber() == 15) {
            switch (this.getHouseNumber()) {
                case 0:
                    this.rent = 3;
                    break;
                case 1:
                    this.rent = 3;
                    break;
                case 2:
                    this.rent = 6;
                    break;
                case 3:
                    this.rent = 6;
                    break;
                case 4:
                    this.rent = 9;
                    break;
                default:
                    throw new AssertionError();
            }
        }
        return this.rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    void setHasEnd(boolean b) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
