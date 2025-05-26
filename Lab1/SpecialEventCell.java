package labs102.Lab1;

public class SpecialEventCell extends Cell {
    public SpecialEventCell(String name){
        super(name);
    }
    @Override
    public String printCellInfo(){
        return this.getName() + "...";
    }
}
