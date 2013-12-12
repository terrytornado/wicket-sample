package com.mycompany.menu;

import java.util.List;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.string.Strings;

public class Menu extends WebComponent {

	/**
	 * SerialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	public Menu(String id, IModel<List<? extends MenuItem>> items) {
		super(id, items);
	}

	public void onComponentTagBody(MarkupStream markupStream, ComponentTag openTag) {
		StringBuilder markup = new StringBuilder();
		renderMenu(markup);
		replaceComponentTagBody(markupStream, openTag, markup);
	}

	@SuppressWarnings("unchecked")
	private void renderMenu(StringBuilder markup) {
		List<? extends MenuItem> items = (List<? extends MenuItem>) getDefaultModelObject();
		markup.append("<ul>");
		MenuItem current = null;
		if (getPage() instanceof MenuPage) {
			current = ((MenuPage) getPage()).getItem();
		}
		for (MenuItem item : items) {
			CharSequence url = urlFor(item.getPage(), null);

			// System.out.println(item.getLabel().getObject());
			// System.out.println((IModel<String>) item.getLabel());
			// System.out.println(Strings.escapeMarkup(item.getLabel().getObject()));
			CharSequence s = Strings.escapeMarkup(item.getLabel().getObject());
			String label = s.toString();

			// String label =
			// (String)Strings.escapeMarkup(item.getLabel().getObject());
			item.getLabel().detach();
			markup.append("<li>");
			markup.append("<a ");
			markup.append("href='").append(url).append("'");
			if (item.equals(current)) {
				markup.append(" class='selected'");
			}
			markup.append(">");
			markup.append("<span>").append(label).append("</span>");
			markup.append("</a>");
			markup.append("</li>");
		}
		markup.append("</ul>");
	}
}
