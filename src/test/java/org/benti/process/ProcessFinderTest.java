package org.benti.process;

import org.benti.process.finder.ProcessFinderFactory;
import org.junit.jupiter.api.Test;

import static org.benti.process.finder.ProcessFinderFactory.ProcessFinderType.JNA;

/**
 * These are not real tests, I used them to test the output of some methods and alerts
 */
public class ProcessFinderTest {

    @Test
    public void getProcessesTest() {
        for (Process process : ProcessFinderFactory.getProcessFinder().getProcesses()) {
            System.out.println(String.format("ID: %s - Name: %s - Title: %s", process.getId(), process.getProcessName(), process.getTitle()));
        }
    }

    @Test
    public void getProcessesJNATest() {
        for (Process process : ProcessFinderFactory.getProcessFinder(JNA).getProcesses()) {
            System.out.println(String.format("ID: %s - Name: %s - Title: %s", process.getId(), process.getProcessName(), process.getTitle()));
        }
    }

}
