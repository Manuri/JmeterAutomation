package org.wso2.sample.perftest;

import org.testng.annotations.Test;
import org.wso2.automation.tools.jmeter.JMeterTest;
import org.wso2.automation.tools.jmeter.JMeterTestManager;
import java.io.File;

public class JmeterTest {
    @Test
    public void runJemterTest() throws Exception {
        JMeterTest script1 = new JMeterTest(
                new File("../../../../../resources/getxml/get-xml-10.jmx"));
        JMeterTest script2 = new JMeterTest(
                new File("../resources/get-xml-100.jmx"));
        JMeterTestManager manager = new JMeterTestManager();

        manager.runTest(script1);
        //manager.runTest(script2);

    }
}
