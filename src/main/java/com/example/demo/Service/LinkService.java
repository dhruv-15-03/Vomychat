package com.example.demo.Service;

import com.example.demo.Entity.Link;
import com.example.demo.Entity.User;
import com.example.demo.Repository.LinkRepository;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LinkService {

    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private UserRepository userRepository;

    public Link createLink(Long userId, String url, String title) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Link link = new Link(user, url, title);
        return linkRepository.save(link);
    }

    public List<Link> getUserLinks(Long userId) {
        return linkRepository.findByUserId(userId);
    }
}

