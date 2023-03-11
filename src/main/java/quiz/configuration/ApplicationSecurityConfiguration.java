package quiz.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ApplicationSecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.cors().and().csrf().disable();
        return http.authorizeHttpRequests()
                .antMatchers("/new_question", "/delete/**")
                .authenticated()
                .antMatchers(HttpMethod.POST, "/edit/**").authenticated()
                .and().formLogin().loginPage("/account/login")
                .loginProcessingUrl("/account/login")
                .defaultSuccessUrl("/edit", true)
                .usernameParameter("email")
                .permitAll()
                .and().logout()
                .logoutUrl("/account/logout")
                .and().build();
    }
}
