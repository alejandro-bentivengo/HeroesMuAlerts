package org.benti.process.finder;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.win32.StdCallLibrary;
import org.benti.exceptions.AbstractException;
import org.benti.exceptions.ProcessNotFoundException;
import org.benti.process.Process;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JNAProcessFinder implements IProcessFinder {

    interface User32 extends StdCallLibrary {
        User32 INSTANCE = Native.load("user32", User32.class);

        interface WNDENUMPROC extends StdCallLibrary.StdCallCallback {
            boolean callback(Pointer hWnd, Pointer arg);
        }

        boolean EnumWindows(WNDENUMPROC lpEnumFunc, Pointer userData);

        int GetWindowTextA(Pointer hWnd, byte[] lpString, int nMaxCount);

        Pointer GetWindow(Pointer hWnd, int uCmd);
    }

    public static List<Process> getAllWindowNames() {
        final List<Process> processes = new ArrayList<>();
        final User32 user32 = User32.INSTANCE;
        user32.EnumWindows((hWnd, arg) -> {
            byte[] windowText = new byte[512];
            user32.GetWindowTextA(hWnd, windowText, 512);
            String wText = Native.toString(windowText, System.getProperty("file.encoding")).trim();
            if (!wText.isEmpty()) {
                processes.add(new Process(wText, "", ""));
            }
            return true;
        }, null);
        return processes;
    }


    @Override
    public List<Process> getProcesses() {
        return getAllWindowNames();
    }

    @Override
    public Process getProcessByProcessName(String processName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Process getProcessByProcessName(String processName, boolean fullMatch) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Process> getProcessesByTitle(String title) {
        return getProcessesByTitle(title, true);
    }

    @Override
    public List<Process> getProcessesByTitle(String title, boolean fullMatch) {
        return getProcesses().stream().filter(process -> {
            if (fullMatch) {
                return process.getTitle().equals(title);
            } else {
                return process.getTitle().contains(title);
            }
        }).collect(Collectors.toList());
    }

    public Process getProcessByTitle(String title) throws AbstractException {
        return getProcessByTitle(title, true);
    }

    @Override
    public Process getProcessByTitle(String title, boolean fullMatch) throws AbstractException {
        return getProcesses()
                .stream()
                .filter(process -> {
                    if (fullMatch) {
                        return process.getTitle().equals(title);
                    } else {
                        return process.getTitle().contains(title);
                    }
                })
                .findFirst()
                .orElseThrow(() -> new ProcessNotFoundException(title));

    }
}
