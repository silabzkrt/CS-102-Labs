package labs102.Lab1;
import java.util.*;

public class Board {
    private ArrayList <Cell> cells;
    public Board() {
        this.cells = generateCells();
    }

    public void moveFromCell(Player player){
        cells.get(player.getCurrentCell()).getPlayersOnTheCell().remove(player);
    }
    public void clearPlayers(int index){
        for (int i = 0 ;i < index; i++){
            this.cells.get(index).getPlayersOnTheCell().remove(i);
        }    
    }

    /*For example, this representation uses the following notation for each cell:
        |X.yz|
        |klmn| 
        X = cell name or number that represents special event
        y= owner's letter (dot (.) if there's no owner)
        z= number of houses
        k,l,m,n slots for the user currently on this cell dot if empty */
    
    public ArrayList<Cell> generateCells() {
        ArrayList <Cell> cells = new ArrayList <>();
        String [] cellNames = {"0", "A", "B", "C", "1", "D", "E", "F", "2", "G", "H", "I", "3", "J", "K", "L"};
        for (String name : cellNames){
            cells.add(new Cell(name));
            if (cells.size() == 1||cells.size() == 5||cells.size() == 9|| cells.size() == 13){
                cells.get(cells.size()-1).makeSpecial();
            }
        }
        return cells;
    } 
    public void printBoard () {
            ArrayList <String> board = new ArrayList<>();
            String line1 = "|";
            for (int i = 0; i < 5; i++) {
                line1 += ArrtoString(printCellInfo(this.cells.get(i))) + "|";
            }
            board.add(line1);
            String line2 = "|";
            for (int i = 0; i < 5; i++) {
                line2 += ArrtoString(printPlayersOnTheCell(this.cells.get(i))) + "|";
            }
            board.add(line2);
            //buraya bir tane for at sonra kodda 15-20 satır kurtarırız
            String line3 = "|" + ArrtoString(printCellInfo(this.cells.get(15)))+"|" +"\t\t    " + "|" + 
                ArrtoString(printCellInfo(this.cells.get(5))) + "|";
            board.add(line3);
            String line4 = "|" + ArrtoString(printPlayersOnTheCell(this.cells.get(15)))+"|" + "\t\t    " + "|" + 
                ArrtoString(printPlayersOnTheCell(this.cells.get(5))) + "|";
            board.add(line4);
            String line5 = "|" + ArrtoString(printCellInfo(this.cells.get(14))) + "|" + "\t\t    " + "|" + 
                ArrtoString(printCellInfo(this.cells.get(6))) + "|";
            board.add(line5);
            String line6 = "|" + ArrtoString(printPlayersOnTheCell(this.cells.get(14)))+"|" + "\t\t    " + "|" + 
                ArrtoString(printPlayersOnTheCell(this.cells.get(6))) + "|";
            board.add(line6);
            String line7 = "|" + ArrtoString(printCellInfo(this.cells.get(13))) + "|" + "\t\t    " + "|" + 
                ArrtoString(printCellInfo(this.cells.get(7))) + "|";
            board.add(line7);
            String line8 =  "|" + ArrtoString(printPlayersOnTheCell(this.cells.get(13)))+"|" + "\t\t    " + "|" + 
                ArrtoString(printPlayersOnTheCell(this.cells.get(8))) + "|";
            board.add(line8);
            String line9 = "|";
            for (int i = 12; i > 7; i--) {
                line9 += ArrtoString(printCellInfo(this.cells.get(i))) + "|";
            }
            board.add(line9);
            String line10 = "|";
            for (int i = 0; i < 5; i++) {
                line10 += ArrtoString(printPlayersOnTheCell(this.cells.get(i))) + "|";
            }
            board.add(line10);
            for (String line : board){
                System.out.print(line+"\n");
            }
        }

        public ArrayList <Cell> getCells(){
            return this.cells;
        }

        public Cell findCellByName(String name){
            for (Cell cell : this.cells){
                if (cell.getName().equals(name)){
                    return cell;
                }
            }
            return null;
        }
        public String ArrtoString (String [] array) {
            String st = "";
            for(int i = 0; i < array.length ; i++){
                st += array[i];
            }
            return st;
        }
        
        public String[] printPlayersOnTheCell(Cell cell) {
            String [] printed = new String [4];
            for (int i = 0; i < cell.getPlayersOnTheCell().size() ; i++) {
                System.out.println(cell.getPlayersOnTheCell().get(i).getInitial());
                printed [i] = cell.getPlayersOnTheCell().get(i).getInitial();
            }
            for (int i = cell.getPlayersOnTheCell().size() ; i < 4 ; i++){
                printed [i] = ".";
            }
            return printed;
        }  

        public String[] printCellInfo(Cell cell) {
            String [] printed = new String[4];
            printed [0] = cell.getName();
            printed [1] = ".";
    
            if(cell.getOwner() != null){
                printed [2] = cell.getOwner().getInitial();
            }
            else{
                printed [2] = ".";
            }
            printed [3] = Integer.toString(cell.getHouseNumber());
            return printed;
        }
    }
