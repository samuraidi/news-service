package com.newsservice.newsservice.service;

import com.newsservice.newsservice.model.News;
import com.newsservice.newsservice.model.User;
import com.newsservice.newsservice.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    public List<News> getNewsForUser(User user) {

        return newsRepository.findAllNewsByRoleId(user.getRole().getRoleId(), user.getUserId());
    }

    public News findByNewsId(Long newsId) {
        return newsRepository.findById(newsId).orElse(null);
    }
}
