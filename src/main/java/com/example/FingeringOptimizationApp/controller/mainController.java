package com.example.FingeringOptimizationApp.controller;

import com.example.FingeringOptimizationApp.form.ConditionForm;
import com.example.FingeringOptimizationApp.service.SubProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.FingeringOptimizationApp.service.Process;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
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
    @Autowired
    SubProcess subProcess;

    @GetMapping("")
    public String index(){
        return "index";
    }

    @PostMapping("findout")
     public String findOut(ConditionForm f, Model model){
        System.out.println("[ 入力 ]");
        System.out.println(f.getMelodies().toString());
        //処理
        List<List<int[]>> results = process.findOut(f);

        System.out.println("[ 結果 ]");
        for(List<int[]> result :results){
            String str = new String();
            for(int[] position : result){
                str += Arrays.toString(position);
            }
        }

        List<boolean[][]> bitLists = subProcess.convertBitList(results);

        model.addAttribute("results",results);
        model.addAttribute("bitLists",bitLists);

        return "result";
     }
}
