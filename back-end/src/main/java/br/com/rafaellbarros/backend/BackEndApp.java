package br.com.rafaellbarros.backend;

import br.com.rafaellbarros.domain.core.property.JwtConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@EntityScan({"br.com.rafaellbarros.domain.model"})
@EnableJpaRepositories({"br.com.rafaellbarros.domain.repository"})
@EnableConfigurationProperties(value = JwtConfiguration.class)
public class BackEndApplication {

    private static final String SWAGGER_UI_HTML = "/swagger-ui.html";
    private static final String SERVER_PORT = "server.port";
    private static final String SERVER_SERVLET_CONTEXT_PATH = "server.servlet.contextPath";
    private static final Logger LOG = LoggerFactory.getLogger(BackEndApplication.class);


    public static void main(final String[] args) {
        final ConfigurableApplicationContext ctx = SpringApplication
                .run(BackEndApplication.class, args);
        try {
            final Environment env = ctx.getEnvironment();
            LOG.info("\n\n *** \n\n" + "\tAplicacao {} iniciada com sucesso!\n" + "\tDisponivel nos enderecos:\n"
                            + "\tLocal: http://localhost:{}\n" + "\tExterno: http://{}:{}\n"
                            + "\tSwagger Url: http://{}:{}\n"
                            + "\tLocal Swagger Url: http://localhost:{}\n" + "\n *** \n\n",
                    env.getProperty("spring.application.name"),
                    env.getProperty(SERVER_PORT) + env.getProperty(SERVER_SERVLET_CONTEXT_PATH),
                    InetAddress.getLocalHost().getHostAddress(),
                    env.getProperty(SERVER_PORT) + env.getProperty(SERVER_SERVLET_CONTEXT_PATH),
                    InetAddress.getLocalHost().getHostAddress(),
                    env.getProperty(SERVER_PORT) + env.getProperty(SERVER_SERVLET_CONTEXT_PATH) + SWAGGER_UI_HTML,
                    env.getProperty(SERVER_PORT) + env.getProperty(SERVER_SERVLET_CONTEXT_PATH)
                            + SWAGGER_UI_HTML);
        } catch (final UnknownHostException e) {
            LOG.error("Falha ao executar aplicacao: {}", e);
            ctx.close();
        }
    }

}
