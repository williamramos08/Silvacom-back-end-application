import com.example.silvacomcraftproject.SilvacomCraftProjectApplication;
import com.example.silvacomcraftproject.api.model.City;
import com.example.silvacomcraftproject.api.model.WeatherResponse;
import com.example.silvacomcraftproject.service.CityService;
import com.example.silvacomcraftproject.service.WeatherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@SpringBootTest(classes = SilvacomCraftProjectApplication.class)
@AutoConfigureMockMvc
class TestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CityService cityService;

    @MockBean
    private WeatherService weatherService;

    @BeforeEach
    public void setUp() throws JsonProcessingException {
        // Setup mock data
        Mockito.when(cityService.getCity("New York")).thenReturn(new City("New York", "United States", "Description"));
        Mockito.when(cityService.getAllCities()).thenReturn(List.of(
                new City("Test City", "Country Name", "City Description"),
                new City("Test City 2", "Country Name 2", "City Description 2")
        ));
        Mockito.when(weatherService.getCurrentWeather("Test City")).thenReturn(new WeatherResponse("Test City", "Sunny", 25.0, 10.0));
        Mockito.when(weatherService.getWeatherForecast("Test City", 2)).thenReturn(List.of(
                new WeatherResponse("Test City", "Sunny", 25.0, 10.0),
                new WeatherResponse("Test City", "Cloudy", 20.0, 8.0)
        ));
    }

    @Test
    void getCity() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/city")
                        .param("id", "New York")  // Provide the required parameter 'id'
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("New York"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.country").value("United States"));
    }


    @Test
    void getAllCities() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cities")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Test City"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Test City 2"));
    }

    @Test
    void getCityWeather() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/city/weather?cityName=Test City")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.cityName").value("Test City"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.weatherDescription").value("Sunny"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.temperature").value(25.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.windSpeed").value(10.0));
    }

    @Test
    void getCityForecast() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/city/forecast?cityName=Test City&days=2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].cityName").value("Test City"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].weatherDescription").value("Sunny"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].temperature").value(25.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].windSpeed").value(10.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].cityName").value("Test City"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].weatherDescription").value("Cloudy"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].temperature").value(20.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].windSpeed").value(8.0));
    }
}
