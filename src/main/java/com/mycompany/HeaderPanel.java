package com.mycompany;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;

import com.mycompany.menu.Menu;
import com.mycompany.menu.MenuItem;
import com.mycompany.menu.MenuPage;

public abstract class HeaderPanel extends Panel implements MenuPage {

	// public class HeaderPanel extends Panel {
	// public HeaderPanel(String id) {
	// super(id);
	// }

	/**
	 * SerialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	protected static MenuItem HOME = new MenuItem(Model.of("Home"), HomePage.class);
	protected static MenuItem PAGE2 = new MenuItem(Model.of("Page2"), Page2.class);
	protected static MenuItem PAGE3 = new MenuItem(Model.of("Page3"), Page3.class);
	protected static MenuItem ABOUTUS = new MenuItem(Model.of("About Us"), AboutUsPage.class);

	public HeaderPanel(String id) {
		super(id);

		add(new Menu("menu", new MenuModel()));
	}

	private static class MenuModel extends LoadableDetachableModel<List<? extends MenuItem>> {

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

}
