package com.turkcell.tcell.core.annotations;

import org.springframework.context.annotation.ComponentScan;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ComponentScan(basePackages = {"org.turkcell.tcell.core"}) // ana proje dışarısında ki başka paket taraması amacıyla
public @interface EnableException {
}
