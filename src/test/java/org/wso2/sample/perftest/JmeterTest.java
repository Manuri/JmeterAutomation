package org.wso2.sample.perftest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.wso2.automation.tools.jmeter.JMeterTest;
import org.wso2.automation.tools.jmeter.JMeterTestManager;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class JmeterTest {
    private static final Log log = LogFactory.getLog(JmeterTest.class);

    @BeforeClass
    public void changeJmeterPropertiesFile() throws IOException {
        Path jmeterpropertiesfilepath = Paths.get(System.getProperty("basedir", ".")
                ,"target", "jmeter","bin","jmeter.properties");

        Files.write(jmeterpropertiesfilepath, (System.getProperty("line.separator")+"jmeterengine.nongui.maxport=500").getBytes(), StandardOpenOption.APPEND);
    }

    @Test
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

        for(int j =1; j<folders.length;j++ ) {
            for(File fileEntry : folders[j].listFiles()) {
                if(!fileEntry.isDirectory()) {
                    manager.runTest(new JMeterTest(new File(fileEntry.getPath())));
                    Thread.sleep(2000);
                }
            }
        }

    }
}
