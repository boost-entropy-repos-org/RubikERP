package com.rubik.erp;

import com.rubik.erp.modulo.compras.ComprasMonitorOrdenes;
import com.rubik.erp.modulo.compras.ComprasMonitorRequisiciones;
import com.rubik.erp.modulo.compras.ComprasOrdenes;
import com.rubik.erp.modulo.compras.ComprasProductos;
import com.rubik.erp.modulo.compras.ComprasProveedores;
import com.rubik.erp.modulo.compras.ComprasRequisiciones;
import com.rubik.erp.modulo.configuracion.ViewConfiguracion;
import com.rubik.erp.modulo.generic.Login;
import com.rubik.erp.modulo.generic.MainPage;
import com.rubik.erp.modulo.rh.Empleados;
import com.rubik.erp.modulo.ventas.VentasClientes;
import com.rubik.erp.modulo.ventas.VentasCotizaciones;
import com.rubik.erp.modulo.ventas.VentasProyectos;
import com.rubik.erp.modulo.compras.ComprasRemisionesDeEntrega;
import com.rubik.erp.modulo.ventas.VentasReporteDeCotizacionesPorEmpleado;
import com.rubik.erp.modulo.ventas.VentasReporteDeCotizacionesPorEmpleadoGrafica;
import com.rubik.erp.modulo.ventas.VentasReporteDeCotizacionesPorProyecto;
import com.rubik.erp.modulo.ventas.VentasReporteDeCotizacionesPorProyectoGrafica;
import com.rubik.erp.modulo.ventas.VentasReporteGeneralCotizaciones;
import com.rubik.erp.modulo.ventas.VentasReporteGeneralCotizacionesGrafica;
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
        navigator.addView(ComprasRequisiciones.NAME, ComprasRequisiciones.class);
        navigator.addView(ComprasOrdenes.NAME, ComprasOrdenes.class);
        navigator.addView(ComprasMonitorRequisiciones.NAME, ComprasMonitorRequisiciones.class);
        navigator.addView(ComprasMonitorOrdenes.NAME, ComprasMonitorOrdenes.class);
        navigator.addView(VentasClientes.NAME, VentasClientes.class);
        navigator.addView(Empleados.NAME, Empleados.class);
        navigator.addView(ViewConfiguracion.NAME, ViewConfiguracion.class);
        navigator.addView(VentasCotizaciones.NAME, VentasCotizaciones.class);
        navigator.addView(ComprasRemisionesDeEntrega.NAME, ComprasRemisionesDeEntrega.class);
        navigator.addView(VentasProyectos.NAME, VentasProyectos.class);
        navigator.addView(VentasReporteGeneralCotizaciones.NAME, VentasReporteGeneralCotizaciones.class);
        navigator.addView(VentasReporteGeneralCotizacionesGrafica.NAME, VentasReporteGeneralCotizacionesGrafica.class);
        navigator.addView(VentasReporteDeCotizacionesPorProyecto.NAME, VentasReporteDeCotizacionesPorProyecto.class);
        navigator.addView(VentasReporteDeCotizacionesPorProyectoGrafica.NAME, VentasReporteDeCotizacionesPorProyectoGrafica.class);
        navigator.addView(VentasReporteDeCotizacionesPorEmpleado.NAME, VentasReporteDeCotizacionesPorEmpleado.class);
        navigator.addView(VentasReporteDeCotizacionesPorEmpleadoGrafica.NAME, VentasReporteDeCotizacionesPorEmpleadoGrafica.class);        
        
        navigator.navigateTo(Login.NAME);
    }

    @WebServlet(urlPatterns = "/*", name = "RubikUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = RubikUI.class, productionMode = false)
    public static class RubikUIServlet extends VaadinServlet {
    }
}
