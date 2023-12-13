package by.bsuir.mycoolstore.config;

import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.annotation.MultipartConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * The {@code WebMvcConfig} class provides configuration for the Spring Web MVC framework.
 */
@Configuration
@MultipartConfig
@ComponentScan("by.bsuir")
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * Configures the view resolver for resolving JSP views.
     *
     * @return The configured {@link InternalResourceViewResolver}.
     */
    @Bean(name = "viewResolver")
    public InternalResourceViewResolver getNewResolver() {
        var viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }

    /**
     * Configures resource handlers to serve static resources.
     *
     * @param registry The {@link ResourceHandlerRegistry} to register the resource handlers.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/style/**")
                .addResourceLocations("/style/");
    }

    /**
     * Configures the multipart configuration element for handling file uploads.
     *
     * @return The configured {@link MultipartConfigElement}.
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        return new MultipartConfigElement(
                "/",
                1024L * 1024L * 1024L,
                10L * 1024L * 1024L * 1024L,
                1024 * 1024
        );
    }

    /**
     * Configures the multipart resolver for handling file uploads.
     *
     * @return The configured {@link MultipartResolver}.
     */
    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }
}
