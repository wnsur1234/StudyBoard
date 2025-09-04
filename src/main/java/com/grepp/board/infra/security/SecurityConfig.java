package com.grepp.board.infra.security;

import com.grepp.board.app.model.repository.UsersRepository;
import com.grepp.board.infra.CustomUserDetailsService;
import com.grepp.board.infra.emuns.Role;
import com.grepp.board.infra.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // 정적 리소스, 홈 등은 허용
                        .requestMatchers("/", "/css/**", "/images/**", "/js/**").permitAll()
                        .requestMatchers("/login", "/logout","/signup","/map").permitAll() // ✅ 로그인/로그아웃 화면 허용 이거까지 해줘야 하는구나~
                        // 글쓰기 보호 (원하시는 URL로 조정)
                        .requestMatchers("/letter").authenticated()
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form
                        .loginPage("/login")                 // ✅ 우리가 만든 페이지
                        .loginProcessingUrl("/login")        // (기본값) POST /login 으로 인증 처리
                        .usernameParameter("email")     // ✅ 폼 name="email"
                        .passwordParameter("password")  // ✅ 폼 name="password"
                        .defaultSuccessUrl("/", false)       // 직전 URL이 있으면 그곳으로
                        .failureUrl("/login?error")          // 로그인 실패 시
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")   // 성공 메시지 표시용
                );
        // DB 인증용 Provider 연결
        http.authenticationProvider(daoAuthenticationProvider());
        return http.build();
    }

    //왜? → 로그인 흐름을 바로 검증하려면 DB에 한 명은 있어야 합니다.
    @Bean
    CommandLineRunner initUsers(UsersRepository repo, PasswordEncoder encoder) {
        return args -> {
            if (!repo.existsByEmail("jun@test.com")) {
                repo.save(Users.builder()
                        .email("jun@test.com")
                        .password(encoder.encode("1234")) // ✅ 반드시 인코딩
                        .role(Role.USER)
                        .build());
            }
        };
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider p = new DaoAuthenticationProvider();
        p.setUserDetailsService(userDetailsService);
        p.setPasswordEncoder(passwordEncoder());
        return p;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
