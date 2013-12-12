package de.agilecoders.wicket.samples.components.issues;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarForm;

/**
 * Sample Navbar containing a search form.
 *
 * @author miha
 */
public class CustomNavbarForm extends NavbarForm {
    public CustomNavbarForm(String componentId) {
        super(componentId);

        add(newSearchField("search"),
            newSubmitButton("submit"));
    }

    private Component newSubmitButton(String markupId) {
        return new BootstrapButton(markupId, Model.of("Search"), Buttons.Type.Default);
    }

    private Component newSearchField(String markupId) {
        return new TextField<String>(markupId);
    }
}
