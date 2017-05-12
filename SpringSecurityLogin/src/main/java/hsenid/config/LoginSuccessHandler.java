package hsenid.config;/*
package hsenid.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

*/
/**
 * Created by hsenid on 5/9/17.
 *//*

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        handle(httpServletRequest, httpServletResponse, authentication);
        clearAuthenticationAttributes(httpServletRequest);
    }

    protected void handle(HttpServletRequest request,
                          HttpServletResponse response, Authentication authentication)
            throws IOException {

        String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {

            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

   */
/* protected String determineTargetUrl(Authentication authentication) {

        String targetUrl = " ";

        Collection<? extends GrantedAuthority> authorities
                = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            String s = grantedAuthority.getAuthority();
            if (s.equals("USER")) {
                targetUrl = "/userPage";
                //Assign your url here

            } else if (s.equals("ADMIN")) {
                targetUrl = "/admin";
                //Assign your URL here

            } else if (s.equals("DBA")) {
                targetUrl = "/db";
                //Assign your URL here

            }
        }
        return targetUrl;


    }*//*

   protected String determineTargetUrl(final Authentication authentication) {
       String targetUrl = "";
       final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
       for (final GrHTTP Status 405 - Request method 'POST' not supportedantedAuthority grantedAuthority : authorities) {
           switch (grantedAuthority.getAuthority()) {
               case "USER":
                   targetUrl = "/userPage";
                   //Assign your url here
                   break;
               case "ADMIN":
                   targetUrl = "/admin";
                   //Assign your URL here
                   break;
               default:
           }
       }
       return targetUrl;
   }

    */
/*protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
*//*

}
*/


/**
 * Created by hsenid on 5/4/17.
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    protected final Log logger = LogFactory.getLog(this.getClass());

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    protected LoginSuccessHandler() {
        super();
    }

    // API

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws IOException {
        handle(request, response, authentication);
    }

    protected void handle(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws IOException {
        final String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(final Authentication authentication) {
        String targetUrl = "";
        final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (final GrantedAuthority grantedAuthority : authorities) {
            switch (grantedAuthority.getAuthority()) {
                case "USER":
                    targetUrl = "/userPage";
                    //Assign your url here
                    break;
                case "ADMIN":
                    targetUrl = "/admin";
                    //Assign your URL here
                    break;
                default:
            }
        }
        return targetUrl;
    }
}