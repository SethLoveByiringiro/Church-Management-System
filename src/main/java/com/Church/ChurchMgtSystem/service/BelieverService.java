package com.Church.ChurchMgtSystem.service;

import com.Church.ChurchMgtSystem.model.Believer;
import com.Church.ChurchMgtSystem.repository.BelieverRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BelieverService {

    @Autowired
    private BelieverRepo believerRepo;

    public List<Believer> findAllBelievers() {
        return believerRepo.findAll();
    }

    public Believer saveBeliever(Believer believer) {
        // Additional business logic or validation can be added here
        return believerRepo.save(believer);
    }

    public Optional<Believer> findBelieverById(int id) {
        return believerRepo.findById(id);
    }

    public void deleteBeliever(int id) {
        believerRepo.deleteById(id);
    }
}
