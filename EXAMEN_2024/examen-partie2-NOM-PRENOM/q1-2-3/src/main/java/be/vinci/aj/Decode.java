package be.vinci.aj;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Decode {

    public static void main(String[] args) throws Exception {


      Class<?> classe = Class.forName("be.vinci.aj.secret.Secrets");

      Object instanceClasse = classe.getDeclaredConstructor().newInstance();

        for (Field field : classe.getDeclaredFields()) {
            field.setAccessible(true);
            System.out.println(field.get(instanceClasse));
        }

        for (Method method : classe.getDeclaredMethods()) {
            method.setAccessible(true);
            System.out.println(method.invoke(instanceClasse));
        }
    }
}
