package ants.admin.config;

import ants.admin.interceptor.AuthInterceptor;
import org.omg.PortableInterceptor.Interceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * Created by richard on 15/04/2019.
 */
@EnableWebMvc
@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/f/**").addResourceLocations("file:/data/file/");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }

    /**
     * 注册拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
            InterceptorRegistration registration = registry.addInterceptor(new AuthInterceptor());
            registration.addPathPatterns("/**")     //所有路径都被拦截
                .excludePathPatterns("/login", "/login_index", "/static","/**/*.html","/**/*.js", "/**/*.css","/**/*.woff");
        }
}
