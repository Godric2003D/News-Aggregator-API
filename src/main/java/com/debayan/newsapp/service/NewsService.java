package com.debayan.newsapp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsService {

    private final WebClient webClient = WebClient.create();

    public List<String> fetchNews(List<String> preferences) {
        List<String> newsList = new ArrayList<>();
        for (String pref : preferences) {
            String url = "https://newsapi.org/v2/everything?q=" + pref + "&from=2025-07-13&sortBy=popularity&apiKey=f8c33ff2f83d4cbf821c750609668d7b";
            String response = webClient.get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            newsList.add(response);
        }
        return newsList;
    }
}
