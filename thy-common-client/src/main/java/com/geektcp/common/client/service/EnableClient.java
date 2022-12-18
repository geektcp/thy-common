package com.geektcp.common.client.service;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ClientService.class})
public @interface EnableClient {

    String H() default "";

    String M() default "";

    String e() default "";

}
