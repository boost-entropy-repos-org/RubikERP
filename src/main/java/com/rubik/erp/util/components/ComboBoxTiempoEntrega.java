/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.util.components;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;

/**
 *
 * @author GRUCAS
 */
public class ComboBoxTiempoEntrega extends HorizontalLayout {
    
    NativeSelect<Integer> cboNumero = new NativeSelect<>();
    NativeSelect<String> cboTiempo = new NativeSelect<>();
    
    String strValue;
    String tiempo;
    String numero;

    public ComboBoxTiempoEntrega() {
        initComponents();
    }
    
    public ComboBoxTiempoEntrega(String tiempoEntrega) {
        initComponents();
        String[] arr = tiempoEntrega.split(" ");
        cboNumero.setValue(Integer.valueOf(arr[0]));
        cboTiempo.setValue(arr[1]);
    }
    
    public void initComponents(){
        setCaption("Tiempo Entrega:");
        
        cboNumero.setItems(1,2,3,4,5,6,7,8,9,10,11,12);
        cboTiempo.setItems("DIAS", "SEMANAS", "MESES");
        
        cboNumero.setValue(1);
        cboTiempo.setValue("DIAS");
        
        cboNumero.setEmptySelectionAllowed(false);
        cboTiempo.setEmptySelectionAllowed(false);
        
        addComponents(cboNumero, cboTiempo);
    }

    public NativeSelect<Integer> getCboNumero() {
        return cboNumero;
    }

    public void setCboNumero(NativeSelect<Integer> cboNumero) {
        this.cboNumero = cboNumero;
    }

    public NativeSelect<String> getCboTiempo() {
        return cboTiempo;
    }

    public void setCboTiempo(NativeSelect<String> cboTiempo) {
        this.cboTiempo = cboTiempo;
    }

    public String getStrValue() {
        strValue = cboNumero.getValue() + " " + cboTiempo.getValue();
        return strValue;
    }

    public void setStrValue(String strValue) {
        this.strValue = strValue;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    
}