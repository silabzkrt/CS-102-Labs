package Lab5;

import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Cell extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.img != null) {
            g.drawImage(this.img, 0, 0, CELL_SIZE, CELL_SIZE, null);
        }
        if (this.onPath){
            this.setBackground(Color.GREEN);
        }
        else{
            this.setBackground(Color.WHITE);
        }
    }
    private int row;
    private int column;
    private int x;
    private int y;
    private boolean hasStart;
    private boolean hasEnd;
    private boolean hasWall;
    private boolean onPath;
    private int value;
    private Point point;
    BufferedImage mouse;
    BufferedImage cheese;
    BufferedImage wall;
    private BufferedImage img;
    final static int CELL_SIZE = 100; 

    public Cell(int row, int column){
        this.row = row;
        this.column = column;
        this.x = (column) * 20;
        this.y = (row) * 20;  
        this.point = new Point(this.x, this.y);
        this.hasStart = false;
        this.hasEnd = false;
        this.hasWall = false;
        this.onPath = false;
        this.value = 1;
        this.img = null;
        try {
            mouse = ImageIO.read(new File("mouse.png"));
            cheese = ImageIO.read(new File("cheese.png"));
            wall = ImageIO.read(new File("wall.png"));
            } catch (IOException e) {
            System.out.println(e);
        }
    }
    public BufferedImage getImage(){
        return this.img;
    }
    public void setImage(BufferedImage image){
        this.img = image;
    }
    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean hasStart() {
        this.value = 2;
        return hasStart;
    }

    public boolean hasEnd() {
        this.value = 3;
        return hasEnd;
    }

    public boolean hasWall() {
        return hasWall;
    }

    public int getValue() {
        return value;
    }

    public Point getPoint() {
        return this.point;
    }

    public void addStart() {
        this.setImage(mouse);
        this.hasStart = true;
        this.value = 2;
        this.revalidate();
        this.repaint();
    }

    public void addEnd() {
        this.setImage(cheese);
        this.hasEnd = true;
        this.value = 3;
        this.revalidate();
        this.repaint();
    }

    public void addWall() {
        this.setImage(wall);
        this.hasWall = true;
        this.value = 0;
        this.revalidate();
        this.repaint();
    }

    public void removeWall() {
        if (this.hasWall) {
            this.setImage(null);
            this.hasWall = false;
            this.value = 1;
            this.revalidate();
            this.repaint();
        } else {
            JOptionPane.showMessageDialog(null, "No wall to remove here",
                    "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void setRow(int row) {
        this.row = row;
        this.y = row * 20;
        this.point = new Point(this.x, this.y);
    }

    public void setColumn(int column) {
        this.column = column;
        this.x = column * 20;
        this.point = new Point(this.x, this.y);
    }

    public void setX(int x) {
        this.x = x;
        this.point = new Point(this.x, this.y);
    }

    public void setY(int y) {
        this.y = y;
        this.point = new Point(this.x, this.y);
    }

    public void setHasStart(boolean hasStart) {
        this.hasStart = hasStart;
        this.value = hasStart ? 2 : 1;
    }

    public void setHasEnd(boolean hasEnd) {
        this.hasEnd = hasEnd;
        this.value = hasEnd ? 3 : 1;
    }

    public void setHasWall(boolean hasWall) {
        this.hasWall = hasWall;
        this.value = hasWall ? 0 : 1;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void reset() {
        this.setImage(null);
        this.hasStart = false;
        this.hasEnd = false;
        this.hasWall = false;
        this.onPath = false;
        this.value = 1;
    }

    public void setPath(){
        this.onPath = true;
        this.revalidate();
        this.repaint();
    }
}