import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ATM_GUI {
    private float balance = 1000;
    private int atmNumber = 12345;
    private int atmPin = 123;
    private HashMap<Double, String> miniStmt = new HashMap<>();

    private JFrame frame;
    private JPanel panel;
    private JButton checkBalanceBtn;
    private JButton withdrawBtn;
    private JButton depositBtn;
    private JButton viewMiniStmtBtn;
    private JLabel balanceLabel;

    public ATM_GUI() {
        frame = new JFrame("ATM Interface");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel atmNumberLabel = new JLabel("ATM Number:");
        atmNumberLabel.setBounds(10, 20, 80, 25);
        panel.add(atmNumberLabel);

        JTextField atmNumberField = new JTextField(20);
        atmNumberField.setBounds(100, 20, 165, 25);
        panel.add(atmNumberField);

        JLabel pinLabel = new JLabel("PIN:");
        pinLabel.setBounds(10, 50, 80, 25);
        panel.add(pinLabel);

        JPasswordField pinField = new JPasswordField(20);
        pinField.setBounds(100, 50, 165, 25);
        panel.add(pinField);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(10, 80, 80, 25);
        panel.add(submitButton);

        checkBalanceBtn = new JButton("Check Balance");
        checkBalanceBtn.setBounds(10, 120, 150, 25);
        panel.add(checkBalanceBtn);

        withdrawBtn = new JButton("Withdraw Money");
        withdrawBtn.setBounds(10, 150, 150, 25);
        panel.add(withdrawBtn);

        depositBtn = new JButton("Deposit Money");
        depositBtn.setBounds(10, 180, 150, 25);
        panel.add(depositBtn);

        viewMiniStmtBtn = new JButton("View Mini Statement");
        viewMiniStmtBtn.setBounds(10, 210, 150, 25);
        panel.add(viewMiniStmtBtn);

        balanceLabel = new JLabel("Balance: " + balance);
        balanceLabel.setBounds(10, 240, 150, 25);
        panel.add(balanceLabel);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int enteredAtmNumber = Integer.parseInt(atmNumberField.getText());
                int enteredPin = Integer.parseInt(new String(pinField.getPassword()));
                if (atmNumber == enteredAtmNumber && atmPin == enteredPin) {
                    JOptionPane.showMessageDialog(null, "Validation successful");
                    enableButtons(true);
                    atmNumberField.setEditable(false);
                    pinField.setEditable(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect ATM Number or PIN.");
                    enableButtons(false);
                }
            }
        });

        checkBalanceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Balance: " + balance);
            }
        });

        withdrawBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amountString = JOptionPane.showInputDialog("Enter Amount to Withdraw:");
                if (amountString != null) {
                    float amount = Float.parseFloat(amountString);
                    if (amount > balance) {
                        JOptionPane.showMessageDialog(null, "Insufficient Balance");
                    } else {
                        balance -= amount;
                        JOptionPane.showMessageDialog(null, "Money Withdrawn Successfully");
                        updateBalanceLabel();
                        miniStmt.put((double) amount, "Amount Withdrawn");
                    }
                }
            }
        });

        depositBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amountString = JOptionPane.showInputDialog("Enter the Amount to Deposit:");
                if (amountString != null) {
                    float amount = Float.parseFloat(amountString);
                    balance += amount;
                    JOptionPane.showMessageDialog(null, "Money Deposited Successfully");
                    updateBalanceLabel();
                    miniStmt.put((double) amount, "Amount Deposited");
                }
            }
        });

        viewMiniStmtBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder miniStatement = new StringBuilder("Mini Statement\n************************\n");
                for (Map.Entry<Double, String> entry : miniStmt.entrySet()) {
                    miniStatement.append(entry.getKey()).append(" ").append(entry.getValue()).append("\n");
                }
                JOptionPane.showMessageDialog(null, miniStatement.toString());
            }
        });
        
        // Initially, disable buttons until valid authentication
        enableButtons(false);
    }

    private void enableButtons(boolean enable) {
        checkBalanceBtn.setEnabled(enable);
        withdrawBtn.setEnabled(enable);
        depositBtn.setEnabled(enable);
        viewMiniStmtBtn.setEnabled(enable);
    }

    private void updateBalanceLabel() {
        balanceLabel.setText("Balance: " + balance);
    }

    public static void main(String[] args) {
        new ATM_GUI();
    }
}