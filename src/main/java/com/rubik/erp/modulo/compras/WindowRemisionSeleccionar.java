/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.compras;

import com.rubik.erp.domain.RemisionDomain;
import com.rubik.erp.model.Empleado;
import com.rubik.erp.model.Remision;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.MessageBox;
import java.util.ArrayList;
import java.util.List;
import org.rubicone.vaadin.fam3.silk.Fam3SilkIcon;

/**
 *
 * @author Dev
 */
public class WindowRemisionSeleccionar extends Window {
    
    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");
    
    VerticalLayout cont = new VerticalLayout();
    String title_window = "Remisiones de Compra";

    Remision remision;

    Button btnChecar = new Button("Guardar", Fam3SilkIcon.ACCEPT);
    Button btnCancelar = new Button("Cancelar", Fam3SilkIcon.CANCEL);
    
    Grid<Remision> gridRemision = new Grid<>();
    List<Remision> listRemision = new ArrayList<>();
    Label lblFolio;

    public WindowRemisionSeleccionar() {
        lblFolio = new Label("SELECCIONAR REMISION") {
            {
                setStyleName("h2");
            }
        };
        initComponents();
    }

    public void initComponents() {
        String strWidth = "300";
        
        setWidth("80%");
        setHeight("80%");
        
        gridRemision.setHeight("272");
        gridRemision.setSelectionMode(Grid.SelectionMode.SINGLE);

        gridRemision.addColumn(Remision::getFolio).setCaption("FOLIO").setWidth(75);
        gridRemision.addColumn(Remision::getPrioridad).setCaption("PRIORIDAD");
        gridRemision.addColumn(Remision::getSolicita).setCaption("SOLICITA").setWidth(100);
        gridRemision.addColumn(Remision::getObservaciones).setCaption("OBSERVACIONES").setWidth(100);
        
        btnCancelar.addClickListener((event) -> {
            close();
        });

        btnChecar.addClickListener((event) -> {

        });

        cont.setSpacing(false);
        cont.addComponents(lblFolio,
                new HorizontalLayout(btnCancelar, btnChecar),
                gridRemision);

        cont.setComponentAlignment(cont.getComponent(0), Alignment.MIDDLE_CENTER);
        cont.setComponentAlignment(cont.getComponent(1), Alignment.MIDDLE_CENTER);
        cont.setComponentAlignment(cont.getComponent(2), Alignment.MIDDLE_CENTER);
        
        setContent(cont);
        setModal(true);
        setResizable(false);
        setClosable(false);
    }
    
    public List getPartidas() {
        String strWhere = " documento_id = " + remision.getId();

        RemisionDomain service = new RemisionDomain();
        service.getRemision(strWhere, "", " id DESC");
        listRemision = service.getObjects();

        if (!service.getOk()) {
            MessageBox.createError()
                    .withCaption("Error al cargar la informacion!")
                    .withMessage("Err: " + service.getNotification())
                    .withRetryButton()
                    .open();
        }
        return listRemision;
    }
    
    public void toUpperCase() {
        remision.setSolicita(txtSolicita.getValue().toUpperCase());
        remision.setObservaciones(txtObservaciones.getValue().toUpperCase());
        remision.setDireccion_entrega(txtDireccionEntrega.getValue().toUpperCase());
    }
    
    public List<Empleado> getAutorizadorCompras() {
        EmpleadoDomain provService = new EmpleadoDomain();
        provService.getEmpleado(" autorizador = 1 ", "", " nombre ASC");
        
        listAutorizadoresCompras = provService.getObjects();
        return listAutorizadoresCompras;
    }
    
    public String getFolio() {
        ConfiguracionDomain domain = new ConfiguracionDomain();
        domain.getOneConfiguracion(_Folios.FOLIO_REMISION, _Folios.SERIE_REMISION);
        Configuracion conf = domain.getObject();
        
        folio = conf.getSerie() + ManageString.fillWithZero(conf.getFolio(), 5);
        
        return folio;
    }
    
    public void updateFolio() {
        ConfiguracionDomain domain = new ConfiguracionDomain();
        domain.ConfiguracionUpdate(_Folios.FOLIO_REMISION);
    }
    
}