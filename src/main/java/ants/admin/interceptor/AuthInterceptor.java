package ants.admin.interceptor;

import ants.admin.auth.UserLogger;
import ants.admin.model.User;
import com.alibaba.dubbo.common.json.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户信息拦截器.
 * @author Richard Liu (liuyongcheng@test.com)
 * @version $Id$
 * @since 2017.09.15
 */
public final class AuthInterceptor implements HandlerInterceptor {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthInterceptor.class);

    /**
     * 在请求处理之前进行调用（Controller调用之前）
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
        LOGGER.debug("something intercepted.");
        final User user = (User)request.getSession().getAttribute("user");
        if (null != user) {
            return true;        //设置为false，拦截器执行到此处将不再继续操作，设置为true：请求将会继续执行后面的操作
        }
        response.sendRedirect(request.getContextPath() + "/login");
        return false;
    }

    /**
     * 在请求处理之后进行调用（Controller调用之前）
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler,
        final ModelAndView modelAndView) throws Exception {
    }

    /**
     * 在整个请求结束和ihou被调用，也就是DispatchServlet 渲染了对应的视图之后执行（主要用于资源清理工作）
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(final HttpServletRequest request,
        final HttpServletResponse response, final Object handler,
        final Exception ex) throws Exception {
    }
}
