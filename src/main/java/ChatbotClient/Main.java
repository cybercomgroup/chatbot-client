package ChatbotClient;

public class Main {

    public static void main(String[] args){
        Connection con = new Connection();
        new ClientWindow(con);
    }

}
