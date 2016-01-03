package spittr.config;

import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@Configuration
@EnableWebMvc
@ComponentScan({ "spittr.web", "spittr.data" })
@EnableTransactionManagement
public class WebConfig extends WebMvcConfigurerAdapter implements ServletContextAware {

	private ServletContext servletContext;
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		
	}
	  @Bean
	    public ServletContextTemplateResolver templateResolver() {
	        ServletContextTemplateResolver resolver = new ServletContextTemplateResolver(servletContext);
	        resolver.setPrefix("/WEB-INF/templates/");
	        resolver.setSuffix(".html");
	        resolver.setTemplateMode("HTML5");
	        resolver.setCacheable(false);
	        return resolver;
	    }

	    @Bean
	    public SpringTemplateEngine templateEngine() {
	        SpringTemplateEngine engine = new SpringTemplateEngine();
	        engine.setTemplateResolver(templateResolver());
	        engine.addDialect(new SpringSecurityDialect());
	        return engine;
	    }

	    @Bean
	    public ThymeleafViewResolver thymeleafViewResolver() {
	        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
	        resolver.setTemplateEngine(templateEngine());
	        return resolver;
	    }
	/*
	 * @Bean public ViewResolver viewResolver() { InternalResourceViewResolver
	 * resolver = new InternalResourceViewResolver();
	 * resolver.setPrefix("/WEB-INF/views/"); resolver.setSuffix(".jsp");
	 * resolver.setExposeContextBeansAsAttributes(true); // poniższe ustawienie
	 * powoduje produkcję widoków JstlView - // przekazywanie locale i źródła
	 * komunikatów skonf. w Springu
	 * resolver.setViewClass(org.springframework.web.servlet.view.JstlView.class
	 * ); return resolver; }
	 */

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}
	@Bean
	public MultipartResolver multipartResolver() throws IOException {
	return new StandardServletMultipartResolver();
	}

	
}