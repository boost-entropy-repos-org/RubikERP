/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.generic;

import com.rubik.erp.domain.ConfiguracionDomain;
import com.rubik.erp.domain.EmpleadoDomain;
import com.rubik.erp.model.Empleado;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import de.steinwedel.messagebox.MessageBox;
import org.rubicone.vaadin.fam3.silk.Fam3SilkIcon;

/**
 *
 * @author Dev
 */
public class Login  extends VerticalLayout implements View {

    public static final String NAME = "LOGIN";

    TextField username = new TextField("Usuario");
    Button btnRecuperarPas = new Button("¿Olvidaste la Contraseña?");

    public Login() {
        setSizeFull();

        Component loginForm = buildLoginForm();

        addComponent(loginForm);
        setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);
        username.focus();
    }

    private Component buildLoginForm() {
        final VerticalLayout loginPanel = new VerticalLayout();

        loginPanel.setSizeUndefined();
        loginPanel.setSpacing(true);
        loginPanel.addStyleName("login-panel");

        Image logoGrucas = new Image(null, new ThemeResource("img/cubeLogo.png"));
        logoGrucas.setWidth("140px");

        btnRecuperarPas.setStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);

        loginPanel.setSizeUndefined();
        loginPanel.setSpacing(true);
        loginPanel.addComponents(logoGrucas, buildLabels(), buildFields(), btnRecuperarPas);
        loginPanel.setComponentAlignment(logoGrucas, Alignment.MIDDLE_CENTER);
        loginPanel.setComponentAlignment(loginPanel.getComponent(1), Alignment.MIDDLE_CENTER);
        loginPanel.setComponentAlignment(loginPanel.getComponent(2), Alignment.MIDDLE_CENTER);
        loginPanel.setComponentAlignment(loginPanel.getComponent(3), Alignment.MIDDLE_RIGHT);

        return loginPanel;
    }

    private Component buildFields() {
        HorizontalLayout fields = new HorizontalLayout();

        username = new TextField("Usuario:");
        username.setIcon(Fam3SilkIcon.USER);
        username.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        username.focus();
        username.setMaxLength(20);

        final PasswordField password = new PasswordField("Contraseña:");
        password.setIcon(Fam3SilkIcon.LOCK);
        password.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        password.setMaxLength(25);

        final Button signin = new Button("Entrar");
        signin.addStyleName(ValoTheme.BUTTON_DANGER);
        signin.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        signin.setIcon(Fam3SilkIcon.DOOR_IN);
        signin.focus();

        fields.addComponents(username, password, signin);
        fields.setComponentAlignment(signin, Alignment.BOTTOM_LEFT);
    
        username.setValue("a");
        password.setValue("a");
        
        signin.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(final ClickEvent event) {

                if (username.getValue().length() == 0 || password.getValue().length() == 0) {
                    MessageBox.createError()
                            .withCaption("Error!")
                            .withMessage("Debe colocar un nombre de usuario y contraseña validos.")
                            .withRetryButton()
                            .open();
                } else {

                    EmpleadoDomain service = new EmpleadoDomain();
                    Empleado usuario = service.Login(username.getValue(), password.getValue());
                    
                    if (usuario != null) {
                        
                        ConfiguracionDomain configService = new ConfiguracionDomain();
                        configService.getConfiguracion();
                        
                        VaadinSession.getCurrent().getSession().setAttribute("CONFIGURACION", configService.getObject());
                        VaadinSession.getCurrent().getSession().setAttribute("USUARIO_ACTIVO", usuario);
                        VaadinService.getCurrentRequest().getWrappedSession().setAttribute("userName", service.getObject().getNombre_completo());
                        getUI().getNavigator().navigateTo(MainPage.NAME);
                    } else {
                        MessageBox.createError()
                                .withCaption("Error: ")
                                .withMessage(service.getNotification())
                                .withRetryButton()
                                .open();
                        username.setValue("");
                        password.setValue("");
                        username.focus();
                    }
                }
            }
        });

        return fields;

    }

    private Component buildLabels() {
        CssLayout labels = new CssLayout();
        labels.addStyleName("labels");

        Label welcome = new Label("R U B I K ♦ S O F T");
        welcome.addStyleName(ValoTheme.LABEL_HUGE);
        labels.addComponents(welcome);
        return labels;
    }
}
