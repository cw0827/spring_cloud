package com.caiw.spring_cloud_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.RewritePathGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.route.builder.UriSpec;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class SpringCloudGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        RewritePathGatewayFilterFactory.Config config = new RewritePathGatewayFilterFactory.Config();
        config.setRegexp("/api-b/(?<segment>.*)");
        config.setReplacement("/${segment}");
        GatewayFilter filter = new RewritePathGatewayFilterFactory()
                .apply(config);

        Function<GatewayFilterSpec, UriSpec> fn = gatewayFilterSpec -> gatewayFilterSpec.filter(filter);

        return builder.routes()
                //basic proxy
                .route(r -> r.path("/api-b/**")
                        .filters(fn)
                        .uri("lb://service-feign")
                ).build();
    }

//    @Bean
//    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
//        RewritePathGatewayFilterFactory.Config config = new RewritePathGatewayFilterFactory.Config();
//        config.setRegexp("/api-a/(?<segment>.*)");
//        config.setReplacement("/${segment}");
//        GatewayFilter filter = new RewritePathGatewayFilterFactory()
//                .apply(config);
//
//        Function<GatewayFilterSpec, UriSpec> fn = gatewayFilterSpec -> gatewayFilterSpec.filter(filter);
//
//        return builder.routes()
//                //basic proxy
//                .route(r -> r.path("/api-a/**")
//                        .filters(fn)
//                        .uri("lb://service-ribbon")
//                ).build();
//    }

}
