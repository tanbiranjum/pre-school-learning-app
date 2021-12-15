package gui;
import repo.*;
import admin.*;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;
import java.sql.*;


public class SignupForm extends JFrame implements ActionListener{
    private JTabbedPane tab;
    private JPanel panel1, panel2;
    private Container c;
    private JLabel unLevel, pwLevel, nameLevel, ageLevel, chooseUserName, choosePassword;
    private JPasswordField pwField, pwField2;
    private JTextField unField, nameField, ageField, userNameField;
    private JButton signIn, signUp;
    private Font f, f2;
    private ImageIcon icon;
    dbConnection con;

    public SignupForm(){
        initForm();
        icon = new ImageIcon(getClass().getResource("storm.png"));
        this.setIconImage(icon.getImage());
    }
    public void initForm(){
        this.setBounds(460, 150, 400, 500);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Pre-School Learning");
        f2 = new Font("Lucida Fax", Font.BOLD, 12);
        f = new Font("Trebuchet MS", Font.BOLD, 12);
        c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(Color.pink);

        //JTabbedPane
        tab = new JTabbedPane();
        tab.setBounds(0, 40, 400, 450);
        c.add(tab);

        //JPanel1
        panel1 = new JPanel();
        panel1.setBackground(Color.RED);
        panel1.setLayout(null);
        panel2 = new JPanel();
        panel2.setLayout(null);
        panel2.setBackground(Color.ORANGE);


        tab.addTab("Sign In", panel1);
        tab.addTab("Don't have one? Sign Up!", panel2);

        unLevel = new JLabel("UserName: ");
        unLevel.setBounds(20, 70, 75, 25);
        unLevel.setForeground(Color.WHITE);
        unLevel.setFont(f2);
        panel1.add(unLevel);

        pwLevel = new JLabel("Password: ");
        pwLevel.setBounds(20, 130, 75, 25);
        pwLevel.setForeground(Color.WHITE);
        pwLevel.setFont(f2);
        panel1.add(pwLevel);

        unField = new JTextField();
        panel1.add(unField);
        unField.setBackground(Color.PINK);
        unField.setBounds(120, 65, 170, 30);

        pwField = new JPasswordField();
        panel1.add(pwField);
        pwField.setBounds(120, 125, 170, 30);
        pwField.setBackground(Color.PINK);


        signIn = new JButton("Sign In");
        panel1.add(signIn);
        signIn.setForeground(Color.BLACK);
        signIn.setBackground(Color.PINK);
        signIn.setBounds(20, 220, 80, 30);
        signIn.setFont(f);

        //Jpanel2
        nameLevel = new JLabel("Name:");
        panel2.add(nameLevel);
        nameLevel.setBounds(20, 50, 75, 25);
        nameLevel.setFont(f2);

        ageLevel = new JLabel("Age: ");
        panel2.add(ageLevel);
        ageLevel.setBounds(20, 95, 75, 25);
        ageLevel.setFont(f2);

        chooseUserName = new JLabel("UserName");
        panel2.add(chooseUserName);
        chooseUserName.setBounds(20, 140, 90, 25);
        chooseUserName.setFont(f2);

        choosePassword = new JLabel("Password");
        panel2.add(choosePassword);
        choosePassword.setBounds(20, 185, 90, 25);
        choosePassword.setFont(f2);

        nameField = new JTextField();
        panel2.add(nameField);
        nameField.setBackground(Color.PINK);
        nameField.setBounds(120, 45, 170, 30);

        ageField = new JTextField();
        panel2.add(ageField);
        ageField.setBackground(Color.PINK);
        ageField.setBounds(120, 90, 170, 30);

        userNameField = new JTextField();
        panel2.add(userNameField);
        userNameField.setBackground(Color.PINK);
        userNameField.setBounds(120, 135, 170, 30);

        pwField2 = new JPasswordField();
        panel2.add(pwField2);
        pwField2.setBackground(Color.PINK);
        pwField2.setBounds(120, 180, 170, 30);

        signUp = new JButton("Sign Up");
        panel2.add(signUp);
        signUp.setBounds(20, 250, 80, 30);
        signUp.setForeground(Color.BLACK);
        signUp.setBackground(Color.PINK);
        signUp.setFont(f);


        signIn.addActionListener(this);
        signUp.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == signIn){
            try {
                con = new dbConnection();
                String query = "select username, password from account where username=? and password=?";
                PreparedStatement pst = con.c.prepareStatement(query);
                String myPass = String.valueOf(pwField.getPassword());
                pst.setString(1, unField.getText());
                pst.setString(2, myPass);
                ResultSet result = pst.executeQuery();
                if(unField.getText().isEmpty() || myPass.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Username & password Required!", "Error", -1);
                }
                else{
                    if(result.next()){
                        String check = result.getString("username");
                        System.out.println(check);
                        if(check.equals("admin")){
                            dispose();
                            ManagerFrame obj2 = new ManagerFrame();
                            obj2.setVisible(true);
                        }
                        else{
                            dispose();
                            Catagory obj = new Catagory();
                            obj.setVisible(true);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Log In Failed. Username Password doesn't match", "OOps!", -1);
                    }
                }
            } catch (Exception a) {
                System.out.println("Exception" + a);
            }
        }
        else if(e.getSource() == signUp){
            String name = nameField.getText();
            String age = ageField.getText();
            String username = userNameField.getText();
            String pass = String.valueOf(pwField2.getPassword());
            try {
                User obj = new User(name, age, username, pass);
                Admin admin = new Admin();
                int i = admin.insertUser(obj);
                if(i == 0){
                    JOptionPane.showMessageDialog(null, "Username already exist", "Error", JOptionPane.ERROR_MESSAGE);
                    userNameField.setText("");
                    pwField2.setText("");
                }
                else if(i == 1) {
                    JOptionPane.showMessageDialog(null, "Account Created! Please Log In", "Success", JOptionPane.DEFAULT_OPTION);
                }
            } catch (Exception b) {
                System.out.println(b);
            }
        }
    }
}