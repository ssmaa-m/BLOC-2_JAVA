package be.vinci.services;

import jakarta.json.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Arrays;

/**
 * Class analyzer. It saves a class into attribute, from a constructor, and
 * gives a lot of convenient methods to transform this into a JSON object
 * to print the UML diagram.
 */
public class ClassAnalyzer {

    private Class aClass;

    public ClassAnalyzer(Class aClass) {
        this.aClass = aClass;
    }

    /**
     * Create a JSON Object with all the info of the class.
     * @return
     */
    public JsonObject getFullInfo() {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("name", aClass.getSimpleName());
        objectBuilder.add("fields", getFields());
        objectBuilder.add("methods" , getMethods());
        return objectBuilder.build();
    }


    /**
     * From the field descriptor f, create a Json Object with all field data.
     * Example :
     * {
     * name: "firstname",
     * type: "String",
     * visibility : "private"  // public, private, protected, package
     * isStatic: false,
     * }
     * @param f filed descriptor - describe an attribute
     * @return the generated JSON
     */
    public JsonObject getField(Field f) {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("name", f.getName());
        objectBuilder.add("type", f.getType().getSimpleName());
        objectBuilder.add("visibility", getFieldVisibility(f));
        objectBuilder.add("isStatic", isFieldStatic(f));
        return objectBuilder.build();
    }

    /**
     * Get fields, and create a Json Array with all fields data.
     * Example :
     * [ {}, {} ]
     * This method rely on the getField() method to handle each field one by one.
     */
    public JsonArray getFields() {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        Field[] tableauField = aClass.getDeclaredFields();

        for (Field field : tableauField) {
            arrayBuilder.add(getField(field));
        }
        return arrayBuilder.build();
    }

    /**
     * Return whether a field is static or not
     *
     * @param f the field to check
     * @return true if the field is static, false else
     */
    private boolean isFieldStatic(Field f) {
        return Modifier.isStatic(f.getModifiers());
    }

    /**
     * Get field visibility in a string form
     *
     * @param f the field to check
     * @return the visibility (public, private, protected, package)
     */
    private String getFieldVisibility(Field f) {
        if(Modifier.isPublic(f.getModifiers())){
            return "public";
        }else if(Modifier.isPrivate(f.getModifiers())){
            return "private";
        }else if(Modifier.isProtected(f.getModifiers())){
            return "protected";
        }else{
            return "package";
        }
    }


    public JsonObject getMethod(Method M) {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("name", M.getName());
        objectBuilder.add("type", getMethodReturnType(M));
        objectBuilder.add("parameters" , getMethodParameters(M));
        objectBuilder.add("visibility", getMethodVisibility(M));
        objectBuilder.add("isStatic", isMethodStatic(M));
        objectBuilder.add("isAbstract", isAbstract(M));
        return objectBuilder.build();
    }

    public JsonArray getMethods(){
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        Method[] method = aClass.getDeclaredMethods();
        for (Method method1 : method) {
            arrayBuilder.add(getMethod(method1));
        }
        return arrayBuilder.build();
    }

    private boolean isMethodStatic(Method M) {
        return Modifier.isStatic(M.getModifiers());
    }

    private boolean isAbstract (Method m ){
        return Modifier.isAbstract(m.getModifiers());
    }

    private String getMethodVisibility(Method M) {
        if(Modifier.isPublic(M.getModifiers())){
            return "public";
        }else if(Modifier.isPrivate(M.getModifiers())){
            return "private";
        }else if(Modifier.isProtected(M.getModifiers())){
            return "protected";
        }else{
            return "package";
        }
    }

    private JsonValue getMethodReturnType (Method m){
        if(m.getReturnType().equals(void.class)){
            return JsonValue.NULL;
        }
        return Json.createValue(m.getReturnType().getSimpleName());
    }

    private JsonArray getMethodParameters (Method m){
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

        for (Parameter parameter : m.getParameters()) {
            jsonArrayBuilder.add(parameter.getType().getSimpleName());
        }

        return jsonArrayBuilder.build();
    }







}
