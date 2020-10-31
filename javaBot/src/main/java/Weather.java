import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class Weather {
    //647e588d688b70bec7708f539c0dc80e
    public static String getWeather(String message, Model model) throws IOException {
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + message + "&units=metric&appid=647e588d688b70bec7708f539c0dc80e");
        Scanner in = new Scanner((InputStream)url.getContent());
        String result = "";
        while (in.hasNext()) {
            result += in.nextLine();
        }
        JSONObject obj = new JSONObject(result);
        model.setName(obj.getString("name"));

        JSONObject main = obj.getJSONObject("main");
        model.setTemp(main.getDouble("temp"));
        model.setHumidity(main.getDouble("humidity"));

        JSONArray getArray = obj.getJSONArray("weather");
        for(int i = 0; i < getArray.length(); i++) {
            JSONObject object = getArray.getJSONObject(i);
            model.setIcon((String)object.get("icon"));
            model.setMain((String)object.get("main"));
        }

        return "City: " + model.getName() + "\n" +
                "Temperature: " + model.getTemp() + "C\n" +
                "Humidity: " + model.getHumidity() + "%\n" +
                "Main: " + model.getMain() + "\n" +
                "https://openweathermap.org/img/wn/" + model.getIcon() + ".png ";
    }
}
