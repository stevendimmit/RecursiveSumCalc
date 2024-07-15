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

       
        numberFields = new JTextField[5];
        for (int i = 0; i < 5; i++) {
            numberFields[i] = new JTextField(5);
        }
        resultField = new JTextField(10);
        resultField.setEditable(false); 

        
        JLabel[] plusLabels = new JLabel[4];
        for (int i = 0; i < 4; i++) {
            plusLabels[i] = new JLabel("+");
        }
        JLabel equalsLabel = new JLabel("=");

       
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        // Add components to the panel
        for (int i = 0; i < 5; i++) {
            panel.add(numberFields[i]);
            if (i < 4) {
                panel.add(plusLabels[i]);
            }
        }
        panel.add(equalsLabel);
        panel.add(resultField);

        // Create Calculate button
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

    // Recursive method 
    private void calculateSumRecursive(int index, double sum) {
        //base case
    	if (index == 5) {
            resultField.setText(String.valueOf(sum));
            return;
        }
    	//recursive case
        try {
            double num = Double.parseDouble(numberFields[index].getText());
            calculateSumRecursive(index + 1, sum + num);
        } catch (Exception ex) {
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
