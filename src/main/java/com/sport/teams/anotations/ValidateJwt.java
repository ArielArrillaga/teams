package com.sport.teams.anotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)

public @interface ValidateJwt {
//Sirve para indicar que metodos deben validar el jwt antes de ejecutarse.
//cada vez que se agregue esta anotacion se llamara al aspect JwtValidationAspect al metodo validateJwt.
}
