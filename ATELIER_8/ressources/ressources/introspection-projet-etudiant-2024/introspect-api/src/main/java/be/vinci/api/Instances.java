package be.vinci.api;

import be.vinci.services.InstancesAnalyzer;
import be.vinci.utils.InstanceGraphBuilder;
import jakarta.json.JsonStructure;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Send instances graph data to make object diagrams
 *
 * The instances graphs are initialized by a class containing the "initInstanceGraph" method,
 * building the instance graph, and returning it.
 *
 * The "instance builder class name" must be given and present into the "instances" package
 */
@Path("instances")
public class Instances {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonStructure getInstanceGraphInfo(@QueryParam("builderclassname") String builderClassname) {

        if(builderClassname == null || builderClassname.isBlank()){
            throw new WebApplicationException(404);
        }

        try {
            Class<?> clazz = Class.forName("be.vinci.instances." + builderClassname);
            Object builder = clazz.getDeclaredConstructor().newInstance();

            for (Method method : clazz.getDeclaredMethods()) {
                if(method.isAnnotationPresent(InstanceGraphBuilder.class)){
                    Object instanceGraph = method.invoke(builder);
                    InstancesAnalyzer analyzer = new InstancesAnalyzer(instanceGraph);
                    return analyzer.getFullInfo();
                }
            }

        } catch (ClassNotFoundException e){
            throw new WebApplicationException(404);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            throw new InternalError();
        }
        return null;
    }
}
