package domain;

public class Factory {

    public Request createRequest () {
        return new RequestImpl();
    }
}
