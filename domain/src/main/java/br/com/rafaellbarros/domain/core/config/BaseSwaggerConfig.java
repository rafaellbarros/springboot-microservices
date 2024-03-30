package java.br.com.rafaellbarros.domain.core.config;

import java.br.com.rafaellbarros.domain.core.config.properties.AppProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static org.apache.commons.lang.StringUtils.isNotBlank;

/**
 * created by:
 *
 * @author rafael barros for DevDusCorre <rafaelbarros.softwareengineer@gmail.com> on 29/03/2024
 */

public class BaseSwaggerConfig {
    private static final Logger LOG = LoggerFactory.getLogger(BaseSwaggerConfig.class);
    private final AppProperties.Documentation properties;
    public BaseSwaggerConfig(final AppProperties.Documentation properties) {
        this.properties = properties;
    }

    @SuppressWarnings("PMD.AppendCharacterWithChar")
    private ApiInfo apiInfo() {
        final StringBuilder version = new StringBuilder();
        version.append(this.properties.getVersion());
        final String env = System.getenv("ENV");
        if (StringUtils.isNotBlank(env)) {
            version.append("-").append(env);
        }

        final String buildNumber = System.getenv("BUILD_NUMBER");
        if (StringUtils.isNotBlank(buildNumber)) {
            version.append(".").append(buildNumber);
        }

        LOG.info(String.format("Info do Swagger: %s@%s", this.properties.getTitle(), version));
        return (new ApiInfoBuilder())
                .title(this.properties.getTitle())
                .description(this.properties.getDescription())
                .version(version.toString())
                .contact(new Contact("Rafael Barros Silva", "https://github.com/rafaellbarros", "rafaelbarros.softwareengineer@gmail.com"))
                .license(" MIT License (MIT)  stuff bro, belongs to DevDusCorre")
                .licenseUrl("https://github.com/rafaellbarros")
                .build();
    }

    @Bean
    public Docket customImplementation() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .build()
                .apiInfo(apiInfo());
    }
}
