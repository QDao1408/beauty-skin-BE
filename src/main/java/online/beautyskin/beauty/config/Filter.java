package online.beautyskin.beauty.config;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import online.beautyskin.beauty.entity.User;
import online.beautyskin.beauty.exception.AuthException;
import online.beautyskin.beauty.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class Filter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Autowired
    @Qualifier("handlerExceptionResolver")
    HandlerExceptionResolver resolver;

List<String> PUBLIC_API = List.of(
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/api/login",
            "/api/register",
            //USER
            "/api/get",
            "/api/user/update",
            //BLOG
            "/api/blog/edit",
            "/api/blog/getByDeleteIsFalse",
            "/api/blog/create",
            "/api/blog/delete",
            "/api/blog/get"
    );

    boolean isPermitted(HttpServletRequest request) {
        AntPathMatcher matcher = new AntPathMatcher();
        String uri = request.getRequestURI();
        String method = request.getMethod();
        if(method.equals("GET") && matcher.match("/api/product/**", uri)) {
            return true;
        }
        return PUBLIC_API.stream().anyMatch(m -> matcher.match(m, uri));
    }


//    public boolean checkIsPublic(String uri){
//        // uri: /api/register
//        // nếu gặp những api trong list ở trên => cho phép truy cập luôn => true
//        AntPathMatcher patchMatch = new AntPathMatcher();
//        // check token => false
//        return  publicAPI.stream().anyMatch(pattern -> patchMatch.match(pattern,uri));
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //check xem api nguoi dung yeu cau co phai la 1 public api?
        //boolean isPublicAPI = checkIsPublic(request.getRequestURI());
        String uri = request.getRequestURI();
        if (isPermitted(request)) {
            filterChain.doFilter(request,response);
        }else{
            String token = getToken(request);
            if (token == null){
                //ko duoc phep truy cap
                resolver.resolveException(request,response,null, new AuthException("Empty token!"));
                return;
            }

            // => co token
            //check xem token co dung hay ko => lay thong tin account tu token
            User user = new User();
            try {
                user = tokenService.getUserByToken(token);
            }catch (ExpiredJwtException e){
                //response token het han
                resolver.resolveException(request,response,null, new AuthException("Expired token!"));
                return;
            }catch (MalformedJwtException malformedJwtException){
                //response token sai
                resolver.resolveException(request,response,null, new AuthException("Invalid token!"));
                return;
            }

            // => token chuan cho phep truy cap
            // => luu lai thong tin account
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    user
                    ,token
                    ,user.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            //token ok, cho vao
            filterChain.doFilter(request,response);
        }

    }

    public String getToken(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.substring(7);
    }

}
