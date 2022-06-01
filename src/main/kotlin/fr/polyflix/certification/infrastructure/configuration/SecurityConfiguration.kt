package fr.polyflix.certification.infrastructure.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
class SecurityConfiguration: WebSecurityConfigurerAdapter() {
    override fun configure(web: HttpSecurity) {
        http.authorizeRequests {
            it
                .antMatchers("/actuator/**")
                .permitAll()
                .anyRequest()
                .authenticated()
        }.oauth2ResourceServer { it.jwt() }
    }
}
