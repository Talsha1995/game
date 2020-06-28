package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//TODO change "WL" label to "screen_msg" label

public class PRS extends JFrame implements ActionListener {
    private final JButton scissors_but, rock_but, paper_but;   //define buttons
    private JLabel wins, ties, losses, WL, user, comp, user_choice, comp_choice; //define labels
    private JPanel top, mid, lower, score, user_pan, comp_pan, result;          //define panels
    private int num_wins = 0, num_losses = 0, num_ties = 0, compaction;  //define counters
    private final ImageIcon rock, scissors, paper, quest;              //define Icons
    private Dimension d = new Dimension(150, 50); //define dimension for Labels
    private Font font = new Font("Forte", Font.BOLD, 36); //define font for Lables


    PRS() {
        setPreferredSize(new Dimension(600, 600));
        Container c = getContentPane();
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));  //box layout is optimal



        top = new JPanel();
        top.setPreferredSize(new Dimension(500, 80)); //arranging the upper Pannel
        top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));       //from top to bottom

        score = new JPanel();
        score.setPreferredSize(new Dimension(500, 40)); //arranging the score panel
        score.setLayout(new BoxLayout(score, BoxLayout.X_AXIS));    //from left to right
        score.add(Box.createHorizontalGlue());              //adding glues for fine shapes
        wins = new JLabel("Human: " + num_wins);        //adding label for human's wins counter
        wins.setFont(font);                                     //with fancy font
        score.add(wins);
        score.add(Box.createHorizontalGlue());              //glue between panels
        ties = new JLabel("Draws: " + num_ties);        //initializing label for human's draws counter
        ties.setFont(font);
        score.add(ties);
        score.add(Box.createHorizontalGlue());          //glue between panels
        losses = new JLabel("Computer: " + num_losses); //initializing label for human's losses counter
        losses.setFont(font);
        score.add(losses);
        score.add(Box.createHorizontalGlue());

        result = new JPanel();
        WL = new JLabel("Good Luck");       //initializing widget for you win/you loose panel
        WL.setFont(font);                       //it says good luck only once, before the game starts
        result.add(WL);                         //it is contained in result panel

        top.add(Box.createHorizontalGlue());         //finally, adding all the sub-panels to the top panel
        top.add(score);
        top.add(Box.createHorizontalStrut(10));
        top.add(result);
        top.add(Box.createHorizontalGlue());

        mid = new JPanel();                                              //start to work on middle panel
        mid.setPreferredSize(new Dimension(500, 100));      //set dimensions
        mid.setBorder(BorderFactory.createLineBorder(Color.RED));       //the only one witch bordered
        mid.setLayout(new BoxLayout(mid, BoxLayout.X_AXIS));
        user_pan = new JPanel();                                        //init. the panel, which displays the user move as a picture
        user_pan.setPreferredSize(new Dimension(250, 100));
        user_pan.setLayout(new BoxLayout(user_pan, BoxLayout.Y_AXIS));
        user = new JLabel("USER");
        user.setFont(font);
        quest = new ImageIcon("question.jpg");                  //in the beginning of the game it displays question picture
        user_choice = new JLabel(quest);
        user_choice.setPreferredSize(d);
        user_pan.add(user);                                             //form the user panel
        user_pan.add(user_choice);
        comp_pan = new JPanel();                                        //same process with computes's part of panel
        comp_pan.setPreferredSize(new Dimension(250, 100));
        comp_pan.setLayout(new BoxLayout(comp_pan, BoxLayout.Y_AXIS));
        comp = new JLabel("COMPUTER");
        comp.setFont(font);
        comp_choice = new JLabel(quest);
        comp_choice.setPreferredSize(d);
        comp_pan.add(comp);
        comp_pan.add(comp_choice);

        mid.add(Box.createVerticalGlue());                  //after all sub-panels are built, add it to middle panel in
        mid.add(user_pan);                                  //order they will appear on the screen(from left to right
        mid.add(Box.createHorizontalStrut(50));
        mid.add(comp_pan);
        mid.add(Box.createVerticalGlue());

        lower = new JPanel();                               //start work on lower panel
        lower.setPreferredSize(new Dimension(500, 80));     //set dimensions
        lower.setLayout(new BoxLayout(lower, BoxLayout.X_AXIS)); 

        rock = new ImageIcon("Rock.jpg");                   //rock image 
        scissors = new ImageIcon("Scissors.jpg");           // scissors image
        paper = new ImageIcon("Paper.jpg");                 // paper image

        scissors_but = new JButton(scissors);               
        scissors_but.setPreferredSize(d);                   //initializing button with scissors image
        scissors_but.addActionListener(this);


        rock_but = new JButton(rock);
        rock_but.setPreferredSize(d);                       //initializing button with rock image
        rock_but.addActionListener(this);

        paper_but = new JButton(paper);
        paper_but.setPreferredSize(d);                      //initializing button with paper image
        paper_but.addActionListener(this);

        lower.add(Box.createHorizontalStrut(20));           
        lower.add(scissors_but);
        lower.add(Box.createHorizontalGlue());
        lower.add(rock_but);                                //adding buttuns to lower panel, with glue between them and strut around
        lower.add(Box.createHorizontalGlue());
        lower.add(paper_but);
        lower.add(Box.createHorizontalStrut(20));

        add(Box.createVerticalStrut(10));
        add(top);
        add(Box.createVerticalGlue());
        add(mid);
        add(Box.createVerticalGlue());                  //finally build main frame with 3 panels in it
        add(lower);
        add(Box.createVerticalStrut(10));
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // program stop when exit the game window
    }

  /*this functiion get random choice (as "Sc"/"Ro"/"Pa") and icon name of the pressed buttun, and make the right changes (win/ lose/ draw) in the frame  */
    
    void result_cal(String comp, String user) {
        String comp_new= comp.substring(0,2);   //make the icon name (user choose) shorter, to be the same as the computer choice ("Sc"/"Ro"/"Pa")
        if (comp_new.equals(user)) {            //tie situation
            num_ties += 1;
            ties.setText("Draws: " + num_ties); //set tie lable to updated ties counter
            WL.setText("IT'S A TIE :/");        //set the screen messege 
        } else if ((comp_new.equals("Sc") && user.equals("Ro")) || (comp_new.equals("Pa") && user.equals("Sc")) || (comp_new.equals("Ro") && user.equals("Pa"))) {
            //all options for win situation^^^
            num_wins += 1;
            wins.setText("Human: " + num_wins); //set win label to updated wins counter
            WL.setText("YOU WIN :)");           // set the screen meesege
        } else { // lose situation
            num_losses += 1;
            losses.setText("Computer: " + num_losses); // set the right labels like before
            WL.setText("YOU LOSE :(");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        compaction = (int) (Math.random() * 3); //random number from 0, 1, 2
        Icon[] icons = {rock, scissors, paper}; //array of icons

        if (e.getSource() == scissors_but) { 
            user_choice.setIcon(scissors);                  //set user's action icon in the middle panel
            comp_choice.setIcon(icons[compaction]);         // sets computer action icon in the middle panel
            result_cal(icons[compaction].toString(), "Sc"); //updates a whole top panel
        } else if (e.getSource() == rock_but) {
            user_choice.setIcon(rock);                      //set user's action icon in the middle panel
            comp_choice.setIcon(icons[compaction]);         // sets computer action icon in the middle panel
            result_cal(icons[compaction].toString(), "Ro"); //updates a whole top panel
        } else {
            user_choice.setIcon(paper);                     //set user's action icon in the middle panel
            comp_choice.setIcon(icons[compaction]);         // sets computer action icon in the middle panel
            result_cal(icons[compaction].toString(), "Pa"); //updates a whole top panel
        }
    }
}
