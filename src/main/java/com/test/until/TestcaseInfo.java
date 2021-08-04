package com.test.until;

import com.test.constant.MethodType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestcaseInfo{
    private String methodName;
    private String className;
    private int invocation;
    private MethodType methodType;

    public TestcaseInfo( String className,String methodName, int invocation) {
        this.methodName = methodName;
        this.className = className;
        this.invocation = invocation;
    }

    public TestcaseInfo() {
    }

    public TestcaseInfo(String className, MethodType methodType, int invocation) {
        this.className = className;
        this.invocation = invocation;
        this.methodType = methodType;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getInvocation() {
        return invocation;
    }

    public void setInvocation(int invocation) {
        this.invocation = invocation;
    }

    public MethodType getMethodType() {
        return methodType;
    }

    public void setMethodType(MethodType methodType) {
        this.methodType = methodType;
    }
}
