/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.util.components;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Window;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GRUCAS
 */
public class ComboBoxSelectable extends HorizontalLayout {
    
    NativeSelect<Object> cboGeneric = new NativeSelect<>();
    List<Object> objectList = new ArrayList<>();
    Button btnWindow = new Button();
    
    Object objetoSeleccionado;
    String strWidth;
    String strWhere;
    
    Window window;
    Class clase;
    Class domain;
    String strDomain = "";

    public ComboBoxSelectable(Object objetoSeleccionado, String strWidth, String strWhere, Window window, Class clase, String domain) {
        this.objetoSeleccionado = objetoSeleccionado;
        this.strWidth = strWidth;
        this.strWhere = strWhere;
        this.window = window;
        this.clase = clase;
        this.strDomain = domain;
        
        initComponents();
    }

    public void initComponents(){
        cboGeneric.setWidth(strWidth);
        cboGeneric.setEmptySelectionAllowed(false);
        cboGeneric.setItems(getListObjects());
        cboGeneric.setValue(objetoSeleccionado);
    }
    
    public List getListObjects(){
        
        String[] params = {strWhere,"",""};
        
        try {
            domain = Class.forName(strDomain);
            
            Class[] cArg = new Class[3];
            cArg[0] = String.class;
            cArg[1] = String.class;
            cArg[2] = String.class;

            Method m = domain.getMethod("get", cArg);
            m.invoke(objetoSeleccionado, "","","");
            
        } catch (Exception e) {
        }
        
        
        
        return null;
    }
    
}