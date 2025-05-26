import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;


public class CellPanel extends JPanel {
    private final Game game;
    private final Cell cell;
    private JLabel nameLabel;
    public CellPanel(Cell cell, Game game) {
    this.cell = cell;
    this.game = game;
    setPreferredSize(new Dimension(100, 100));
    setBorder(BorderFactory.createLineBorder(Color.BLACK));
    
    // Use a layout that centers the label
    setLayout(new BorderLayout());
    nameLabel = new JLabel(cell.getName(), SwingConstants.CENTER);
    add(nameLabel, BorderLayout.CENTER);
    
    // Schedule updateCell to run after the GUI is set up.
    SwingUtilities.invokeLater(this::updateCell);
    }

    // Update the cell colors 
    public void updateCell() {
        if (cell.getOwner() != null) {
            setBackground(cell.getOwner().getColor());
        } else {
            setBackground(cell.getColor());
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        // First let the panel paint its background and label
        super.paintComponent(g);
        
        // Create a list to hold all players currently on this cell
        ArrayList<Player> playersOnCell = new ArrayList<>();
        for (Player p : game.getPlayers()) {
            if (p.getCurrentCell() == cell) {
                playersOnCell.add(p);
            }
        }
        
        int count = playersOnCell.size();
        if (count == 0) return;
        
        // Define the token (circle) size.
        int tokenSize = 20;
        int w = getWidth();
        int h = getHeight();
        int x = 0, y = 0;
        
        // For a maximum of four players, choose positions so the tokens do not completely overlap.
        for (int i = 0; i < count; i++) {
            Player p = playersOnCell.get(i);
            switch(count) {
                case 1:
                    x = w / 2 - tokenSize / 2;
                    y = h / 2 - tokenSize / 2;
                    break;
                case 2:
                    if (i == 0) {
                        x = w / 4 - tokenSize / 2;
                        y = h / 2 - tokenSize / 2;
                    } else {
                        x = (3 * w) / 4 - tokenSize / 2;
                        y = h / 2 - tokenSize / 2;
                    }
                    break;
                case 3:
                    if (i == 0) {
                        x = w / 4 - tokenSize / 2;
                        y = h / 3 - tokenSize / 2;
                    } else if (i == 1) {
                        x = (3 * w) / 4 - tokenSize / 2;
                        y = h / 3 - tokenSize / 2;
                    } else {
                        x = w / 2 - tokenSize / 2;
                        y = (2 * h) / 3 - tokenSize / 2;
                    }
                    break;
                case 4:
                    if (i == 0) {
                        x = w / 4 - tokenSize / 2;
                        y = h / 4 - tokenSize / 2;
                    } else if (i == 1) {
                        x = (3 * w) / 4 - tokenSize / 2;
                        y = h / 4 - tokenSize / 2;
                    } else if (i == 2) {
                        x = w / 4 - tokenSize / 2;
                        y = (3 * h) / 4 - tokenSize / 2;
                    } else {
                        x = (3 * w) / 4 - tokenSize / 2;
                        y = (3 * h) / 4 - tokenSize / 2;
                    }
                    break;
                default:
                    x = w / 2 - tokenSize / 2;
                    y = h / 2 - tokenSize / 2;
                    break;
            }
            g.setColor(p.getColor());
            g.fillOval(x, y, tokenSize, tokenSize);
            g.setColor(Color.black);
            g.drawOval(x, y, tokenSize, tokenSize);
        }
    } 
}