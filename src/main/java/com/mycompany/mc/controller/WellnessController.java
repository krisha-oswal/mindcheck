package com.mycompany.mc.controller;

import com.mycompany.mc.model.JournalEntry;
import com.mycompany.mc.service.WellnessAnalyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class WellnessController {

    @Autowired
    private WellnessAnalyzer wellnessAnalyzer;

    @PostMapping("/analyze")
    public Map<String, Object> analyze(@RequestBody JournalEntry entry) {
        return wellnessAnalyzer.analyze(entry);
    }
}