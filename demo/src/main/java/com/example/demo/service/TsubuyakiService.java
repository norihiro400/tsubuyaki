package com.example.demo.service;

import java.sql.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.entity.TsubuyakiEntity;
import com.example.demo.repository.TsubuyakiRepository;

@Service
public class TsubuyakiService {

    public final TsubuyakiRepository tsubuyakiRepository;

    public TsubuyakiService(TsubuyakiRepository tsubuyakiRepository){
        this.tsubuyakiRepository = tsubuyakiRepository;
    }

    // 全つぶやきの取得
    public List<TsubuyakiEntity> getAllTsubuyaki(){
        return tsubuyakiRepository.findAll();
    }

    // つぶやきの投稿
    public TsubuyakiEntity postTsubuyaki(TsubuyakiEntity tsubuyaki){
        String name = tsubuyaki.getName();
        if (name == null || name.length() == 0){
            tsubuyaki.setName("名無しさん");
        }
        tsubuyaki.setCreatedAt(new Date(0));
        return tsubuyakiRepository.save(tsubuyaki);
    }

    // つぶやきの検索
    public List<TsubuyakiEntity> searchTsubuyaki(String keyword){
        return tsubuyakiRepository.searchTsubuyaki(keyword);
    }

}
