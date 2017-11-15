package ChatbotClient;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.Normalizer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class ClientWindow implements ActionListener, FocusListener{

    private JFrame window;
    private JPanel contentPanel;
    private Connection con;

    public ClientWindow(Connection con){
        this.con = con;
        createWindow();
        createLayout();
        window.pack();
    }

    private void createWindow(){
        window = new JFrame("Chatbot client");
        window.setMinimumSize(new Dimension(400,125));
        window.setPreferredSize(new Dimension(400,125));
        window.setMaximumSize(new Dimension(400,125));

        window.setLocationRelativeTo(null);

        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    private void createLayout(){
        contentPanel = new JPanel(new GridLayout(3,1));
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new BorderLayout());

        JLabel requestLabel = new JLabel("Fråga: ");
        JTextField writeField = new JTextField();
        writeField.requestFocus();
        JTextField returnDisplay = new JTextField();

        JButton sendBtn = new JButton("Send");
        window.getRootPane().setDefaultButton(sendBtn);
        sendBtn.addActionListener((ActionEvent e) -> {
            returnDisplay.setText(sendRequest(writeField.getText()));
        });

        contentPanel.add(requestLabel);
        contentPanel.add(middlePanel);
        contentPanel.add(returnDisplay);

        middlePanel.add(writeField, BorderLayout.CENTER);
        middlePanel.add(sendBtn, BorderLayout.EAST);

        window.add(contentPanel);
    }

    private String sendRequest(String request){
        String query = queryParser(request);
        return con.send(query);
    }

    private String queryParser(String query){

        String temp = query.replace("ö", "o");
        query = temp.replace("å", "a");
        temp = query.replace("ä", "a");
        query = temp.replace("?", "");

        return query.replaceAll(" ", "+");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {

    }
}
