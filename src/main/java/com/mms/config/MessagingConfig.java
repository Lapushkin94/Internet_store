package com.mms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

@Configuration
public class MessagingConfig {

    private String MESSAGE = "Test";

    private String CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
    private String DESTINATION = "jms/queue/myQueue";

    private final Properties properties = new Properties();

    @Lazy
    @Bean
    public ConnectionFactory connectionFactory() throws NamingException {

        Context namingContext = null;
        ConnectionFactory connectionFactory;

        properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        properties.put(Context.PROVIDER_URL, "http-remoting://127.0.0.1:8080");
        properties.put(Context.SECURITY_PRINCIPAL, "lapushkin94");
        properties.put(Context.SECURITY_CREDENTIALS, "ghlqw10Kighlqw10Ki!");
        namingContext = new InitialContext(properties);

        connectionFactory = (ConnectionFactory) namingContext.lookup(CONNECTION_FACTORY);

        return connectionFactory;
    }


    @Lazy
    @Bean
    public JmsTemplate jmsTemplate() throws NamingException {

        JmsTemplate template = new JmsTemplate();
        Context namingContext = null;

        properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        properties.put(Context.PROVIDER_URL, "http-remoting://127.0.0.1:8080");
        properties.put(Context.SECURITY_PRINCIPAL, "lapushkin94");
        properties.put(Context.SECURITY_CREDENTIALS, "ghlqw10Kighlqw10Ki!");
        namingContext = new InitialContext(properties);

        Destination destination = (Destination) namingContext.lookup(DESTINATION);

        template.setConnectionFactory(connectionFactory());
        template.setDefaultDestination(destination);

        return template;
    }


}
