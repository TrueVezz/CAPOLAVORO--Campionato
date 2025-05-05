/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package partita;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 *
 * @author Patrick
 */
public class MyFrm extends JFrame {

    private static final JLabel squadra1 = new JLabel();
    private static final JLabel squadra2 = new JLabel();
    private static final JLabel punteggioS1 = new JLabel("0");
    private static final JLabel separatorePunteggi = new JLabel("-");
    private static final JLabel punteggioS2 = new JLabel("0");

    private static final JButton btnAggiungiS1 = new JButton("+1");
    private static final JButton btnAggiungiS2 = new JButton("+1");
    private static final JButton btnRimuoviS1 = new JButton("-1");
    private static final JButton btnRimuoviS2 = new JButton("-1");
    private static final JButton btnAzzera = new JButton("Azzera");
    private static final JButton btnTermina = new JButton("Termina");

    JPanel matchPanel = new JPanel();
    JPanel btnPanelS1 = new JPanel();
    JPanel btnPanelS2 = new JPanel();

    JPanel btnManage = new JPanel();

    public MyFrm(Scoreboard s, String nomeS1, String nomeS2) {
        Scoreboard scoreboard = s;
        squadra1.setText(nomeS1);
        squadra2.setText(nomeS2);

        matchPanel.setLayout(new GridLayout(3, 5));
        matchPanel.add(squadra1);
        matchPanel.add(punteggioS1);
        matchPanel.add(separatorePunteggi);
        matchPanel.add(punteggioS2);
        matchPanel.add(squadra2);

        btnPanelS1.setLayout(new BoxLayout(btnPanelS1, BoxLayout.X_AXIS));
        btnPanelS1.add(btnAggiungiS1);
        btnPanelS1.add(btnRimuoviS1);

        btnPanelS2.setLayout(new BoxLayout(btnPanelS2, BoxLayout.X_AXIS));
        btnPanelS2.add(btnAggiungiS2);
        btnPanelS2.add(btnRimuoviS2);
        
        btnManage.setLayout(new BoxLayout(btnManage, BoxLayout.X_AXIS));
        btnManage.add(btnAzzera);
        btnManage.add(btnTermina);

        matchPanel.add(btnPanelS1);
        
        
        JPanel empty1 = new JPanel();
        JPanel empty2 = new JPanel();
        JPanel empty3 = new JPanel();
        empty1.setOpaque(false);
        empty2.setOpaque(false);
        empty3.setOpaque(false);
        matchPanel.add(empty1);
        matchPanel.add(empty2);
        matchPanel.add(empty3);
        
        matchPanel.add(btnPanelS2);

        matchPanel.add(btnManage);

        btnAggiungiS1.addActionListener((ActionEvent e) -> {
            s.getHomeCounter().increaseContatore(1);
            punteggioS1.setText(String.valueOf(s.getHomeCounter().getCounter()));
        });
        btnRimuoviS1.addActionListener((ActionEvent e) -> {
            if(s.getHomeCounter().decreaseContatore(1))
                punteggioS1.setText(String.valueOf(s.getHomeCounter().getCounter()));
            else {
                punteggioS1.setText(String.valueOf(0));
                s.getHomeCounter().resetCounter();
            }
                
        });

        btnAggiungiS2.addActionListener((ActionEvent e) -> {
            s.getAwayCounter().increaseContatore(1);
            punteggioS2.setText(String.valueOf(s.getAwayCounter().getCounter()));
        });
        btnRimuoviS2.addActionListener((ActionEvent e) -> {
            if(s.getAwayCounter().decreaseContatore(1))
                punteggioS2.setText(String.valueOf(s.getAwayCounter().getCounter()));
            else {
                punteggioS2.setText(String.valueOf(0));
                s.getAwayCounter().resetCounter();
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

        Container container = this.getContentPane();
        container.add(matchPanel);

        this.setVisible(true);
        this.setSize(600, 300);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
