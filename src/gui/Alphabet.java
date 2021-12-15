package gui;
import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.*;
import java.util.List;

public class Alphabet extends JFrame implements ActionListener{
    Container c;
    ImageIcon icon[], icon2, backIcon, imgicon;
    private JButton button[], back;
    JPanel panel;
    private FlowLayout fLayout;
    public Alphabet(){
        initComponents();
        this.setBounds(300, 85, 765, 600);
        this.setVisible(true);
        this.setResizable(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Pre-School Learning");
        imgicon = new ImageIcon(getClass().getResource("storm.png"));
        this.setIconImage(imgicon.getImage());
    }
    public void initComponents(){
        c = this.getContentPane();
        fLayout = new FlowLayout(FlowLayout.LEFT);
        fLayout.setHgap(20);
        fLayout.setVgap(50);
        c.setLayout(fLayout);
        c.setBackground(Color.YELLOW);

        backIcon = new ImageIcon("./return-button.png");
        back = new JButton(backIcon);
        c.add(back);
        back.setBounds(5, 5, 80, 25);

        icon = new ImageIcon[27];
        button = new JButton[27];
        for(int i = 1; i < 27; i++){
            int ascii = 64 + i;
            String strascii = Character.toString((char) ascii);
            icon[i] = new ImageIcon(getClass().getResource("./images/"+strascii+".jpg"));
            Image image = icon[i].getImage();
            Image newimg = image.getScaledInstance(70, 50, java.awt.Image.SCALE_SMOOTH);
            icon2 = new ImageIcon(newimg);
            button[i] = new JButton(icon2);
            button[i].setBackground(Color.PINK);
            c.add(button[i]);
            button[i].addActionListener(this);
        }
        back.addActionListener(this);
    }
    public void PlaySound(File Sound){
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(Sound));
            clip.start();
            Thread.sleep(clip.getMicrosecondLength()/10000);
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
    public void actionPerformed(ActionEvent e){
        for(int i = 1; i < 27; i++){
            if(e.getSource() == button[i]){
                for(int j = 1; j < 27; j++){
                    if(i == j){
                        int as = 64 + i;
                        String str = Character.toString((char) as);
                        File newfile = new File("./alphbetSound/"+str+".WAV");
                        PlaySound(newfile);
                    }
                }
            }
        }
        if(e.getSource() == back){
            this.dispose();
            Catagory obj = new Catagory();
            obj.setVisible(true);
        }
    }
}