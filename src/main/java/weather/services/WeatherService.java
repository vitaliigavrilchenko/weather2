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
                .addQueryParameter("q", city)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("x-rapidapi-host", "community-open-weather-map.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "ed385f03demsh436761739ad79aap1ced52jsnef448043b68d")
                .build();

        Response response = client.newCall(request).execute();

        ObjectMapper mapper = new ObjectMapper();
        Weather weather = mapper.readValue(Objects.requireNonNull(response.body()).string(), Weather.class);

        return weather;
    }

}
