package gui;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//package gui;

class Catagory extends JFrame implements ActionListener{
    private JButton alphabet, number, drawing;
    private JLabel aLabel, bLabel, cLabel;
    ImageIcon alphabetIcon, numberIcon, drawingIcon, icon;
    Container c;
    public Catagory(){
        initComponent();
        this.setBounds(300, 85, 765, 600);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Catagory");
        icon = new ImageIcon(getClass().getResource("storm.png"));
        this.setIconImage(icon.getImage());
    }

    public void initComponent() {
        c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(Color.PINK);
        
        alphabetIcon = new ImageIcon("./alphabet.jfif");
        Image alphaimg = alphabetIcon.getImage();
        Image alphaimg2 = alphaimg.getScaledInstance(240, 210, Image.SCALE_SMOOTH);
        alphabet = new JButton();
        alphabet.setIcon(new ImageIcon(alphaimg2));
        alphabet.setBounds(85, 50, 240, 210);
        c.add(alphabet);

        numberIcon = new ImageIcon("./number.jpg");
        Image numberimg = numberIcon.getImage();
        Image numberimg2 = numberimg.getScaledInstance(240, 210, Image.SCALE_SMOOTH);
        number = new JButton();
        number.setIcon(new ImageIcon(numberimg2));
        number.setBounds(440, 50, 240, 210);
        c.add(number);

        drawingIcon = new ImageIcon("./drawing.jpg");
        Image drawimg = drawingIcon.getImage();
        Image drawimg2 = drawimg.getScaledInstance(240, 210, Image.SCALE_SMOOTH);
        drawing = new JButton();
        drawing.setIcon(new ImageIcon(drawimg2));
        drawing.setBounds(270, 310, 240, 210);
        c.add(drawing);


        aLabel = new JLabel("Learn Alphabet");
        aLabel.setBounds(150, 270, 120, 30);
        c.add(aLabel);

        cLabel = new JLabel("Art Board");
        cLabel.setBounds(350, 535, 120, 30);
        c.add(cLabel);

        bLabel = new JLabel("Learn Number");
        bLabel.setBounds(510, 270, 120, 30);
        c.add(bLabel);

        alphabet.addActionListener(this);
        number.addActionListener(this);
        drawing.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == alphabet){
            dispose();
            Alphabet obj = new Alphabet();
        }
        else if(e.getSource() == number){
            dispose();
            Number obj = new Number();
        }
        else if(e.getSource() == drawing){
            dispose();
            Drawing obj = new Drawing();
        }
    }
}