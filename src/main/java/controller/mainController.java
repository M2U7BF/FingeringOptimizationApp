package controller;

import form.ConditionForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import service.Process;

import java.util.List;

@Controller
public class mainController {
    @ModelAttribute
    public ConditionForm setUpForm() {
        return new ConditionForm();
    }

    @Autowired
    Process process;

    @GetMapping("")
    public String index(ConditionForm f){
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
