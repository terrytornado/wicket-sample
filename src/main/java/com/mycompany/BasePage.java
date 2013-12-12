package com.mycompany;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.filter.HeaderResponseContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.internal.HtmlHeaderContainer;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;

import com.mycompany.menu.Menu;
import com.mycompany.menu.MenuItem;
import com.mycompany.menu.MenuPage;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.markup.html.themes.bootstrap.BootstrapResponsiveCssReference;
import de.agilecoders.wicket.themes.markup.html.bootstrap3.Bootstrap3Theme;

public abstract class BasePage extends WebPage implements MenuPage
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    protected static MenuItem HOME = new MenuItem(Model.of("Home"), HomePage.class);
    protected static MenuItem PAGE2 = new MenuItem(Model.of("Page2"), Page2.class);
    protected static MenuItem PAGE3 = new MenuItem(Model.of("Page3"), Page3.class);
    protected static MenuItem ABOUTUS = new MenuItem(Model.of("About Us"), AboutUsPage.class);

    public BasePage() {
        add(new HeaderResponseContainer("footer-container", "footer-bucket"));

        add(new Menu("menu", new MenuModel()));
    }

    private static class MenuModel extends LoadableDetachableModel<List<? extends MenuItem>>
    {

        /**
         * SerialVersionUID.
         */
        private static final long serialVersionUID = 1L;

        protected List<? extends MenuItem> load() {
            List<MenuItem> items = new ArrayList<MenuItem>();
            items.add(HOME);
            items.add(PAGE2);
            items.add(PAGE3);
            items.add(ABOUTUS);
            return items;
        }
    }

    // test
    @Override
    public void renderHead(HtmlHeaderContainer container) {
        super.renderHead(container);
        System.out.println("kkkkk");
//        container.getHeaderResponse().render(JavaScriptHeaderItem.forReference(BootstrapResponsiveCssReference.INSTANCE));
        container.getHeaderResponse().render(JavaScriptHeaderItem.forReference(BootstrapResponsiveCssReference.INSTANCE));
    }
}
