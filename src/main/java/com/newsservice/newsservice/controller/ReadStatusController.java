package com.newsservice.newsservice.controller;

import com.newsservice.newsservice.model.News;
import com.newsservice.newsservice.model.ReadStatus;
import com.newsservice.newsservice.model.User;
import com.newsservice.newsservice.repository.NewsRepository;
import com.newsservice.newsservice.repository.ReadStatusRepository;
import com.newsservice.newsservice.repository.UserRepository;
import com.newsservice.newsservice.service.NewsService;
import com.newsservice.newsservice.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import static com.newsservice.newsservice.Constants.API_PREFIX;

@RestController
@RequestMapping(API_PREFIX + "user/{userId}/news/{newsId}/read_status")
public class ReadStatusController {

    @Autowired
    ReadStatusRepository readStatusRepository;

    UserRepository userRepository;

    UserServiceImpl userService;
    NewsService newsService;

    NewsRepository newsRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/news/{id}/read_status")
    @Transactional
    ReadStatus readStatus(@PathVariable(value = "userId") Long userId,
                          @PathVariable(value = "newsId") Long newsId,
                          @RequestBody ReadStatus readStatus) throws Exception {
        User user = userService.findByUserId(userId);
        News news = newsService.findByNewsId(newsId);
        readStatus.setUser(user);
        readStatus.setNews(news);

        return readStatusRepository.save(readStatus);
    }
}