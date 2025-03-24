package com.Backend.BookStoreMgt.Config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Grace",
                        email = "gracemuriithi771@gmail.com"
                ),
                description = "API documentation for Bookstore Management App",
                title = "BSMA API",
                version = "1.0",
                license = @License(
                        name = "Licence name",
                        url = "https://github.com/Sonnieta"
                ),
                termsOfService = "Terms of service"
        ),
        security = {
                @SecurityRequirement(
                        name = "basicAuth" // 👈 Change this to Basic Authentication
                )
        }
)
@SecurityScheme(
        name = "basicAuth", // 👈 Change this to Basic Authentication
        description = "Basic Authentication",
        scheme = "basic",
        type = SecuritySchemeType.HTTP,
        in = SecuritySchemeIn.HEADER
)
public class SwaggerConfig {}
