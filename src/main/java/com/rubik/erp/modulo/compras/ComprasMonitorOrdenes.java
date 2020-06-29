/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.compras;

import com.rubik.erp.config._DocumentoEstados;
import com.rubik.erp.domain.OrdenDeCompraDomain;
import com.rubik.erp.fragments.FragmentTop;
import com.rubik.erp.model.Empleado;
import com.rubik.erp.model.OrdenDeCompra;
import com.rubik.erp.modulo.generic.WindowCancelarDocumento;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import de.steinwedel.messagebox.MessageBox;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.rubicone.vaadin.fam3.silk.Fam3SilkIcon;

/**
 *
 * @author GRUCAS
 */
public class ComprasMonitorOrdenes  extends Panel implements View {

    public static final String NAME = "MONITOR_ORDENES_COMPRA";
    VerticalLayout container = new VerticalLayout();

    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");
    
    Grid<OrdenDeCompra> gridRequisiciones = new Grid<>();
    List<OrdenDeCompra> listOC = new ArrayList<>();
    
    NativeSelect<String> cboBusqueda = new NativeSelect();

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    public ComprasMonitorOrdenes() {
        initComponents();
        
        Label lblTitulo = new Label("MONITOR DE ORDENES DE COMPRA") {
            {
                setStyleName("h1");
            }
        };
        lblTitulo.setSizeUndefined();

        setContent(container);

        container.setMargin(false);
        container.addComponents(new FragmentTop(),
                lblTitulo,
                new HorizontalLayout(new Label("Autorizador:"), cboBusqueda){{setComponentAlignment(getComponent(0), Alignment.MIDDLE_CENTER);}},
                gridRequisiciones);
        
        container.setComponentAlignment(container.getComponent(0), Alignment.MIDDLE_CENTER);
        container.setComponentAlignment(container.getComponent(1), Alignment.MIDDLE_CENTER);
        container.setComponentAlignment(container.getComponent(2), Alignment.MIDDLE_CENTER);
        container.setComponentAlignment(container.getComponent(3), Alignment.MIDDLE_CENTER);

        setContent(container);
    }

    public void initComponents() {
        setSizeFull();
        
        cboBusqueda.setItems("POR RECIBIR", "VENCIDOS SIN RECEPCION", "PAGADO");
        cboBusqueda.setEmptySelectionAllowed(false);
        cboBusqueda.setValue("POR RECIBIR");
        
        cboBusqueda.addValueChangeListener((event) -> {
            gridRequisiciones.setItems(getOrdenes());
        });

        Grid.Column<OrdenDeCompra, String> columnFecha = gridRequisiciones.addColumn(det -> ((det.getFecha_elaboracion()!= null) ? dateFormat.format(det.getFecha_elaboracion()) : ""));
        columnFecha.setCaption("F. REQ");
        columnFecha.setId("F. REQ");
        columnFecha.setWidth(120);
        gridRequisiciones.addColumn(OrdenDeCompra::getFolio).setCaption("FOLIO").setId("FOLIO").setWidth(120);
        gridRequisiciones.addColumn(OrdenDeCompra::getProveedor).setCaption("PROVEEDOR").setId("PROVEEDOR").setWidth(135);
        gridRequisiciones.addColumn(OrdenDeCompra::getEstado_doc).setCaption("ESTADO").setId("ESTADO").setWidth(135);
        gridRequisiciones.addColumn(OrdenDeCompra::getSolicita).setCaption("SOLICITA").setId("SOLICITA");
        gridRequisiciones.addComponentColumn(this::getBtnInfo).setCaption("").setWidth(131);
        gridRequisiciones.addComponentColumn(this::getBtnCancelar).setCaption("").setWidth(175);

        gridRequisiciones.setItems(getOrdenes());
        gridRequisiciones.setSelectionMode(Grid.SelectionMode.SINGLE);
        gridRequisiciones.setSizeFull();
        gridRequisiciones.setHeight("500px");
    }
    
    private Button getBtnInfo(OrdenDeCompra oc) {
        Button btnTabulador = new Button("INFO", Fam3SilkIcon.REPORT_MAGNIFY);
        btnTabulador.addClickListener((event) -> {
//            WindowRequisicionDetInfo window = new WindowRequisicionDetInfo(oc);
//            window.center();
//            window.setModal(true);
//            window.addCloseListener(ev -> {
//            });
//            getUI().addWindow(window);
        });

        return btnTabulador;
    }
    
    private Button getBtnCancelar(OrdenDeCompra oc) {
        Button btnTabulador = new Button("CANCELAR", Fam3SilkIcon.CANCEL);
        btnTabulador.addClickListener((event) -> {
            MessageBox.createQuestion()
                    .withCaption("Confirmar Accion")
                    .withMessage("Desea Cancelar la Requisicion " + oc.getFolio() + "?")
                    .withYesButton(() -> {

                        WindowCancelarDocumento winCancelar = new WindowCancelarDocumento();
                        winCancelar.addCloseListener((e) -> {
                            oc.setActivo(false);
                            String razon_cancelar = winCancelar.RAZON_DE_CANCELAMIENTO;
                            oc.setRazon_cancelar(razon_cancelar);
                            oc.setEstado_doc(_DocumentoEstados.CANCELADO);
                            oc.setFecha_modificacion(new Date());
                            
                            OrdenDeCompraDomain service = new OrdenDeCompraDomain();
                            service.OrdenDeCompraUpdate(oc);

                            gridRequisiciones.setItems(getOrdenes());

                        });
                        getUI().addWindow(winCancelar);
                    })
                    .withNoButton()
                    .open();
        });

        return btnTabulador;
    }
    
//    private Button getBtnAutorizar(Requisicion requisicion) {
//        Button btnTabulador = new Button("AUTORIZAR", Fam3SilkIcon.ACCEPT);
//        btnTabulador.addClickListener((event) -> {
//            MessageBox.createQuestion()
//                    .withCaption("Confirmar Accion")
//                    .withMessage("Desea Autorizar y pasar al Departamento de Compras la Requisicion " + requisicion.getFolio() + "?")
//                    .withYesButton(() -> {
//                        FirmaElectronica firma = 
//                            new FirmaElectronica(requisicion.getAutoriza(), _DocumentoTipos.REQUISICION_DE_COMPRA, 
//                                requisicion.getFolio(), 1);
//                        
//                        requisicion.setAutoriza(empleado.getNombre_completo());
//                        requisicion.setAutoriza_id(empleado.getId());
//                        requisicion.setFirma_autorizo(firma.getEfirma());
//                        requisicion.setFecha_autorizo(firma.getFecha_firma());
//                        
//                        RequisicionDomain service = new RequisicionDomain();
//                        service.RequisicionAutorizar(requisicion);
//
//                        gridRequisiciones.setItems(getOrdenes());
//                    })
//                    .withNoButton()
//                    .open();
//        });
//
//        return btnTabulador;
//    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    public List getOrdenes() {
        
        
        
        
        String strWhere = " activo = 1 and estado_doc = '" + _DocumentoEstados.POR_SURTIR + "'";

        OrdenDeCompraDomain service = new OrdenDeCompraDomain();
        service.getOrdenDeCompra(strWhere, "", "");
        listOC = service.getObjects();

        if (!service.getOk()) {
            MessageBox.createError()
                    .withCaption("Error al cargar la informacion!")
                    .withMessage("Err: " + service.getNotification())
                    .withRetryButton()
                    .open();
        }
        return listOC;
    }
    
}