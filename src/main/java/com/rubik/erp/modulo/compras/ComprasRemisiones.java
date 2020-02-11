/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.compras;

import com.rubik.erp.config._DocumentoEstados;
import com.rubik.erp.domain.RemisionDomain;
import com.rubik.erp.fragments.FragmentTop;
import com.rubik.erp.model.Empleado;
import com.rubik.erp.model.Remision;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import de.steinwedel.messagebox.MessageBox;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.rubicone.vaadin.fam3.silk.Fam3SilkIcon;

/**
 *
 * @author Dev
 */
public class ComprasRemisiones extends Panel implements View {

    public static final String NAME = "REMISIONES";
    VerticalLayout container = new VerticalLayout();

    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");

    TextField txtBusqueda = new TextField();
    
    Grid<Remision> gridRemisiones = new Grid<>();
    DateField txtFechaIni = new DateField();
    DateField txtFechaFin = new DateField();
    Button btnSearch = new Button(Fam3SilkIcon.MAGNIFIER);
    
    Button btnAdd = new Button("Agregar", Fam3SilkIcon.ADD);
    Button btnModify = new Button("Modificar", Fam3SilkIcon.PENCIL);
    Button btnCancel = new Button("Cancelar", Fam3SilkIcon.CANCEL);
    Button btnTerminar = new Button("Terminar", Fam3SilkIcon.NOTE_GO);
    Button btnPrint = new Button("Imprimir", Fam3SilkIcon.PRINTER);
    
    List<Remision> listRemisiones = new ArrayList<>();

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    public ComprasRemisiones() {
        initComponents();

        HorizontalLayout hLayoutAux = new HorizontalLayout(
                txtBusqueda,
                new Label("Fecha: "),txtFechaIni,new Label("A: "),txtFechaFin,btnSearch);
        
        HorizontalLayout hLayoutAux2 = new HorizontalLayout(btnAdd, btnModify, btnCancel, btnPrint, btnTerminar);
        
        hLayoutAux.setComponentAlignment(hLayoutAux.getComponent(1), Alignment.MIDDLE_CENTER);
        hLayoutAux.setComponentAlignment(hLayoutAux.getComponent(3), Alignment.MIDDLE_CENTER);
        
        Label lblTitulo = new Label("REMISIONES") {
            {
                setStyleName("h1");
            }
        };
        lblTitulo.setSizeUndefined();

        setContent(container);

        container.setMargin(false);
        container.addComponents(new FragmentTop(),
                lblTitulo,
                hLayoutAux,
                hLayoutAux2,
                gridRemisiones);
        
        container.setComponentAlignment(container.getComponent(0), Alignment.MIDDLE_CENTER);
        container.setComponentAlignment(container.getComponent(1), Alignment.MIDDLE_CENTER);
        container.setComponentAlignment(container.getComponent(2), Alignment.MIDDLE_CENTER);
        container.setComponentAlignment(container.getComponent(3), Alignment.MIDDLE_CENTER);

        setContent(container);
    }

    public void initComponents() {
        setSizeFull();

        txtFechaIni.setWidth("115");
        txtFechaFin.setWidth("115");
        
        txtBusqueda.setWidth("200");
        txtBusqueda.setPlaceholder("Folio de Remision");      

        Grid.Column<Remision, String> columnFecha = gridRemisiones.addColumn(det -> ((det.getFecha_requerida() != null) ? dateFormat.format(det.getFecha_requerida()) : ""));
        columnFecha.setCaption("F. REQ");
        columnFecha.setId("F. REQ");
        columnFecha.setWidth(120);
        gridRemisiones.addColumn(Remision::getFolio).setCaption("FOLIO").setId("FOLIO").setWidth(120);
        gridRemisiones.addColumn(Remision::getPrioridad).setCaption("PRIORIDAD").setId("PRIORIDAD").setWidth(135);
        gridRemisiones.addColumn(Remision::getEstado_doc).setCaption("ESTADO").setId("ESTADO").setWidth(135);
        gridRemisiones.addColumn(Remision::getFecha_orden_compra).setCaption("FECHA OC").setId("FECHA OC").setWidth(120);
        gridRemisiones.addColumn(Remision::getFolio_orden_compra).setCaption("FOLIO OC").setId("FOLIO OC").setWidth(120);
        gridRemisiones.addColumn(Remision::getRazon_cancelar).setCaption("CANCELACION").setId("CANCELACION");

        gridRemisiones.setItems(getRemisiones());
        gridRemisiones.setSelectionMode(Grid.SelectionMode.SINGLE);
        gridRemisiones.setSizeFull();
        gridRemisiones.setHeight("500px");

        btnAdd.addClickListener((event) -> {
            WindowRemision windows = new WindowRemision();
            windows.center();
            windows.setModal(true);
            windows.addCloseListener(ev -> {
                gridRemisiones.setItems(getRemisiones());
            });
            getUI().addWindow(windows);
        });

        btnModify.addClickListener((event) -> {
            if (gridRemisiones.getSelectedItems().size() == 1) {
                if(gridRemisiones.getSelectedItems().iterator().next().getEstado_doc().equals(_DocumentoEstados.EN_PROCESO)){
                    WindowRemision windows = new WindowRemision(gridRemisiones.getSelectedItems().iterator().next());
                    windows.center();
                    windows.setModal(true);
                    windows.addCloseListener((e) -> {
                        gridRemisiones.setItems(getRemisiones());
                    });
                    getUI().addWindow(windows);
                }else{
                    MessageBox.createError()
                            .withCaption("Error!")
                            .withMessage("No puede modificar una Remision de Compra que esta en espera de su Autorizacion.")
                            .withRetryButton()
                            .open();
                }
            } else {
                MessageBox.createError()
                        .withCaption("Error!")
                        .withMessage("Debe tener una Remision seleccionada para poder modificarla.")
                        .withRetryButton()
                        .open();
            }
        });
        
        btnSearch.addClickListener((event) -> {
            gridRemisiones.setItems(getRemisiones());
            txtBusqueda.setValue("");
        });
        
        btnPrint.addClickListener((event) -> {
        });
        
        btnTerminar.addClickListener((event) -> {
            if (gridRemisiones.getSelectedItems().size() == 1) {
                
                Remision remision = gridRemisiones.getSelectedItems().iterator().next();
                
                if(remision.getEstado_doc().equals(_DocumentoEstados.EN_PROCESO)){
                    
                    MessageBox.createQuestion()
                        .withCaption("Atencion!")
                        .withMessage("Desea que la Remision " + remision.getFolio() + ""
                                + " sea terminada? Ya no podrá realizar modificaciones.")
                        .withOkButton(() -> {
                            RemisionDomain domain = new RemisionDomain();
                            domain.RemisionTerminar(remision);
                            
                            gridRemisiones.setItems(getRemisiones());
                            
                            MessageBox.createInfo()
                                    .withCaption("Error!")
                                    .withMessage("Remision de Compra terminada correctamente. Aun esta pendiente de Autorizacion.")
                                    .withRetryButton()
                                    .open();
                        })
                        .withNoButton(() -> {})
                        .open();
                    
                }else{
                    MessageBox.createError()
                        .withCaption("Error!")
                        .withMessage("Con el estado " + remision.getEstado_doc() + " de la Remision " + remision.getFolio() + ""
                                + " no es posible pasar a Autorizacion.")
                        .withRetryButton()
                        .open();
                }
            } else {
                MessageBox.createError()
                        .withCaption("Error!")
                        .withMessage("Debe tener una Remision seleccionada para poder pasarla a Autorizacion.")
                        .withRetryButton()
                        .open();
            }
        });

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    public List getRemisiones() {
        String strWhere = " activo = 1 ";

        if (!"".equals(txtBusqueda.getValue())) {
            strWhere += " AND folio = '" + txtBusqueda.getValue().toUpperCase() + "'";
        }
        
        if(txtFechaIni.getValue() != null && txtFechaFin.getValue() != null){
            strWhere += "";
        }

        RemisionDomain service = new RemisionDomain();
        service.getRemision(strWhere, "", "fecha_requerida DESC");
        listRemisiones = service.getObjects();

        if (!service.getOk()) {
            MessageBox.createError()
                    .withCaption("Error al cargar la informacion!")
                    .withMessage("Err: " + service.getNotification())
                    .withRetryButton()
                    .open();
        }
        return listRemisiones;
    }
    
}