package org.nju.sesta.sams.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.nju.sesta.sams.exception.AuthenticationException;
import org.nju.sesta.sams.parameter.authentication.JwtAuthenticationParameter;
import org.nju.sesta.sams.response.authentication.JwtAuthenticationResponse;
import org.nju.sesta.sams.security.JwtUser;
import org.nju.sesta.sams.util.token.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@RestController() //RestController本身包含ResponseBody注解
//@RequestMapping(value = "/login")
public class AuthenticationController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtToken jwtToken;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    DefaultKaptcha defaultKaptcha;

    @GetMapping(value = "/captcha")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");//内容类型设为图片
        response.setHeader("Pragma", "no-cache");//禁止缓存
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        HttpSession session = request.getSession();//验证码保存在session里
        String text = defaultKaptcha.createText();
        session.setAttribute("captchaCode", text);
        ImageIO.write(defaultKaptcha.createImage(text), "png", response.getOutputStream());
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationParameter parameter, HttpServletRequest request)
            throws AuthenticationException {

        HttpSession session = request.getSession();
        validateCaptcha(parameter.getCaptcha(), (String) session.getAttribute("captchaCode"));
        authenticate(parameter.getUsername(), parameter.getPassword(), parameter.getCaptcha());

        // Reload password post-security so we can generate the token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(parameter.getUsername());
        final String token = jwtToken.generateToken(userDetails);

        // Return the token
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String authToken = request.getHeader(tokenHeader);
        final String token = authToken.substring(7);
        String username = jwtToken.getUsernameFromToken(token);
        JwtUser jwtUser = (JwtUser) userDetailsService.loadUserByUsername(username);

        if (jwtToken.canTokenBeRefreshed(token, jwtUser.getLastPasswordResetDate().getTime())) {
            String refreshedToken = jwtToken.refreshToken(token);
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }

    /**
     * 认证用户(等于登录). 如果出现错误,  {@link AuthenticationException} 认证异常会被抛出
     */
    private void authenticate(String username, String password, String code) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        Objects.requireNonNull(code);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new AuthenticationException("用户已被禁用", e);
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("密码错误!", e);
        }
    }

    private void validateCaptcha(String code, String captcha) throws AuthenticationException {
        if (!code.equalsIgnoreCase(captcha)) {
            throw new AuthenticationException("验证码错误!");
        }
    }


}

