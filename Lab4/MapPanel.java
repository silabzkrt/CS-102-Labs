import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class MapPanel extends JPanel {
    private Game game;
    private ArrayList<CellPanel> propertyPanels; // holds the 16 property cells
    private JPanel centerPanel;
    private JLabel[] scoreBoard;

    public MapPanel(Game game) {
        this.game = game;
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setPreferredSize(new Dimension(600, 600));
        
        propertyPanels = new ArrayList<>();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;
        
        ArrayList<Cell> cells = game.getCells();
        int cellIndex = 0;
        for (int col = 0; col < 5; col++) {
            gbc.gridx = col;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            CellPanel cp = new CellPanel(cells.get(cellIndex),this.game);
            propertyPanels.add(cp);
            add(cp, gbc);
            cellIndex++;
        }
        for (int row = 1; row <= 3; row++) {
            gbc.gridx = 4;
            gbc.gridy = row;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            CellPanel cp = new CellPanel(cells.get(cellIndex),this.game);
            propertyPanels.add(cp);
            add(cp, gbc);
            cellIndex++;
        }
        
        for (int col = 4; col >= 0; col--) {
            gbc.gridx = col;
            gbc.gridy = 4;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            CellPanel cp = new CellPanel(cells.get(cellIndex),this.game);
            propertyPanels.add(cp);
            add(cp, gbc);
            cellIndex++;
        }
        
        for (int row = 3; row >= 1; row--) {
            gbc.gridx = 0;
            gbc.gridy = row;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            CellPanel cp = new CellPanel(cells.get(cellIndex),this.game);
            propertyPanels.add(cp);
            add(cp, gbc);
            cellIndex++;
        }
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.gridheight = 3;
        centerPanel = new JPanel();
        centerPanel.setBackground(Color.DARK_GRAY);
        int numPlayers = game.getPlayers().size();
        centerPanel.setLayout(new GridLayout(numPlayers, 1, 5, 5));
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        JLabel centerLabel = new JLabel("User: " + game.findUser().getName(), SwingConstants.CENTER);
        
        scoreBoard = new JLabel[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            Player p = game.getPlayers().get(i);
            scoreBoard[i] = new JLabel(p.getName() + ": " + p.getMoney(), SwingConstants.CENTER);
            scoreBoard[i].setFont(new Font("Arial", Font.PLAIN, 36));
            scoreBoard[i].setForeground(p.getColor());
            scoreBoard[i].setHorizontalAlignment(SwingConstants.CENTER);
            centerPanel.add(scoreBoard[i]);
        }
        centerLabel.setForeground(Color.WHITE);
        centerPanel.add(centerLabel, BorderLayout.CENTER);
        centerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(centerPanel, gbc);
    }
    
    // Call this method after each turn to update the board display.
    public void updateMap() {
        // Update each property cell panel
        for (CellPanel cp : propertyPanels) {
            cp.updateCell();
        }
        // Optionally update centerPanel components here if needed.
        revalidate();
        repaint();
    }

    public void refreshScoreboard() {
        int numPlayers = game.getPlayers().size();
        JLabel centerLabel = (JLabel) centerPanel.getComponent(centerPanel.getComponentCount() - 1);
        for (int i = 0; i < numPlayers; i++) {
            Player p = game.getPlayers().get(i);
            scoreBoard[i].setText(p.getName() + ": " + p.getMoney());
        }
    }
}