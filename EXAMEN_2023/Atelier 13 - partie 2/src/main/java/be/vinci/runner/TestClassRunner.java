package be.vinci.runner;

import be.vinci.domain.Factory;
import be.vinci.domain.TestClass;
import com.google.common.reflect.ClassPath;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.stream.Collectors;

public class TestClassRunner {

    private Factory factory;

    public static class ThreadRunTest extends Thread {
        private Class testClass;
        private Factory factory;

        public ThreadRunTest(Class testClass , Factory factory) {
            this.testClass = testClass;
            this.factory = factory;
        }

        public void run () {
            TestClass testClass1 = factory.createTestClass();
            testClass1.loadFromClass(testClass);
            testClass1.runAllTests();
        }
    }

    public TestClassRunner(Factory factory) {
        this.factory = factory;
    }

    /**
     * Exécute toutes les classes de tests trouvés dans le package "tests", une par une.
     */
    public void runTests() {
        getResourcesClasses().forEach(c -> {
            new ThreadRunTest(c, factory).start();
        });
    }

    /**
     * Méthode privée.
     * Cette méthode permet d'exécuter qu'une seule classe de test, en passant
     * l'objet Class en paramètre. On va charger la classe dans un objet TestClass,
     * et ensuite exécuter tous les tests dessus en utilisant la méthode runAllTests().
     *
     * @param aClass la classe de test à exécuter
     */
    private void runTests(Class aClass) throws InvocationTargetException, IllegalAccessException {
        TestClass testClass = factory.createTestClass();
        testClass.loadFromClass(aClass);
        testClass.runAllTests();
    }

    /**
     * Méthode privée.
     * Cette méthode parcourt le package "tests" et renvoit un ensemble (Set)
     * avec toutes les classes dedans.
     *
     * @return L'ensemble des classes dans le package "tests"
     */
    private Set<Class> getResourcesClasses() {
        try {
            return ClassPath.from(ClassLoader.getSystemClassLoader())
                    .getAllClasses()
                    .stream()
                    .filter(c -> c.getPackageName().contains("be.vinci.tests"))
                    .map(c -> c.load())
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
