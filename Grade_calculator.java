import java.util.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Grade_Calculator extends JFrame {

    private JTextField subjectcount;
    private JTextField[] marksFields;
    private JLabel totalmark_label;
    private JLabel averagepercentage_label;
    private JLabel grade_label;
    private JButton Calculate_button;
    private JButton resetButton;

    public Grade_Calculator() {
        setTitle("Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel subjectcountLabel = new JLabel("Enter no of subjects:");
        subjectcountLabel.setBounds(20, 20, 200, 30);
        add(subjectcountLabel);

        subjectcount = new JTextField();
        subjectcount.setBounds(220, 20, 150, 30);
        add(subjectcount);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(150, 60, 100, 30);
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                creationOfMarksField();
            }
        });
        add(submitButton);

        totalmark_label = new JLabel("Total Marks:");
        totalmark_label.setBounds(20, 300, 200, 30);
        add(totalmark_label);

        averagepercentage_label = new JLabel("Average Percentage: ");
        averagepercentage_label.setBounds(20, 330, 200, 30);
        add(averagepercentage_label);

        grade_label = new JLabel("Grade: ");
        grade_label.setBounds(20, 360, 200, 30);
        add(grade_label);

        Calculate_button = new JButton("Calculate");
        Calculate_button.setBounds(250, 300, 100, 30);
        Calculate_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculate_result();
            }
        });
        Calculate_button.setEnabled(false);
        add(Calculate_button);

        resetButton = new JButton("Reset");
        resetButton.setBounds(250, 340, 100, 30);
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetFields();
            }
        });
        resetButton.setEnabled(false);
        add(resetButton);
    }

    // Function for taking input from user
    void creationOfMarksField() {
        int num_subjects = Integer.parseInt(subjectcount.getText());
        marksFields = new JTextField[num_subjects];

        int yPosition = 100;
        for (int i = 0; i < num_subjects; i++) {
            JLabel label = new JLabel("Marks for subject " + (i + 1) + ":");
            label.setBounds(20, yPosition, 150, 30);
            add(label);

            marksFields[i] = new JTextField();
            marksFields[i].setBounds(220, yPosition, 150, 30);
            add(marksFields[i]);

            yPosition += 40;
        }

        revalidate();
        repaint();

        Calculate_button.setEnabled(true);
        resetButton.setEnabled(true);
    }

    // Function for calculating total and percentage
    private void calculate_result() {
        int sum = 0;
        for (JTextField field : marksFields) {
            sum += Integer.parseInt(field.getText());
        }
        int numSubjects = marksFields.length;
        double average_Percentage = (double) sum / numSubjects;

        // Function for calculating grade
        char grade;
        if (average_Percentage >= 90) {
            grade = 'A';
        } else if (average_Percentage >= 75) {
            grade = 'B';
        } else if (average_Percentage >= 50) {
            grade = 'C';
        } else if (average_Percentage >= 30) {
            grade = 'D';
        } else {
            grade = 'F';
        }

        totalmark_label.setText("Total Marks: " + sum);
        averagepercentage_label.setText("Average Percentage: " + average_Percentage);
        grade_label.setText("Grade: " + grade);
    }

    // Function to reset fields
    private void resetFields() {
        subjectcount.setText("");
        if (marksFields != null) {
            for (JTextField field : marksFields) {
                remove(field);
            }
        }
        totalmark_label.setText("Total Marks:");
        averagepercentage_label.setText("Average Percentage:");
        grade_label.setText("Grade:");

        Calculate_button.setEnabled(false);
        resetButton.setEnabled(false);

        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Grade_Calculator().setVisible(true);
            }
        });
    }
}
