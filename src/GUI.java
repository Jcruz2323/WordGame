import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GUI extends JFrame {
    private ArrayList<Players> players;
    private Hosts host;
    private JLabel currentPlayersLabel;
    private JLabel currentHostLabel;
    private JLabel currentPhraseLabel;
    private JMenuBar menuBar;

    public GUI() {
        players = new ArrayList<>();
        host = null;

        setTitle("Word Game GUI");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        setupMenuBar();
        setupPlayersPanel();
        setupHostPanel();
        setupPhrasePanel();
        setupStartPanel();

        setVisible(true);
    }

    private void setupMenuBar() {
        // create and register the menu bar
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // create and add the Game menu, accessible by Alt+G
        JMenu game = new JMenu("Game");
        game.setMnemonic(KeyEvent.VK_G);
        menuBar.add(game);

        // create Add Player menu item
        JMenuItem addPlayer = new JMenuItem("Add Player");
        addPlayer.addActionListener(e -> addPlayer());
        game.add(addPlayer);

        // create Set Host menu item
        JMenuItem setHost = new JMenuItem("Set Host");
        setHost.addActionListener(e -> setHost());
        game.add(setHost);

        JMenu about = new JMenu("About");
        about.setMnemonic(KeyEvent.VK_A);
        menuBar.add(about);

        JMenuItem layout = new JMenuItem("Layout");
        about.add(layout);
        layout.addActionListener(e -> JOptionPane.showMessageDialog(
                this,
                "I used the GridLayout so the content would be displayed in a table like format and be " +
                        "easier to see the players, host, phrase, and start button clearly."
        ));
    }

    private void setupPlayersPanel() {
        JPanel panel = new JPanel();
        add(panel);
        panel.setLayout(new GridLayout(1, 2));

        JLabel playersTitle = new JLabel("Players:");
        playersTitle.setHorizontalAlignment(SwingConstants.RIGHT);
        playersTitle.setBorder(new EmptyBorder(0, 0, 0, 20));
        playersTitle.setFont(playersTitle.getFont().deriveFont(Font.BOLD));
        panel.add(playersTitle);

        currentPlayersLabel = new JLabel("None");
        panel.add(currentPlayersLabel);
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
        StringBuilder sb = new StringBuilder("<html>");
        if (players.isEmpty()) {
            sb.append("None");
        } else {
            for (Players p : players) {
                sb.append("%s ($%,d)".formatted(p.getFirstName(), p.getCurrentAmountOfMoney())).append(", ");
            }
            sb.setLength(sb.length() - 2);
        }

        sb.append("</html>");

        currentPlayersLabel.setText(sb.toString());
    }

    private void setupHostPanel() {
        JPanel panel = new JPanel();
        add(panel);
        panel.setLayout(new GridLayout(1, 2));

        JLabel hostLabel = new JLabel("Host:");
        hostLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        hostLabel.setBorder(new EmptyBorder(0, 0, 0, 20));
        hostLabel.setFont(hostLabel.getFont().deriveFont(Font.BOLD));
        panel.add(hostLabel);

        currentHostLabel = new JLabel("None");
        panel.add(currentHostLabel);
    }

    private void setHost() {
        String hostName = JOptionPane.showInputDialog(this, "Enter Host Name:");
        if (hostName == null || hostName.trim().isEmpty()) return;

        String gamePhrase = JOptionPane.showInputDialog(this, "Enter Game Phrase:");
        if (gamePhrase == null || gamePhrase.trim().isEmpty()) return;

        host = new Hosts(hostName.trim(),gamePhrase.trim() );

        currentHostLabel.setText(host.getFirstName());
        updatePhraseLabel(host.getPlayingPhrase());
    }

    private void setupPhrasePanel() {
        JPanel panel = new JPanel();
        add(panel);
        panel.setLayout(new GridLayout(1, 2));

        JLabel phraseLabel = new JLabel("Phrase:");
        phraseLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        phraseLabel.setBorder(new EmptyBorder(0, 0, 0, 20));
        phraseLabel.setFont(phraseLabel.getFont().deriveFont(Font.BOLD));
        panel.add(phraseLabel);

        currentPhraseLabel = new JLabel("None");
        panel.add(currentPhraseLabel);
    }

    private void updatePhraseLabel(String playingPhrase) {
        currentPhraseLabel.setText(playingPhrase);
    }

    private void setupStartPanel() {
        JPanel panel = new JPanel();
        add(panel);

        JButton startTurnsButton = new JButton("Start Player Turns");
        startTurnsButton.setPreferredSize(new Dimension(400, 50));
        panel.add(startTurnsButton);
        startTurnsButton.addActionListener(e -> startTurns());
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