/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.views;

import com.rubik.erp.fragments.FragmentBottom;
import com.rubik.erp.fragments.FragmentTop;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author Dev
 */
public class MainPage extends VerticalLayout implements View {

    public static final String NAME = "INICIO";

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        setSizeFull();
        setMargin(false);
        addComponents(new FragmentTop(), new FragmentBottom());
        setComponentAlignment(getComponent(1), Alignment.BOTTOM_CENTER);
    }
    
}