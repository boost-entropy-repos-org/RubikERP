/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.fragments;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import java.util.Calendar;

/**
 *
 * @author GrucasDev
 */
public class FragmentBottom extends VerticalLayout {

    Label lb0 = new Label("♦ R U B I K ♦");
    Label lb2 = new Label();

    public FragmentBottom() {
        VerticalLayout vLayout = new VerticalLayout();

        Calendar c1 = Calendar.getInstance();
        String Actual = c1.get(Calendar.YEAR) + "";
        lb2 = new Label("Copyright © " + Actual + " - RubikSoft - Todos los Derechos Reservados");

        setMargin(false);
        setSpacing(false);
        setWidth("100%");
        setHeight("100px");

        vLayout.addComponents(lb0, lb2);
        vLayout.setComponentAlignment(lb0, Alignment.TOP_CENTER);
        vLayout.setComponentAlignment(lb2, Alignment.TOP_CENTER);

        vLayout.setMargin(false);
        vLayout.setSpacing(false);

        addComponent(vLayout);
        setComponentAlignment(vLayout, Alignment.TOP_CENTER);
    }
}
