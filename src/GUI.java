
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GUI extends JFrame {

    private ArrayList<Players> players;
    private Hosts host;

    private JLabel currentPlayersLabel;
    private JLabel currentHostLabel;
    private JLabel currentPhraseLabel;

    private JMenuBar menuBar;
    private JTextArea messages;
    private JCheckBox saveMessages;

    private Clip musicClip;
    private PrizeAnimation animationPanel;

    private final Random random = new Random();

    private static final String RESOURCE_FOLDER = "resources/";

    public GUI() {

        players = new ArrayList<>();
        host = null;

        setTitle("Word Game GUI");
        setSize(650, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        setupMenuBar();
        setupPlayersPanel();
        setupHostPanel();
        setupPhrasePanel();
        setupStartPanel();
        setupMessagePanel();

        setLocationRelativeTo(null);
        setVisible(true);
    }


    private File findResourceFile(String filename) {

        File file = new File(RESOURCE_FOLDER + filename);

        if (file.exists()) {
            System.out.println("Found file: " + file.getAbsolutePath());
            return file;
        }

        file = new File("../" + RESOURCE_FOLDER + filename);

        if (file.exists()) {
            System.out.println("Found file: " + file.getAbsolutePath());
            return file;
        }

        file = new File("../../" + RESOURCE_FOLDER + filename);

        if (file.exists()) {
            System.out.println("Found file: " + file.getAbsolutePath());
            return file;
        }

        System.out.println("FILE NOT FOUND: " + filename);
        System.out.println("Current working directory: "
                + System.getProperty("user.dir"));

        return null;
    }

    private void setupMenuBar() {

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu game = new JMenu("Game");
        game.setMnemonic(KeyEvent.VK_G);
        menuBar.add(game);

        JMenuItem addPlayer = new JMenuItem("Add Player");
        addPlayer.addActionListener(e -> addPlayer());
        game.add(addPlayer);

        JMenuItem setHost = new JMenuItem("Set Host");
        setHost.addActionListener(e -> setHost());
        game.add(setHost);

        JMenu about = new JMenu("About");
        about.setMnemonic(KeyEvent.VK_A);
        menuBar.add(about);

        JMenuItem layout = new JMenuItem("Layout");

        layout.addActionListener(e -> JOptionPane.showMessageDialog(
                this,
                "I used GridLayout to organize the players, host, phrase, "
                        + "start button, and messages."
        ));

        about.add(layout);

        JMenuItem attribution = new JMenuItem("Attribution");

        attribution.addActionListener(e -> showAttribution());

        about.add(attribution);
    }

    private void showAttribution() {

        String credits =
                "WORD GAME - MEDIA ATTRIBUTION\n\n"

                        + "IMAGES:\n"
                        + "Laptop.jpg - Creator: Pexels\n"
                        + "URL: https://pixabay.com/photos/laptop-apple-computer-desk-macbook-1846277/\n\n"
                        + "NewBoat.jpg - Creator: Andypoland\n"
                        + "URL: https://pixabay.com/photos/boat-beach-sea-ship-sailing-yacht-4418919/\n\n"
                        + "newCar.jpg - Creator: lusepho\n"
                        + "URL: https://pixabay.com/photos/tank-400-tank-suv-great-wall-motor-8494825/\n\n"
                        + "Newhouse.jpg - Creator: not listed\n"
                        + "URL: https://pixabay.com/photos/house-new-building-home-apartment-66627/\n\n"
                        + "Trip4two.jpg - Creator: Pamjpat\n"
                        + "URL: https://pixabay.com/photos/passport-map-world-trip-tourism-2714675/\n\n"

                        + "BACKGROUND MUSIC:\n"
                        + "Music creator: veeru5656\n"
                        + "Music source: Ahttps://freesound.org/people/veeru5656/sounds/367536/\n"
                        + "Music License: Creative Commons 0.\n";

        JTextArea creditsArea = new JTextArea(credits);

        creditsArea.setEditable(false);
        creditsArea.setLineWrap(true);
        creditsArea.setWrapStyleWord(true);
        creditsArea.setFont(new Font("Arial", Font.PLAIN, 13));

        JOptionPane.showMessageDialog(
                this,
                new JScrollPane(creditsArea),
                "Media Attribution",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
    private void setupPlayersPanel() {

        JPanel panel = new JPanel();
        add(panel);

        panel.setLayout(new GridLayout(1, 2));

        JLabel playersTitle = new JLabel("Players:");

        playersTitle.setHorizontalAlignment(SwingConstants.RIGHT);
        playersTitle.setBorder(new EmptyBorder(0, 0, 0, 20));
        playersTitle.setFont(
                playersTitle.getFont().deriveFont(Font.BOLD)
        );

        panel.add(playersTitle);

        currentPlayersLabel = new JLabel("None");

        panel.add(currentPlayersLabel);
    }

    private void addPlayer() {

        String playerName = JOptionPane.showInputDialog(
                this,
                "Enter Player Name:"
        );

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

                sb.append(String.format(
                        "%s ($%,d)",
                        p.getFirstName(),
                        p.getCurrentAmountOfMoney()
                ));

                sb.append(", ");
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
        hostLabel.setFont(
                hostLabel.getFont().deriveFont(Font.BOLD)
        );

        panel.add(hostLabel);

        currentHostLabel = new JLabel("None");

        panel.add(currentHostLabel);
    }

    private void setHost() {

        String hostName = JOptionPane.showInputDialog(
                this,
                "Enter Host Name:"
        );

        if (hostName == null || hostName.trim().isEmpty()) {
            return;
        }

        String gamePhrase = JOptionPane.showInputDialog(
                this,
                "Enter Game Phrase:"
        );

        if (gamePhrase == null || gamePhrase.trim().isEmpty()) {
            return;
        }

        host = new Hosts(
                hostName.trim(),
                gamePhrase.trim()
        );

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
        phraseLabel.setFont(
                phraseLabel.getFont().deriveFont(Font.BOLD)
        );

        panel.add(phraseLabel);

        currentPhraseLabel = new JLabel("None");

        panel.add(currentPhraseLabel);
    }

    private void updatePhraseLabel(String playingPhrase) {

        currentPhraseLabel.setText(playingPhrase);
    }

    private void setupStartPanel() {

        JPanel panel = new JPanel(new BorderLayout());

        add(panel);

        JButton startTurnsButton = new JButton("Start Player Turns");

        startTurnsButton.setPreferredSize(
                new Dimension(250, 40)
        );

        panel.add(startTurnsButton, BorderLayout.NORTH);

        animationPanel = new PrizeAnimation();

        animationPanel.setPreferredSize(
                new Dimension(500, 65)
        );

        panel.add(animationPanel, BorderLayout.CENTER);

        startTurnsButton.addActionListener(e -> startTurns());
    }

    private class PrizeAnimation extends JPanel {

        private int x = 0;
        private int direction = 1;

        private final Timer timer;

        public PrizeAnimation() {

            setBackground(Color.WHITE);

            timer = new Timer(20, e -> {

                x += direction * 3;

                if (x >= getWidth() - 35) {
                    direction = -1;
                }

                if (x <= 0) {
                    direction = 1;
                }

                repaint();
            });
        }

        public void startAnimation() {

            timer.start();
        }

        @Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setColor(new Color(255, 190, 30));

            g2.fillOval(x, 12, 35, 35);

            g2.setColor(Color.BLACK);

            g2.drawString("GOOD LUCK!", 45, 35);

            g2.dispose();
        }
    }

    private void setupMessagePanel() {

        JPanel panel = new JPanel();

        add(panel);

        panel.setLayout(
                new BoxLayout(panel, BoxLayout.PAGE_AXIS)
        );

        messages = new JTextArea();

        messages.setRows(5);
        messages.setEditable(false);
        messages.setLineWrap(true);

        panel.add(new JScrollPane(messages));

        saveMessages = new JCheckBox("Save Messages");

        saveMessages.setToolTipText(
                "If checked, only one message will be displayed at a time."
        );

        panel.add(saveMessages);
    }

    private void playBackgroundMusic() {

        try {

            File musicFile = findResourceFile("gameMusic.wav");

            if (musicFile == null) {

                sendMessage("Music file could not be found.");

                return;
            }

            if (musicClip != null && musicClip.isRunning()) {
                return;
            }

            AudioInputStream audioStream =
                    AudioSystem.getAudioInputStream(musicFile);

            musicClip = AudioSystem.getClip();

            musicClip.open(audioStream);

            audioStream.close();

            musicClip.loop(Clip.LOOP_CONTINUOUSLY);

            System.out.println("Background music is playing!");

        } catch (UnsupportedAudioFileException |
                 IOException |
                 LineUnavailableException ex) {

            System.out.println(
                    "Music Error: " + ex.getMessage()
            );

            ex.printStackTrace();
        }
    }

   private void displayPrize(String prizeName, String filename) {

        try {

            File imageFile = findResourceFile(filename);

            if (imageFile == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Congratulations! You won a " + prizeName
                                + "!\nHowever, the image file could not be found."
                );

                return;
            }

            BufferedImage image = ImageIO.read(imageFile);

            if (image == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Java could not read the image: " + filename
                );

                return;
            }

            Image scaledImage = image.getScaledInstance(
                    350,
                    220,
                    Image.SCALE_SMOOTH
            );

            ImageIcon prizeIcon = new ImageIcon(scaledImage);

            JOptionPane.showMessageDialog(
                    this,
                    "Congratulations! You won a " + prizeName + "!",
                    "Physical Prize Winner!",
                    JOptionPane.INFORMATION_MESSAGE,
                    prizeIcon
            );

        } catch (IOException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to display prize image: " + ex.getMessage()
            );
        }
    }

   private void awardPhysicalPrize() {

        String[] prizeNames = {
                "Laptop",
                "Boat",
                "New Car",
                "New House",
                "Vacation for Two"
        };

        String[] prizeImages = {
                "Laptop.jpg",
                "NewBoat.jpg",
                "newCar.jpg",
                "Newhouse.jpg",
                "Trip4two.jpg"
        };

        int prizeIndex = random.nextInt(prizeNames.length);

        displayPrize(
                prizeNames[prizeIndex],
                prizeImages[prizeIndex]
        );
    }

    private void startTurns() {

        if (players.isEmpty() || host == null) {

            sendMessage("Add players and set host first!");

            return;
        }

        messages.setText("");

        playBackgroundMusic();

        animationPanel.startAnimation();

        for (Players player : players) {

            String guess = JOptionPane.showInputDialog(
                    this,
                    player.getFirstName() + ", enter your guess:"
            );

            if (guess == null) {
                break;
            }

            boolean correct = host.checkGuess(guess);

            if (correct) {

                player.setCurrentAmountOfMoney(
                        player.getCurrentAmountOfMoney() + 100
                );

                sendMessage(
                        "Correct! " + player.getFirstName()
                                + " wins 100 points. Total: $"
                                + player.getCurrentAmountOfMoney()
                );

                awardPhysicalPrize();

                updatePlayersLabel();

            } else {

                sendMessage("Incorrect guess.");
            }

            updatePhraseLabel(host.getPlayingPhrase());

            if (host.isGameWon()) {

                sendMessage(
                        player.getFirstName() + " won the game!"
                );

                break;
            }
        }
    }

   private void sendMessage(final String message) {

        if (saveMessages.isSelected()) {

            messages.setText("");
        }

        messages.append(message + "\n");
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new GUI());
    }
}
