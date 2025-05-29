package lt.viko.eif.mjurevicius.pi23s.ND3JaxRestWs.configuration;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.UrlPathHelper;

import java.io.IOException;

/**
 * Spring component that removes trailing slashes from incoming requests.
 * It forwards requests with trailing slashes (excluding just "/") to the same path without the slash.
 */
@Component
public class TrailingSlash extends OncePerRequestFilter {
    /**
     * Helper for accessing URL paths of servlet requests.
     */
    private final UrlPathHelper urlPathHelper = new UrlPathHelper();

    /**
     * Intercepts each request to remove trailing slashes.
     * If a path (longer than "/") ends with a slash, it forwards the request to the path without the slash.
     *
     * @param request     The incoming HTTP request.
     * @param response    The outgoing HTTP response.
     * @param filterChain Allows the request to proceed to the next filter or servlet.
     * @throws ServletException If an error occurs during forwarding.
     * @throws IOException      If an I/O error occurs during forwarding.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String path = urlPathHelper.getRequestUri(request);
        if (path.length() > 1 && path.endsWith("/")) {
            String newPath = path.substring(0, path.length() - 1);
            request.getRequestDispatcher(newPath).forward(request, response);
            return;
        }
        filterChain.doFilter(request, response);
    }
}
