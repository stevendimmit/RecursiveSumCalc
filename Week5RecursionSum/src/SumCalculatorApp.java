import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SumCalculatorApp extends JFrame {

    private JTextField[] numberFields;
    private JTextField resultField;

    public SumCalculatorApp() {
        setTitle("Sum Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 150);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        numberFields = new JTextField[5];
        resultField = new JTextField(10);
        resultField.setEditable(false);

        createComponents(panel, 0); // Create number fields and plus labels recursively

        JLabel equalsLabel = new JLabel("=");
        panel.add(equalsLabel);

        panel.add(resultField);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateSumRecursive(0, 0.0);
            }
        });
        panel.add(calculateButton);

        add(panel);
        setVisible(true);
    }

    // Recursive method to create number fields and plus labels
    private void createComponents(JPanel panel, int index) {
        if (index >= 5) {
            return; // Base case: stop recursion when all fields are created
        }

        numberFields[index] = new JTextField(5);
        panel.add(numberFields[index]);

        if (index < 4) { // Add plus label after each text field except the last one
            JLabel plusLabel = new JLabel("+");
            panel.add(plusLabel);
        }

        createComponents(panel, index + 1); // Recursively create next component
    }

    // Recursive method to calculate sum
    private void calculateSumRecursive(int index, double sum) {
        if (index == 5) {
            resultField.setText(String.valueOf(sum));
            return; // Base case: stop recursion when all numbers are summed
        }

        try {
            String input = numberFields[index].getText().trim();
            double num = Double.parseDouble(input);
            calculateSumRecursive(index + 1, sum + num); // Recursively sum next number
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(SumCalculatorApp.this,
                    "Please enter valid numbers in all fields.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SumCalculatorApp();
            }
        });
    }
}
