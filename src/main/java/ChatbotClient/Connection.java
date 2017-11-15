package ChatbotClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Connection {

    private final String url = "http://localhost:8080/chatbot/request";

    private URLConnection connection;

    public Connection(){
    }

    public String send(String request){
        try {
            connection = new URL(url + "?request=" +  request).openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            InputStream temp = connection.getInputStream();
            String sTemp = getStringFromInputStream(temp);
            temp.close();
            return sTemp;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String getStringFromInputStream(InputStream temp) {
        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = new StringBuilder();

        String line;

        try{
            bufferedReader = new BufferedReader(new InputStreamReader(temp));
            while ((line = bufferedReader.readLine())!=null)
                stringBuilder.append(line);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (bufferedReader != null){
                try{
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuilder.toString();
    }
}
