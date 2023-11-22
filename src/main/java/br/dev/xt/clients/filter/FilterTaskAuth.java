package br.dev.xt.clients.filter;

import java.io.IOException;
import java.util.Base64;

import br.dev.xt.clients.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var servletPath = request.getServletPath();

        if (servletPath.startsWith("/client")) {
            var authorization = request.getHeader("Authorization");

            var token = authorization.substring(5).trim();

            byte[] tokenDecode = Base64.getDecoder().decode(token);

            var tokenString = new String(tokenDecode);

            String[] credentials = tokenString.split(":");
            String username = credentials[0];
            String password = credentials[1];

            var user = this.userRepository.findByUsername(username);
            if (user == null)
                response.sendError(401, "Acesso não autorizado.");
            else {
                var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                if (passwordVerify.verified) {
                    request.setAttribute("userId", user.getId());
                    filterChain.doFilter(request, response);
                } else {
                    response.sendError(401, "Senha inválida.");
                }
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }
}