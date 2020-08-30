package org.benti.process.finder;

import org.benti.process.Process;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProcessFinder implements IProcessFinder {

    private static final String CSV_REGEX = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";

    public List<Process> getProcesses() {
        List<Process> processes = new ArrayList<>();
        try {
            String line;
            java.lang.Process p = Runtime.getRuntime().exec(
                    System.getenv("windir") + "\\system32\\" + "tasklist.exe /v /fo csv /fi \"STATUS eq running\""
            );
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            int lineIndex = 0;
            Map<String, Integer> indexes = new HashMap<>();
            while ((line = input.readLine()) != null) {
                if (lineIndex == 0) {
                    String[] title = line.split(CSV_REGEX);
                    for (int i = 0; i < title.length; i++) {
                        String current = title[i].replaceAll("\"", "");
                        if (current.equals("Image Name")) {
                            indexes.put("Image Name", i);
                        } else if (current.equals("PID")) {
                            indexes.put("PID", i);
                        } else if (current.equals("Window Title")) {
                            indexes.put("Window Title", i);
                        }
                    }
                } else {
                    String[] splitLine = line.split(CSV_REGEX);
                    processes.add(
                            new Process(
                                    splitLine[indexes.get("Window Title")].replaceAll("\"", ""),
                                    splitLine[indexes.get("Image Name")].replaceAll("\"", ""),
                                    splitLine[indexes.get("PID")].replaceAll("\"", "")
                            )
                    );
                }
                lineIndex++;
            }
            input.close();
        } catch (Exception err) {
            err.printStackTrace();
        }
        return processes;
    }

    public Process getProcessByProcessName(String processName) {
        return getProcessByProcessName(processName, true);
    }

    public Process getProcessByProcessName(String processName, boolean fullMatch) {
        List<Process> processes = getProcesses();
        for (Process process : processes) {
            if (fullMatch) {
                if (process.getProcessName().equals(processName)) {
                    return process;
                }
            } else {
                if (process.getProcessName().contains(processName)) {
                    return process;
                }
            }
        }
        return null;
    }

    public List<Process> getProcessesByTitle(String title) {
        return getProcessesByTitle(title, true);
    }

    public List<Process> getProcessesByTitle(String title, boolean fullMatch) {
        List<Process> processes = getProcesses();
        return processes.stream().filter(process -> {
            if (fullMatch) {
                return process.getTitle().equals(title);
            } else {
                return process.getTitle().contains(title);
            }
        }).collect(Collectors.toList());
    }

    public Process getProcessByTitle(String title) {
        return getProcessByTitle(title, true);
    }

    public Process getProcessByTitle(String title, boolean fullMatch) {
        List<Process> processes = getProcesses();
        for (Process process : processes) {
            if (fullMatch) {
                if (process.getTitle().equals(title)) {
                    return process;
                }
            } else {
                if (process.getTitle().contains(title)) {
                    return process;
                }
            }
        }
        return null;
    }

}
