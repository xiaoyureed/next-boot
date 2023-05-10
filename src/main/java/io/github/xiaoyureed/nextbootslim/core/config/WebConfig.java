package io.github.xiaoyureed.nextbootslim.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.github.xiaoyureed.nextbootslim.core.util.FileNameUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    /**
     * To serve the nextjs static resources directly
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }

    /**
     * 为静态页面的路由自动补全 .html 然后重定向 (nextjs在打包进 springboot 后, 作为springboot 里的静态资源, 整个应用只认识 xxx.html. 当然如果前后分离开发时, nextjs 的服务器是认识 xxx的)
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                boolean isApiHandle = handler instanceof HandlerMethod;
                String servletPath = request.getServletPath();

                if (!isApiHandle && !"/".contentEquals(servletPath) && FileNameUtils.getExtension(servletPath).isEmpty()) {
                    request.getRequestDispatcher(servletPath + ".html").forward(request, response);
                    return false;
                }

                return true;
            }
        });
    }
}
