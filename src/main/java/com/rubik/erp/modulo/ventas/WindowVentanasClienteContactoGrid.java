/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.ventas;

import com.rubik.erp.domain.ClienteContactoDomain;
import com.rubik.erp.model.Cliente;
import com.rubik.erp.model.ClienteContacto;
import com.rubik.erp.model.Empleado;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.MessageBox;
import java.util.ArrayList;
import java.util.List;
import org.rubicone.vaadin.fam3.silk.Fam3SilkIcon;

/**
 *
 * @author GRUCAS
 */
public class WindowVentanasClienteContactoGrid extends Window {
    
    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");
    
    VerticalLayout cont = new VerticalLayout();

    public Cliente cliente = new Cliente();
    
    Button btnAgregar = new Button("Agregar", Fam3SilkIcon.ADD);
    Button btnModificar = new Button("Modificar", Fam3SilkIcon.APPLICATION_EDIT);
    Button btnEliminar = new Button("Eliminar", Fam3SilkIcon.DELETE);
    
    Grid<ClienteContacto> gridClienteContacto = new Grid<>();
    List<ClienteContacto> listProveedor = new ArrayList<>();

    public WindowVentanasClienteContactoGrid(Cliente cliente) {
        setCaption("CONTACTOS DE " + cliente.getRazon_social());
        this.cliente = cliente;
        initComponents();
    }

    public void initComponents() {
        setContent(cont);
        setResizable(false);
        
        setWidth("700");
        setHeight("80%");
        
        gridClienteContacto.addColumn(ClienteContacto::getNombre).setCaption("NOMBRE").setId("NOMBRE");
        gridClienteContacto.addColumn(ClienteContacto::getEmail).setCaption("EMAIL").setId("EMAIL").setWidth(120);
        gridClienteContacto.addColumn(ClienteContacto::getTelefono).setCaption("TELEFONO").setId("TELEFONO").setWidth(150);

        gridClienteContacto.setItems(getClienteContacto());
        gridClienteContacto.setSelectionMode(Grid.SelectionMode.SINGLE);
        gridClienteContacto.setSizeFull();
        gridClienteContacto.setHeight("500px");

        btnModificar.addClickListener((event) -> {
            if (gridClienteContacto.getSelectedItems().size() == 1) {
                WindowVentanasClienteContacto windows = new WindowVentanasClienteContacto(cliente,gridClienteContacto.getSelectedItems().iterator().next());
                windows.center();
                windows.setModal(true);
                windows.addCloseListener((e) -> {
                    gridClienteContacto.setItems(getClienteContacto());
                });
                getUI().addWindow(windows);
            } else {
                MessageBox.createError()
                        .withCaption("Error!")
                        .withMessage("Debe tener un Contacto seleccionado para poder modificarlo.")
                        .withRetryButton()
                        .open();
            }
        });

        btnAgregar.addClickListener((event) -> {
            WindowVentanasClienteContacto windows = new WindowVentanasClienteContacto(cliente);
            windows.center();
            windows.setModal(true);
            windows.addCloseListener(ev -> {
                gridClienteContacto.setItems(getClienteContacto());
            });
            getUI().addWindow(windows);
        });
        
        btnEliminar.addClickListener((event) -> {
            if (gridClienteContacto.getSelectedItems().size() == 1) {
                MessageBox.createQuestion()
                        .withCaption("Confirmar Accion")
                        .withMessage("Desea Eliminar el Contacto Seleccionado?")
                        .withYesButton(() -> {

                                ClienteContactoDomain service = new ClienteContactoDomain();
                                service.ClienteContactoDelete(gridClienteContacto.getSelectedItems().iterator().next());
                                gridClienteContacto.setItems(getClienteContacto());
                        })
                        .withNoButton()
                        .open();
            } else {
                MessageBox.createError()
                        .withCaption("Error!")
                        .withMessage("Debe seleccionar un Contacto para Eliminarlo")
                        .withRetryButton()
                        .open();
            }
        });

        cont.addComponents(new HorizontalLayout(btnAgregar, btnModificar, btnEliminar),gridClienteContacto);
        cont.setComponentAlignment(cont.getComponent(0), Alignment.MIDDLE_CENTER);
        cont.setComponentAlignment(cont.getComponent(1), Alignment.MIDDLE_CENTER);
    }
    
    public List<ClienteContacto> getClienteContacto(){
        ClienteContactoDomain service = new ClienteContactoDomain();
        service.getClienteContacto("cliente_id = " + cliente.getId(), "", "nombre ASC");
        listProveedor = service.getObjects();

        if (!service.getOk()) {
            MessageBox.createError()
                    .withCaption("Error al cargar la informacion!")
                    .withMessage("Err: " + service.getNotification())
                    .withRetryButton()
                    .open();
        }

        return listProveedor;
    }
    
}