package br.usp.suricato.conf;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringMVCServlet extends AbstractAnnotationConfigDispatcherServletInitializer
{

   @Override
   protected Class<?>[] getRootConfigClasses()
   {
      return new Class[] { JPAConfiguration.class, SecurityConfiguration.class, AppWebConfiguration.class};
   }

   @Override
   protected Class<?>[] getServletConfigClasses()
   {
      return null;
   }

   @Override
   protected String[] getServletMappings()
   {
      return new String[] { "/" };
   }

   
   
}
