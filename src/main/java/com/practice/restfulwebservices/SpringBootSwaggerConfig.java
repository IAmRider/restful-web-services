package com.practice.restfulwebservices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringBootSwaggerConfig {

	public static final Contact DEFAULT_CONTACT = new Contact("Suraj Dhembare", "www.userresourceportal.com",
			"surajdhembre8@gmail.com");
	public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("UserResource Documentation",
			"UserResource Documentation", "1.0", "urn:tos", DEFAULT_CONTACT, "User Portal 2.0",
			"http://www.praciserestproject.org/licenses/LICENSE-2.0");
	private static final Set<String> DEFAULT_PRODUCE_CONSUME = new HashSet<String>(
			Arrays.asList("application/json", "application/xml"));

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(DEFAULT_API_INFO).produces(DEFAULT_PRODUCE_CONSUME)
				.select().apis(RequestHandlerSelectors.basePackage("com.practice.restfulwebservices.restcontroller"))
				.build();

	}

	@Primary
	@Bean
	public LinkDiscoverers discoverers() {
		List<LinkDiscoverer> plugins = new ArrayList<>();
		plugins.add(new CollectionJsonLinkDiscoverer());
		return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
	}

}
