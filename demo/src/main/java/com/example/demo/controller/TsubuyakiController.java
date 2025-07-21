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
    public String showTsubuyakiList(Model model,TsubuyakiForm tsubuyakiForm){
        if (tsubuyakiForm == null){
            tsubuyakiForm = new TsubuyakiForm(null,null);
        }
        model.addAttribute("tsubuyakiForm", tsubuyakiForm);
        var tsubuyaki_list = tsubuyakiService.getAllTsubuyaki();
        model.addAttribute("tsubuyakiList", tsubuyaki_list);
        return "tsubuyaki_list";
    }

    // つぶやきの投稿
    @PostMapping
    public String postTsubuyaki(TsubuyakiForm form){
        var  entity = form.toEntity();
        tsubuyakiService.postTsubuyaki(entity);
        return "redirect:/tsubuyaki/read";
    }
    
    // つぶやきの検索
    @GetMapping("/search")
    public String showSearchresults(@RequestParam(name = "keyword",required = false) String keyword, Model model ){
        if (keyword != null && !keyword.isEmpty()){
            List<TsubuyakiEntity> searchResult = tsubuyakiService.searchTsubuyaki(keyword);
            if (searchResult.isEmpty()){
                model.addAttribute("emptyMessage", "該当するつぶやきはありません。");
            }
            model.addAttribute("searchResult", searchResult);
        }
        return "search_result";
    }

}
