package org.benti.process.finder;

import org.benti.exceptions.AbstractException;
import org.benti.process.Process;

import java.util.List;

public interface IProcessFinder {

    List<Process> getProcesses();

    Process getProcessByProcessName(String processName);

    Process getProcessByProcessName(String processName, boolean fullMatch);

    List<Process> getProcessesByTitle(String title);

    List<Process> getProcessesByTitle(String title, boolean fullMatch);

    Process getProcessByTitle(String title) throws AbstractException;

    Process getProcessByTitle(String title, boolean fullMatch) throws AbstractException;
}
