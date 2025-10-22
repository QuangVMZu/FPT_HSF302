package sum25.SE192755.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Bắt buộc đăng nhập cho các trang quản trị
        registry.addInterceptor(new AuthInterceptor())
                .addPathPatterns("/home", "/products/**", "/categories/**")
                .excludePathPatterns("/login", "/", "/403", "/css/**", "/js/**", "/images/**");

        // Chỉ admin mới được tạo/sửa/xóa category (ví dụ: danh sách thì ai cũng xem được)
        registry.addInterceptor(new AdminInterceptor())
                .addPathPatterns("/categories/create", "/categories/edit/**", "/categories/delete/**");
        // Nếu muốn admin cho toàn bộ /categories/**
        // .addPathPatterns("/categories/**");
    }

    // Tùy chọn: nếu có static resources
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**","/js/**","/images/**")
                .addResourceLocations("classpath:/static/css/","classpath:/static/js/","classpath:/static/images/");
    }
}
