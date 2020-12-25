package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.json.JSONObject;

public class Controller {

    String[] kirillic = {"а", "А" ,"б", "Б" ,"В", "в" ,"Г", "г" ,"Д", "д" ,"Е", "е" ,"Є", "є" ,"ж", "Ж" , "З", "з", "И", "и", "І", "і", "Й", "й", "К", "к", "Л", "л", "М", "м", "Н", "н", "О", "о", "П", "п", "Р", "р", "С", "с", "Т", "т", "У", "у", "Ф", "ф", "Ч", "ч", "Ц", "ц", "Ь", "ь", "Ю", "ю", "Я", "я"};

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField city;

    @FXML
    private Button getData;

    @FXML
    private Text temp_info;

    @FXML
    private Text temp_feels;

    @FXML
    private Text temp_max;

    @FXML
    private Text temp_min;

    @FXML
    void initialize() {
        getData.setOnAction(event -> {
            String getUserCity = city.getText().trim();
            if(!getUserCity.equals("")) {
                String output = null;
                try {
                    output = getUrlContent("http://api.openweathermap.org/data/2.5/weather?q=" + getUserCity + "&appid=82b797b6ebc625032318e16f1b42c016&units=metric");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (!output.isEmpty()) {
                    JSONObject obj = new JSONObject(output);
                    temp_info.setText(""+ obj.getJSONObject("main").getDouble("temp"));
                    temp_feels.setText(""+ obj.getJSONObject("main").getDouble("feels_like"));
                    temp_max.setText(""+ obj.getJSONObject("main").getDouble("temp_max"));
                    temp_min.setText(""+obj.getJSONObject("main").getDouble("temp_min"));
                }
            }
        });
    }

    private static String getUrlContent(String urlAdress) throws IOException {
        StringBuffer content = new StringBuffer();

        try {
            URL url = new URL(urlAdress);
            URLConnection urlConn = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;

            while((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("Такой город был не найден!");
        }
        return content.toString();
    }

}









