package com.example.api_aggregator.controller;

import com.example.api_aggregator.response.WeatherResponse;
import com.example.api_aggregator.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping
    public Mono<WeatherResponse> getWeather(
            @RequestParam double lat,
            @RequestParam double lon) {
        return weatherService.getCurrentWeather(lat, lon);
    }

    @GetMapping("/sentence")
    public Mono<String> getWeatherSentence(
            @RequestParam double lat,
            @RequestParam double lon) {
        return weatherService.getWeatherSentence(lat, lon);
    }
}