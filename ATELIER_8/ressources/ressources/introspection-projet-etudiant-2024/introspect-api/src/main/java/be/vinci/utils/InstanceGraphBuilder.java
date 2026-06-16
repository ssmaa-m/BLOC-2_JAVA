package be.vinci.utils;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // pour pouvoir utilise isAnnotationPresent ()
@Target(ElementType.METHOD) // interdit de la mettre autre pars que dans une methode
public @interface InstanceGraphBuilder {
}
