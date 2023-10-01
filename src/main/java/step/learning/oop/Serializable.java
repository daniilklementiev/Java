package step.learning.oop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Serializable {
    // аннотация-маркер, без тела

}
/*
    Аннотации - разновидность интерфейсов, но в отличии от них могут маркировать не только типы (классы), но и их части - поля, методы, конструкторы и т.д.
 */