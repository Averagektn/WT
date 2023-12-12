package by.bsuir.mycoolstore.config;

import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        var context = new AnnotationConfigWebApplicationContext();
        context.register(WebMvcConfig.class);

        var dispatcher = servletContext.addServlet("SpringDispatcher", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
        // Задание параметров конфигурации для обработки мультипартных запросов
        dispatcher.setMultipartConfig(new MultipartConfigElement(
                "/tmp", // Директория для временного хранения файлов
                1024 * 1024 * 1024, // Максимальный размер загружаемого файла
                10L * 1024 * 1024 * 1024, // Максимальный размер всего запроса
                1024 * 1024 // Пороговый размер
        ));
    }
}
