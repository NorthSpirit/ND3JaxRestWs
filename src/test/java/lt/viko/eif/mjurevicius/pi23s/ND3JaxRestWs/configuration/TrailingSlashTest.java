package lt.viko.eif.mjurevicius.pi23s.ND3JaxRestWs.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrailingSlashTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @Mock
    private RequestDispatcher requestDispatcher;

    @InjectMocks
    private TrailingSlash trailingSlash;

    @Test
    void doFilterInternal_withTrailingSlash_shouldForward() throws ServletException, IOException {
        when(request.getRequestURI()).thenReturn("/api/");
        when(request.getRequestDispatcher("/api")).thenReturn(requestDispatcher);

        trailingSlash.doFilterInternal(request, response, filterChain);

        verify(requestDispatcher).forward(request, response);
        verify(filterChain, never()).doFilter(request, response);
    }

    @Test
    void doFilterInternal_withoutTrailingSlash_shouldContinueChain() throws ServletException, IOException {
        when(request.getRequestURI()).thenReturn("/api");

        trailingSlash.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
        verify(requestDispatcher, never()).forward(request, response);
    }

    @Test
    void doFilterInternal_withRootTrailingSlash_shouldContinueChain() throws ServletException, IOException {
        when(request.getRequestURI()).thenReturn("/");

        trailingSlash.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
        verify(requestDispatcher, never()).forward(request, response);
    }

    @Test
    void doFilterInternal_withSingleCharPathTrailingSlash_shouldForward() throws ServletException, IOException {
        when(request.getRequestURI()).thenReturn("/a/");
        when(request.getRequestDispatcher("/a")).thenReturn(requestDispatcher);

        trailingSlash.doFilterInternal(request, response, filterChain);

        verify(requestDispatcher).forward(request, response);
        verify(filterChain, never()).doFilter(request, response);
    }
}