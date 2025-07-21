package com.example.demo.form;

import com.example.demo.entity.TsubuyakiEntity;

public record TsubuyakiForm(
    String name,
    String comment
) {
    public TsubuyakiEntity toEntity(){
        return new TsubuyakiEntity(null,name,comment,null);
    }
}
