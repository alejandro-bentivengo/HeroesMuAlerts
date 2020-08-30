package org.benti.process.finder;

public class ProcessFinderFactory {
    public static IProcessFinder getProcessFinder() {
        return getProcessFinder(ProcessFinderType.JNA);
    }

    public static IProcessFinder getProcessFinder(ProcessFinderType processFinderType) {
        switch (processFinderType) {
            case JNA:
                return new JNAProcessFinder();
            case DEFAULT:
            default:
                return new ProcessFinder();
        }
    }

    public enum ProcessFinderType {
        DEFAULT, JNA
    }
}

