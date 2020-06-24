package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PRS<Sting> extends JFrame implements ActionListener {
    private final JButton scb, rob, pab;
    private JLabel wins, ties, losses, WL, user, comp, user_choice, comp_choice;
    private JPanel top, mid, lower, score, user_pan, comp_pan, result;
    private int num_wins = 0, num_losses = 0, num_ties = 0, compaction, userl;
    private final ImageIcon ro, sc, pa, quest;
    private Dimension d = new Dimension(150, 50);
    private Font font = new Font("David", Font.BOLD, 36);


    PRS() {
        setPreferredSize(new Dimension(600, 300));
        Container c = getContentPane();
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));



        top = new JPanel();
        top.setPreferredSize(new Dimension(500, 80));
        top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));

        score = new JPanel();
        score.setPreferredSize(new Dimension(500, 40));
        score.setLayout(new BoxLayout(score, BoxLayout.X_AXIS));
        score.add(Box.createHorizontalGlue());
        wins = new JLabel("Human: " + num_wins);
        wins.setFont(font);
        score.add(wins);
        score.add(Box.createHorizontalGlue());
        ties = new JLabel("Draws: " + num_ties);
        ties.setFont(font);
        score.add(ties);
        score.add(Box.createHorizontalGlue());
        losses = new JLabel("Computer: " + num_losses);
        losses.setFont(font);
        score.add(losses);
        score.add(Box.createHorizontalGlue());

        result = new JPanel(); //TODO fix spaces between score and result
        WL = new JLabel("mas=ushu");
        WL.setFont(font);
        result.add(WL);

        top.add(Box.createHorizontalGlue());
        top.add(score);
        top.add(Box.createHorizontalStrut(10));
        top.add(result);
        top.add(Box.createHorizontalGlue());

        mid = new JPanel();
        mid.setPreferredSize(new Dimension(500, 100));
        mid.setBorder(BorderFactory.createLineBorder(Color.RED));
        mid.setLayout(new BoxLayout(mid, BoxLayout.X_AXIS));
        user_pan = new JPanel();
        user_pan.setPreferredSize(new Dimension(250, 100));
        user_pan.setLayout(new BoxLayout(user_pan, BoxLayout.Y_AXIS));
        user = new JLabel("USER");
        user.setFont(font);
        quest = new ImageIcon("question.jpeg");
        user_choice = new JLabel(quest);
        user_choice.setPreferredSize(d);
        user_pan.add(Box.createVerticalGlue());
        user_pan.add(user);
        user_pan.add(Box.createVerticalGlue());
        user_pan.add(user_choice);
        user_pan.add(Box.createVerticalGlue());
        comp_pan = new JPanel();
        comp_pan.setPreferredSize(new Dimension(250, 100));
        comp_pan.setLayout(new BoxLayout(comp_pan, BoxLayout.Y_AXIS));
        comp = new JLabel("COMPUTER");
        comp.setFont(font);
        comp_choice = new JLabel(quest);
        comp_choice.setPreferredSize(d);
        comp_pan.add(Box.createVerticalGlue());
        comp_pan.add(comp);
        comp_pan.add(Box.createVerticalGlue());
        comp_pan.add(comp_choice);
        comp_pan.add(Box.createVerticalGlue());

        mid.add(Box.createVerticalStrut(50));
        mid.add(user_pan);
        mid.add(Box.createHorizontalStrut(400));
        mid.add(comp_pan);
        mid.add(Box.createVerticalStrut(50));

        lower = new JPanel();
        lower.setPreferredSize(new Dimension(500, 80));
        lower.setLayout(new BoxLayout(lower, BoxLayout.X_AXIS));

        ro = new ImageIcon("Rock.jpg");
        sc = new ImageIcon("Scissors.jpg");
        pa = new ImageIcon("Paper.jpg");

        scb = new JButton(sc);
        scb.setPreferredSize(d);
        scb.addActionListener(this);


        rob = new JButton(ro);
        rob.setPreferredSize(d);
        rob.addActionListener(this);

        pab = new JButton(pa);
        pab.setPreferredSize(d);
        pab.addActionListener(this);

        lower.add(Box.createHorizontalStrut(20));
        lower.add(scb);
        lower.add(Box.createHorizontalGlue());
        lower.add(rob);
        lower.add(Box.createHorizontalGlue());
        lower.add(pab);
        lower.add(Box.createHorizontalStrut(20));

        add(Box.createVerticalStrut(10));
        add(top);
        add(Box.createVerticalGlue());
        add(mid);
        add(Box.createVerticalGlue());
        add(lower);
        add(Box.createVerticalStrut(10));
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void result_cal(String comp, String user) {
        if (comp.equals(user)) {
            num_ties += 1;
            WL.setText("IT'S TIE :/");
        } else if ((comp.equals("scb") && user.equals("rob")) || (comp.equals("pab") && user.equals("scb")) || (comp.equals("rob") && user.equals("pab"))) {
            num_wins += 1;
            WL.setText("YOU WIN :)");
        } else {
            num_losses += 1;
            WL.setText("YOU LOSE :(");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        compaction = (int) (Math.random() % 3);
        Icon[] icons = {ro, sc, pa};
        String act = ((JButton) e.getSource()).getActionCommand();

        switch ((e.getSource().toString())) {
            case "scb":
                user_choice.setIcon(sc); //set user's action icon in the middle panel
                comp_choice.setIcon(icons[compaction]); // sets computer action icon in the middle panel
                result_cal(icons[compaction].toString(), "sc"); //updates a whole top panel
                break;
            case "rob" :
                user_choice.setIcon(ro);
                comp_choice.setIcon(icons[compaction]);
                result_cal(icons[compaction].toString(), "ro");
                break;
            case "pab" :
                user_choice.setIcon(pa);
                comp_choice.setIcon(icons[compaction]);
                result_cal(icons[compaction].toString(), "pa");
                break;
        }
    }
}