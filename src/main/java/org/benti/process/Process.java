package org.benti.process;

public class Process {
    private String title;
    private String processName;
    private String id;

    public Process(String title, String processName, String id) {
        this.title = title;
        this.processName = processName;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
