package com.Church.ChurchMgtSystem.controller;

import com.Church.ChurchMgtSystem.model.Believer;
import com.Church.ChurchMgtSystem.service.BelieverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/believers")
public class BelieverController {

    @Autowired
    private BelieverService believerService;

    @GetMapping
    public List<Believer> getAllBelievers() {
        return believerService.findAllBelievers();
    }

    @PostMapping
    public Believer addBeliever(@RequestBody Believer believer) {
        return believerService.saveBeliever(believer);
    }

    @GetMapping("/{id}")
    public Believer getBelieverById(@PathVariable int id) {
        return believerService.findBelieverById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteBeliever(@PathVariable int id) {
        believerService.deleteBeliever(id);
    }
}
