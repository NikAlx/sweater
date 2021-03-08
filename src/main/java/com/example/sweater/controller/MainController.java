package com.example.sweater.controller;

import com.example.sweater.domain.Message;
import com.example.sweater.domain.User;
import com.example.sweater.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String tagFilter, Model model) {
        Iterable<Message> messages = (tagFilter != null && !tagFilter.isEmpty()) ?
                messageRepo.findByTag(tagFilter) : messageRepo.findAll();
        model.addAttribute("messages", messages);
        model.addAttribute("tagFilter", tagFilter);
        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag, Map<String, Object> model) {
        if (text != null && !text.isEmpty()) {
            messageRepo.save(new Message().setText(text).setTag(tag).setAuthor(user));
        }
        model.put("messages", messageRepo.findAll());
        return "main";
    }

    @PostMapping("/delete")
    public String delete(Long deletedId, Map<String, Object> model) {
        if (deletedId != null) {
            messageRepo.deleteById(deletedId);
        }
        model.put("messages", messageRepo.findAll());
        return "main";
    }
}
