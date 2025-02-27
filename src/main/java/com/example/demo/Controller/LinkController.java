package com.example.demo.Controller;
import com.example.demo.Entity.Link;
import com.example.demo.Service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/links")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @PostMapping("/create")
    public Link createLink(@RequestParam Long userId,
                           @RequestParam String url,
                           @RequestParam String title) {
        return linkService.createLink(userId, url, title);
    }

    @GetMapping("/user/{userId}")
    public List<Link> getUserLinks(@PathVariable Long userId) {
        return linkService.getUserLinks(userId);
    }
}

