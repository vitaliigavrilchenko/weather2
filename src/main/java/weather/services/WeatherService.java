package weather.services;

import weather.models.Weather;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@Component
public class WeatherService {

    public Weather getWeather(String city) throws IOException {
        OkHttpClient client = new OkHttpClient();

        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host("community-open-weather-map.p.rapidapi.com")
                .addPathSegment("weather")
                .addQueryParameter("callback", "test")
                .addQueryParameter("id", "2172797")
                .addQueryParameter("units", "%22metric%22 or %22imperial%22")
                .addQueryParameter("mode", "xml%2C html")
                .addQueryParameter("q", city)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("x-rapidapi-host", "community-open-weather-map.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "ed385f03demsh436761739ad79aap1ced52jsnef448043b68d")
                .build();

        Response response = client.newCall(request).execute();

        String body = Objects.requireNonNull(response.body()).string();
        String result = body.substring(body.indexOf("(") + 1, body.lastIndexOf(")"));

        ObjectMapper mapper = new ObjectMapper();
        Weather weather = mapper.readValue(result, Weather.class);

        return weather;
    }

    public String getTemp(String city) throws IOException {
        int tempInKelvin = (int) Math.round(getWeather(city).getMain().getTemp());
        //int tempInKelvin = 288;
        int tempInCelsius = tempInKelvin - 273;
        return Integer.toString(tempInCelsius);
    }

}
