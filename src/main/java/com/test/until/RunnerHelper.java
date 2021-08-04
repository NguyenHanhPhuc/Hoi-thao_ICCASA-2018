package com.test.until;

import com.test.constant.MethodType;
import com.test.constant.PropertyConstant;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;

public class RunnerHelper {

    ConfigurationUtil props;
    List<TestcaseInfo> testcaseInfoList;
    String pack;
    Map<String, Set<String>> methods ;

    public RunnerHelper() throws IOException {
        props = ConfigurationUtil.getInstance();
        pack = props.getProperty(PropertyConstant.TESTCASE_PACKAGE);
        testcaseInfoList = new ArrayList<>();
        methods = MethodUltil.getAllAvailableTestMethod();
    }

    protected XmlSuite createSuite(List<TestcaseInfo> testcases) throws IOException {
        int threadCount = Integer.valueOf(props.getProperty(PropertyConstant.THREAD_COUNT));
        XmlSuite.ParallelMode mode=getParallelMode();
        XmlSuite suite = new XmlSuite();
        suite.setName("JavaSecurity");
        suite.setThreadCount(threadCount);
        suite.setParallel(mode);
        List<String> listener = new ArrayList<>();
        listener.add(PropertyConstant.EXTENT_LISTENER);
        suite.setListeners(listener);
        XmlTest test = new XmlTest(suite);
        test.setName("JavaSecurity-"+ DateTimeUtils.getCurrentDateTime());
        List<XmlClass> testClasses = new ArrayList<>();
        addTestcase(testClasses,testcases);
        test.setXmlClasses(testClasses);
        return suite;
    }

    private XmlSuite.ParallelMode getParallelMode(){
        String pMode = props.getProperty(PropertyConstant.PARALLEL_MODE);
        if (pMode.equalsIgnoreCase("methods")){
            return XmlSuite.ParallelMode.METHODS;
        }else if(pMode.equalsIgnoreCase("classes")){
            return XmlSuite.ParallelMode.CLASSES;
        }else if(pMode.equalsIgnoreCase("instances")){
            return XmlSuite.ParallelMode.INSTANCES;
        }else if(pMode.equalsIgnoreCase("tests")){
            return XmlSuite.ParallelMode.TESTS;
        }else {
            return XmlSuite.ParallelMode.NONE;
        }
    }

    protected void addTestcase(List<XmlClass> xmlClasses,List<TestcaseInfo> testcases) throws IOException {
        testcases.forEach(tc->{
            String classname = pack+"."+tc.getClassName();
            if (methods.containsKey(classname)){
                Set<String> temp = methods.get(classname);
                if (temp.contains(tc.getMethodName())){
                    XmlClass testClass= new XmlClass(classname);
                    for (XmlClass cl: xmlClasses){
                        if (classname.equals(cl.getName())){
                            testClass = cl;
                        }
                    }
                    testClass.getIncludedMethods().add(new XmlInclude(tc.getMethodName()));
                    if (!xmlClasses.contains(testClass))xmlClasses.add(testClass);

                }
            }
        });
    }

    private void addTestcase(List<TestcaseInfo> original) throws IOException {
        if (original.size() == 0) return;

        Map<String, Set<String>> methods = MethodUltil.getAllAvailableTestMethod();
        if (original.get(0).getMethodType() == MethodType.ALL){
            original.stream().forEach(testcase -> {
                String classname = pack+"."+testcase.getClassName();
                Set<String> temp = methods.get(classname);
                for (String method:temp){
                    testcaseInfoList.add(new TestcaseInfo(testcase.getClassName(),method,testcase.getInvocation()));
                }
            });
        }else{
            testcaseInfoList=original;
        }

    }

    protected void run(List<TestcaseInfo> list) throws IOException {
        addTestcase(list);
        XmlSuite suite = createSuite(testcaseInfoList);
        System.out.println(suite.toXml());
        TestNG testNG = new TestNG();
        testNG.setAnnotationTransformer(new AnnotationTransform(testcaseInfoList));
        testNG.setXmlSuites(Arrays.asList(suite));
        testNG.run();
    }

    protected void loadTestCaseFromFile(List<TestcaseInfo> list) {
        String filename = props.getProperty(PropertyConstant.TESTCASE_FILE);

        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            URL resource = classLoader.getResource(filename);
            File file = new File(URLDecoder.decode(resource.getPath(),"UTF-8"));
            if (!file.exists()) {
                file.createNewFile();
            }
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String readLine;
            while ((readLine = bufferedReader.readLine()) != null) {
                if (readLine != null && !readLine.isEmpty()) {
                    String[] temp = readLine.split("\\|");
                    if (temp[1].equalsIgnoreCase("all")){
                        list.add(new TestcaseInfo(temp[0],MethodType.ALL,Integer.parseInt(temp[2])));
                    }else {
                        list.add(new TestcaseInfo(temp[0],temp[1],Integer.parseInt(temp[2])));
                    }

                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return;
    }

}
