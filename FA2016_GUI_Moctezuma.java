package PhoneService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;

public class FA2016_GUI_Moctezuma extends JFrame implements ActionListener {
    private int tracker = 0;
    private JButton JBN;
    private JButton Delete;
    private JTextField JTF;
    private JTextArea JTA_NAME;
    private JTextArea JTA_PHONE;
    private JTextArea JTA_ADDRESS;
    private JTextArea JTA_TEMP;
    private JTextArea JTA_ChangeName;
    private JTextArea JTA_ChangeName2;
    private JLabel to = new JLabel("to");
    private JButton Change = new JButton("Change");
    private final JLabel Name = new JLabel("Name");
    private final JLabel Phone = new JLabel("Phone Number");
    private final JLabel Address = new JLabel("Address");
    private Hashtable<String, FA2016_PhoneCustomer_Moctezuma> table = new Hashtable<String, FA2016_PhoneCustomer_Moctezuma>();


    public FA2016_GUI_Moctezuma() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JBN = new JButton("Add Contact");
        Delete = new JButton("Delete Contact");
        //Where the user types contact.
        JTF = new JTextField();
        //Where it displays user contact.
        JTA_NAME = new JTextArea();
        JTA_PHONE = new JTextArea();
        JTA_ADDRESS = new JTextArea();
        JTA_TEMP = new JTextArea();
        JTA_ChangeName = new JTextArea();
        JTA_ChangeName2 = new JTextArea();

        //MenuBar
        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);
        JMenu file = new JMenu("File");
        menubar.add(file);
        // Add a JMenuItem
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(new exit());
        file.add(exit);



        JTF.setPreferredSize(new Dimension(100, 20));
        JTA_NAME.setPreferredSize(new Dimension(200, 150));
        JTA_NAME.setEditable(false);
        JTA_PHONE.setPreferredSize(new Dimension(200,150));
        JTA_PHONE.setEditable(false);
        JTA_ADDRESS.setPreferredSize(new Dimension(200,150));
        JTA_ADDRESS.setEditable(false);
        JTA_TEMP.setPreferredSize(new Dimension(200,50));
        JTA_TEMP.setEditable(false);
        JPanel left = new JPanel();
        JPanel leftCenter = new JPanel();
        JPanel leftDown = new JPanel();
        leftCenter.setPreferredSize(new Dimension(200, 200));
        leftDown.setPreferredSize(new Dimension(500,100));
        leftCenter.add(JBN);
        leftCenter.add(JTF);
        leftCenter.add(Delete);
        leftCenter.add(JTA_TEMP);

        leftDown.setLayout(new FlowLayout());
        JTA_ChangeName.setPreferredSize(new Dimension(100,20));
        JTA_ChangeName2.setPreferredSize(new Dimension(100,20));
        leftDown.add(JTA_ChangeName);
        leftDown.add(to);
        leftDown.add(JTA_ChangeName2);
        leftDown.add(Change);

        JPanel right = new JPanel();
        this.setLayout(new FlowLayout());
        left.setLayout(new BorderLayout());
        right.setLayout(new GridBagLayout());

        left.add(leftCenter, BorderLayout.NORTH);
        left.add(leftDown, BorderLayout.SOUTH);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        right.add(Name,c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 1;
        c.gridx = 1;
        c.gridy = 0;
        right.add(Phone,c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 1;
        c.gridx = 2;
        c.gridy = 0;
        right.add(Address,c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 1;
        right.add(JTA_NAME,c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 1;
        c.gridx = 1;
        c.gridy = 1;
        right.add(JTA_PHONE,c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 1;
        c.gridx = 2;
        c.gridy = 1;
        right.add(JTA_ADDRESS,c);
        this.add(left);
        this.add(right);

        JBN.addActionListener(this);
        Delete.addActionListener(this);
        Change.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e) {
        //Contact button
        if (e.getSource() == JBN) {
            if (JTF.getText().equals("")) {}
            else {
                tracker++;
                String temp;
                switch (tracker) {
                    case 1:
                        JBN.setText("Add Phone #");
                        JTA_TEMP.append(JTF.getText() + "\n");
                        JTF.setText("");
                        //JBN = new JButton("Add Phone #");
                        break;
                    case 2:
                        JBN.setText("Add Address");
                        JTA_TEMP.append(JTF.getText() + "\n");
                        JTF.setText("");
                        break;
                    case 3:
                        JBN.setText("Add Contact");
                        JTA_TEMP.append(JTF.getText() + "\n");
                        JTF.setText("");
                        tracker = 0;//reset tracker.
                        FA2016_PhoneCustomer_Moctezuma contact = new FA2016_PhoneCustomer_Moctezuma(JTA_TEMP.getText());
                        if (contact.getLastName() == null)
                            table.put(contact.getFirstName(), contact);
                        else
                            table.put(contact.getLastName(), contact);
                        updateBook(table);
                        JTA_TEMP.setText("");
                        break;
                }
            }
        }
        else if(e.getSource() == Delete) {
            if(JTF.getText().equals("")) {}
            else {
                String key = JTF.getText();
                table.remove(key);
                updateBook(table);
                JTF.setText("");
            }
        }
        else if(e.getSource() == Change) {
            if(JTA_ChangeName.getText().equals("") || JTA_ChangeName2.getText().equals("")) {}
            else {
                String original = JTA_ChangeName.getText();
                Set<String> keys = table.keySet();
                String temp;
                for(String key:keys) {
                    temp = table.get(key).getFirstName();
                    if(temp.equals(original))
                        table.get(key).setFirstName(JTA_ChangeName2.getText());
                    temp = table.get(key).getLastName();
                    if(temp.equals(original))
                        table.get(key).setLastName(JTA_ChangeName2.getText());
                    temp = table.get(key).getAddress();
                    if(temp.equals(original))
                        table.get(key).setAddress(JTA_ChangeName2.getText());
                    temp = table.get(key).getPhoneNumber();
                    if(temp.equals(original))
                        table.get(key).setPhoneNumber(JTA_ChangeName2.getText());
                    temp = table.get(key).getFirstName()+ " " + table.get(key).getLastName();
                    if(temp.equals(original)) {
                        String name[] = JTA_ChangeName2.getText().split(" ");
                        table.get(key).setFirstName(name[0]);
                        table.get(key).setLastName(name[1]);
                    }
                }
                JTA_ChangeName.setText("");
                JTA_ChangeName2.setText("");
                updateBook(table);
            }
        }
    }
    private void updateBook(Hashtable<String, FA2016_PhoneCustomer_Moctezuma> table) {
        //Display contact in phone book.
        JTA_PHONE.setText("");
        JTA_NAME.setText("");
        JTA_ADDRESS.setText("");
        Enumeration names = table.keys();
        String key;
        while(names.hasMoreElements()) {
            key = (String) names.nextElement();
            if(table.get(key).getLastName() == null)
                JTA_NAME.append(table.get(key).getFirstName()+"\n");
            else
                JTA_NAME.append(table.get(key).getFirstName() + " " + table.get(key).getLastName()+"\n");
            JTA_PHONE.append(table.get(key).getPhoneNumber()+"\n");
            JTA_ADDRESS.append(table.get(key).getAddress()+ "\n");
        }
    }
    static class exit implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
