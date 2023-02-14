package techsuppDev.techsupp.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration // spring의 환경설정 파일
@EnableWebSecurity // 모든 요청 url이 스프링 시큐리티의 제어를 받도록/ spring security 필터가 spring filter chain에 등록이 된다.
@RequiredArgsConstructor
public class SecurityConfig {
    @Bean
    // 모든 인증되지 않은 요청을 허락한다. 로그인을 하지 않더라도 모든 페이지에 접근 가능
    // 이후 admin 페이지에 대한 접속 제한 설정
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests().requestMatchers(
                new AntPathRequestMatcher("/**")).permitAll();
        http.cors().and();
        http.csrf().disable();
        return http.build();
    }

    // 객체 직접 생성보다는 PasswordEncoder bean등록
    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}


