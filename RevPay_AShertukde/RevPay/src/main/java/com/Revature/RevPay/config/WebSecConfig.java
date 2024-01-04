package com.Revature.RevPay.config;

import com.Revature.RevPay.services.UserAccountsDetailsService;
import com.Revature.RevPay.services.UserAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@CrossOrigin(origins = "http://localhost:4200/", allowCredentials = "true")
public class WebSecConfig {

    @Bean
    protected UserDetailsService userDetailsService()
    {
        return new UserAccountsDetailsService();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        try {
            http
                    .authorizeHttpRequests((authorizeHttpRequests) ->
                            authorizeHttpRequests.requestMatchers("/BusinessAccounts","/custom-logout","email","/GetUserAccounts","/logout-success","/failure","/UserAccounts").permitAll()
                                    .requestMatchers("/user_moneybyusername","/CardsUser","/user_moneybyemail","/user_moneybyphone","/TransactionSend").hasAuthority("USER")
                                    .requestMatchers("/BusinessLoansAll","/CardsBusiness","/BusinessLoan","/BusinessLoanPayment").hasAuthority("BUSINESS")
                                    .requestMatchers("/business_moneybyusername","/TransactionRequest","/GetAllRequests","/GetBusinessAccounts","/GetAllRequestsToOthers","/TransactionResponse","/success").hasAnyAuthority("USER","BUSINESS")
                            ).formLogin((formLogin)->
                            formLogin.loginPage("/login").permitAll().successHandler(authenticationSuccessHandler()).failureUrl("/failure")
                            )
                    .logout((logout)->
                            logout.deleteCookies("remove").invalidateHttpSession(false)
                                    .logoutUrl("/custom-logout")
                                    .logoutSuccessUrl("/logout-success"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(withDefaults());
        return http.build();

    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(){
        return new CustomAuthenticationSuccessHandler();
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
