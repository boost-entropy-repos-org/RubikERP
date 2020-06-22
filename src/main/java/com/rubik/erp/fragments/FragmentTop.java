/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.fragments;

import com.rubik.erp.model.Empleado;
import com.rubik.erp.modulo.compras.ComprasMonitorOrdenes;
import com.rubik.erp.modulo.compras.ComprasMonitorRequisiciones;
import com.rubik.erp.modulo.compras.ComprasOrdenes;
import com.rubik.erp.modulo.compras.ComprasProductos;
import com.rubik.erp.modulo.compras.ComprasProveedores;
import com.rubik.erp.modulo.compras.ComprasRequisiciones;
import com.rubik.erp.modulo.configuracion.ViewConfiguracion;
import com.rubik.erp.modulo.generic.Login;
import com.rubik.erp.modulo.rh.Empleados;
import com.rubik.erp.modulo.ventas.VentasClientes;
import com.rubik.erp.modulo.ventas.VentasCotizaciones;
import com.rubik.erp.modulo.ventas.VentasProyectos;
import com.rubik.erp.modulo.compras.ComprasRemisionesDeEntrega;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.themes.ValoTheme;
import de.steinwedel.messagebox.ButtonOption;
import de.steinwedel.messagebox.MessageBox;
import org.rubicone.vaadin.fam3.silk.Fam3SilkIcon;

/**
 *
 * @author GrucasDev
 */
public class FragmentTop extends HorizontalLayout {

    MenuBar menubar = new MenuBar();
    Command actionMenu;
    
    MenuBar.MenuItem menuCompras;
    MenuBar.MenuItem subComprasProveedor;
    MenuBar.MenuItem subComprasProducto;
    MenuBar.MenuItem subComprasRequisiciones;
    MenuBar.MenuItem subComprasOrdenDeCompra;
    MenuBar.MenuItem subComprasMonitoreoRequisiciones;
    MenuBar.MenuItem subComprasMonitoreoOC;
    MenuBar.MenuItem subComprasRemisionEntrega;
    MenuBar.MenuItem subComprasReportesCompras;
    MenuBar.MenuItem subComprasReporteRequisicion;
    MenuBar.MenuItem subComprasReporteOrdenDeCompra;
    
    MenuBar.MenuItem menuVentas;
    MenuBar.MenuItem subVentasClientes;
    MenuBar.MenuItem subVentasProyectos;
    MenuBar.MenuItem subVentasCotizaciones;
    
    MenuBar.MenuItem menuRecursosHumanos;
    MenuBar.MenuItem subRHEmpleados;

    MenuBar.MenuItem menuConfiguracion;
    MenuBar.MenuItem subConfiguracion;
    
    MenuBar.MenuItem menuSalir;
    
    Label lblInfoSession;
    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");
    Label lblusuario = new Label(empleado.getNombre() + " | " + empleado.getDepartamento()+ " ");
    
    Button btnPassword = new Button("Cambiar Contraseña");
    
    public FragmentTop() {
        setHeight("37px");
        setWidth("100%");

        Image logo = new Image(null, new ThemeResource("img/logoSimple.png"));
        logo.setHeight("37px");
        Image profilePic = new Image(null, new ThemeResource("img/user.png"));
        profilePic.setWidth("37px");

        btnPassword.setStyleName(ValoTheme.BUTTON_PRIMARY);

        profilePic.addClickListener(ev -> {
            MessageBox.createInfo()
                    .withCaption("Bienvenido!")
                    .withMessage("USUARIO: " + empleado.getNombre_completo()+ "\n"
                            + "DEPARTAMENTO: " + empleado.getDepartamento() + "\n"
                            + "PUESTO: " + empleado.getPuesto() + "\n"
                    )
                    .withButton(btnPassword, ButtonOption.caption("CAMBIAR CONTRASEÑA"))
                    .withNoButton(ButtonOption.caption("CERRAR")) 
                    .open();
        });

        btnPassword.addClickListener((event) -> {
//            WindowCambioPassword windows = new WindowCambioPassword();
//            windows.addCloseListener((e) -> {
//                String password = WindowCambioPassword.PASSWORD;
//                usuario.setPassword(password);
//
//                UsuarioService service = new UsuarioService();
//                service.UsuarioUpdate(usuario);
//            });
//
//            getUI().addWindow(windows);
        });

        HorizontalLayout rolePanel = new HorizontalLayout();
        initializeActionMenu();
        rolePanel.addComponents(profilePic, lblusuario);
        rolePanel.setComponentAlignment(lblusuario, Alignment.MIDDLE_CENTER);

        menubar.setHtmlContentAllowed(true);
        menubar.setWidth("100%");
        
// ----------------------------------------------------------------
// ----------------------------------------------------------------
//----------------------------------------------------------------- VENTAS
        menuVentas = menubar.addItem("Ventas", null);
        menuVentas.setIcon(Fam3SilkIcon.FOLDER_GO);

        subVentasClientes = menuVentas.addItem("Clientes", actionMenu);
        subVentasClientes.setIcon(Fam3SilkIcon.USER_SUIT);
        
        subVentasProyectos = menuVentas.addItem("Proyectos / Contratos", actionMenu);
        subVentasProyectos.setIcon(Fam3SilkIcon.FOLDER_BELL);
        
        menuVentas.addSeparator();
        
        subVentasCotizaciones = menuVentas.addItem("Cotizaciones de Venta", actionMenu);
        subVentasCotizaciones.setIcon(Fam3SilkIcon.PAGE_WHITE_TEXT);

// ----------------------------------------------------------------
// ----------------------------------------------------------------
// ---------------------------------------------------------------- COMPRAS
        menuCompras = menubar.addItem("Compras", null);
        menuCompras.setIcon(Fam3SilkIcon.MONEY_ADD);
        
        subComprasProveedor = menuCompras.addItem("Proveedores", actionMenu);
        subComprasProveedor.setIcon(Fam3SilkIcon.USER_SUIT);
        
        subComprasProducto = menuCompras.addItem("Productos", actionMenu);
        subComprasProducto.setIcon(Fam3SilkIcon.PACKAGE);

        menuCompras.addSeparator();

        subComprasRequisiciones = menuCompras.addItem("Requisiciones", actionMenu);
        subComprasRequisiciones.setIcon(Fam3SilkIcon.PAGE);

        subComprasOrdenDeCompra = menuCompras.addItem("Ordenes de Compra", actionMenu);
        subComprasOrdenDeCompra.setIcon(Fam3SilkIcon.PAGE_GO);
        
        subComprasOrdenDeCompra = menuCompras.addItem("Remisiones de Entrega", actionMenu);
        subComprasOrdenDeCompra.setIcon(Fam3SilkIcon.TRANSMIT_GO);
                
        menuCompras.addSeparator();
        
        subComprasMonitoreoRequisiciones = menuCompras.addItem("Monitoreo de Requisiciones", actionMenu);
        subComprasMonitoreoRequisiciones.setIcon(Fam3SilkIcon.PAGE_WHITE_PAINTBRUSH);
        
        subComprasMonitoreoOC = menuCompras.addItem("Monitoreo OC", actionMenu);
        subComprasMonitoreoOC.setIcon(Fam3SilkIcon.BOOK_GO);
        
        menuCompras.addSeparator();

        subComprasReportesCompras = menuCompras.addItem("Reportes de Compras", actionMenu);
        subComprasReportesCompras.setIcon(Fam3SilkIcon.REPORT);

        subComprasReporteRequisicion = subComprasReportesCompras.addItem("Reporte de Requisiciones", actionMenu);
        subComprasReporteRequisicion.setIcon(Fam3SilkIcon.LAYOUT_EDIT);

        subComprasReporteOrdenDeCompra = subComprasReportesCompras.addItem("Reporte de Ordenes de Compra", actionMenu);
        subComprasReporteOrdenDeCompra.setIcon(Fam3SilkIcon.LAYOUT_EDIT);
        
// ----------------------------------------------------------------
// ----------------------------------------------------------------
//----------------------------------------------------------------- RECURSOS HUMANOS
        menuRecursosHumanos = menubar.addItem("R. H.", null);
        menuRecursosHumanos.setIcon(Fam3SilkIcon.GROUP);

        subRHEmpleados = menuRecursosHumanos.addItem("Empleados", actionMenu);
        subRHEmpleados.setIcon(Fam3SilkIcon.USER);
        
// ----------------------------------------------------------------
// ----------------------------------------------------------------
//----------------------------------------------------------------- CONFIG
        menuConfiguracion = menubar.addItem("Conf", null);
        menuConfiguracion.setIcon(Fam3SilkIcon.COG);
        
        subConfiguracion = menuConfiguracion.addItem("Configuracion", actionMenu);
        subConfiguracion.setIcon(Fam3SilkIcon.COG_GO);
        
// ----------------------------------------------------------------
// ---------------------------------------------------------------- 
// ---------------------------------------------------------------- SALIR
        menuSalir = menubar.addItem("Salir", actionMenu);
        menuSalir.setIcon(Fam3SilkIcon.DOOR_OUT);

        addComponents(new HorizontalLayout(logo, menubar), rolePanel);
        setSpacing(true);
        setComponentAlignment(rolePanel, Alignment.MIDDLE_RIGHT);
        
//        if(!empleado.getDepartamento().equals(_TipoUsuario.USUARIO_SUPER)){
//            security();
//        }
    }
    
    public void initializeActionMenu(){
        actionMenu = (MenuItem selectedItem) -> {
            switch (selectedItem.getText()) {

// ------------- COMPRAS
                case "Proveedores":
                    getUI().getNavigator().navigateTo(ComprasProveedores.NAME);
                    break;
                case "Productos":
                    getUI().getNavigator().navigateTo(ComprasProductos.NAME);
                    break;
                case "Requisiciones":
                    getUI().getNavigator().navigateTo(ComprasRequisiciones.NAME);
                    break;                    
                case "Ordenes de Compra":
                    getUI().getNavigator().navigateTo(ComprasOrdenes.NAME);
                    break;
                case "Monitoreo de Requisiciones":
                    getUI().getNavigator().navigateTo(ComprasMonitorRequisiciones.NAME);
                    break;
                case "Monitoreo OC":
                    getUI().getNavigator().navigateTo(ComprasMonitorOrdenes.NAME);
                    break;
                case "Remisiones de Entrega":
                    getUI().getNavigator().navigateTo(ComprasRemisionesDeEntrega.NAME);
                    break;
                    
// ------------- COMPRAS ---------- REPORTES DE COMPRA
//                case "Reporte de Requisiciones":
//                    getUI().getNavigator().navigateTo(ViewComprasReporteRequisiciones.NAME);
//                    break;
//                case "Reporte de Ordenes de Compra":
//                    getUI().getNavigator().navigateTo(ViewComprasReporteOrdenesCompra.NAME);
//                    break;

// ------------- VENTAS
                case "Clientes":
                    getUI().getNavigator().navigateTo(VentasClientes.NAME);
                    break;
                    
                case "Proyectos / Contratos":
                    getUI().getNavigator().navigateTo(VentasProyectos.NAME);
                    break;
                    
                case "Cotizaciones de Venta":
                    getUI().getNavigator().navigateTo(VentasCotizaciones.NAME);
                    break;
                    
 // ------------- RECURSOS HUMANOS
                case "Empleados":
                    getUI().getNavigator().navigateTo(Empleados.NAME);
                    break;
                    
// ------------- CONFIGURACION
                case "Configuracion":
                    getUI().getNavigator().navigateTo(ViewConfiguracion.NAME);
                    break;

// ------------- CONFIGURACION & EXIT
                case "Salir":
                    VaadinSession.getCurrent().getSession().invalidate();
                    getUI().getNavigator().navigateTo(Login.NAME);
                    break;
            }
        };
//
//        menubar.setHtmlContentAllowed(true);
//        menubar.setWidth("100%");
    }
    
    public void security(){
        
        for (MenuItem item : menubar.getItems()) {
            
            item.setVisible(false);
            
            if(item.getChildren()!=null){
                
                for (MenuItem item2 : item.getChildren()) {
                    
                    item2.setVisible(false);
                    
                    if(item2.getChildren()!=null){
                        for (MenuItem item3 : item2.getChildren()) {
                            item3.setVisible(false);
                        }
                    }else{
                        item2.setVisible(false);
                    }
                }
            }else{
                item.setVisible(false);
            }
        }

        
        





        
    }

}
