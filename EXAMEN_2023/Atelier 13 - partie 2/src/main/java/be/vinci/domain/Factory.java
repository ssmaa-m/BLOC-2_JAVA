package be.vinci.domain;

public class Factory {

    public TestClass createTestClass () {
        return new TestClassImpl(this);
    }

    public TestMethodImpl createTestMethod () {
        return new TestMethodImpl();
    }
}
