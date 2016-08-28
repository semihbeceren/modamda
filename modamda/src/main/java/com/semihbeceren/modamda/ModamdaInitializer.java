/*
 * 
 */
package com.semihbeceren.modamda;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

import com.github.dandelion.core.web.DandelionFilter;
import com.github.dandelion.core.web.DandelionServlet;
import com.github.dandelion.datatables.core.web.filter.DatatablesFilter;

/**
 * @author semihbeceren
 *
 */
public class ModamdaInitializer extends AbstractDispatcherServletInitializer {

	

    private static final String SPRING_PROFILE = "jpa";

    private static final String DANDELION_SERVLET = "dandelionServlet";

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        registerDandelionServlet(servletContext);
    }

    @Override
    protected WebApplicationContext createRootApplicationContext() {
        XmlWebApplicationContext rootAppContext = new XmlWebApplicationContext();
        rootAppContext.setConfigLocations("classpath:spring/business-config.xml", "classpath:spring/tools-config.xml");
        rootAppContext.getEnvironment().setActiveProfiles(SPRING_PROFILE);
        return rootAppContext;
    }


    @Override
    protected WebApplicationContext createServletApplicationContext() {
        XmlWebApplicationContext webAppContext = new XmlWebApplicationContext();
        webAppContext.setConfigLocation("classpath:spring/mvc-core-config.xml");
        return webAppContext;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        // Used to provide the ability to enter Chinese characters inside the Owner Form
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter("UTF-8", true);

        // Dandelion filter definition and mapping -->
        DandelionFilter dandelionFilter = new DandelionFilter();

        // Dandelion-Datatables filter, used for basic export -->
        DatatablesFilter datatablesFilter = new DatatablesFilter();

        return new Filter[]{characterEncodingFilter, dandelionFilter, datatablesFilter};
    }

    @Override
    protected FilterRegistration.Dynamic registerServletFilter(ServletContext servletContext, Filter filter) {
        FilterRegistration.Dynamic registration = super.registerServletFilter(servletContext, filter);
        registration.addMappingForServletNames(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE), false, DANDELION_SERVLET);
        return registration;
    }

    private void registerDandelionServlet(ServletContext servletContext) {
        DandelionServlet dandelionServlet = new DandelionServlet();
        ServletRegistration.Dynamic registration = servletContext.addServlet(DANDELION_SERVLET, dandelionServlet);
        registration.setLoadOnStartup(2);
        registration.addMapping("/dandelion-assets/*");
    }
	
}
