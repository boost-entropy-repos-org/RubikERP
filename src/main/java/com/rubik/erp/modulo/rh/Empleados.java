/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.rh;

import com.rubik.erp.config._Departamentos;
import com.rubik.erp.domain.EmpleadoDomain;
import com.rubik.erp.fragments.FragmentTop;
import com.rubik.erp.model.Empleado;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
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
 * @author GrucasDev
 */
public class Empleados extends Panel implements View {

    public static final String NAME = "EMPLEADOS";
    VerticalLayout container = new VerticalLayout();

    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");

    Grid<Empleado> gridEmpleados = new Grid<>();
    Button btnAdd = new Button("Agregar", Fam3SilkIcon.ADD);
    Button btnModify = new Button("Modificar", Fam3SilkIcon.PENCIL);
    List<Empleado> listEmpleados = new ArrayList<>();

    TextField txtBusqueda = new TextField();
    NativeSelect<String> cboDepartamento = new NativeSelect();
    Button btnBuscar = new Button("Buscar",Fam3SilkIcon.MAGNIFIER);

    public Empleados() {
        initComponents();

        HorizontalLayout hLayoutAux = new HorizontalLayout(txtBusqueda, cboDepartamento, btnBuscar, btnAdd, btnModify);

        Label lblTitulo = new Label("EMPLEADOS") {
            {
                setStyleName("h1");
            }
        };
        lblTitulo.setSizeUndefined();

        container.setMargin(false);
        container.addComponents(new FragmentTop(),
                lblTitulo,
                hLayoutAux,
                gridEmpleados);

        container.setComponentAlignment(container.getComponent(0), Alignment.MIDDLE_CENTER);
        container.setComponentAlignment(container.getComponent(1), Alignment.MIDDLE_CENTER);
        container.setComponentAlignment(container.getComponent(2), Alignment.MIDDLE_CENTER);
        container.setComponentAlignment(container.getComponent(3), Alignment.MIDDLE_CENTER);

        setContent(container);
    }

    public void initComponents() {
        setSizeFull();

        txtBusqueda.setWidth("300");
        cboDepartamento.setWidth("300");

        txtBusqueda.setPlaceholder("Nombre del empleado a buscar");

        cboDepartamento.setItems(_Departamentos.DEPARTAMENTOS);

        SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");

        gridEmpleados.addColumn(Empleado::getClave_empleado).setCaption("No").setId("No");
        gridEmpleados.addColumn(Empleado::getNombre_completo).setCaption("NOMBRE").setId("NOMBRE");
        gridEmpleados.addColumn(Empleado::getEmail_empresa).setCaption("EMAIL").setId("EMAIL");
        gridEmpleados.addColumn(Empleado::getDepartamento).setCaption("DEPARTAMENTO").setId("DEPARTAMENTO");
        gridEmpleados.addColumn(Empleado::getPuesto).setCaption("PUESTO").setId("PUESTO");
        Column<Empleado, String> cFechaEta = gridEmpleados.addColumn(det -> ((det.getFecha_ingreso() != null) ? dt.format(det.getFecha_ingreso()) : ""));
        cFechaEta.setCaption("F. INGRESO");
        cFechaEta.setId("F. INGRESO");

        gridEmpleados.setSelectionMode(Grid.SelectionMode.SINGLE);
        gridEmpleados.setSizeFull();

        gridEmpleados.addItemClickListener((event) -> {
            if (event.getMouseEventDetails().isDoubleClick()) {
                WindowEmpleado windows = new WindowEmpleado(event.getItem());
                windows.center();
                windows.setModal(true);
                windows.addCloseListener((e) -> {
                    gridEmpleados.setItems(getEmpresas());
                });
                getUI().addWindow(windows);
            }
        });
       
        btnAdd.addClickListener(e -> {
            WindowEmpleado windows = new WindowEmpleado();
            windows.center();
            windows.setModal(true);
            windows.addCloseListener(ev -> {
                gridEmpleados.setItems(getEmpresas());
            });
            getUI().addWindow(windows);
        });

        btnModify.addClickListener((event) -> {
            if (gridEmpleados.getSelectedItems().size() == 1) {
                WindowEmpleado windows = new WindowEmpleado(gridEmpleados.getSelectedItems().iterator().next());
                windows.center();
                windows.setModal(true);
                windows.addCloseListener((e) -> {
                    gridEmpleados.setItems(getEmpresas());
                });
                getUI().addWindow(windows);

            } else {
                MessageBox.createError()
                        .withCaption("Error!")
                        .withMessage("Debe tener un Producto seleccionado para poder modificarlo.")
                        .withRetryButton()
                        .open();
            }
        });

        btnBuscar.addClickListener(ev -> {
            gridEmpleados.setItems(getEmpresas());
            txtBusqueda.setValue("");
        });

//        gridEmpleados.setItems(getEmpresas());

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    public List getEmpresas() {

        String strWhere = "activo = 1 ";

        if (txtBusqueda.getValue() != "") {
            strWhere += " AND (nombre LIKE '%" + txtBusqueda.getValue() + "%' OR apellido_paterno LIKE '%" + txtBusqueda.getValue() + "%' OR apellido_materno LIKE '%" + txtBusqueda.getValue() + "%') ";
        }

        if (cboDepartamento.getValue() != null) {
            strWhere += " AND departamento = '" + cboDepartamento.getValue() + "'";
        }

        EmpleadoDomain service = new EmpleadoDomain();
        service.getEmpleado(strWhere, "", " nombre ASC ");
        listEmpleados = service.getObjects();

        if (!service.getOk()) {
            MessageBox.createError()
                    .withCaption("Error al cargar la informacion!")
                    .withMessage("Err: " + service.getNotification())
                    .withRetryButton()
                    .open();
        }
        return listEmpleados;
    }

}