package spittr.config;

import java.io.IOException;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring3.SpringTemplateEngine;
import org.thymeleaf.spring3.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

@Configuration
@EnableWebMvc
@ComponentScan({ "spittr.web", "spittr.data" })
public class WebConfig extends WebMvcConfigurerAdapter {

	  @Bean
	  public ViewResolver viewResolver(SpringTemplateEngine templateEngine) {
	    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
	    viewResolver.setTemplateEngine(templateEngine);
	    return viewResolver;
	  }
	  @Bean
	  public SpringTemplateEngine templateEngine(TemplateResolver templateResolver) {
	    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	    templateEngine.setTemplateResolver(templateResolver);
	    return templateEngine;
	  }

	  @Bean
	  public TemplateResolver templateResolver() {
	    TemplateResolver templateResolver = new ServletContextTemplateResolver();
	    templateResolver.setPrefix("/WEB-INF/templates/");
	    templateResolver.setSuffix(".html");
	    templateResolver.setTemplateMode("HTML5");
	    return templateResolver;
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