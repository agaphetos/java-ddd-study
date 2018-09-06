package com.khakiout.study.ddddemo.interfaces.http.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.contentType;

import com.khakiout.study.ddddemo.interfaces.http.controller.user.UserController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class UserRouter {

    @Bean
    public RouterFunction<ServerResponse> route(UserController handler) {

        return RouterFunctions.route(GET("/users/{id}").and(accept(APPLICATION_JSON)), handler::get)
            .andRoute(GET("/users").and(accept(APPLICATION_JSON)), handler::list)
            .andRoute(POST("/users").and(accept(APPLICATION_JSON)).and(contentType(APPLICATION_JSON)), handler::create)
            .andRoute(PUT("/users/{id}").and(accept(APPLICATION_JSON)).and(contentType(APPLICATION_JSON)), handler::update)
            .andRoute(DELETE("/users/{id}"), handler::delete);


    }

}