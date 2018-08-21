package com.ziemo.fileReader;


import java.util.HashSet;
import java.util.Set;

public class ServiceHolder {

    private final Set<Object> services = new HashSet<Object>();

    public void addService(Object service) {
        services.add(service);
    }

    public void removeService(Object service) {
        services.remove(service);
    }

    public static void main(String[] args) {
        ServiceHolder s = new ServiceHolder();
        Object o = new Object();
        s.addService(o);
    }
}
