package com.project.planner.configuration;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@AllArgsConstructor // Lombok
@Configuration  // SpringFramework
@EnableWebSecurity  // security
public class AppConfig {

    @Bean   // SpringFramework
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // 모든 경로는 인증 없이 허용, 이외 모든 요청은 로그인이 필요합니다.
        http.authorizeRequests().antMatchers("/**").permitAll().anyRequest().authenticated();
        // 로그인폼의 위치는 "/login"이고 로그인 성공시 "/"로 이동합니다.
        http.formLogin().loginPage("/login").defaultSuccessUrl("/");
        // 로그아웃 시 "/"로 이동합니다.
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/");
        // 다중 중복 로그인
        http.sessionManagement().maximumSessions(5).maxSessionsPreventsLogin(false);
        // 로그인 성공시 세션 보호
        http.sessionManagement().sessionFixation().changeSessionId();
        // (임시) csrf 동장되면 post를 할때 같이 보내줘야 이동이 된다.
        // post 요청 시 <input type="hidden" name="_csrf" value="{{_csrf.token}}" />
        // ajax 요청 시 <meta name="_csrf" content="{{_csrf.token}}" /><meta name="_csrf_header" content="{{_csrf.headerName}}" />
//        http.csrf().disable();

        return http.build();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
