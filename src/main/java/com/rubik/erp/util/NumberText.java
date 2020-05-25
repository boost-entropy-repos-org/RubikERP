/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.util;

import com.rubik.manage.ManageString;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

/**
 *
 * @author Dev
 */
@SuppressWarnings("serial")
public class NumberText extends TextField {

    public NumberText() {
        this.initComponent();
    }
    
    public NumberText(Integer valor) {
        this.initComponent();
        String value  = "";
        
        if(ManageString.isString(valor.toString())){
            value = valor.toString();
        }else{
            value = "0.0";
        }
        
        this.setValue(value);
    }
    
    public NumberText(Double valor) {
        this.initComponent();
        String value  = "";
        
        if(ManageString.isString(valor.toString())){
            value = valor.toString();
        }else{
            value = "0.0";
        }
        
        this.setValue(value);
    }

    public NumberText(String caption) {
        super(caption);
        this.initComponent();
    }

    public void initComponent(){
        setStyleName(ValoTheme.TEXTFIELD_ALIGN_RIGHT);
    }
    
    @Override
    public void setValue(String value) {
        String valor = "";
        
        if(ManageString.isString(value)){
            valor = value;
        }else{
            valor = "0.0";
        }
        
        super.setValue(valor);
    }
    
    
}
