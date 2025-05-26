package Lab5;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import javax.sound.sampled.*;
import javax.swing.*;

public class Maze {
    private MazeFrame mazeFrame;
    private JFrame controlFrame;
    private Cell[][] cells;
    private int[][] maze;
    private Cell startCell;
    private Cell endCell;
    private ArrayList<Cell> optimalPath;

    public Maze() {
        this.controlFrame = createControlFrame();
        this.cells = new Cell[5][5];
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                this.cells[row][col] = new Cell(row, col);
            }
        }
        this.mazeFrame = new MazeFrame(this); 
        this.maze = createMaze(this.cells);
        this.mazeFrame.setVisible(true);
        this.controlFrame.setVisible(true);
        this.optimalPath = null;
    }

    public Cell[][] getCells() {
        return this.cells;
    }

    public JFrame createControlFrame() {
        JFrame controlFrame = new JFrame("Control Frame");
        JPanel choices = new JPanel(new GridLayout(6, 1));
        JButton setStart = new JButton("Set Start");
        JButton setStop = new JButton("Set Stop");
        JButton addWall = new JButton("Add Wall");
        JButton removeWall = new JButton("Remove Wall");
        JButton findPath = new JButton("Find Path");
        JButton reset = new JButton("Reset");

        setStart.addActionListener(e -> addStart());
        setStop.addActionListener(e -> addEnd());
        addWall.addActionListener(e -> addWall());
        removeWall.addActionListener(e -> removeWall());
        findPath.addActionListener(e -> findPath());
        reset.addActionListener(e -> reset());

        choices.add(setStart);
        choices.add(setStop);
        choices.add(addWall);
        choices.add(removeWall);
        choices.add(findPath);
        choices.add(reset);

        controlFrame.add(choices);
        controlFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        controlFrame.setSize(300, 300);
        return controlFrame;
    }

    public int[][] createMaze(Cell[][] cells) {
        int[][] maze = new int[5][5];
        for (int i = 0; i < 25; i++) {
            maze[i / 5][i % 5] = cells[i / 5][i % 5].getValue();
        }
        return maze;
    }

    public void addStart() {
        clearMouseListeners();
        if(startCell == null){
            JFrame popupFrame = new JFrame("Choose Start Cell");
            popupFrame.setSize(200, 100);
            popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            JLabel label = new JLabel("Click on the MazeFrame to choose a start cell.");
            popupFrame.setLayout(new GridLayout(1, 1));
            popupFrame.add(label);
            popupFrame.setVisible(true);
    
            mazeFrame.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int row = e.getY() / Cell.CELL_SIZE;
                    int col = e.getX() / Cell.CELL_SIZE;
                    if (row >= 0 && row < 5 && col >= 0 && col < 5) {
                        startCell = cells[row][col];
                        startCell.addStart();
                        startCell = cells[row][col];
                        mazeFrame.getCells().get(row * 5 + col).addStart(); 
                        mazeFrame.getCells().get(row * 5 + col).repaint(); 
                        popupFrame.dispose();
                    }
                    clearMouseListeners();
                }
            });
        }
        else{
            JFrame popupFrame = new JFrame("Existing Start Cell");
            popupFrame.setSize(200, 100);
            popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            JLabel label = new JLabel("!! You already have a start cell set !!");
            popupFrame.setLayout(new GridLayout(1, 1));
            popupFrame.add(label);
            popupFrame.setVisible(true);
        }
       
    }

    public void addEnd() {
        clearMouseListeners();
        if(endCell == null){
            JFrame popupFrame = new JFrame("Choose End Cell");
            popupFrame.setSize(200, 100);
            popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            JLabel label = new JLabel("Click on the MazeFrame to choose an end cell.");
            popupFrame.setLayout(new GridLayout(1, 1));
            popupFrame.add(label);
            popupFrame.setVisible(true);
    
            this.mazeFrame.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int row = e.getY() / Cell.CELL_SIZE;
                    int col = e.getX() / Cell.CELL_SIZE;
                    if (row >= 0 && row < 5 && col >= 0 && col < 5) {
                        endCell = cells[row][col];
                        endCell.addEnd();
                        endCell = cells[row][col];
                        mazeFrame.getCells().get(row * 5 + col).addEnd(); 
                        mazeFrame.getCells().get(row * 5 + col).repaint(); 
                        popupFrame.dispose();
                    }
                    clearMouseListeners();
                }
            });
        }
        else{
            JFrame popupFrame = new JFrame("Existing End Cell");
            popupFrame.setSize(200, 100);
            popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            JLabel label = new JLabel("!! You already have a end cell set !!");
            popupFrame.setLayout(new GridLayout(1, 1));
            popupFrame.add(label);
            popupFrame.setVisible(true);
        }

    }

    public void addWall() {
        clearMouseListeners();
        JFrame popupFrame = new JFrame("Choose Wall Cell");
        popupFrame.setSize(200, 100);
        popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JLabel label = new JLabel("Click on the MazeFrame to choose a wall cell.");
        popupFrame.setLayout(new GridLayout(1, 1));
        popupFrame.add(label);
        popupFrame.setVisible(true);

        mazeFrame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = e.getY() / Cell.CELL_SIZE;
                int col = e.getX() / Cell.CELL_SIZE;
                while(cells[row][col].hasStart() && cells[row][col].hasEnd()){
                    JFrame errorFrame = new JFrame("Invalid Wall Placement");
                    errorFrame.setSize(300, 100);
                    errorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    JLabel errorLabel = new JLabel("You cannot add walls to start or end cells. Please choose again.");
                    errorFrame.setLayout(new GridLayout(1, 1));
                    errorFrame.add(errorLabel);
                    errorFrame.setVisible(true);
                    return;
                }
                if (row >= 0 && row < 5 && col >= 0 && col < 5) {
                    cells[row][col].addWall();
                    mazeFrame.getCells().get(row * 5 + col).addWall();
                    popupFrame.dispose();
                }
                
            }
        });
    }

    public void removeWall() {
        clearMouseListeners();
        JFrame popupFrame = new JFrame("Remove Wall Cell");
        popupFrame.setSize(200, 100);
        popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JLabel label = new JLabel("Click on the MazeFrame to remove a wall cell.");
        popupFrame.setLayout(new GridLayout(1, 1));
        popupFrame.add(label);
        popupFrame.setVisible(true);

        mazeFrame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = e.getY() / Cell.CELL_SIZE;
                int col = e.getX() / Cell.CELL_SIZE;
                if (row >= 0 && row < 5 && col >= 0 && col < 5) {
                    cells[row][col].removeWall();
                    popupFrame.dispose();
                }
            }
        });
    }

    public void reset() {
        clearMouseListeners();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                this.cells[i][j].reset();
                mazeFrame.getCells().get(i * 5 + j).reset(); 
                this.cells[i][j].repaint();
            }
        }
        startCell = null;
        endCell = null;
    }
    

    public Cell getCellFromRowAndColumn(Cell[][] cells, int row, int column) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == row && j == column) {
                    return cells[i][j];
                }
            }
        }
        return null;
    }

    private void clearMouseListeners() {
        for (MouseListener listener : mazeFrame.getMouseListeners()) {
            mazeFrame.removeMouseListener(listener);
        }
    }
    

     public void findPath() {
        if (startCell == null || endCell == null) {
            JOptionPane.showMessageDialog(null, "Start or End cell is not set!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        optimalPath = null;
        
        boolean[][] visited = new boolean[cells.length][cells[0].length];
        ArrayList <Cell> currentPath = new ArrayList<>();
        
        solveMaze(startCell.getRow(), startCell.getColumn(), visited, currentPath);

        if (optimalPath != null) {
            String pathStr = "Optimal path found:\n";
            for (Cell cell : optimalPath) {
                pathStr += "(" + cell.getRow() + ", " + cell.getColumn() + ") ";
                cell.setPath();
            }
            pathStr += "\nPath length: " + optimalPath.size();
            JOptionPane.showMessageDialog(null, pathStr, "Path Found", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No path found from start to end.", "Path Not Found", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void solveMaze(int row, int col, boolean[][] visited, ArrayList<Cell> currentPath) {
        
        if (row < 0 || row >= cells.length || col < 0 || col >= cells[0].length) {
            return;
        }
        
        if (cells[row][col].getValue() == 0) {
            return;
        }
        if (visited[row][col]) {
            return;
        }
        
        currentPath.add(cells[row][col]);
        
        if (cells[row][col] == endCell || cells[row][col].getValue() == 3) {
            if (optimalPath == null || currentPath.size() < optimalPath.size()) {
                optimalPath = new ArrayList<>(currentPath);
            }
            currentPath.remove(currentPath.size() - 1);
            return;
        }
        
        visited[row][col] = true;

        solveMaze(row - 1, col, visited, currentPath); 
        solveMaze(row + 1, col, visited, currentPath); 
        solveMaze(row, col - 1, visited, currentPath); 
        solveMaze(row, col + 1, visited, currentPath);
      
        visited[row][col] = false;
        currentPath.remove(currentPath.size() - 1);
    }

    public void playMusic() {
        try {
            File musicFile = new File("videoplayback.wav");
            if (musicFile.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                clip.start();
            } else {
                System.out.println("Music file not found: " + musicFile.getAbsolutePath());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Maze maze = new Maze();
        maze.playMusic();
    }
}