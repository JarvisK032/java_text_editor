import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TextEditor extends JFrame implements ActionListener {

    private JTextArea textArea;
    private JButton openButton;
    private JButton saveButton;
    private JFileChooser fileChooser;

    public TextEditor() {

        // Set up the frame
        setTitle("Java Text Editor");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Set up the text area
        textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));

        // Set up the buttons
        openButton = new JButton("Open");
        openButton.addActionListener(this);

        saveButton = new JButton("Save");
        saveButton.addActionListener(this);

        // Set up the file chooser
        fileChooser = new JFileChooser();

        // Set up the panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(openButton);
        buttonPanel.add(saveButton);

        JScrollPane scrollPane = new JScrollPane(textArea);

        getContentPane().add(buttonPanel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openButton) {
            int returnVal = fileChooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getPath();
                openFile(filePath);
            }
        } else if (e.getSource() == saveButton) {
            int returnVal = fileChooser.showSaveDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getPath();
                saveFile(filePath);
            }
        }
    }

    private void openFile(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            reader.close();
            textArea.setText(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveFile(String filePath) {
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(textArea.getText());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new TextEditor();
    }

}
