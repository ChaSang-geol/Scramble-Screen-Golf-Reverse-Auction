package member.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  // @Override
  // public void addCorsMappings(CorsRegistry registry) {
  //   registry.addMapping("/members")
  //       .allowedOrigins("http://localhost:8082");
  // }
  @Bean
  PageableHandlerMethodArgumentResolverCustomizer pageableHandlerMethodArgumentResolverCustomizer() {
      return pageableResolver -> {
          pageableResolver.setSizeParameterName("limit");
          pageableResolver.setPageParameterName("offset");
      };
  }
}