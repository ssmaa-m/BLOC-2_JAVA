package be.vinci.services;

import jakarta.json.*;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Instances analyzer. It saves an instance into attribute, from a constructor, and
 * gives a lot of convenient methods to transform this into a JSON object
 * to print the UML diagram.
 */
public class InstancesAnalyzer {

    private Object anInstance;

    public InstancesAnalyzer(Object anInstance) {
        this.anInstance = anInstance;
    }

    /**
     * Create a Json Object with all instance data.
     * Example :
     * {
     * classname: "User",
     * fields: [{}, {}],
     * }
     */
    public JsonObject getFullInfo() {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("classname" , anInstance.getClass().getSimpleName());
        objectBuilder.add("fields", getFields());
        return objectBuilder.build();
    }

    /**
     * Get a field, and create a Json Object with all field data.
     * Example :
     * {
     * name: "firstname",
     * type: "String",
     * value: "Laurent"
     * isStatic: false
     * }
     * If the type is an object, the value will be null
     */
    public JsonObject getField(Field f) {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("name" , f.getName());
        objectBuilder.add("type", f.getType().getSimpleName());
        objectBuilder.add("value", getValue(f));
        objectBuilder.add("isStatic", Modifier.isStatic(f.getModifiers()));
        return objectBuilder.build();
    }

    /**
     * Get fields, and create a Json Array with all fields data.
     * Example :
     * [ {}, {} ]
     */
    public JsonArray getFields() {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        Field[] fields = anInstance.getClass().getDeclaredFields();

        for (Field field : fields) {
            arrayBuilder.add(getField(field));
        }

        return arrayBuilder.build();
    }

    public JsonValue getValue (Field f){
        try {
            // permet de rentre chaque attribut MEME PRIVATE en public
            f.setAccessible(true);
            // retourne la valeur de l'attribut !!
            Object value = f.get(anInstance);

            if(value == null){
                return JsonValue.NULL;
            }

            Class<?> type = f.getType();

            if(type.isPrimitive() || type == String.class){
                return Json.createValue(value.toString());
            }

            return JsonValue.NULL;


        } catch (IllegalArgumentException e){
            return JsonValue.NULL;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }



}
