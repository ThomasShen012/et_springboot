package com.et.expense_tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.et.expense_tracker.repository.TestConnectionRepository;
import com.et.expense_tracker.model.TestConnection;


@RestController
public class TestController {
    
    @Autowired
    private TestConnectionRepository testConnectionRepository;

    @GetMapping("/test")
    public String testDatabase(){
        try{
            TestConnection test = new TestConnection();
            test.setMessage("Test Connection Success");
            testConnectionRepository.save(test);
            return "Database Test Success";
        }catch(Exception e){
            return "Database Test Failed: " + e.getMessage();
        }
    }

}
