package de.fourzerofournotfound.rateyourstuff.rays.core;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@Import(org.springdoc.data.rest.SpringDocDataRestConfiguration.class)
public class SpringFoxConfiguration {
}
