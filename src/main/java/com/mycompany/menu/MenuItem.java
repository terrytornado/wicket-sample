package com.mycompany.menu;

import java.io.Serializable;

import org.apache.wicket.Page;
import org.apache.wicket.model.IModel;

public class MenuItem implements Serializable {
	/**
	 * SerialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	private final IModel<String> label;
	private final Class<? extends Page> page;

	public MenuItem(IModel<String> label, Class<? extends Page> page) {
		this.label = label;
		this.page = page;
	}
	// getters for label and page

	public Class<? extends Page> getPage() {
		return page;
	}

	public IModel<String> getLabel() {
		return label;
	}
}