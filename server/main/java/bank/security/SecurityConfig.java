package bank.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtConverter converter;

    public SecurityConfig(JwtConverter converter) {
        this.converter = converter;
    }

    @Override
    protected void configure(HttpSecurity http) throws  Exception {
        http.cors().and().csrf().disable();

        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/account", "/api/account/*").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/api/account").hasAnyRole("USER")
                .antMatchers(HttpMethod.GET, "/api/loan", "/api/loan/*").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/api/loan").hasAnyRole("USER")
                .antMatchers(HttpMethod.PUT, "/api/loan").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/loan").hasAnyRole("USER")
                .antMatchers(HttpMethod.GET, "/api/loan/type").hasAnyRole("USER")
                .antMatchers(HttpMethod.GET, "/api/transaction/category").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/api/transaction/category").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/transaction/category").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/transaction/category").hasAnyRole("ADMIN")
                .antMatchers( "/api/transaction").hasAnyRole("USER")
                .antMatchers("/api/user/authenticate").permitAll()
                .antMatchers(HttpMethod.GET,"/api/user").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST,"/api/user").permitAll()
                .and()
                .addFilterBefore(new JwtRequestFilter(authenticationManager(), converter), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000")
                        .allowedMethods("*")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
