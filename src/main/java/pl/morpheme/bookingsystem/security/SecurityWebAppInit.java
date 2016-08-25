package pl.morpheme.bookingsystem.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.support.MultipartFilter;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;

/**
 * Created by sylwek on 2016-05-24.
 */
public class SecurityWebAppInit extends AbstractSecurityWebApplicationInitializer {

    @Override
    protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
        super.beforeSpringSecurityFilterChain(servletContext);

        /**
         * Bez tego cuda nie udałoby się uploadować żadnego pliku.
         * 6 godzin wycięte z życiorysu :/
         **/
        MultipartFilter mp = new MultipartFilter();
        mp.setMultipartResolverBeanName("multipartResolver");
        insertFilters(servletContext, mp);

        /**
         * Obsługa UTF-8 również musi być narzucona przed filterChain
         **/
        FilterRegistration.Dynamic characterEncodingFilter;
        characterEncodingFilter = servletContext.addFilter("encodingFilter",
                new CharacterEncodingFilter());
        characterEncodingFilter.setInitParameter("encoding", "UTF-8");
        characterEncodingFilter.setInitParameter("forceEncoding", "true");
        characterEncodingFilter.addMappingForUrlPatterns(null, false, "/*");
    }
}