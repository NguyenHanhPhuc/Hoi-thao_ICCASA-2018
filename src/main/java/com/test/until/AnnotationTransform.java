package com.test.until;

import com.test.constant.PropertyConstant;
import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

public class AnnotationTransform implements IAnnotationTransformer {
    List<TestcaseInfo> list;
    String pack = ConfigurationUtil.getInstance().getProperty(PropertyConstant.TESTCASE_PACKAGE);

    public AnnotationTransform(List<TestcaseInfo> list) throws IOException {
        this.list = list;
    }

    @Override
    public void transform(ITestAnnotation annotation, Class aClass, Constructor constructor, Method method) {
        list.forEach(tc->{
            if (tc.getMethodName().equalsIgnoreCase(method.getName())&&
                    method.getDeclaringClass().getName().equals(pack+"."+tc.getClassName())){
                annotation.setInvocationCount(tc.getInvocation());
            }
        });
    }
}
