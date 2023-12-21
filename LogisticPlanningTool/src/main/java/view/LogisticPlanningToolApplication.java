package view;

import controller.ApplicationController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class LogisticPlanningToolApplication {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        ApplicationController controller = new ApplicationController();
        JFrame frame = new JFrame("Simple Addition Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 300);
        frame.setLayout(new GridLayout(6, 2));

        JLabel filePathLabel = new JLabel("FilePath:");
        JTextField filePathField = new JTextField();

        JButton addFileButton = new JButton("AddFile");
        JLabel addResultLabel = new JLabel("Result:");

        JLabel sourceLabel = new JLabel("Source:");
        JTextField sourceField = new JTextField();

        JLabel targetLabel = new JLabel("Target:");
        JTextField targetField = new JTextField();

        JButton calculateButton = new JButton("Calculate");
        JTextField calculateResultLabel = new JTextField("Result:");

        addFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addResultLabel.setText("reading");
                    String path = filePathField.getText();
                    controller.GenerateGraphWithExcelFile(path);
                    addResultLabel.setText("added file successfully");
                }catch (IllegalArgumentException ex){
                    addResultLabel.setText(ex.getMessage());
                }
            }
        });

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String source = sourceField.getText();
                    String target = targetField.getText();
                    String result = controller.GenerateShortestPath(source,target);
                    calculateResultLabel.setText("shortest path: "+result);
                }catch (IllegalArgumentException ex){
                    calculateResultLabel.setText(ex.getMessage());
                }
            }
        });
        frame.add(filePathLabel);
        frame.add(filePathField);
        frame.add(addFileButton);
        frame.add(addResultLabel);
        frame.add(sourceLabel);
        frame.add(sourceField);
        frame.add(targetLabel);
        frame.add(targetField);
        frame.add(calculateButton);
        frame.add(calculateResultLabel);
        frame.setVisible(true);
    }
}
