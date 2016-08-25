package pl.morpheme.bookingsystem.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.format.FormatterRegistry;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import pl.morpheme.bookingsystem.converter.*;
import pl.morpheme.bookingsystem.security.SecurityConfig;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by sylwek on 2016-04-29.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "pl.morpheme.bookingsystem")
@Import({SecurityConfig.class})
@PropertySource(value = { "classpath:email.properties" })
public class AppConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private Environment environment;
    @Autowired
    RoomTypeToRoom roomTypeToRoom;
    @Autowired
    LocalDateConverter localDateConverter;
    @Autowired
    UserNameToUser userNameToUser;
    @Autowired
    RoleToUserProfile roleToUserProfile;

    static final Logger logger = LoggerFactory.getLogger(AppConfig.class);

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setContentType("text/html;charset=UTF-8");
        registry.viewResolver(viewResolver);
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public JavaMailSenderImpl javaMailSenderImpl() {
        final JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
        try {
            mailSenderImpl.setHost(environment.getRequiredProperty("smtp.host"));
            mailSenderImpl.setPort(environment.getRequiredProperty("smtp.port", Integer.class));
            mailSenderImpl.setProtocol(environment.getRequiredProperty("smtp.protocol"));
            mailSenderImpl.setUsername(environment.getRequiredProperty("smtp.username"));
            mailSenderImpl.setPassword(environment.getRequiredProperty("smtp.password"));
        } catch (IllegalStateException ise) {
            logger.error("Ustawienia email.properites są niepoprawne.");
            throw ise;
        }
        Properties javaMailProps = new Properties();
        javaMailProps.put("mail.smtp.auth", true);
        javaMailProps.put("mail.smtp.starttls.enable", true);
        mailSenderImpl.setJavaMailProperties(javaMailProps);
        return mailSenderImpl;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(localDateConverter);
        registry.addConverter(userNameToUser);
        registry.addConverter(roomTypeToRoom);
        registry.addConverter(roleToUserProfile);
    }

    @Bean(name="multipartResolver")
    public CommonsMultipartResolver getResolver() throws IOException {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSizePerFile(5242880);//5MB
        resolver.setMaxInMemorySize(1000000);
        resolver.setDefaultEncoding("UTF-8");
        return resolver;
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
        registry.addResourceHandler("/static/js/**").addResourceLocations("/static/js/");
        registry.addResourceHandler("/fragments/**").addResourceLocations("/fragments/");
    }
}
