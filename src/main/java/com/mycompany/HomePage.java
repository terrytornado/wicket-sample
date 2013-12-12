package com.mycompany;

import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.internal.HtmlHeaderContainer;

import com.mycompany.menu.MenuItem;

import de.agilecoders.wicket.core.markup.html.themes.bootstrap.BootstrapResponsiveCssReference;

public class HomePage extends BasePage {

    private static final long serialVersionUID = 1L;

    public HomePage() {

        // TODO Add your page's components here

        // add(new Label("version",
        // getApplication().getFrameworkSettings().getVersion()));
    }

    public MenuItem getItem() {
        return HOME;
    }

    @Override
    public void renderHead(HtmlHeaderContainer container) {
        super.renderHead(container);
        System.out.println("aaaaaaa");
        container.getHeaderResponse().render(JavaScriptHeaderItem.forReference(BootstrapResponsiveCssReference.INSTANCE));
    }
}
