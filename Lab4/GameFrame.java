import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class GameFrame extends JFrame {
    private Game game;
    private JTextArea infoLog;
    private Timer logUpdater;
    
    public GameFrame(Game game) {
        this.game = game;
        setTitle("Monopoly Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mapPanel = game.getMapPanel();
        JPanel controlPanel = game.getControlPanel();
        infoLog = new JTextArea(15,30); 
        infoLog.setEditable(false);
        JScrollPane logScroll = new JScrollPane(infoLog);

        setLayout(new BorderLayout());
        add(mapPanel, BorderLayout.WEST);
        add(logScroll, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.EAST);
        logUpdater = new Timer(1000, e -> updateLog());
        logUpdater.start();  
        pack();
        setLocationRelativeTo(null); 
        setVisible(true);
        
        SwingUtilities.invokeLater(() -> game.start()); 

    }

    private void updateLog() {
        ArrayList<String> logs = game.getLogMessages();
        infoLog.setText(""); 
        for (String log : logs) {
            infoLog.append(log + "\n");
        }
        infoLog.setCaretPosition(infoLog.getDocument().getLength()); 
    }
    
}
    