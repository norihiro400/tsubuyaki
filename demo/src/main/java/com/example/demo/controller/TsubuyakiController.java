package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.form.TsubuyakiForm;
import com.example.demo.service.TsubuyakiService;
import com.example.demo.entity.TsubuyakiEntity;

import lombok.var;



@Controller
@RequestMapping("/tsubuyaki")
public class TsubuyakiController {

    private final TsubuyakiService tsubuyakiService;

    public TsubuyakiController(TsubuyakiService tsubuyakiService){
        this.tsubuyakiService = tsubuyakiService;
    }

    @GetMapping
    public String index(){
        return "index";
    }

    // つぶやきの取得
    @GetMapping("/read")
    public String showTsubuyakiList(Model model){
        var tsubuyaki_list = tsubuyakiService.getAllTsubuyaki();
        model.addAttribute("tsubuyakiList", tsubuyaki_list);
        return "tsubuyaki_list";
    }

    // つぶやきの投稿
    @PostMapping("")
    public String postTsubuyaki(TsubuyakiForm form){
        var  entity = form.toEntity();
        tsubuyakiService.postTsubuyaki(entity);
        return "redirect://read";
    }
    
    @GetMapping("/search")
    public String showSearchresults(@RequestParam(name = "keyword",required = false) String keyword, Model model ){
        if (keyword != null && !keyword.isEmpty()){
            List<TsubuyakiEntity> searchResult = tsubuyakiService.searchTsubuyaki(keyword);
            model.addAttribute("searchResult", searchResult);
        }else {
            model.addAttribute("enptyMessage", "該当するつぶやきはありません");
        }
        return "search_result";
    }

}
