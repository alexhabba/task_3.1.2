package web.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import web.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {

        User user = (User) authentication.getPrincipal();
        System.out.println(user);
        if (user.getRoles().stream().anyMatch(role -> role.getAuthority().equals("ADMIN"))) {
            httpServletResponse.sendRedirect("/admin/" + 0);
        } else {
            httpServletResponse.sendRedirect("/user");
        }
    }
}