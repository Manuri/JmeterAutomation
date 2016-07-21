package org.wso2.sample.perftest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.annotations.Test;
import org.wso2.automation.tools.jmeter.JMeterTest;
import org.wso2.automation.tools.jmeter.JMeterTestManager;
import java.io.File;

public class JmeterTest {
    private static final Log log = LogFactory.getLog(JmeterTest.class);

    @Test
    public void runJemterTest() throws Exception {

        File folder = new File("src/test/resources/jmx");
        File[] folders = new File[11];

        int i =0 ;
        for(File fileEntry : folder.listFiles()) {
            if(fileEntry.isDirectory()) {
                folders[i] = new File(fileEntry.getPath());
            }
            i++;
        }

        JMeterTestManager manager = new JMeterTestManager();

        for(int j =0; j<folders.length;j++ ) {
            for(File fileEntry : folders[j].listFiles()) {
                if(!fileEntry.isDirectory()) {
                    manager.runTest(new JMeterTest(new File(fileEntry.getPath())));
                }
            }
        }

    }
}
