package com.sar.initialize.config;

import com.sar.initialize.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;
    private final UserService userService;

    public SecurityConfig(DataSource dataSource,
                          UserService userService) {
        this.dataSource = dataSource;
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and().formLogin().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        /*auth.jdbcAuthentication()
                .dataSource(this.dataSource)
                .usersByUsernameQuery("select username, password, email, cell_phone, enabled, locked, expired from sar_user where username=? or email=? or cell_phone=?")
                .authoritiesByUsernameQuery("select su.username, su.email, su.cell_phone, authorities from sar_user su inner join sar_user_roles sur on su.id = sur.user_id inner join role_authorities ra on sur.roles_id = ra.role_id where username=? or email=? or cell_phone=?");*/

        auth.userDetailsService(userService);

    }
}
