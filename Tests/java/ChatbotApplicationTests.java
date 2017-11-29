import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import ChatbotClient.ClientWindow;
import ChatbotClient.Connection;
import org.junit.Before;
import org.junit.jupiter.api.Test;

public class ChatbotApplicationTests {

    private ClientWindow cw;
    private Connection connection;

    @Test
    public void placeService_GetPizzaInfo_ReturnInfo(){

        connection = new Connection();
        cw = new ClientWindow(connection);

        cw.setDisplay("Vart kan jag köpa pizza?");
        assertEquals("\nVart kan jag köpa pizza?\n"
                + "Chatbot:  Kapten Nemo's Restaurang Pizzeria\n"
                + "                Lindholmens Pizzeria\n"
                + "                Pizzeria Class\n", cw.getReturnDisplay());
    }

    @Test
    public void placeService_GetKaffeInfo_ReturnInfo(){

        connection = new Connection();
        cw = new ClientWindow(connection);

        cw.setDisplay("Vart kan jag köpa kaffe?");
        assertEquals("\nVart kan jag köpa kaffe?\n"
                + "Chatbot:  Kokboken\n"
                + "                Pressbyrån\n"
                + "                Café Norra Älvstranden\n", cw.getReturnDisplay());
    }

}
