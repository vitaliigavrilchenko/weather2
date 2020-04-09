package weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import weather.services.WeatherService;

import java.io.IOException;

@Controller
public class MainController {
    @Autowired
    private WeatherService weatherService;

    @RequestMapping("/temp")
    public String getTemp(@RequestParam(value = "city", required = false, defaultValue = "London") String city, Model model)
            throws IOException {

        String temp = weatherService.getTemp(city);
        model.addAttribute("city", city);
        model.addAttribute("temp", temp);

        return "tempPage";
    }
}
