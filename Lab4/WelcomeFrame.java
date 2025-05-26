import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WelcomeFrame extends JFrame {
    private JTextField[] playerFields = new JTextField[4];
    private GameStartListener listener;

    public WelcomeFrame(Game game) {
        setTitle("Welcome to Simple Monopoly");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Define labels with colored text and text fields for each player
        Color[] colors = {Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN};
        for (int i = 0; i < 4; i++) {
            JLabel label = new JLabel(getColorName(colors[i]) + " Player :");
            label.setForeground(colors[i]);
            playerFields[i] = new JTextField("");
            inputPanel.add(label);
            inputPanel.add(playerFields[i]);
        }
        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Collect player names
                String[] names = new String[4];
                for (int i = 0; i < 4; i++) {
                    names[i] = playerFields[i].getText().trim();
                }
                for(int i = 0; i < game.getPlayers().size(); i++){
                    game.getPlayers().get(i).setName(names[i]);
                }
                if (listener != null) {
                    listener.onGameStart(game);
                }
                game.getMapPanel().updateMap();
                game.getMapPanel().refreshScoreboard();
                new GameFrame(game);
                dispose();
            }
        });
        
        inputPanel.add(startButton);
        
        add(inputPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private String getColorName(Color color) {
        if (color.equals(Color.RED)) {
            return "Red";
        } else if (color.equals(Color.BLUE)) {
            return "Blue";
        } else if (color.equals(Color.YELLOW)) {
            return "Yellow";
        } else if (color.equals(Color.GREEN)) {
            return "Green";
        }
        return "Unknown";
    }

    public void setGameStartListener(GameStartListener listener) {
        this.listener = listener;
    }
    
    // For testing purposes (launch the welcome screen)
    public static void main(String[] args) {
        Game game = new Game();
        SwingUtilities.invokeLater(() -> new WelcomeFrame(game));
    }
}