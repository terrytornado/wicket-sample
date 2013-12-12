package de.agilecoders.wicket.samples.panels.pagination;

import org.apache.wicket.Component;

import de.agilecoders.wicket.core.markup.html.bootstrap.navigation.ajax.BootstrapAjaxPagingNavigator;

/**
 * A demo panel for BootstrapAjaxPagingNavigator
 */
public class AjaxPaginationPanel extends AbstractPaginationPanel {

    public AjaxPaginationPanel(String id) {
        super(id);

        setOutputMarkupId(true);
    }

    @Override
    protected Component createPager(String id) {
        return new BootstrapAjaxPagingNavigator(id, pageable);
    }
}
