package org.wso2.sample.perftest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.annotations.Test;
import org.wso2.automation.tools.jmeter.JMeterTest;
import org.wso2.automation.tools.jmeter.JMeterTestManager;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JmeterTest {
    private static final Log log = LogFactory.getLog(JmeterTest.class);
    Path jmeterPropertyFilePath = Paths.get("src","test","resources","jmeter.properties");

    @Test(enabled = false)
    public void runJemterTest() throws Exception {

        File folder = new File("src/test/resources/jmx");
        File[] folders = new File[11];

        int i =1 ;
        folders[0] = new File("src/test/resources/jmx/odata");
        for(File fileEntry : folder.listFiles()) {
            if(fileEntry.isDirectory() && !fileEntry.getPath().contains("odata")) {
                folders[i] = new File(fileEntry.getPath());
                i++;
            }
        }

        JMeterTestManager manager = new JMeterTestManager();
        JMeterTest test;
        for(int j =1; j<folders.length;j++ ) {
            for(File fileEntry : folders[j].listFiles()) {
                if(!fileEntry.isDirectory()) {
                    test = new JMeterTest(new File(fileEntry.getPath()));
                    test.setJMeterPropertyFile(jmeterPropertyFilePath.toFile());
                    try {
                        manager.runTest(test);
                    } catch (Exception e) {
                        log.error(e.getMessage());
                    }
                    Thread.sleep(2000);
                }
            }
        }

    }

    @Test
    public void longRunningTest() throws Exception {
        File jmxFile = new File("src/test/resources/jmx/longrunningtest/get-xml-10.jmx");

        JMeterTestManager manager = new JMeterTestManager();
        JMeterTest test = new JMeterTest(new File(jmxFile.getPath()));
        test.setJMeterPropertyFile(jmeterPropertyFilePath.toFile());
        manager.runTest(test);
    }
}
