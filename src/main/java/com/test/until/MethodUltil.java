package com.test.until;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.test.constant.PropertyConstant;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class MethodUltil {


    public MethodUltil() throws IOException {
    }

    public static Map<String,Set<String>> getAllAvailableTestMethod() throws IOException {
        Map<String,Set<String>> result = new HashMap();
        ConfigurationUtil props = ConfigurationUtil.getInstance();
        String pack= props.getProperty(PropertyConstant.TESTCASE_PACKAGE);
        ArrayList<String> items = new ArrayList<String>();
        URL newUrl;
        List<URL> newUrls = new ArrayList<>();
        Collections.addAll(items, pack.split("\\s*,\\s*"));
        int a = 0;
        Collection<URL> urls = ClasspathHelper.forPackage(items.get(a));
        Iterator<URL> iter = urls.iterator();
        URL url = iter.next();
        urls.clear();
        for (int i = 0; i < items.size(); i++) {
            newUrl = new URL(url.toString() + items.get(i).replaceAll("\\.", "/"));
            newUrls.add(newUrl);
            a++;

        }
        Reflections reflections = new Reflections(new ConfigurationBuilder().setUrls(newUrls)
                .setScanners(new MethodAnnotationsScanner()));
        Set<Method> resources =
                reflections.getMethodsAnnotatedWith(org.testng.annotations.Test.class);
        Reflections classRef = new Reflections(pack);
        Set<Class<? extends Object>> classess = classRef.getSubTypesOf(Object.class);

        classess.toString();
        for (Method m: resources) {
            String method = m.getName();
            String classname = m.getDeclaringClass().getName();

            if (!result.containsKey(classname)){
                result.put(classname,new HashSet());
            }

            result.get(classname).add(method);

        }
        return result;
    }
}
