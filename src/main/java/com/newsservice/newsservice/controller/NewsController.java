package com.newsservice.newsservice.controller;

import com.newsservice.newsservice.model.News;
import com.newsservice.newsservice.model.User;
import com.newsservice.newsservice.repository.NewsRepository;
import com.newsservice.newsservice.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.newsservice.newsservice.Constants.API_PREFIX;

@RestController("newsservice-api/NewsController")
@RequestMapping(API_PREFIX + "user/{userId}/news")
public class NewsController {

    @Autowired
    private NewsRepository repository;

    @Autowired
    private NewsService newsService;

    @GetMapping("/")
    List<News> findUnreadNews(@PathVariable("userId") User user){
        return newsService.getNewsForUser(user);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    News news(@RequestBody News news) {
        return repository.save(news);
    }

    @PutMapping("/{id}")
    News saveOrUpdate(@RequestBody News news, @PathVariable Long id) {

        return repository.findById(id)
                .map(x -> {
                    x.setTitle(news.getTitle());
                    x.setText(news.getText());
                    x.setDateCreated(news.getDateCreated());
                    x.setPictureLink(news.getPictureLink());
                    x.setValidTo(news.getValidTo());
                    x.setValidFrom(news.getValidFrom());
                    return repository.save(x);
                })
                .orElseGet(() -> {
                    news.setNewsId(id);
                    return repository.save(news);
                });
    }

    @DeleteMapping("/news/{id}")
    void deleteNews(@PathVariable Long id){
        repository.deleteById(id);
    }
}
