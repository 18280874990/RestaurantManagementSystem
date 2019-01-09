package cn.edu.uestc.rms.intercepter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@Configuration
public class IntercepterConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInIntercepter()).addPathPatterns("/rms/order/**", "/rms/food/**", "/rms/employee/**");
        super.addInterceptors(registry);
    }
}