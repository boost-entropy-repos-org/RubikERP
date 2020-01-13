package com.rubik.erp;

import com.rubik.erp.views.ComprasProductos;
import com.rubik.erp.views.ComprasProveedores;
import com.rubik.erp.views.Login;
import com.rubik.erp.views.MainPage;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.UI;
import java.util.Locale;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("RubikTheme")
public class RubikUI extends UI {

    Navigator navigator;
    CssLayout ccsLayout = new CssLayout();
    
    @Override
    protected void init(VaadinRequest vaadinRequest) {

        setLocale(Locale.US);

        Responsive.makeResponsive(this);
        setContent(ccsLayout);
        ccsLayout.setSizeFull();
        navigator = new Navigator(this, ccsLayout);
        navigator.addView(Login.NAME, Login.class);
        navigator.addView(MainPage.NAME, MainPage.class);
        navigator.addView(ComprasProveedores.NAME, ComprasProveedores.class);
        navigator.addView(ComprasProductos.NAME, ComprasProductos.class);
        
        navigator.navigateTo(Login.NAME);
    }

    @WebServlet(urlPatterns = "/*", name = "RubikUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = RubikUI.class, productionMode = false)
    public static class RubikUIServlet extends VaadinServlet {
    }
}
