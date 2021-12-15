package gui;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JSlider.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.MouseMotionListener;

class Drawing extends JFrame implements MouseMotionListener, ChangeListener, ActionListener {
    private JSlider slider;
    private JLabel label1, label2;
    private int value = 5;
    private ImageIcon backIcon, icon;
    private JComboBox combo;
    private JButton clearButton, back;
    private JPanel panel;
    private String[] color = {"BLUE", "BLACK", "RED", "ORANGE"};
    private String col = "BLUE";
    private boolean clear = false;
    Font font = new Font("Comic Sans MS", Font.PLAIN, 13);
    Font font2 = new Font("Century Gothic", Font.BOLD, 13);
    Container c;
    public Drawing(){
        initDrawingPanel();
        this.setBounds(410, 95, 500, 600);
        this.setVisible(true);
        this.setTitle("Drawing Tool");
        icon = new ImageIcon(getClass().getResource("storm.png"));
        this.setIconImage(icon.getImage());
    }

    public void initDrawingPanel(){
        c = getContentPane();
        c.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        c.setBackground(Color.PINK);
        addMouseMotionListener(this);

        panel = new JPanel();
        panel.setBounds(0, 80, 500, 500);
        panel.setBackground(Color.WHITE);
        c.add(panel);

        slider = new JSlider(5, 20, 5);
        slider.setBounds(60, 10, 100, 50);
        slider.setMajorTickSpacing(5);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setBackground(Color.PINK);
        c.add(slider);

        label1 = new JLabel("Pen Size");
        label1.setBounds(7, 12, 60, 30);
        label1.setFont(font);
        c.add(label1);

        label2 = new JLabel("Set Color");
        label2.setBounds(200, 12, 70, 30);
        label2.setFont(font);
        c.add(label2);

        clearButton = new JButton("Clear");
        clearButton.setBounds(400, 10, 70, 40);
        clearButton.setFont(font);
        clearButton.setBackground(Color.RED);
        c.add(clearButton);

        combo = new JComboBox(color);
        combo.setBounds(270, 10, 90, 40);
        combo.setFont(font2);
        c.add(combo);

        backIcon = new ImageIcon("./return-button.png");
        back = new JButton(backIcon);
        c.add(back);
        back.setBounds(400, 52, 70, 22);

        slider.addChangeListener(this);
        combo.addActionListener(this);
        clearButton.addActionListener(this);
        back.addActionListener(this);
    } 

    public void mouseDragged(MouseEvent e){
        if(clear){
            this.clear = false;
        }
        else{
            Graphics g = getGraphics();
        if(this.col.equals("BLACK")){
            g.setColor(Color.BLACK);
            g.fillOval(e.getX(), e.getY(), value, value);
        }
        else if(this.col.equals("BLUE")){
            g.setColor(Color.BLUE);
            g.fillOval(e.getX(), e.getY(), value, value);
        }
        else if(this.col.equals("RED")){
            g.setColor(Color.RED);
            g.fillOval(e.getX(), e.getY(), value, value);
        }
        else if(this.col.equals("ORANGE")){
            g.setColor(Color.ORANGE);
            g.fillOval(e.getX(), e.getY(), value, value);
        }
        }
    }
    public void mouseMoved(MouseEvent a){

    }

    public void stateChanged(ChangeEvent e){
        this.value = slider.getValue();
    }

    public void actionPerformed(ActionEvent e){
        this.col = combo.getSelectedItem().toString();
        if(e.getSource() == clearButton){
            this.clear = true;
            repaint();
        }
        else if(e.getSource() == back){
            this.dispose();
            Catagory obj = new Catagory();
            obj.setVisible(true);
        }
    }
}