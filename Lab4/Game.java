import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Game {
    private ArrayList <Cell> cells;
    private ArrayList <Player> players;
    private Player [] bankcrupcy;
    private WelcomeFrame frame;
    private MapPanel mapPanel;
    private ControlPanel controlPanel;
    private int currentPlayerIndex;
    private int roundCounter;
    private ArrayList <String> logMessages;
    private boolean userTurnCompleted;
    private int turnCount;

    public Game(){
        this.cells = this.generateCells();
        this.players = this.addPlayers();
        this.bankcrupcy = new Player[4];
        mapPanel = new MapPanel(this);
        controlPanel = new ControlPanel(this);
        this.currentPlayerIndex = 0;
        this.roundCounter = 1;
        this.logMessages = new ArrayList<>();
        this.userTurnCompleted = false;
        this.turnCount = 0;

    }
    public void logMessage(String message) {
        logMessages.add(message);
    }

    // Retrieve all stored logs
    public ArrayList<String> getLogMessages() {
        return new ArrayList<>(logMessages); 
    }
    

    // Clear logs after displaying them
    public void clearLogs() {
        logMessages.clear();
    }

    public Cell getCellsByIndex(int index){
        for(Cell cell: this.cells){
            if (cell.getNumber() == index){
                return cell;
            }
        }
        return null;
    }
    public ArrayList <Cell> getCells(){
        return this.cells;
    }
    public ArrayList <Player> addPlayers(){
        ArrayList <Player> players = new ArrayList<>();
        players.add(new Player(null, 1, this));
        for (int i = 2; i <= 4; i++) {
            players.add(new ComputerPlayer(null, i, this));
        }
        return players;
    }
    public ArrayList generateCells(){
        ArrayList <Cell> cells = new ArrayList<>();
         for (int i = 0; i < 16; i++) {
        if (i % 4 == 0) {
            cells.add(new SpecialEventCell(names[i], i));
        } else {
            cells.add(new Cell(names[i], i));
        }
    }
        return cells;
    }
    public void start() {
        Timer timer = new Timer(1000, e -> {
            if (roundCounter >= 100) {
                JOptionPane.showMessageDialog(null, "The game ends in a tie after " + roundCounter + " rounds.");
                System.exit(0);
            }
        
            Player currentPlayer = players.get(currentPlayerIndex);
        
            if (currentPlayer instanceof ComputerPlayer) {
                simulateComputerTurn((ComputerPlayer) currentPlayer);
                nextPlayer();
            } else {
                markUserTurnCompleted(false);
                controlPanel.playUser();
            }
        
            mapPanel.updateMap();
            mapPanel.refreshScoreboard();
        });
        timer.start();
    }
    
    public ArrayList <Player> getPlayers(){
        return this.players;
    }
    public void setPlayers(){
        this.players = determinePlayOrder();
    }
    public Player findUser(){
        for(Player player : this.players){
            boolean isUser = player instanceof ComputerPlayer;
            if(!isUser){
                return player;
            }
        }
        return null;
    }
    public String[] names = {"0" , "A" , "B" , "C" , "1", "D" , "E" , "F" ,"2", "G" , "H" , "I" ,"3", "J" , "K" , "L"};
    public int getCurrentPlayerIndex() {
        return this.currentPlayerIndex;
    }
    public ArrayList <Player> determinePlayOrder(){
        Random random = new Random();
        for (int i = this.getPlayers().size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            Player temp = this.getPlayers().get(i);
            this.getPlayers().set(i, this.getPlayers().get(j));
            this.getPlayers().set(j, temp);
        }
        return this.getPlayers();
    }
    public MapPanel getMapPanel() {
        return mapPanel;
    }

    public void setMapPanel(MapPanel mapPanel) {
        this.mapPanel = mapPanel;
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    public void setControlPanel(ControlPanel controlPanel) {
        this.controlPanel = controlPanel;
    }
    public void nextPlayer() {
        // Check if a new round is starting
        if (this.turnCount == 4) {
            roundCounter++;
            markUserTurnCompleted(false);
            logMessage("New Round: " + roundCounter );
            this.turnCount = 0;
        }

        // Move to the next player
        currentPlayerIndex = (currentPlayerIndex + 1) % this.players.size();


        if (this.players.size() == 1 || roundCounter >= 100) {
            JOptionPane.showMessageDialog(null, "Winner: " + this.players.get(0).getName());
            System.exit(0);
        }
        Player currentPlayer = players.get(currentPlayerIndex);
        logMessage("Turn " + (currentPlayerIndex + 1) + ": " + currentPlayer.getName() + "'s turn");

        SwingUtilities.invokeLater(() -> {
            if (mapPanel != null) {
                mapPanel.updateMap();
            }
            mapPanel.refreshScoreboard();
        });

        if (!userTurnCompleted && !(players.get(currentPlayerIndex) instanceof ComputerPlayer)) {
            logMessage("Waiting for human player: " + currentPlayer.getName());
            controlPanel.enableUserTurnControls();
            turnCount++;
            return;
        }

        else if(this.userTurnCompleted){
            logMessage("Computer Player Turn: " + currentPlayer.getName());
            simulateComputerTurn((ComputerPlayer) currentPlayer);
            turnCount++;
            return;
        }
    }

    public void markUserTurnCompleted(boolean choice) {
        userTurnCompleted = choice;
    }
    public void simulateComputerTurn(ComputerPlayer computerPlayer) {
        controlPanel.disableUserTurnControls();
    
        computerPlayer.rollDice();
    
        if (computerPlayer.getCurrentCell() instanceof SpecialEventCell) {
            SpecialEventCell special = (SpecialEventCell) computerPlayer.getCurrentCell();
            special.findWhichSpecial(computerPlayer, this.getPlayers());
        } else {
            if (computerPlayer.getCurrentCell().getOwner() != null &&
                computerPlayer.getCurrentCell().getOwner() != computerPlayer) {
                computerPlayer.payRent();
            } else {
                computerPlayer.buyCell();
                if (!computerPlayer.isBoughtCell()) {
                    computerPlayer.sellHouse(computerPlayer.getRandomCell());
                    if (!computerPlayer.isSoldHouse() && !computerPlayer.getCells().isEmpty()) {
                        computerPlayer.buildHouse(computerPlayer.getRandomCell());
                    }
                }
            }
        }
        SwingUtilities.invokeLater(() -> {
            this.getMapPanel().updateMap();
            this.getMapPanel().refreshScoreboard();
        });
    
        // **Move to the next player after a small delay**
        Timer timer = new Timer(1000, e -> nextPlayer());
        timer.setRepeats(false);
        timer.start();
    }    
    public ArrayList<Player> removedBankcrupted() {
        ArrayList<Player> activePlayers = new ArrayList<>();
        for (Player player : this.players) {
            if (player.getMoney() >= 0) {
                activePlayers.add(player);
            }
        }
        return activePlayers;
    }
    public int getRound() {
        return this.roundCounter;
    }
}
