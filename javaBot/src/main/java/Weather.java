import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class Weather {
    //647e588d688b70bec7708f539c0dc80e
    public static String getWeather(String message, Model model) throws IOException {
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + message + "&units=metric&appid=647e588d688b70bec7708f539c0dc80e");
        Scanner in = new Scanner((InputStream)url.getContent());
        String result = " ";
        while (in.hasNext()) {
            result += in.nextLine();
        }
        return result;
    }
}
