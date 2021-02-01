package de.smotastic.microservices.apigateway;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.core.annotation.AliasFor;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@SpringBootTest
@AutoConfigureWireMock
@AutoConfigureMockMvc
public @interface SpringCloudTest {
	@AliasFor(annotation = AutoConfigureWireMock.class, attribute = "port")
	int port() default 8888;
}
