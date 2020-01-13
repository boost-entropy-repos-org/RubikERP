/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.fragments;

import com.rubik.erp.model.Empleado;
import com.rubik.erp.views.ComprasProductos;
import com.rubik.erp.views.ComprasProveedores;
import com.rubik.erp.views.Login;
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
    MenuBar.MenuItem subRequisicion;
    MenuBar.MenuItem subOrdenDeCompra;
    MenuBar.MenuItem subMonitoreoRequi;
    MenuBar.MenuItem subMonitoreoOC;
    MenuBar.MenuItem submReportesCompras;
    MenuBar.MenuItem subReporteRequisicion;
    MenuBar.MenuItem subReporteOrdenDeCompra;
    
    MenuBar.MenuItem menuComercial;
    MenuBar.MenuItem subProductosServicios;
    MenuBar.MenuItem subProspectoClientes;
    
    MenuBar.MenuItem menuRecursosHumanos;
    MenuBar.MenuItem subDepartamentos;
    MenuBar.MenuItem subPuesto;
    MenuBar.MenuItem subEmpleados;
    MenuBar.MenuItem subUsuarios;

    MenuBar.MenuItem menuConfiguracion;
    
    MenuBar.MenuItem menuSalir;
    
    Label lblInfoSession;
    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");
    Label lblusuario = new Label(empleado.getNombre() + " | " + empleado.getDepartamento()+ " ");
    
    Button btnPassword = new Button("Cambiar Contraseña");
    
    public FragmentTop() {
        setHeight("37px");
        setWidth("100%");

        Image logo = new Image(null, new ThemeResource("img/cubeLogo.png"));
        logo.setHeight("37px");
        Image profilePic = new Image(null, new ThemeResource("img/user.png"));
        profilePic.setWidth("37px");

        btnPassword.setStyleName(ValoTheme.BUTTON_PRIMARY);

        profilePic.addClickListener(ev -> {
            MessageBox.createQuestion()
                    .withCaption("Bienvenido!")
                    .withMessage("USUARIO: " + empleado.getNombre_completo()+ "\n"
                            + "EMPRESA: " + empleado.getEmpresa_id() + " - " + empleado.getEmpresa() + "\n"
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

        subRequisicion = menuCompras.addItem("Requisiciones", actionMenu);
        subRequisicion.setIcon(Fam3SilkIcon.PAGE);

        subOrdenDeCompra = menuCompras.addItem("Ordenes de Compra", actionMenu);
        subOrdenDeCompra.setIcon(Fam3SilkIcon.PAGE_GO);
                
        subMonitoreoRequi = menuCompras.addItem("Monitoreo Requisicion", actionMenu);
        subMonitoreoRequi.setIcon(Fam3SilkIcon.PAGE_WHITE_PAINTBRUSH);
        
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
//----------------------------------------------------------------- COMERCIAL
        menuComercial = menubar.addItem("Comercial", null);
        menuComercial.setIcon(Fam3SilkIcon.BELL_GO);

        subProductosServicios = menuComercial.addItem("Productos & Servicios", actionMenu);
        subProductosServicios.setIcon(Fam3SilkIcon.BRICK_GO);
        
        menuComercial.addSeparator();
        
        subProspectoClientes = menuComercial.addItem("Prospecto de Clientes", actionMenu);
        subProspectoClientes.setIcon(Fam3SilkIcon.GROUP_LINK);
        
// ----------------------------------------------------------------
// ----------------------------------------------------------------
//----------------------------------------------------------------- RECURSOS HUMANOS
        menuRecursosHumanos = menubar.addItem("R. H.", null);
        menuRecursosHumanos.setIcon(Fam3SilkIcon.GROUP);

        subDepartamentos = menuRecursosHumanos.addItem("Departamentos", actionMenu);
        subDepartamentos.setIcon(Fam3SilkIcon.WORLD);
        
        subPuesto = menuRecursosHumanos.addItem("Puestos",actionMenu);
        subPuesto.setIcon(Fam3SilkIcon.GROUP);
        
        menuRecursosHumanos.addSeparator();

        subEmpleados = menuRecursosHumanos.addItem("Empleados", actionMenu);
        subEmpleados.setIcon(Fam3SilkIcon.USER);
        
        subUsuarios = menuRecursosHumanos.addItem("Usuarios", actionMenu);
        subUsuarios.setIcon(Fam3SilkIcon.USER_GRAY);
        
// ----------------------------------------------------------------
// ----------------------------------------------------------------
//----------------------------------------------------------------- CONFIG
        menuConfiguracion = menubar.addItem("Conf", null);
        menuConfiguracion.setIcon(Fam3SilkIcon.COG);
        
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
//                case "Requisiciones":
//                    getUI().getNavigator().navigateTo(ViewComprasRequisicion.NAME);
//                    break;                    
//                case "Ordenes de Compra":
//                    getUI().getNavigator().navigateTo(ViewComprasOrdenCompra.NAME);
//                    break;
//                case "Monitoreo Requisicion":
//                    getUI().getNavigator().navigateTo(ViewComprasMonitoreoRequisicion.NAME);
//                    break;
//                case "Monitoreo OC":
//                    getUI().getNavigator().navigateTo(ViewComprasMonitoreoOC.NAME);
//                    break;
                    
// ------------- COMPRAS ---------- REPORTES DE COMPRA
//                case "Reporte de Requisiciones":
//                    getUI().getNavigator().navigateTo(ViewComprasReporteRequisiciones.NAME);
//                    break;
//                case "Reporte de Ordenes de Compra":
//                    getUI().getNavigator().navigateTo(ViewComprasReporteOrdenesCompra.NAME);
//                    break;
                    
 // ------------- RECURSOS HUMANOS
//                case "Departamentos":
//                    getUI().getNavigator().navigateTo(ViewDepartamento.NAME);
//                    break;
//                case "Puestos":
//                    getUI().getNavigator().navigateTo(ViewRHPuestos.NAME);
//                    break;
//                case "Empleados":
//                    getUI().getNavigator().navigateTo(ViewEmpleado.NAME);
//                    break;
//                case "Usuarios":
//                    getUI().getNavigator().navigateTo(ViewUsuario.NAME);
//                    break;
                    
// ------------- COMERCIAL
//                case "Productos & Servicios":
//                    getUI().getNavigator().navigateTo(ViewComercialProductoServicio.NAME);
//                    break;
//                    
//                case "Prospecto de Clientes":
//                    getUI().getNavigator().navigateTo(ViewComerciaClienteslProspecto.NAME);
//                    break;
                    
// ------------- EXPEDIENTE DIGITAL ---------- DOCUMENTOS
//                case "Clientes":
//                    getUI().getNavigator().navigateTo(ViewClienteDocument.NAME);
//                    break;
//                case "Cliente":
//                    getUI().getNavigator().navigateTo(ViewCliente.NAME);
//                    break; 


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
        
        subProducto.setVisible(false);
        subProveedor.setVisible(false);
        
        menuCompras.setVisible(false);
        subReporteRequisicion.setVisible(false);
        subOrdenDeCompra.setVisible(false);
        subMonitoreoRequi.setVisible(false);
        subMonitoreoOC.setVisible(false);
        subReporteRequisicion.setVisible(false);
        subReporteOrdenDeCompra.setVisible(false);

        menuRecursosHumanos.setVisible(false);
        subDepartamentos.setVisible(false);
        subPuesto.setVisible(false);
        subEmpleados.setVisible(false);
        subUsuarios.setVisible(false);
        
        menuComercial.setVisible(false);
        subProspectoClientes.setVisible(false);
        
        menuConfiguracion.setVisible(false);
        
        menuSalir.setVisible(false);

//        if (usuario.getDepartamento().equals(_Departamentos.RECURSOS_HUMANOS.getDepartamento())) {
//
//            menuRecursosHumanos.setVisible(true);
//            subDepartamentos.setVisible(true);
//            subPuesto.setVisible(true);
//            subUsuarios.setVisible(true);
//            subEmpleados.setVisible(true);
//            
//        } else if (usuario.getDepartamento().equals(_Departamentos.COMPRAS.getDepartamento())) {
//            
//            menuCatalogos.setVisible(true);
//            menuCompras.setVisible(true);
//            subReporteRequisicion.setVisible(true);
//            subOrdenDeCompra.setVisible(true);
//            subMonitoreoRequi.setVisible(true);
//            subMonitoreoOC.setVisible(true);
//            subReporteRequisicion.setVisible(true);
//            subReporteOrdenDeCompra.setVisible(true);
//            
//            menuCatalogos.setVisible(true);
//            subEmpresas.setVisible(true);
//            subUnidadNegocio.setVisible(true);
//            subProducto.setVisible(true);
//            subProveedor.setVisible(true);
//        
//            menuExpedienteDigital.setVisible(true);
//            
//        } else if (usuario.getDepartamento().equals(_Departamentos.COMERCIAL.getDepartamento())) {
//            
//            menuComercial.setVisible(true);
//            subProspectoClientes.setVisible(true);
//            
//        } else if (usuario.getDepartamento().equals(_Departamentos.OPERACIONES.getDepartamento())) {
//
//            menuOperacion.setVisible(true);
//            menuExpedienteDigital.setVisible(true);
//            subXMLToGlobal.setVisible(true);
//            subSolicitudPrevios.setVisible(true);
//            
//        } else if (usuario.getDepartamento().equals(_Departamentos.TECNOLOGIAS_DE_LA_INFORMACION.getDepartamento())) {
//            
//            menuCatalogos.setVisible(true);
//            subEmpresas.setVisible(true);
//            subUnidadNegocio.setVisible(true);
//            subProducto.setVisible(true);
//            subProveedor.setVisible(true);
//            
//            menuRecursosHumanos.setVisible(true);
//            subDepartamentos.setVisible(true);
//            subPuesto.setVisible(true);
//            subUsuarios.setVisible(true);
//            subEmpleados.setVisible(true);
//            
//        } else if (usuario.getDepartamento().equals(_Departamentos.ADMINISTRATIVO.getDepartamento())) {
//
//        } else if (usuario.getDepartamento().equals(_Departamentos.LIMPIEZA.getDepartamento())) {
//
//        } else if (usuario.getDepartamento().equals(_Departamentos.COBRANZA.getDepartamento())) {
//
//        }
//        
//        // ROLES ESPECIFICOS
//        if(usuario.getRol().equals(_RolesDeUsuario.ROL_REQUISITOR) 
//                || usuario.getClasificacion_puesto().equals(_ClasificacionDePuestos.LIDER_DE_AREA)){
//            
//            menuCompras.setVisible(true);
//            subRequisicion.setVisible(true);
//            subMonitoreoRequi.setVisible(true);
//        }
//
//        // VISIBLES PARA TODOS LOS USUARIOS
//        // -------------- TI - SERVICE DESK
//        menuConfiguracion.setVisible(true);
//        subCambiarUnidad.setVisible(true);
//        menuSalir.setVisible(true);
        
    }

}
