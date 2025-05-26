package labs102.Lab1;

import java.util.*;

public class Game {
    private Board board;
    private ArrayList<Player> players;
    private int round;

    public Game() {
        this.players = new ArrayList<>();
        this.round = 1;
        this.board = new Board();
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public void createPlayers() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the name for your user: ");
        Player user = new Player(sc.next());
        user.makeUser();
        this.players.add(user);
        System.out.print("Enter the number of players you wish to have in this game (1-3): ");
        int number = sc.nextInt();
        for (int i = 0; i < number; i++) {
            System.out.print("Enter the name for the player: ");
            Player player = new Player(sc.next());
            this.players.add(player);
        }
    }
    

    public void printRoundInfo() {
        System.out.println("**************************");
        System.out.println("Round #" + this.round);
        for (Player player : players) {
            System.out.println(player.toString());
        }
        System.out.println("**************************");
    }

    public ArrayList<Player> orderOfPlay(ArrayList <Player> players) {
        Random random = new Random();
        ArrayList<Player> orderedPlay = new ArrayList<>();
        while (!players.isEmpty()) {
            int rand = random.nextInt(players.size());
            orderedPlay.add(players.get(rand));
            players.remove(rand);
        } 
        return orderedPlay;
    }

    public void initializeGame() {
        
    }

    public void startGame() {
        
    }

    public int price(Cell property) {
        for (Cell prop : this.board.getCells()) {
            int index = this.board.getCells().indexOf(prop);
            if (index < 3) {
                return 2;
            } else if (index >= 3 && index < 6) {
                return 4;
            } else if (index >= 6 && index < 9) {
                return 6;
            } else {
                return 8;
            }
        }
        return 0;
    }
    public void displayRoundInfo(Player player, int roll){
        System.out.println("Player name: " + player.getName());
        System.out.println("Player Balance: " + player.getBalance());
        System.out.println("Current space: " + player.getCurrentCell());
        System.out.println("Player rolled dice: " + roll);
        for(int i = 0; i < this.board.getCells().size(); i++){
            if(this.board.getCells().get(i).getOwner() == player){
                System.out.print(i + " || ");
            }
        }
    }

    public void turnRounds(ArrayList<Player> players) {
        Scanner sc = new Scanner(System.in);
        Dice dice = new Dice(null);
        for (Player player : players) {

            if (!player.skipping()) {
                int roll = dice.rollDice();
                displayRoundInfo(player, roll);
                player.checkForCompletition(roll);
                player.movePlayer(roll);
                System.out.println("New Cell: " + this.board.getCells().get(player.getCurrentCell()).getName());
                if (!player.Computer()) {
                    // if it's a property Cell
                    if (!this.board.getCells().get(player.getCurrentCell()).checkForSpecial()) {
                        if (this.board.getCells().get(player.getCurrentCell()).getOwner() != null) {
                            if (player.getBalance() >= rent(this.board.getCells().indexOf(this.board.getCells().get(player.getCurrentCell())))) {
                                payRent(player);
                            } 
                            else {
                                for (Cell cell : this.board.getCells()) {
                                    if (cell.getOwner() == player) {
                                        cell.setOwner(null);
                                    }
                                }
                                this.players.remove(player);
                            }
                        }
                        buyProperty(player);
                        buildHouse(player);
                        sellPropery(player);
                    }
                    // If it contains a Special Case
                    else {
                        implementSpecialCase(player);
                    }
                } else {
                    // for computer players
                    if (!this.board.getCells().get(player.getCurrentCell()).checkForSpecial()) {
                        buyForComputer(player);
                        buildForComputer(player);
                        sellForComputer(player);
                    }
                    // If it contains a Special Case
                    else {
                        implementSpecialCase(player);
                    }
                }
            }
        }
    }

    public void buildHouse(Player player) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Do you wish to build a house? (Y/N)");
        if (sc.next().equals("Y")) {
            System.out.print("Where do you wish to build your house?: ");
            int cellNo = sc.nextInt();
            player.loseCoin(costOfBuilding(cellNo));
            this.board.getCells().get(cellNo).addHouse(cellNo);
            System.out.print("Player " + player.getName() + " has build a house on cell " + this.board.getCells().get(cellNo).getName());
            System.out.println();
        }
        
    }

    public int costOfBuilding(int cellNo) {
        if (cellNo == 1 || cellNo == 2 || cellNo == 3) {
            return 1;
        } else if (cellNo == 5 || cellNo == 6 || cellNo == 7) {
            return 1;
        } else if (cellNo == 9 || cellNo == 10 || cellNo == 11) {
            return 2;
        } else if (cellNo == 13 || cellNo == 14 || cellNo == 15) {
            return 3;
        } else {
            return 0;
        }
    }

    public int rent(int cellNo) {
        if (cellNo == 1 || cellNo == 2 || cellNo == 3) {
            int house = this.board.getCells().get(cellNo).getHouseNumber();
            if (house >= 4) {
                house = 4;
            }
            switch (house) {
                case 0:
                    return 1;
                case 1:
                    return 2;
                case 2:
                    return 3;
                case 3:
                    return 4;
                case 4:
                    return 6;
            }
        } else if (cellNo == 5 || cellNo == 6 || cellNo == 7) {
            int house = this.board.getCells().get(cellNo).getHouseNumber();
            if (house >= 4) {
                house = 4;
            }
            switch (house) {
                case 0:
                    return 3;
                case 1:
                    return 2;
                case 2:
                    return 3;
                case 3:
                    return 3;
                case 4:
                    return 7;
            }
        } else if (cellNo == 9 || cellNo == 10 || cellNo == 11) {
            int house = this.board.getCells().get(cellNo).getHouseNumber();
            if (house >= 4) {
                house = 4;
            }
            switch (house) {
                case 0:
                    return 1;
                case 1:
                    return 3;
                case 2:
                    return 4;
                case 3:
                    return 6;
                case 4:
                    return 7;
            }
        } else if (cellNo == 13 || cellNo == 14 || cellNo == 15) {
            int house = this.board.getCells().get(cellNo).getHouseNumber();
            if (house >= 4) {
                house = 4;
            }
            switch (house) {
                case 0:
                    return 3;
                case 1:
                    return 3;
                case 2:
                    return 6;
                case 3:
                    return 6;
                case 4:
                    return 9;
            }
        } else {
            return 0;
        }
        return 0;
    }

    public void payRent(Player player) {
        Cell cell = this.board.getCells().get(player.getCurrentCell());
        int index = this.board.getCells().indexOf(cell);
        player.loseCoin(rent(index));
        this.board.getCells().get(index).getOwner().earnCoin(rent(index));
    }

    public void sellPropery(Player player) {
        Scanner sc = new Scanner(System.in);
        if(player.getHouse() != 0){
            System.out.println("Do you wish to sell any property?");
            if(sc.next().equals("Y")){
                System.out.print("Which buildings you wish to sell? :");
                int cellNo = sc.nextInt();
                player.earnCoin(costOfBuilding(cellNo));
                System.out.println("Player " + player.getName() + " has sold a house on cell" + this.board.getCells().get(cellNo).getName());
            }
        }   
    }

    public void buyProperty(Player player) {
        Scanner sc = new Scanner(System.in);
        if (player.getCurrentCell() % 4 != 0) {
            if (this.board.getCells().get(player.getCurrentCell()).getOwner() == null) {
                System.out.print("Do you wish to purchase this property? (Y/N):");
                if (sc.next().equals("Y")) {
                    if (player.getBalance() - price(this.board.getCells().get(player.getCurrentCell())) > 0)
                        this.board.getCells().get(player.getCurrentCell()).setOwner(player);
                        player.loseCoin(price(this.board.getCells().get(player.getCurrentCell())));
                        this.board.getCells().get(player.getCurrentCell()).setOwner(player);
                        System.out.println("Player " + player.getName() + " has bought cell" + this.board.getCells().get(player.getCurrentCell()).getName());
                }
            }
        }
    }

    public void buyForComputer(Player player) {
        Random random = new Random();
        if (player.getCurrentCell() % 4 != 0) {
            if (this.board.getCells().get(player.getCurrentCell()).getOwner() != null) {
                System.out.print("Do you wish to purchase this property? (Y/N):");
                if (random.nextBoolean() == true) {
                    if (player.getBalance() - price(this.board.getCells().get(player.getCurrentCell())) > 0)
                        this.board.getCells().get(player.getCurrentCell()).setOwner(player);
                        player.loseCoin(price(this.board.getCells().get(player.getCurrentCell())));
                        this.board.getCells().get(player.getCurrentCell()).setOwner(player);
                        System.out.println("Player " + player.getName() + " has bought cell" + 
                            this.board.getCells().get(player.getCurrentCell()).getName());
                }
            }
        }
    }

    public void buildForComputer(Player player){
        Random random = new Random();
        if (this.board.getCells().get(player.getCurrentCell()).getOwner() != null) {
            if (random.nextBoolean() == true) {
                int cellNo = player.getCurrentCell();
                if (player.getBalance() - price(this.board.getCells().get(cellNo)) > 0){
                    player.loseCoin(costOfBuilding(cellNo));
                    this.board.getCells().get(cellNo).addHouse(cellNo);
                    System.out.print("Player " + player.getName() + " has build a house on cell " + this.board.getCells().get(cellNo).getName());
                    System.out.println();
                }
            }
        }
    }
    public void sellForComputer(Player player){
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        if (this.board.getCells().get(player.getCurrentCell()).getOwner() != null) {
            if (random.nextBoolean() == true) {
                int cellNo = player.getCurrentCell();
                if (player.getBalance() - price(this.board.getCells().get(cellNo)) > 0){
                    System.out.print("Which buildings you wish to sell? :");
                    int toSell = sc.nextInt();
                    player.earnCoin(costOfBuilding(toSell));
                    System.out.println("Player " + player.getName() + " has sold a house on cell" + this.board.getCells().get(cellNo).getName());
                }
            }
        }
    }
    public void addIndexToCell(){

    }

    public void checkForSpecialCase(Player player) {
        if (player.getCurrentCell() % 4 == 0) {
            implementSpecialCase(player);
        } else {
            player.enableFinish();
        }
    }

    public void implementSpecialCase(Player player) {
        Dice dice = new Dice(null);
        if (this.board.getCells().get(player.getCurrentCell()).getName().equals("0")) {
            System.out.println("#Case 0#");
            player.earnCoin(3);
        } else if (this.board.getCells().get(player.getCurrentCell()).getName().equals("1")) {
            System.out.println("#Case 1#");
            int rolled = dice.rollDice();
            switch (rolled) {
                case 1:
                    player.loseCoin(2);
                    break;
                case 2:
                    player.loseCoin(1);
                    break;
                case 3:
                    player.movePlayer(1);
                    buyProperty(player);
                    break;
                case 4:
                    player.movePlayer(2);
                    buyProperty(player);
                    break;
                case 5:
                    player.earnCoin(1);
                    player.movePlayer(1);
                    buyProperty(player);
                    break;
                case 6:
                    player.earnCoin(2);
                    player.movePlayer(2);
                    while (!player.checkForFinish()) {
                        buyProperty(player);
                        checkForSpecialCase(player);
                    }
                    break;
            }

        } else if (this.board.getCells().get(player.getCurrentCell()).getName().equals("2")) {
            System.out.println("#Case 2#");
            int index = this.players.indexOf(player);
            for (Player player1 : this.players) {
                if (this.players.indexOf(player1) != index) {
                    player1.loseCoin(1);
                    player.earnCoin(1);
                }
            }
        } else {
            System.out.println("#Case 3#");
            player.skipTurn();
        }
    }

    public Board getBoard(){
        return this.board;
    }

    public void setPlayers(ArrayList <Player> playerrs){
        this.players = playerrs;
    }

    public static void main(String[] args) {
        Board board = new Board();
        Game game = new Game();
        game.createPlayers();
        ArrayList <Player> players = game.orderOfPlay(game.getPlayers());
        game.setPlayers(players);
        while (game.round < 100 && !game.players.isEmpty()){
            board.printBoard();
            game.printRoundInfo();
            game.turnRounds(game.players);
            game.round++;
        }
    }
}