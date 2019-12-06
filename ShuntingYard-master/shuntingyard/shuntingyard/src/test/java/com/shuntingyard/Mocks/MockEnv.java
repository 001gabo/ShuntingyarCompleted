package com.shuntingyard.Mocks;

import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;

import java.util.HashMap;
import java.util.Map;

public class MockEnv implements Environment {

    Map<String, String> env;

    public MockEnv() {
        env = new HashMap<>();

        env.put("service.url","/");
        env.put("service.url.endpoint.evaluate","evaluate");

        env.put("service.response.bad.request","The expression {} is invalid");
        env.put("service.response.internal.error","Some error ocurred while processing the expression");
    }
    @Override
    public String[] getActiveProfiles() {
        return new String[0];
    }

    @Override
    public String[] getDefaultProfiles() {
        return new String[0];
    }

    @Override
    public boolean acceptsProfiles(String... strings) {
        return false;
    }

    @Override
    public boolean acceptsProfiles(Profiles profiles) {
        return false;
    }

    @Override
    public boolean containsProperty(String s) {
        return false;
    }

    @Override
    public String getProperty(String key) {
        return env.get(key);
    }

    @Override
    public String getProperty(String s, String s1) {
        return null;
    }

    @Override
    public <T> T getProperty(String s, Class<T> aClass) {
        return null;
    }

    @Override
    public <T> T getProperty(String s, Class<T> aClass, T t) {
        return null;
    }

    @Override
    public String getRequiredProperty(String s) throws IllegalStateException {
        return null;
    }

    @Override
    public <T> T getRequiredProperty(String s, Class<T> aClass) throws IllegalStateException {
        return null;
    }

    @Override
    public String resolvePlaceholders(String s) {
        return null;
    }

    @Override
    public String resolveRequiredPlaceholders(String s) throws IllegalArgumentException {
        return null;
    }
}
