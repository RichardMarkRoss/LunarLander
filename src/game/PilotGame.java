package game;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PilotGame implements Runnable {

    @Override
    public void run() {

        final JFrame dialog = new JFrame("LunarLander LaunchPad");
        // dialog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final String[] options = new String[2];
        Array.set(options, 0, "Easy");
        Array.set(options, 1, "Realistic");

        final int difficulty = JOptionPane.showOptionDialog(dialog, "Choose a difficulty",
                "LunarLander Launchpad", 0, 0, new ImageIcon("Files/MissionControl.jpg"), options,
                null);
        boolean isEasy = false;
        if (difficulty == 0) {
            isEasy = true;

        }

        else if (difficulty == 1) {
            isEasy = false;

        } else if (difficulty == JOptionPane.CLOSED_OPTION) {
            dialog.dispose();
            System.exit(0);
        }

        // For each game you run, start a new server
        final GameState gameState = new GameState(isEasy);
        final Server server = new Server(gameState);
        final Thread serverThread = new Thread(server);
        serverThread.start();

        // Top-level frame in which game components live
        // Be sure to change "TOP LEVEL FRAME" to the name of your game
        final JFrame frame = new JFrame("LunarLander");
        frame.setLocation(50, 100);

        // Panel for Lander information that is linked to the canvas (i.e. the lander)
        // TODO:Panel or component?
        final TelemetryPanel telemetryPanel = new TelemetryPanel(gameState, false);

        frame.add(telemetryPanel, BorderLayout.EAST);

        // Panel for more general game state information
        // TODO:Another game panel

        // Main playing area
        final Canvas court = new Canvas(gameState, telemetryPanel);
        frame.add(court, BorderLayout.CENTER);

        // Reset buttons
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);

        // Note here that when we add an action listener to the reset button, we define it as an
        // anonymous inner class that is an instance of ActionListener with its actionPerformed()
        // method overridden. When the button is pressed, actionPerformed() will be called.
        final JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                court.reset();
            }
        });
        control_panel.add(reset);

        // Put the frame on the screen
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // TODO:Make visible when done with network testing.
        frame.setVisible(true);

        // Start game
        court.reset();
    }

}
