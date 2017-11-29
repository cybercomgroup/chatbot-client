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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.text.DefaultCaret;
import org.json.JSONObject;
import org.json.JSONTokener;

public class ClientWindow implements ActionListener, FocusListener{

    private JFrame window;
    private JPanel contentPanel;
    private JTextArea returnDisplay;
    private Connection con;

    public ClientWindow(Connection con){
        this.con = con;
        createWindow();
        createLayout();
        window.pack();
    }

    private void createWindow(){
        window = new JFrame("Chatbot client");
        window.setMinimumSize(new Dimension(400,600));
        window.setPreferredSize(new Dimension(400,600));
        window.setMaximumSize(new Dimension(400,600));

        window.setLocationRelativeTo(null);

        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    private void createLayout(){
        contentPanel = new JPanel(new BorderLayout());
        JPanel topContentPanel = new JPanel(new GridLayout(2,1));
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new BorderLayout());

        JLabel requestLabel = new JLabel("Fråga: ");
        JTextField writeField = new JTextField();
        writeField.requestFocus();
        writeField.grabFocus();
        returnDisplay = new JTextArea();
        returnDisplay.setLineWrap(true);
        returnDisplay.setEditable(false);

        JScrollPane chatAreaScrollerPane = new JScrollPane(returnDisplay);
        chatAreaScrollerPane.setPreferredSize(new Dimension(394,475));
        chatAreaScrollerPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        chatAreaScrollerPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        DefaultCaret caret = (DefaultCaret) returnDisplay.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        JButton sendBtn = new JButton("Send");
        window.getRootPane().setDefaultButton(sendBtn);
        sendBtn.addActionListener((ActionEvent e) -> {
            setDisplay(writeField.getText());
            writeField.setText("");
        });

        contentPanel.add(topContentPanel, BorderLayout.PAGE_START);
        contentPanel.add(chatAreaScrollerPane, BorderLayout.CENTER);

        topContentPanel.add(requestLabel);
        topContentPanel.add(middlePanel);

        middlePanel.add(writeField, BorderLayout.CENTER);
        middlePanel.add(sendBtn, BorderLayout.EAST);

        window.add(contentPanel);
    }

    public String sendRequest(String request){
        String query = queryParser(request);
        JSONObject jsonObject = new JSONObject(new JSONTokener(con.send(query))) ;
        return jsonParser(jsonObject);
    }

    private String jsonParser(JSONObject jsonObject){
        System.out.println("test2");
        StringBuilder sb = new StringBuilder();
        sb.append(" " + jsonObject.get("response1") + "\n");
        sb.append("                " + jsonObject.get("response2") + "\n");
        sb.append("                " + jsonObject.get("response3") + "\n");

        return sb.toString();
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

    public void setDisplay(String writeText){
        StringBuilder sb = new StringBuilder();
        sb.append(returnDisplay.getText() + "\n" + writeText + "\n" + "Chatbot: " + sendRequest(writeText));
        /*sb.append(writeText + "\n");
        sb.append("_" + sendRequest(writeText));*/
        returnDisplay.setText(sb.toString());
    }

    public String getReturnDisplay(){
        return returnDisplay.getText();
    }
}
