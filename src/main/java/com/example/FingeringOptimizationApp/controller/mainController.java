package com.example.FingeringOptimizationApp.controller;

import com.example.FingeringOptimizationApp.form.ConditionForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.FingeringOptimizationApp.service.Process;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class mainController {
    @ModelAttribute
    public ConditionForm setUpForm() {
        return new ConditionForm();
    }

    @Autowired
    Process process;

    @GetMapping("home")
    public String index(){
        return "index";
    }

    @PostMapping("findout")
     public String findOut(ConditionForm f, Model model){
        //処理
        List<int[]> result = process.findOut(f);

        model.addAttribute(result);
        return "result";
     }
}
