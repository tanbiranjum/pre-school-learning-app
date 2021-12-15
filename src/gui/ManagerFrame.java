package gui;
import repo.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.color.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Event;


class ManagerFrame extends JFrame implements ActionListener, ListSelectionListener{
    Container c;
    private JButton remove, update, clear, back;
    private JLabel title, nameLevel, ageLabel, userLabel;
    private JTextField nameField, ageField, usernameField, newNameField, newAgeField;
    private JTable table;
    ImageIcon backIcon, icon;
    private JScrollPane scroll;
    private DefaultTableModel model;
    private boolean selected = false;
    private String[] columnNames = { "Name", "Age", "Username" };
    private String[] rows = new String[3];
    DbManager con;
    public ManagerFrame(){
        initFrame();
        intiTable();
        this.setBounds(460, 80, 450, 600);
        this.setVisible(true);
        icon = new ImageIcon(getClass().getResource("storm.png"));
        this.setIconImage(icon.getImage());
    }


    public void initFrame(){
        c = this.getContentPane();
        c.setLayout(null);
        this.setTitle("Admin Panel");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        title = new JLabel("ADMIN PANEL");
        c.add(title);
        title.setBounds(10, 10, 80, 40);
        title.setForeground(Color.BLUE);

        table = new JTable();

        model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        table.setModel(model);
        table.setSelectionBackground(Color.GREEN);
        table.setForeground(Color.ORANGE);
        table.setRowHeight(30);

        scroll = new JScrollPane(table);
        scroll.setBounds(5, 200, 320, 350);
        c.add(scroll);

        remove = new JButton("Remove");
        c.add(remove);
        remove.setBounds(340, 200, 80, 30);

        update = new JButton("Update");
        c.add(update);
        update.setBounds(340, 250, 80, 30);

        clear = new JButton("Clear");
        c.add(clear);
        clear.setBounds(340, 300, 80, 30);

        remove.addActionListener(this);
        update.addActionListener(this);
        clear.addActionListener(this);


        nameLevel = new JLabel("Name:");
        c.add(nameLevel);
        nameLevel.setBounds(10, 65, 90, 30);

        ageLabel = new JLabel("Age:");
        c.add(ageLabel);
        ageLabel.setBounds(10, 105, 90, 30);

        userLabel = new JLabel("Username:");
        c.add(userLabel);
        userLabel.setBounds(10, 150, 90, 30);

        nameField = new JTextField();
        c.add(nameField);
        nameField.setBounds(100, 65, 160, 30);

        ageField = new JTextField();
        c.add(ageField);
        ageField.setBounds(100, 110, 160, 30);

        usernameField = new JTextField();
        c.add(usernameField);
        usernameField.setBounds(100, 150, 160, 30);

        backIcon = new ImageIcon("./return-button.png");
        back = new JButton(backIcon);
        c.add(back);
        back.setBounds(360, 10, 70, 22);

        newNameField = new JTextField("New Name");
        c.add(newNameField);
        newNameField.setBounds(275, 65, 140, 30);

        newAgeField = new JTextField("New Age");
        c.add(newAgeField);
        newAgeField.setBounds(275, 110, 140, 30);

        table.getSelectionModel().addListSelectionListener(this);
        back.addActionListener(this);
    }

    public void valueChanged(ListSelectionEvent e){
        this.selected = true;
        int rows = table.getSelectedRow();

        String name = model.getValueAt(rows, 0).toString();
        String age = model.getValueAt(rows, 1).toString();
        String username = model.getValueAt(rows, 2).toString();

        usernameField.setText(username);
        ageField.setText(age);
        nameField.setText(name);

    }

    public void actionPerformed(ActionEvent e){
        try {
            if(e.getSource() == clear){
                nameField.setText("");
                usernameField.setText("");
                ageField.setText("");
                this.selected = false;
            }
            else if(e.getSource() == remove){
                if(selected){
                    String username = usernameField.getText();
                    String name = nameField.getText();

                    DbManager con = new DbManager();
                    con.removeData(name, username);

                    JOptionPane.showMessageDialog(null, "Account Deleted!", "Success", -1);
                    this.setVisible(false);
                    new ManagerFrame().setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Please Select a row first!", "Error!", -1);
                }
            }
            else if(e.getSource() == back){
                this.dispose();
                new SignupForm().setVisible(true);
            }
            else if(e.getSource() == update){
                if(newNameField.getText().isEmpty() || newAgeField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Fill up all required Information", "ERROR", -1);
                }
                else{
                    DbManager con = new DbManager();
                    String name = nameField.getText();
                    String newname = newNameField.getText();
                    String newage = newAgeField.getText();
                    String username = usernameField.getText();
                    con.updateData(name, newname, newage, username);
                    JOptionPane.showMessageDialog(null, "Updated!");
                    this.setVisible(false);
                    new ManagerFrame().setVisible(true);
                }
            }
        } catch (Exception a) {
            System.out.println(a);
        }
    }

    public void intiTable() {
        try {
            con = new DbManager();
            ArrayList<String> data = con.getData();
            int count = data.size();
            for(int i = 0; i < count; i = i + 3){
                String[] row = new String[3];
                int rowIndex = 0;
                for(int j = i; j < i+3; j++){
                    row[rowIndex] = data.get(j);
                    rowIndex++;
                }
                model.addRow(row);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}