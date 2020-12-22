package com.sar.initialize.config;

import com.sar.initialize.service.OAuth2UserService;
import com.sar.initialize.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;
    private final UserService userService;
    private final OAuth2UserService oAuth2UserService;
    private final JwtFilter jwtFilter;

    public SecurityConfig(DataSource dataSource,
                          UserService userService,
                          OAuth2UserService oAuth2UserService,
                          JwtFilter jwtFilter) {
        this.dataSource = dataSource;
        this.userService = userService;
        this.oAuth2UserService = oAuth2UserService;
        this.jwtFilter = jwtFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // JDBC Login
        /*http.csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and().formLogin().disable();*/

        // Login with Google
        /*http.csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and().oauth2Login()
                .authorizationEndpoint().baseUri("/login/oauth2")
                .and().redirectionEndpoint().baseUri("/login/callback")
                .and().userInfoEndpoint().userService(oAuth2UserService);*/

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/jwt/login").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        /*auth.jdbcAuthentication()
                .dataSource(this.dataSource)
                .usersByUsernameQuery("select username, password, email, cell_phone, enabled, locked, expired from sar_user where username=? or email=? or cell_phone=?")
                .authoritiesByUsernameQuery("select su.username, su.email, su.cell_phone, authorities from sar_user su inner join sar_user_roles sur on su.id = sur.user_id inner join role_authorities ra on sur.roles_id = ra.role_id where username=? or email=? or cell_phone=?");*/

        auth.userDetailsService(userService);

    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
