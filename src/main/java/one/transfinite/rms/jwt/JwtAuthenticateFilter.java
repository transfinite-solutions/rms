package one.transfinite.rms.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import one.transfinite.rms.execption.ApiBadRequestException;
import one.transfinite.rms.user.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@CrossOrigin("*")
@Component
public class JwtAuthenticateFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        System.out.println(httpServletRequest.getServletPath());
        if(httpServletRequest.getServletPath().equals("/authenticate")
            || httpServletRequest.getServletPath().equals("/register")) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } else {
//            final String requestHeaderToken = httpServletRequest.getHeader("Authorization");
//            System.out.println(requestHeaderToken);
//            String token = null;
//            String username = null;
//
//            if (requestHeaderToken != null && requestHeaderToken.startsWith("BEARER ")) {
//                token = requestHeaderToken.substring(7);
//
//                try {
//                    username = jwtUtils.extractUsername(token);
//                } catch (ExpiredJwtException e) {
//                    e.printStackTrace();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//            else {
//                throw new ApiBadRequestException("Invalid Token");
//            }
//
//            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//
//                UserDetails userDetails = this.userDetailService.loadUserByUsername(username);
//
//                if (jwtUtils.validateToken(token, userDetails)) {
//                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
//
//                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//                }
//            }
//            else {
//                throw new ApiBadRequestException("Invalid Token");
//            }
//
//            filterChain.doFilter(httpServletRequest, httpServletResponse);

            final String requestTokenHeader = httpServletRequest.getHeader("Authorization");
            System.out.println(requestTokenHeader);

            String username = null;
            String jwtToken = null;

                if(requestTokenHeader!=null && requestTokenHeader.startsWith("BEARER ")){
                jwtToken = requestTokenHeader.substring(7);
                try {
                    username = this.jwtUtils.extractUsername(jwtToken);
                }catch (ExpiredJwtException e){
                    e.printStackTrace();
                    System.out.println("token expired");
                }catch (Exception e){
                    e.printStackTrace();
                    System.out.println("Error");
                }
            }else{
                System.out.println("Invalid token");
            }

            //Validate
            if(username!=null && SecurityContextHolder.getContext().getAuthentication() == null){

                final UserDetails userDetails = this.userDetailService.loadUserByUsername(username);

                if(this.jwtUtils.validateToken(jwtToken,userDetails)){
                    //token valid

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }else {
                System.out.println("Token is not valid");
            }

            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }
}
