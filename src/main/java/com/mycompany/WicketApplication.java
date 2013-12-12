package com.mycompany;

import java.io.IOException;
import java.util.Properties;

import org.apache.wicket.Application;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.caching.FilenameWithVersionResourceCachingStrategy;
import org.apache.wicket.request.resource.caching.NoOpResourceCachingStrategy;
import org.apache.wicket.request.resource.caching.version.CachingResourceVersion;
import org.apache.wicket.serialize.java.DeflatedJavaSerializer;
import org.apache.wicket.settings.IRequestCycleSettings;

import com.google.javascript.jscomp.CompilationLevel;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.markup.html.RenderJavaScriptToFooterHeaderResponseDecorator;
import de.agilecoders.wicket.core.markup.html.references.ModernizrJavaScriptReference;
import de.agilecoders.wicket.core.request.resource.caching.version.Adler32ResourceVersion;
import de.agilecoders.wicket.core.settings.BootstrapSettings;
import de.agilecoders.wicket.core.settings.ThemeProvider;
import de.agilecoders.wicket.extensions.javascript.GoogleClosureJavaScriptCompressor;
import de.agilecoders.wicket.extensions.javascript.YuiCssCompressor;
//import de.agilecoders.wicket.extensions.markup.html.bootstrap.jqueryui.JQueryUICoreJavaScriptReference;
//import de.agilecoders.wicket.extensions.markup.html.bootstrap.jqueryui.JQueryUIDraggableJavaScriptReference;
//import de.agilecoders.wicket.extensions.markup.html.bootstrap.jqueryui.JQueryUIMouseJavaScriptReference;
//import de.agilecoders.wicket.extensions.markup.html.bootstrap.jqueryui.JQueryUIResizableJavaScriptReference;
//import de.agilecoders.wicket.extensions.markup.html.bootstrap.jqueryui.JQueryUIWidgetJavaScriptReference;
import de.agilecoders.wicket.less.BootstrapLess;
//import de.agilecoders.wicket.samples.assets.base.ApplicationJavaScript;
//import de.agilecoders.wicket.samples.assets.base.FixBootstrapStylesCssResourceReference;
import de.agilecoders.wicket.themes.markup.html.google.GoogleTheme;
import de.agilecoders.wicket.themes.markup.html.metro.MetroTheme;
import de.agilecoders.wicket.themes.markup.html.wicket.WicketTheme;
import de.agilecoders.wicket.themes.settings.BootswatchThemeProvider;
//import de.agilecoders.wicket.webjars.collectors.VfsJarAssetPathCollector;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 * 
 * @see com.mycompany.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{

    private Properties properties;

    /**
     * Get Application for current thread.
     * 
     * @return The current thread's Application
     */
    public static WicketApplication get() {
        return (WicketApplication)Application.get();
    }

    /**
     * Constructor
     */
    public WicketApplication() {
        super();

        properties = loadProperties();
        setConfigurationType(RuntimeConfigurationType.valueOf(properties.getProperty("configuration.type")));

    }

    /**
     * @see org.apache.wicket.Application#init()
     */
    @Override
    public void init() {
        super.init();

        // WicketWebjars.install(this);
        // WicketWebjars.registerCollector(new VfsJarAssetPathCollector());

        // deactivate ajax debug mode
        getDebugSettings().setAjaxDebugModeEnabled(false);

        setHeaderResponseDecorator(new RenderJavaScriptToFooterHeaderResponseDecorator("footer-bucket"));

        configureBootstrap();
        configureResourceBundles();

        optimizeForWebPerformance();

        // BootstrapSettings settings = new BootstrapSettings();
        // Bootstrap.install(Application.get(), settings);

        // System.out.println(this.getConfigurationType());

        // add your configuration here
    }

    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
    @Override
    public Class<? extends WebPage> getHomePage() {
        return HomePage.class;
    }

    /**
     * optimize wicket for a better web performance
     */
    private void optimizeForWebPerformance() {
        if (usesDeploymentConfig()) {
            getResourceSettings().setCachingStrategy(new FilenameWithVersionResourceCachingStrategy("-v-", new CachingResourceVersion(new Adler32ResourceVersion())));

            getResourceSettings().setJavaScriptCompressor(new GoogleClosureJavaScriptCompressor(CompilationLevel.SIMPLE_OPTIMIZATIONS));
            getResourceSettings().setCssCompressor(new YuiCssCompressor());

            getFrameworkSettings().setSerializer(new DeflatedJavaSerializer(getApplicationKey()));
        } else {
            getResourceSettings().setCachingStrategy(new NoOpResourceCachingStrategy());
        }

        setHeaderResponseDecorator(new RenderJavaScriptToFooterHeaderResponseDecorator());
        getRequestCycleSettings().setRenderStrategy(IRequestCycleSettings.RenderStrategy.ONE_PASS_RENDER);
    }

    /**
     * configure all resource bundles (css and js)
     */
    private void configureResourceBundles() {

        getResourceBundles().addJavaScriptBundle(WicketApplication.class, "core.js", (JavaScriptResourceReference)getJavaScriptLibrarySettings().getJQueryReference(),
                        (JavaScriptResourceReference)getJavaScriptLibrarySettings().getWicketEventReference(), (JavaScriptResourceReference)getJavaScriptLibrarySettings().getWicketAjaxReference(),
                        (JavaScriptResourceReference)ModernizrJavaScriptReference.INSTANCE);

        // getResourceBundles().addJavaScriptBundle(WicketApplication.class,
        // "bootstrap.js",
        // (JavaScriptResourceReference)Bootstrap.getSettings().getJsResourceReference(),
        // (JavaScriptResourceReference)BootstrapPrettifyJavaScriptReference.INSTANCE,
        // ApplicationJavaScript.INSTANCE);

        // 0.9.0-SNAPSHOT
        // getResourceBundles().addJavaScriptBundle(WicketApplication.class,
        // "bootstrap-extensions.js",
        // JQueryUICoreJavaScriptReference.instance(),
        // JQueryUIWidgetJavaScriptReference.instance(),
        // JQueryUIMouseJavaScriptReference.instance(),
        // JQueryUIDraggableJavaScriptReference.instance(),
        // JQueryUIResizableJavaScriptReference.instance(),
        // Html5PlayerJavaScriptReference.instance());

        // getResourceBundles().addCssBundle(WicketApplication.class,
        // "bootstrap-extensions.css", Html5PlayerCssReference.instance(),
        // OpenWebIconsCssReference.instance());

        // getResourceBundles().addCssBundle(WicketApplication.class,
        // "application.css",
        // (CssResourceReference)BootstrapPrettifyCssReference.INSTANCE,
        // FixBootstrapStylesCssResourceReference.INSTANCE);
    }

    /**
     * configures wicket-bootstrap and installs the settings.
     */
    private void configureBootstrap() {
        final ThemeProvider themeProvider = new BootswatchThemeProvider()
        {
            {
                add(new MetroTheme());
                add(new GoogleTheme());
                add(new WicketTheme());
                // defaultTheme("wicket");
                // defaultTheme("google");
                defaultTheme("metro");
            }
        };

        final BootstrapSettings settings = new BootstrapSettings();

        settings.setJsResourceFilterName("footer-container").setThemeProvider(themeProvider);
        System.out.println("default Theme: " + settings.getThemeProvider().defaultTheme());

        Bootstrap.install(this, settings);

        BootstrapLess.install(this);
    }

    /**
     * @return used configuration properties
     */
    public Properties getProperties() {
        return properties;
    }

    /**
     * loads all configuration properties from disk
     * 
     * @return configuration properties
     */
    private Properties loadProperties() {
        Properties properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream("/config.properties"));
        } catch (IOException e) {
            throw new WicketRuntimeException(e);
        }
        return properties;
    }

}
