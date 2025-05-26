
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class SpecialEventCell extends Cell {
    public SpecialEventCell(String name, int number){
        super(name, number);
        this.setOwner(null);
        this.setColor(Color.GRAY);
        this.canBuy();
    }
    public void findWhichSpecial(Player player, ArrayList <Player> players){
        Random random = new Random();
        if (player.getCurrentCell().getNumber() == 0 || player.getCurrentCell().getName().equals("0")){
            player.setMoney(player.getMoney() + 3);
        }
        else if (player.getCurrentCell().getNumber() == 4 || player.getCurrentCell().getName().equals("1")){
            int roll = random.nextInt(0,6)+1;
            if (roll == 1){
                player.setMoney(player.getMoney()-2);
            }
            else if (roll == 2){
                player.setMoney(player.getMoney()-1);
            }
            else if(roll == 3){
                player.movePlayer(1);
            }
            else if(roll == 4){
                player.movePlayer(2);
            }
            else if(roll == 5){
                player.setMoney(player.getMoney()+1);
                player.movePlayer(1);
            }
            else{
                player.setMoney(player.getMoney()+2);
                player.movePlayer(2);
            }
        }
        else if(player.getCurrentCell().getNumber() == 8 || player.getCurrentCell().getName().equals("2")){
            for(Player pl : players){
                player.setMoney(player.getMoney() + 1);
                pl.setMoney(pl.getMoney() - 1);
            }
        }
        else if(player.getCurrentCell().getNumber() == 12 || player.getCurrentCell().getName().equals("3")){
            player.endTurn();
        }

    }
}
