package by.bsuir.exam.service.factory;

import by.bsuir.exam.service.DomService;
import by.bsuir.exam.service.SaxService;
import by.bsuir.exam.service.StaxService;
import by.bsuir.exam.service.impl.DomServiceClass;
import by.bsuir.exam.service.impl.SaxServiceClass;
import by.bsuir.exam.service.impl.StaxServiceClass;

public final class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final DomService domService = new DomServiceClass();
    private final SaxService saxService = new SaxServiceClass();
    private final StaxService staxService = new StaxServiceClass();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public DomService getDomService() {
        return domService;
    }

    public SaxService getSaxService() {
        return saxService;
    }

    public StaxService getStaxService() {
        return staxService;
    }
}
