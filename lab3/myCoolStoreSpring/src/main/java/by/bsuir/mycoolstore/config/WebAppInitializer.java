package by.bsuir.mycoolstore.config;

import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * The {@code WebAppInitializer} class initializes the web application and configures the DispatcherServlet.
 */
public class WebAppInitializer implements WebApplicationInitializer {
    /**
     * Configures and initializes the web application.
     *
     * @param servletContext The {@link ServletContext} for the web application.
     */
    @Override
    public void onStartup(ServletContext servletContext) {
        var context = new AnnotationConfigWebApplicationContext();
        context.register(WebMvcConfig.class);

        var dispatcher = servletContext.addServlet("SpringDispatcher", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

        dispatcher.setMultipartConfig(new MultipartConfigElement(
                "/",
                1024 * 1024 * 1024,
                10L * 1024 * 1024 * 1024,
                1024 * 1024
        ));
    }
}
