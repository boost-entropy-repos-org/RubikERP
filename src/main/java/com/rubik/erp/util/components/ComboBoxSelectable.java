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
    Object objetoDomain;
    String strWidth;
    String strWhere;
    
    Class windowClass;
    Class objectClass;
    Class domainClass;

    public ComboBoxSelectable(Object objetoSeleccionado, String strWidth, String strWhere, Class window, Class clase, Class domain) {
        this.objetoSeleccionado = objetoSeleccionado;
        this.strWidth = strWidth;
        this.strWhere = strWhere;
        this.windowClass = window;
        this.objectClass = clase;
        this.domainClass = domain;
        
        initComponents();
    }

    public void initComponents(){
        cboGeneric.setWidth(strWidth);
        cboGeneric.setEmptySelectionAllowed(false);
        cboGeneric.setItems(getListObjects());
        cboGeneric.setValue(objetoSeleccionado);
        
        
        addComponents(cboGeneric,btnWindow);
    }
    
    public List getListObjects(){
        String[] params = {strWhere,"",""};
        
        List listObjetos = new ArrayList<>();
        
        try {
            objetoDomain = domainClass.getDeclaredConstructor().newInstance();
            
            Class[] cArg = new Class[3];
            cArg[0] = String.class;
            cArg[1] = String.class;
            cArg[2] = String.class;

//            System.out.println(" Metodo = " + "get"+clase.getSimpleName());
            
            Method mGetFromDataBase = domainClass.getMethod("get"+objectClass.getSimpleName(), cArg);
            mGetFromDataBase.invoke(objetoDomain, strWhere,"","");
             
            Method mGetList = domainClass.getMethod("getObjects", null);
            listObjetos = (ArrayList) mGetList.invoke(objetoDomain);

//            System.out.println("OBJETOS: " + listObjetos);

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return listObjetos;
    }
    
}