package pl.potoczak.chatroom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import pl.potoczak.chatroom.service.CustomUserDetailsService;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/admin","/admin/*")
                    .hasAuthority("ADMIN_PANEL_PRIVILEGE")
                    .antMatchers("/login", "/register")
                    .not().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .defaultSuccessUrl("/index")
                    .usernameParameter("inputLogin")
                    .passwordParameter("inputPassword")
                    .failureUrl("/login?error=true")
                    .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login")
                    .and()
                .exceptionHandling()
                    .accessDeniedHandler((request, response, accessDeniedException) ->
                            response.sendError(HttpServletResponse.SC_FORBIDDEN))
                    .authenticationEntryPoint((request, response, authException) ->
                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED));
    }

    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}
