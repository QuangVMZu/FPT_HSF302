package sum25.SE192755.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import sum25.SE192755.Entity.SonyAccounts;

public class AuthInterceptor implements HandlerInterceptor {
    public static final String SESSION_KEY = "LoggedInAccount";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        SonyAccounts acc = (session != null) ? (SonyAccounts) session.getAttribute(SESSION_KEY) : null;
        if (acc == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }
        return true;
    }
}
