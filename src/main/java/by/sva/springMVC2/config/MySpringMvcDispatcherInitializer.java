package by.sva.springMVC2.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MySpringMvcDispatcherInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		
		return new Class[] {SpringConfig.class}; // Spring конфигурация
	}

	@Override
	protected String[] getServletMappings() {

		return new String[] {"/"}; // принимать все запросы от пользователя
	}

}
