package Lab5;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class MazeFrame extends JFrame {
    private Maze maze;
    private ArrayList <Cell> cells;
    private Cell startCell;
    private Cell endCell;
    private ArrayList<Cell> optimalPath = null;
    public MazeFrame(Maze maze) {
        this.maze = maze;
        this.cells = new ArrayList<>(); 
        this.setTitle("Maze Frame");
        this.setLayout(new BorderLayout());
    
        JPanel mazePanel = new JPanel(new GridLayout(5, 5, 2, 2));
        mazePanel.setPreferredSize(new Dimension(500, 500));
    
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                Cell cell = this.getMaze().getCells()[row][col];
                mazePanel.add(cell);
                this.cells.add(cell); 
            }
        }
        this.add(mazePanel, BorderLayout.CENTER);
    
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public Maze getMaze() {
        return this.maze;
    }
    public ArrayList<Cell> getCells() {
        return this.cells;
    }
}