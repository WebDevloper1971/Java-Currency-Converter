import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

import javax.swing.*;

public class CurrencyConverter {

    public CurrencyConverter() {

        Set<String> currencies = new HashSet<>();
        currencies.add("INR");
        currencies.add("RUB");
        currencies.add("JPY");
        currencies.add("ILS");
        currencies.add("NPR");
        currencies.add("EUR");
        currencies.add("USD");

        JFrame frame = new JFrame("Currency Converter");
        JPanel panel = new JPanel();

        int x = 40;
        int y = 50;
        int gap = 10;

        JLabel fromLbl = new JLabel("From :");
        fromLbl.setBounds(x, y - 30, 100, 30);

        @SuppressWarnings({ "rawtypes", "unchecked" })
        JComboBox comboBox = new JComboBox(currencies.toArray());
        comboBox.setBounds(x, y, 80, 30);
        comboBox.setBorder(BorderFactory.createLineBorder(Color.gray));
        comboBox.setBackground(Color.white);
        comboBox.setOpaque(false);

        JLabel toLbl = new JLabel("To :");
        toLbl.setBounds(x + comboBox.getWidth() + gap + 20, y - 30, 100, 30);

        @SuppressWarnings({ "rawtypes", "unchecked" })
        JComboBox comboBox2 = new JComboBox(currencies.toArray());
        comboBox2.setBounds(toLbl.getX(), y, 80, 30);
        comboBox2.setBorder(BorderFactory.createLineBorder(Color.gray));

        JLabel lbl = new JLabel("Enter amount :");
        lbl.setBounds(x, comboBox.getY() + comboBox.getHeight() + 20, 100, 30);

        JTextField tf = new JTextField();
        tf.setBounds(x, lbl.getY() + lbl.getHeight(), 80, 30);
        tf.setHorizontalAlignment(SwingConstants.CENTER);
        tf.setFont(new Font("Verdana", Font.BOLD, 14));
        tf.setBorder(BorderFactory.createLineBorder(Color.gray));

        JLabel lbl2 = new JLabel("Converted amount :");
        lbl2.setBounds(lbl.getX() + lbl.getWidth() + gap, lbl.getY(), 120, 30);

        JLabel lblConverted = new JLabel("0", SwingConstants.CENTER);
        lblConverted.setBorder(BorderFactory.createLineBorder(Color.gray));
        lblConverted.setFont(new Font("Verdana", Font.BOLD, 14));
        lblConverted.setBounds(lbl2.getX(), lbl2.getY() + lbl2.getHeight(), 80, 30);

        JButton btn = new JButton("Convert");
        btn.setBounds(tf.getX() + 50, tf.getY() + tf.getHeight() + 30, 100, 30);
        btn.setBorder(null);
        btn.setBorderPainted(false);
        btn.setBackground(Color.black);
        btn.setForeground(Color.white);
        btn.setFocusPainted(false);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DecimalFormat df = new DecimalFormat("#.00");

                if (tf.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Enter Amount To Be Converted");
                } else {

                    String from = comboBox.getSelectedItem().toString();
                    String to = comboBox2.getSelectedItem().toString();

                    try {
                        double userAmount = Double.parseDouble(tf.getText());
                        double conversion = sendHttpRequest(from, to, 100.0d);
                        double convertedAmount = conversion * userAmount;
                        lblConverted.setText(df.format(convertedAmount));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                }

            }
        });

        panel.setBackground(Color.white);
        panel.setLayout(null);
        panel.add(comboBox);
        panel.add(comboBox2);
        panel.add(tf);
        panel.add(lbl);
        panel.add(toLbl);
        panel.add(fromLbl);
        panel.add(btn);
        panel.add(lbl2);
        panel.add(lblConverted);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setContentPane(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new CurrencyConverter();
    }

    public double sendHttpRequest(String from, String to, double amount) throws IOException {

        double conversionRate = 0.0d;
        try {
            // URL of the server endpoint
            String urlString = "any free currency conversion api url ?base=" + from + "&symbols=" + to;
            @SuppressWarnings("deprecation")
            URL url = new URL(urlString);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Get the response code
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Read the response
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Print the response
            // System.out.println("Response: " + response.toString());
            // Change this according to url or use JSON library
            String rates = (response.toString().split(",")[3]);
            String conversion = rates.substring(15, 20);
            conversionRate = Double.parseDouble(conversion);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conversionRate;
    }
}
