import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GUI extends JFrame {
    private ArrayList<Players> players;
    private Hosts host;
    private JLabel playersLabel;
    private JLabel hostLabel;
    private JLabel phraseLabel;

    public GUI() {
        players = new ArrayList<>();
        host = null;

        setTitle("Word Game GUI");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        // Label showing players
        playersLabel = new JLabel("Players: None");
        add(playersLabel);

        // Button to add player
        JButton addPlayerButton = new JButton("Add Player");
        add(addPlayerButton);
        addPlayerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addPlayer();
            }
        });

        hostLabel = new JLabel("Host: None");
        add(hostLabel);

        JButton setHostButton = new JButton("Set Host");
        add(setHostButton);
        setHostButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setHost();
            }
        });

        phraseLabel = new JLabel("Phrase: ");
        add(phraseLabel);

        JButton startTurnsButton = new JButton("Start Player Turns");
        add(startTurnsButton);
        startTurnsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startTurns();
            }
        });

        setVisible(true);
    }

    private void addPlayer() {
        String playerName = JOptionPane.showInputDialog(this, "Enter Player Name:");
        if (playerName != null && !playerName.trim().isEmpty()) {
            Players newPlayer = new Players(playerName.trim());
            players.add(newPlayer);
            updatePlayersLabel();
        }
    }

    private void updatePlayersLabel() {
        StringBuilder sb = new StringBuilder("Players: ");
        if (players.isEmpty()) {
            sb.append("None");
        } else {
            for (Players p : players) {
                sb.append(p.getFirstName()).append(", ");
            }
            sb.setLength(sb.length() - 2);
        }
        playersLabel.setText(sb.toString());
    }

    private void setHost() {
        String hostName = JOptionPane.showInputDialog(this, "Enter Host Name:");
        if (hostName == null || hostName.trim().isEmpty()) return;

        String gamePhrase = JOptionPane.showInputDialog(this, "Enter Game Phrase:");
        if (gamePhrase == null || gamePhrase.trim().isEmpty()) return;

        host = new Hosts(hostName.trim(),gamePhrase.trim() );

        hostLabel.setText("Host: " + host.getFirstName());
        updatePhraseLabel(host.getPlayingPhrase());
    }

    private void updatePhraseLabel(String playingPhrase) {
        phraseLabel.setText("Phrase: " + playingPhrase);
    }

    private void startTurns() {
        if (players.isEmpty() || host == null) {
            JOptionPane.showMessageDialog(this, "Add players and set host first!");
            return;
        }

        for (Players player : players) {
            String guess = JOptionPane.showInputDialog(this, player.getFirstName() + ", enter your guess:");
            if (guess == null) break;

            boolean correct = host.checkGuess(guess);
            if (correct) {
                player.setCurrentAmountOfMoney(player.getCurrentAmountOfMoney() + 100);
                JOptionPane.showMessageDialog(this, "Correct! " + player.getFirstName() +
                        " wins 100 points. Total: $" + player.getCurrentAmountOfMoney());
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect guess.");
            }

            updatePhraseLabel(host.getPlayingPhrase());

            if (host.isGameWon()) {
                int response = JOptionPane.showConfirmDialog(this, player.getFirstName() + " won! Play again?",
                        "Play Again?", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.NO_OPTION) {
                    System.exit(0);
                } else {
                }
                break;
            }
        }
    }

    public static void main(String[] args) {
        new GUI();
    }
}