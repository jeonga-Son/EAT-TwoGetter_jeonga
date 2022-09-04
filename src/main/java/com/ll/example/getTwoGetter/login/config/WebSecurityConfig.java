package com.ll.example.getTwoGetter.login.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","/account/**","/css/**","/api/**","/chatApi/**", "/images/**","/board/**", "/post/**", "/chat/**","/boardInfo2","/boardInfo", "/js/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/account/login")
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .permitAll();
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("select username,password,enabled "
                        + "from user "
                        + "where username = ?")
                .authoritiesByUsernameQuery("select u.username,r.name  "
                        + "from user_role ur inner join user u on ur.user_id=u.id "
                        + "inner join role r on ur.role_id = r.id "
                        + "where u.username = ?");
    }
    //    Authentication 로그인
//    Authorization 권한
    @Bean
    public static PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
}


//http
////                .csrf().disable() // csrf를 사용할지 여부
//        .authorizeRequests() // HttpServletRequest에 따라접근을 제한
//        .antMatchers("/admin/**").hasRole("ADMIN") // url에 따른 권한만 허용
//        .antMatchers("/info").hasRole("MEMBER") // url에 따른 권한만 허용
//        .antMatchers("/**").permitAll() // url에 따른 모두 허용
//        .and()
//        .formLogin() // form 기반 로그인 인증 관련하며 HttpSession 이용
//        .loginPage("/login") // 지정하고 싶은 로그인 페이지 url
//        .usernameParameter("email") // 지정하고 싶은 username name 명칭이며, 기본은 username
//        .passwordParameter("upwd") // 지정하고 싶은 password name 명칭이며, 기본은 password
//        .defaultSuccessUrl("/login/result") // 로그인 성공 시 이동페이지
//        .permitAll() // 모두 접근 허용
//        .and()
//        .csrf() // csrf 사용
//        .ignoringAntMatchers("/h2-console/**") // csrf 제외 url
//        .ignoringAntMatchers("/post/**") // csrf 제외 url
//        .ignoringAntMatchers("/admin/**") // csrf 제외 url
//        .and()
//        .logout() // 로그아웃
//        .logoutRequestMatcher(new AntPathRequestMatcher(("/logout"))) // 지정하고 싶은 로그아웃 url
//        .logoutSuccessUrl("/index") // 성공 시 이동 페이지
//        .invalidateHttpSession(true) // 세션 초기화
////              .deleteCookies("cookies") // 특정 쿠키를 제거
//        .and()
//        .exceptionHandling() // 에러 처리
//        .accessDeniedPage("/error"); // 에러 시 이동할 페이지