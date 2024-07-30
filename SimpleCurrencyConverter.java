import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class SimpleCurrencyConverter extends JFrame {
    private JComboBox<String> baseCurrency;
    private JComboBox<String> targetCurrency;
    private JTextField amountField;
    private JLabel resultLabel;
    private JButton convertButton;
    private HashMap<String, Double> exchangeRates;

    public SimpleCurrencyConverter() {
        setTitle("Currency Converter");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        String[] currencies = {"USD", "EUR", "GBP", "JPY", "AUD", "CAD", "INR"};

        baseCurrency = new JComboBox<>(currencies);
        baseCurrency.setBounds(50, 50, 100, 30);
        add(baseCurrency);

        targetCurrency = new JComboBox<>(currencies);
        targetCurrency.setBounds(200, 50, 100, 30);
        add(targetCurrency);

        amountField = new JTextField();
        amountField.setBounds(50, 100, 250, 30);
        add(amountField);

        convertButton = new JButton("Convert");
        convertButton.setBounds(50, 150, 250, 30);
        add(convertButton);

        resultLabel = new JLabel("Converted Amount:");
        resultLabel.setBounds(50, 200, 250, 30);
        add(resultLabel);

        // Hardcoded exchange rates (relative to USD)
        exchangeRates = new HashMap<>();
        exchangeRates.put("USD", 1.0);
        exchangeRates.put("EUR", 0.85);
        exchangeRates.put("GBP", 0.75);
        exchangeRates.put("JPY", 110.0);
        exchangeRates.put("AUD", 1.35);
        exchangeRates.put("CAD", 1.25);
        exchangeRates.put("INR", 74.0); // Example rate: 1 USD = 74 INR

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });
    }

    private void convertCurrency() {
        String base = (String) baseCurrency.getSelectedItem();
        String target = (String) targetCurrency.getSelectedItem();
        double amount = Double.parseDouble(amountField.getText());

        double baseRate = exchangeRates.get(base);
        double targetRate = exchangeRates.get(target);

        double convertedAmount = amount / baseRate * targetRate;

        resultLabel.setText("Converted Amount: " + convertedAmount + " " + target);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SimpleCurrencyConverter().setVisible(true);
            }
        });
    }
}
