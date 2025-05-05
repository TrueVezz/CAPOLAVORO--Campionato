/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package partita;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Patrick
 */
public class ChatFrm extends JFrame {

    private final JLabel squadra1 = new JLabel();
    private final JLabel squadra2 = new JLabel();
    private final JLabel punteggioS1 = new JLabel("0");
    private final JLabel separatorePunteggi = new JLabel("-");
    private final JLabel punteggioS2 = new JLabel("0");

    private final JButton btnAggiungiS1 = new JButton("+1");
    private final JButton btnAggiungiS2 = new JButton("+1");
    private final JButton btnRimuoviS1 = new JButton("-1");
    private final JButton btnRimuoviS2 = new JButton("-1");
    private final JButton btnAzzera = new JButton("Azzera");
    private final JButton btnTermina = new JButton("Termina");

    private final JPanel matchPanel = new JPanel();
    private final JPanel btnPanelS1 = new JPanel();
    private final JPanel btnPanelS2 = new JPanel();
    private final JPanel btnManage = new JPanel();

    public ChatFrm(Scoreboard s, String nomeS1, String nomeS2) {
        super("Scoreboard - " + nomeS1 + " vs " + nomeS2);

        squadra1.setText(nomeS1);
        squadra2.setText(nomeS2);

        // Layout configuration
        matchPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        // Row 0 - Labels and scores
        gbc.gridx = 0;
        gbc.gridy = 0;
        matchPanel.add(squadra1, gbc);

        gbc.gridx = 1;
        matchPanel.add(punteggioS1, gbc);

        gbc.gridx = 2;
        matchPanel.add(separatorePunteggi, gbc);

        gbc.gridx = 3;
        matchPanel.add(punteggioS2, gbc);

        gbc.gridx = 4;
        matchPanel.add(squadra2, gbc);

        // Row 1 - Buttons for Squadra 1
        btnPanelS1.setLayout(new BoxLayout(btnPanelS1, BoxLayout.X_AXIS));
        btnPanelS1.add(btnAggiungiS1);
        btnPanelS1.add(Box.createHorizontalStrut(5));
        btnPanelS1.add(btnRimuoviS1);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        matchPanel.add(btnPanelS1, gbc);

        // Row 1 - Buttons for Squadra 2
        btnPanelS2.setLayout(new BoxLayout(btnPanelS2, BoxLayout.X_AXIS));
        btnPanelS2.add(btnAggiungiS2);
        btnPanelS2.add(Box.createHorizontalStrut(5));
        btnPanelS2.add(btnRimuoviS2);

        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        matchPanel.add(btnPanelS2, gbc);

        // Row 2 - Control buttons
        btnManage.setLayout(new BoxLayout(btnManage, BoxLayout.X_AXIS));
        btnManage.add(btnAzzera);
        btnManage.add(Box.createHorizontalStrut(10));
        btnManage.add(btnTermina);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        matchPanel.add(btnManage, gbc);

        // Action listeners
        btnAggiungiS1.addActionListener((ActionEvent e) -> {
            s.getHomeCounter().increaseContatore(1);
            punteggioS1.setText(String.valueOf(s.getHomeCounter().getCounter()));
        });

        btnRimuoviS1.addActionListener((ActionEvent e) -> {
            if (s.getHomeCounter().decreaseContatore(1)) {
                punteggioS1.setText(String.valueOf(s.getHomeCounter().getCounter()));
            } else {
                punteggioS1.setText("0");
                s.getHomeCounter().resetCounter();
            }
        });

        btnAggiungiS2.addActionListener((ActionEvent e) -> {
            s.getAwayCounter().increaseContatore(1);
            punteggioS2.setText(String.valueOf(s.getAwayCounter().getCounter()));
        });

        btnRimuoviS2.addActionListener((ActionEvent e) -> {
            if (s.getAwayCounter().decreaseContatore(1)) {
                punteggioS2.setText(String.valueOf(s.getAwayCounter().getCounter()));
            } else {
                punteggioS2.setText("0");
                s.getAwayCounter().resetCounter(); // bug fix qui
            }
        });

        btnAzzera.addActionListener((ActionEvent e) -> {
            s.getHomeCounter().resetCounter();
            s.getAwayCounter().resetCounter();
            punteggioS1.setText("0");
            punteggioS2.setText("0");
        });

        btnTermina.addActionListener((ActionEvent e) -> {
            s.terminaPartita();
        });

        // Final setup
        Container container = this.getContentPane();
        container.add(matchPanel);
        this.setSize(600, 300);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setVisible(true);
    }
}
