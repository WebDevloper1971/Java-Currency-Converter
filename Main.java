import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Main extends JFrame implements ActionListener{

    // 1 yen = 0.56 rupees
    // 1 dollar = 83.29 rupees
    // 1 chinese yuan = 11.55 rupees
    // 1 pakistani rupee = 0.29 rupees
    // 1 russian ruble = 0.93 rupees
    // 1 pound sterling = 103.74 rupees

    private JLabel ftitle, rtitle;
    private JTextField foreignField,valueInRupees;
    private JComboBox <String> selection;

    private double foreignValue, indianValue;
   
    Main(){

        ftitle = new JLabel("Choose Foreign Currency");
        ftitle.setBounds(10,25,200,30);

        foreignField = new JTextField();
        foreignField.setBounds(10,75,200,35);
        foreignField.setMargin(new Insets(0, 10, 0, 0));
        foreignField.setOpaque(true);

        String [] foreignCurrency = {"yen","dollar","yuan","pak rupee", "ruble", "pound sterling"};
        selection = new JComboBox <String>(foreignCurrency);
        selection.addActionListener(this);
        selection.setBounds(250,75,200,33);

        rtitle = new JLabel("Value In Rupees");
        rtitle.setBounds(10,150,200,30);

        valueInRupees = new JTextField("Rs. ");
        valueInRupees.setBounds(10,200,200,35);
        valueInRupees.setMargin(new Insets(0, 10, 0, 0));
        valueInRupees.setEnabled(false);
        valueInRupees.setDisabledTextColor(Color.black);

        add(ftitle);
        add(foreignField);
        add(rtitle);
        add(valueInRupees);
        add(selection);


        setTitle("Currency Converter");
        setLayout(null);
        setResizable(false);
        setSize(500,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    public static void main(String[] args) {
        new Main();   

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
                if (e.getSource() == selection) {
            
                    if(selection.getSelectedItem() == "yen"){
                        foreignValue = Integer.parseInt(foreignField.getText());
                        indianValue = foreignValue * 0.56;

                        valueInRupees.setText("Rs. "+ indianValue);
                    }
                    if(selection.getSelectedItem() == "dollar"){
                        foreignValue = Integer.parseInt(foreignField.getText());
                        indianValue = foreignValue * 83.29;

                        valueInRupees.setText("Rs. "+ indianValue);
                    }
                    if(selection.getSelectedItem() == "yuan"){
                        foreignValue = Integer.parseInt(foreignField.getText());
                        indianValue = foreignValue * 11.55;

                        valueInRupees.setText("Rs. "+ indianValue);
                    }
                    if(selection.getSelectedItem() == "pak rupee"){
                        foreignValue = Integer.parseInt(foreignField.getText());
                        indianValue = foreignValue * 0.29;

                        valueInRupees.setText("Rs. "+ indianValue);
                    }
                    if(selection.getSelectedItem() == "ruble"){
                        foreignValue = Integer.parseInt(foreignField.getText());
                        indianValue = foreignValue * 0.93;

                        valueInRupees.setText("Rs. "+ indianValue);
                    }
                    if(selection.getSelectedItem() == "pound sterling"){
                        foreignValue = Integer.parseInt(foreignField.getText());
                        indianValue = foreignValue * 103.74;

                        valueInRupees.setText("Rs. "+ indianValue);
                    }
                }
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(getParent(), "Please enter a number");
        }
    }
    
}