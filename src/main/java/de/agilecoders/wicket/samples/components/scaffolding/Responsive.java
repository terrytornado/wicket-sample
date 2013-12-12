package de.agilecoders.wicket.samples.components.scaffolding;

import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.request.resource.SharedResourceReference;

import de.agilecoders.wicket.samples.components.base.Section;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class Responsive extends Section<Void> {

    private static final ResourceReference responsiveImage = new SharedResourceReference(Responsive.class, "responsive-illustrations.png");

    public Responsive(String id) {
        super(id);

        add(new Image("responsive-illustrations", responsiveImage));
    }
}
