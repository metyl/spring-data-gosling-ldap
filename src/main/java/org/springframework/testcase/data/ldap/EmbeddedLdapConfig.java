package org.springframework.testcase.data.ldap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.test.EmbeddedLdapServerFactoryBean;
import org.springframework.ldap.test.LdifPopulator;

@Configuration
public class EmbeddedLdapConfig {

	@Bean
	public EmbeddedLdapServerFactoryBean embeddedLdap() {
		EmbeddedLdapServerFactoryBean server = new EmbeddedLdapServerFactoryBean();
		server.setPartitionName("test");
		server.setPartitionSuffix("dc=example,dc=com");
		server.setPort(18880);
		return server;
	}

	@Bean
	public LdifPopulator ldif(ContextSource contextSource) throws Exception {
		LdifPopulator ldif = new LdifPopulator();
		ldif.setResource(new ClassPathResource("users.ldif"));
		ldif.setBase("dc=example,dc=com");
		ldif.setClean(true);
		ldif.setDefaultBase("dc=example,dc=com");
		ldif.setContextSource(contextSource);
		return ldif;
	}
}
