/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.fragments;

import com.rubik.erp.model.Empleado;
import com.rubik.erp.modulo.compras.ComprasMonitorRequisiciones;
import com.rubik.erp.modulo.compras.ComprasOrdenes;
import com.rubik.erp.modulo.compras.ComprasProductos;
import com.rubik.erp.modulo.compras.ComprasProveedores;
import com.rubik.erp.modulo.compras.ComprasRequisiciones;
import com.rubik.erp.modulo.configuracion.ViewConfiguracion;
import com.rubik.erp.modulo.generic.Login;
import com.rubik.erp.modulo.rh.Empleados;
import com.rubik.erp.modulo.ventas.VentasClientes;
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
    MenuBar.MenuItem subProveedor;
    MenuBar.MenuItem subProducto;
    MenuBar.MenuItem subRequisiciones;
    MenuBar.MenuItem subOrdenDeCompra;
    MenuBar.MenuItem subMonitoreoRequisiciones;
    MenuBar.MenuItem subMonitoreoOC;
    MenuBar.MenuItem submReportesCompras;
    MenuBar.MenuItem subReporteRequisicion;
    MenuBar.MenuItem subReporteOrdenDeCompra;
    
    MenuBar.MenuItem menuComercial;
    MenuBar.MenuItem subClientes;
    MenuBar.MenuItem subProspectoClientes;
    
    MenuBar.MenuItem menuRecursosHumanos;
    MenuBar.MenuItem subEmpleados;

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
// ---------------------------------------------------------------- COMPRAS
        menuCompras = menubar.addItem("Compras", null);
        menuCompras.setIcon(Fam3SilkIcon.MONEY_ADD);
        
        subProducto = menuCompras.addItem("Productos", actionMenu);
        subProducto.setIcon(Fam3SilkIcon.PACKAGE);

        subProveedor = menuCompras.addItem("Proveedores", actionMenu);
        subProveedor.setIcon(Fam3SilkIcon.USER_SUIT);
        
        menuCompras.addSeparator();

        subRequisiciones = menuCompras.addItem("Requisiciones", actionMenu);
        subRequisiciones.setIcon(Fam3SilkIcon.PAGE);

        subOrdenDeCompra = menuCompras.addItem("Ordenes de Compra", actionMenu);
        subOrdenDeCompra.setIcon(Fam3SilkIcon.PAGE_GO);
                
        menuCompras.addSeparator();
        
        subMonitoreoRequisiciones = menuCompras.addItem("Monitoreo de Requisiciones", actionMenu);
        subMonitoreoRequisiciones.setIcon(Fam3SilkIcon.PAGE_WHITE_PAINTBRUSH);
        
        subMonitoreoOC = menuCompras.addItem("Monitoreo OC", actionMenu);
        subMonitoreoOC.setIcon(Fam3SilkIcon.BOOK_GO);
        
        menuCompras.addSeparator();

        submReportesCompras = menuCompras.addItem("Reportes de Compras", actionMenu);
        submReportesCompras.setIcon(Fam3SilkIcon.REPORT);

        subReporteRequisicion = submReportesCompras.addItem("Reporte de Requisiciones", actionMenu);
        subReporteRequisicion.setIcon(Fam3SilkIcon.LAYOUT_EDIT);

        subReporteOrdenDeCompra = submReportesCompras.addItem("Reporte de Ordenes de Compra", actionMenu);
        subReporteOrdenDeCompra.setIcon(Fam3SilkIcon.LAYOUT_EDIT);
        
// ----------------------------------------------------------------
// ----------------------------------------------------------------
//----------------------------------------------------------------- VENTAS
        menuComercial = menubar.addItem("Ventas", null);
        menuComercial.setIcon(Fam3SilkIcon.FOLDER_GO);

        subClientes = menuComercial.addItem("Clientes", actionMenu);
        subClientes.setIcon(Fam3SilkIcon.USER_SUIT);
        
        menuComercial.addSeparator();
        
        subProspectoClientes = menuComercial.addItem("Prospecto de Clientes", actionMenu);
        subProspectoClientes.setIcon(Fam3SilkIcon.GROUP_LINK);
        
// ----------------------------------------------------------------
// ----------------------------------------------------------------
//----------------------------------------------------------------- RECURSOS HUMANOS
        menuRecursosHumanos = menubar.addItem("R. H.", null);
        menuRecursosHumanos.setIcon(Fam3SilkIcon.GROUP);

        subEmpleados = menuRecursosHumanos.addItem("Empleados", actionMenu);
        subEmpleados.setIcon(Fam3SilkIcon.USER);
        
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
//                case "Monitoreo OC":
//                    getUI().getNavigator().navigateTo(ViewComprasMonitoreoOC.NAME);
//                    break;//                case "Monitoreo OC":
//                    getUI().getNavigator().navigateTo(ViewComprasMonitoreoOC.NAME);
//                    break;
                    
// ------------- COMPRAS ---------- REPORTES DE COMPRA
//                case "Reporte de Requisiciones":
//                    getUI().getNavigator().navigateTo(ViewComprasReporteRequisiciones.NAME);
//                    break;
//                case "Reporte de Ordenes de Compra":
//                    getUI().getNavigator().navigateTo(ViewComprasReporteOrdenesCompra.NAME);
//                    break;

// ------------- COMERCIAL
                case "Clientes":
                    getUI().getNavigator().navigateTo(VentasClientes.NAME);
                    break;
//                    
//                case "Prospecto de Clientes":
//                    getUI().getNavigator().navigateTo(ViewComerciaClienteslProspecto.NAME);
//                    break;
                    
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
