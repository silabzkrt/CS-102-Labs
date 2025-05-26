import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ControlPanel extends JPanel {
    private Game game;
    private JButton roll;
    private JButton buy;
    private JButton sell;
    private JButton build;
    private JButton endTurn;

    private Cell selectedCellToSell; // Variable to store the selected cell to sell
    private boolean sellFrameOpen = false; // Flag to track if the sell frame is open
    private boolean buildFrameOpen = false; // Flag to track if the build frame is open
    private Cell selectedCellToBuild; // Variable to store the selected cell to build

    public ControlPanel(Game game) {
        this.game = game;
        JPanel choicePanel = new JPanel(new GridLayout(5, 1));
        choicePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setPreferredSize(new Dimension(600, 600));
        this.roll = addRollButton();
        this.buy = addBuyButton();
        this.sell = addSellButton();
        this.build = addBuildButton();
        this.endTurn = addEndTurnButton();
        choicePanel.add(roll);
        choicePanel.add(buy);
        choicePanel.add(sell);
        choicePanel.add(build);
        choicePanel.add(endTurn);
        this.add(choicePanel);
        disableUserTurnControls();
    }

    public JButton addRollButton() {
        JButton roll = new JButton("Roll");
        roll.setPreferredSize(new Dimension(600, 120));
        roll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.findUser().rollDice();
                enableUserTurnControls();
                handlePostRollActions();
            }
        });
        return roll;
    }

    public JButton addBuyButton() {
        JButton buy = new JButton("Buy");
        buy.setPreferredSize(new Dimension(600, 120));
        buy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.findUser().buyCell();
                disableUserTurnControls();
            }
        });
        return buy;
    }

    public JButton addSellButton() {
        JButton sell = new JButton("Sell");
        sell.setPreferredSize(new Dimension(600, 120));
        sell.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSellFrame();
            }
        });
        return sell;
    }

    public JButton addBuildButton() {
        JButton build = new JButton("Build");
        build.setPreferredSize(new Dimension(600, 120));
        build.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openBuildFrame();
            }
        });
        return build;
    }

    public JButton addEndTurnButton() {
        JButton endTurn = new JButton("End Turn");
        endTurn.setPreferredSize(new Dimension(600, 120));
        endTurn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.logMessage("User ended turn. Moving to next player...");
                game.nextPlayer(); 
            }
        });
        return endTurn;
    }
    

    public void enableUserTurnControls() {
        roll.setEnabled(false);
        buy.setEnabled(true);
        sell.setEnabled(true);
        build.setEnabled(true);
        endTurn.setEnabled(true);
        game.markUserTurnCompleted(false);
    }

    public void disableUserTurnControls() {
        roll.setEnabled(false);
        buy.setEnabled(false);
        sell.setEnabled(false);
        build.setEnabled(false);
        endTurn.setEnabled(false);
    }

    public void handlePostRollActions() {
        Player currentPlayer = game.findUser();
        Cell currentCell = currentPlayer.getCurrentCell();
        if (currentCell.getOwner() != null && !currentCell.getOwner().equals(currentPlayer)) {
            currentPlayer.payRent();
        }
    }

    public void handleEndTurn() {
        disableUserTurnControls();
        Player user = game.findUser();
        if (user != null) {
            user.endTurn();
        } else {
            game.nextPlayer();
        }
        game.logMessage("Turn ended. Moving to next player...");
    }
    

    public void openSellFrame() {
        if (sellFrameOpen) return;
        sellFrameOpen = true;

        JFrame sellFrame = new JFrame("Choose a Property to Sell");
        sellFrame.setSize(400, 300);
        sellFrame.setLayout(new GridLayout(0, 1));
        for (Cell cell : game.findUser().getCells()) {
            JButton cellButton = new JButton(cell.getName()+ " -> " + cell.getPrice());
            cellButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectedCellToSell = cell;
                    sellFrame.dispose();
                    sellFrameOpen = false;
                    game.findUser().sellHouse(selectedCellToSell);
                }
            });
            sellFrame.add(cellButton);
        }

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            sellFrame.dispose();
            sellFrameOpen = false;
        });
        sellFrame.add(cancelButton);

        sellFrame.setVisible(true);
    }

    public void openBuildFrame() {
        if (buildFrameOpen) return;
        buildFrameOpen = true;

        JFrame buildFrame = new JFrame("Choose a Property to Build On");
        buildFrame.setSize(400, 300);
        buildFrame.setLayout(new GridLayout(0, 1));

        for (Cell cell : game.findUser().getCells()) {
            JButton cellButton = new JButton(cell.getName() + " - Build Cost: " + cell.getHouseCost());
            cellButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectedCellToBuild = cell;
                    buildFrame.dispose();
                    buildFrameOpen = false;
                    game.findUser().buildHouse(selectedCellToBuild);
                }
            });
            buildFrame.add(cellButton);
        }

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            buildFrame.dispose();
            buildFrameOpen = false;
        });
        buildFrame.add(cancelButton);

        buildFrame.setVisible(true);
    }
    public Game getGame() {
        return game;
    }

    public void playUser() {
        roll.setEnabled(true);
        roll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enableUserTurnControls();
                handlePostRollActions();
                if(game.findUser().getCurrentCell() instanceof SpecialEventCell && game.getRound() > 1){
                    SpecialEventCell specialCell = (SpecialEventCell) game.findUser().getCurrentCell();
                    
                }
                else{
                    if (game.findUser().getCurrentCell().getOwner() == null) {
                        buy.setEnabled(true);
                        sell.setEnabled(true);
                        build.setEnabled(true);
                    }
                }

            }
        });

        buy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.findUser().buyCell();
                SwingUtilities.invokeLater(() -> {
                    handleEndTurn();            
                });
            }
        });

        sell.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSellFrame();
                SwingUtilities.invokeLater(() -> {
                    handleEndTurn();            
                });
            }
        });

        build.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openBuildFrame();
                SwingUtilities.invokeLater(() -> {
                    handleEndTurn();            
                });
            }
        });

        endTurn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleEndTurn();
            }
        });
        game.markUserTurnCompleted(true);
    }
    public void updateCellCosts() {
        if (sellFrameOpen || buildFrameOpen) {
            SwingUtilities.invokeLater(() -> {
                if (sellFrameOpen) {
                    openSellFrame();
                }
                if (buildFrameOpen) {
                    openBuildFrame();
                }
            });
        }
    }
}

