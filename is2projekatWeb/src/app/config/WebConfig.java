package app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("app")
public class WebConfig extends WebMvcConfigurerAdapter {
	
	@Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        TilesViewResolver viewResolver = new TilesViewResolver();
        registry.viewResolver(viewResolver);
    }


	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}


	@Bean(name = "multipartResolver")
	public MultipartResolver getResolver() {
		CommonsMultipartResolver multipartRes = new CommonsMultipartResolver();
		multipartRes.setMaxUploadSize(2000000);
		return multipartRes;
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource rb = new ReloadableResourceBundleMessageSource();
		rb.addBasenames("classpath:resources/messages");
		return rb;
	}

	@Bean
	public AcceptHeaderLocaleResolver getLocalResolver() {
		AcceptHeaderLocaleResolver lr = new AcceptHeaderLocaleResolver();
		return lr;
	}
	
	@Bean
	public JasperReportsViewResolver getJasperReportsViewResolver() {
	    JasperReportsViewResolver resolver = new JasperReportsViewResolver();
	    resolver.setPrefix("classpath:resources/jasperreports/");
	    resolver.setSuffix(".jasper");
	    resolver.setReportDataKey("datasource");
	    resolver.setViewNames("rpt_*");
	    resolver.setViewClass(JasperReportsMultiFormatView.class);
	    resolver.setOrder(0);
	    return resolver;
	}
	
	@Bean
	public TilesConfigurer tilesConfigurer(){
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions(new String[] {"/views/tiles.xml"});
		tilesConfigurer.setCheckRefresh(true);
		return tilesConfigurer;
	}

}
