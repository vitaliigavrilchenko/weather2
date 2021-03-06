package weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import weather.models.Weather;
import weather.services.WeatherService;


@Controller
public class MainController {
    @Autowired
    private WeatherService weatherService;

    @GetMapping("/greeting")
    public String greetingForm() {

        return "greeting";
    }

    @PostMapping("/result")
    public String greetingSubmit(@RequestParam(name = "city") String city, Model model) throws Exception {
        Weather weather  = weatherService.getWeather(city);

        model.addAttribute("weather", weather);
        return "result";
    }
}
