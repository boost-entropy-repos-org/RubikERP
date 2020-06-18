/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.ventas;

import com.rubik.erp.config._DocumentoEstados;
import com.rubik.erp.config._DocumentoTipos;
import com.rubik.erp.config._Folios;
import com.rubik.erp.domain.ClienteDomain;
import com.rubik.erp.domain.ConfiguracionDomain;
import com.rubik.erp.domain.RemisionEntregaDetDomain;
import com.rubik.erp.domain.RemisionEntregaDomain;
import com.rubik.erp.model.Cliente;
import com.rubik.erp.model.Configuracion;
import com.rubik.erp.model.Empleado;
import com.rubik.erp.model.OrdenDeCompra;
import com.rubik.erp.model.OrdenDeCompraDet;
import com.rubik.erp.model.RemisionEntrega;
import com.rubik.erp.model.RemisionEntregaDet;
import com.rubik.erp.modulo.compras.WindowOrdenDeCompraSeleccionar;
import com.rubik.manage.ManageDates;
import com.rubik.manage.ManageString;
import com.vaadin.data.Binder;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.MessageBox;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.rubicone.vaadin.fam3.silk.Fam3SilkIcon;

/**
 *
 * @author Dev
 */
public class WindowVentasRemisionDeEntrega  extends Window {
    
    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");
    
    VerticalLayout cont = new VerticalLayout();
    String title_window = "Remision de Entrega";

    OrdenDeCompra ordenDeCompra;
    RemisionEntrega remisionDeEntrega;
    Boolean isEdit = false;

    TextField txtFolioOC = new TextField();
    
    DateField txtFechaEntrega = new DateField("Fecha Entrega:", LocalDate.now());
    TextField txtEntrega = new TextField("Entrega:");
    TextArea txtObservaciones = new TextArea("Notas de Entrega:");
    NativeSelect<Cliente> cboCliente = new NativeSelect("Cliente:");

    Button btnBuscarCotizacion = new Button("",Fam3SilkIcon.MAGNIFIER);
    Button btnGuardar = new Button("Guardar", Fam3SilkIcon.DISK);
    Button btnCancelar = new Button("Cancelar", Fam3SilkIcon.CANCEL);
    
    Grid<RemisionEntregaDet> gridRemisionDet = new Grid<>();
    List<RemisionEntregaDet> listRemisionDet = new ArrayList<>();
    
    List<Cliente> clienteList = new ArrayList<>();
    
    Label lblFolio;
    String folio = "";

    public WindowVentasRemisionDeEntrega() {
        remisionDeEntrega = new RemisionEntrega();
        
        lblFolio = new Label("REMISION DE ENTREGA " + getFolio()) {
            {
                setStyleName("h2");
            }
        };
        
        initComponents();
    }

    public WindowVentasRemisionDeEntrega(RemisionEntrega remEntrega) {
        isEdit = true;
        remisionDeEntrega = remEntrega;
        lblFolio = new Label("REMISION DE ENTREGA " + remEntrega.getFolio()) {
            {
                setStyleName("h2");
            }
        };
        
        initComponents();
    }

    public void initComponents() {
        String strWidth = "300";
        
        setWidth("85%");
        setHeight("80%");

        Binder<RemisionEntrega> binder = new Binder<>();
        binder.forField(txtFolioOC).bind(RemisionEntrega::getFolio_orden_compra, RemisionEntrega::setFolio_orden_compra);
        binder.forField(txtEntrega).bind(RemisionEntrega::getEntrega, RemisionEntrega::setEntrega);
        binder.forField(txtObservaciones).bind(RemisionEntrega::getObservaciones, RemisionEntrega::setObservaciones);
        binder.forField(txtObservaciones).bind(RemisionEntrega::getObservaciones, RemisionEntrega::setObservaciones);
        
        cboCliente.setItems(getProveedor());
        cboCliente.setEmptySelectionAllowed(false);
        try {
            cboCliente.setSelectedItem(clienteList.get(0));
        } catch (Exception e) {
            MessageBox.createError()
                    .withCaption("Error!")
                    .withMessage("No existen Clientes dados de alta. ")
                    .withRetryButton()
                    .open();
        }
        
        gridRemisionDet.setHeight("272");
        gridRemisionDet.setSelectionMode(Grid.SelectionMode.SINGLE);

        gridRemisionDet.addColumn(RemisionEntregaDet::getCantidad).setCaption("CTD").setWidth(75);
        gridRemisionDet.addColumn(RemisionEntregaDet::getDescripcion).setCaption("DESCRIPCION");
        
        btnBuscarCotizacion.addClickListener((event) -> {
            WindowOrdenDeCompraSeleccionar windowCotSelected = new WindowOrdenDeCompraSeleccionar();
            windowCotSelected.center();
            windowCotSelected.setModal(true);
            windowCotSelected.addCloseListener((e) -> {
                if (windowCotSelected.seleccionado) {
                    ordenDeCompra = windowCotSelected.oc_selected;

                    remisionDeEntrega.setOrden_compra_id(ordenDeCompra.getId());
                    remisionDeEntrega.setFolio_orden_compra(ordenDeCompra.getFolio());

                    txtFolioOC.setValue(ordenDeCompra.getFolio());
                    
                    List<OrdenDeCompraDet> partidasCotizacion = windowCotSelected.listPartidasSeleccionadas;
                    
                    for (OrdenDeCompraDet partSelected : partidasCotizacion) {
                        RemisionEntregaDet partida = new RemisionEntregaDet();
                        
                        partida.setCantidad(partSelected.getCantidad());
                        partida.setDescripcion(partSelected.getDescripcion());
                        partida.setProducto_id(partSelected.getProducto_id());
                        partida.setUnidad_medida(partSelected.getUnidad_medida());
                        listRemisionDet.add(partida);
                    }
                    gridRemisionDet.setItems(listRemisionDet);
                }
            });
            getUI().addWindow(windowCotSelected);
        });
        
        btnCancelar.addClickListener((event) -> {
            close();
        });

        btnGuardar.addClickListener((event) -> {
            try {
                
                if(!isEdit){
                    remisionDeEntrega = new RemisionEntrega();
                }

                binder.writeBean(remisionDeEntrega);
                toUpperCase();
                
                Cliente cteTemp = cboCliente.getValue();
                
                if(cteTemp != null){
                    remisionDeEntrega.setCliente(cteTemp.getRazon_social());
                    remisionDeEntrega.setCliente_id(cteTemp.getId());
                }else{
                    remisionDeEntrega.setCliente("");
                    remisionDeEntrega.setCliente_id(0);
                }
                
                remisionDeEntrega.setUsuario_id(empleado.getId());
                remisionDeEntrega.setUsuario(empleado.getNombre_completo());
                remisionDeEntrega.setEstado_doc(_DocumentoEstados.TERMINADO);
                remisionDeEntrega.setTipo_documento(_DocumentoTipos.REMISION_DE_ENTREGA);
                remisionDeEntrega.setTipo_archivo("PDF");
                remisionDeEntrega.setFecha_entrega(ManageDates.getDateFromLocalDate(txtFechaEntrega.getValue()));
                remisionDeEntrega.setEntrega(txtEntrega.getValue().toUpperCase());
                remisionDeEntrega.setActivo(true);

                
                RemisionEntregaDomain service = new RemisionEntregaDomain();

                if (isEdit) {
                    remisionDeEntrega.setFecha_modificacion(new Date());
                    service.RemisionEntregaUpdate(remisionDeEntrega);
                    
                } else {
                    remisionDeEntrega.setFolio(getFolio());
                    remisionDeEntrega.setSerie("");
                    
                    RemisionEntregaDetDomain domainDet = new RemisionEntregaDetDomain();
                    service.RemisionEntregaInsert(remisionDeEntrega);
                    updateFolio();
                    
                    for (RemisionEntregaDet partidaTemp : listRemisionDet) { //Guarda la partida con el ID de la remisionDeEntrega
                        partidaTemp.setFolio(remisionDeEntrega.getFolio());
                        partidaTemp.setDocumento_id(remisionDeEntrega.getId());
                        domainDet.RemisionEntregaDetInsert(partidaTemp);
                    }
                }

                if (service.getOk()) {
                    MessageBox.createInfo()
                            .withCaption("Atencion")
                            .withMessage(service.getNotification())
                            .open();
                    close();
                } else {
                    MessageBox.createError()
                            .withCaption("Error!")
                            .withMessage(service.getNotification())
                            .withRetryButton()
                            .open();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                MessageBox.createError()
                        .withCaption("Error!")
                        .withMessage("Verifique que la informacion este completa o sea correcta. ")
                        .withRetryButton()
                        .open();
            }
        });
        
        txtFolioOC.setEnabled(false);
        txtEntrega.setEnabled(false);
        txtEntrega.setValue(empleado.getNombre_completo());
        txtObservaciones.setRows(2);
       
        txtFechaEntrega.setWidth(strWidth);
        txtEntrega.setWidth(strWidth);
        txtObservaciones.setWidth(strWidth);
        cboCliente.setWidth(strWidth);

        if (isEdit) {
            binder.readBean(remisionDeEntrega);
            
            for (Cliente prov : clienteList) {
                if (remisionDeEntrega.getCliente_id().equals(prov.getId())) {
                    cboCliente.setValue(prov);
                }
            }
            
            gridRemisionDet.setItems(getPartidas());
        }
        
        FormLayout fLay = new FormLayout();
        fLay.setSpacing(false);
        fLay.addComponents(txtFechaEntrega,txtEntrega,txtObservaciones,cboCliente);

        cont.setSpacing(false);
        cont.addComponents(lblFolio,
                new HorizontalLayout(new Label("Folio Orden de Compra:"), txtFolioOC, btnBuscarCotizacion) // 1
                    {{
                        setComponentAlignment(getComponent(0), Alignment.MIDDLE_CENTER);
                    }},
                
                new HorizontalLayout(fLay, 
                        new VerticalLayout(
                                gridRemisionDet){{setComponentAlignment(getComponent(0), Alignment.MIDDLE_CENTER);}}
                ){{setSpacing(false);}}, new HorizontalLayout(btnCancelar, btnGuardar));
        cont.setComponentAlignment(cont.getComponent(0), Alignment.MIDDLE_CENTER);
        cont.setComponentAlignment(cont.getComponent(1), Alignment.MIDDLE_RIGHT);
        cont.setComponentAlignment(cont.getComponent(2), Alignment.MIDDLE_CENTER);
        cont.setComponentAlignment(cont.getComponent(3), Alignment.MIDDLE_CENTER);
        
        setContent(cont);
        setModal(true);
        setResizable(false);
        setClosable(false);
    }
    
    public List getPartidas() {
        String strWhere = " documento_id = " + remisionDeEntrega.getId();

        RemisionEntregaDetDomain service = new RemisionEntregaDetDomain();
        service.getRemisionEntregaDet(strWhere, "", " id DESC");
        listRemisionDet = service.getObjects();

        if (!service.getOk()) {
            MessageBox.createError()
                    .withCaption("Error al cargar la informacion!")
                    .withMessage("Err: " + service.getNotification())
                    .withRetryButton()
                    .open();
        }
        return listRemisionDet;
    }
    
    public void toUpperCase() {
        remisionDeEntrega.setEntrega(txtEntrega.getValue().toUpperCase());
        remisionDeEntrega.setObservaciones(txtObservaciones.getValue().toUpperCase());
    }
    
    public String getFolio() {
        ConfiguracionDomain domain = new ConfiguracionDomain();
        domain.getOneFolioConfiguracion(_Folios.FOLIO_REMISION_ENTREGA, _Folios.SERIE_REMISION_ENTREGA);
        Configuracion conf = domain.getObject();
        
        folio = conf.getSerie() + ManageString.fillWithZero(conf.getFolio(), 5);
        
        return folio;
    }
    
    public void updateFolio() {
        ConfiguracionDomain domain = new ConfiguracionDomain();
        domain.ConfiguracionFolioUpdate(_Folios.FOLIO_REMISION_ENTREGA);
    }
    
    public List<Cliente> getProveedor() {
        ClienteDomain provService = new ClienteDomain();
        provService.getCliente("", "", "razon_social ASC");
        clienteList = provService.getObjects();
        return clienteList;
    }
    
}