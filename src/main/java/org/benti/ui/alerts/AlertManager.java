package org.benti.ui.alerts;

import org.benti.log.SimpleLogger;
import org.benti.process.AlertHandler;
import org.benti.process.AlertHandlerFactory;
import org.benti.process.ResetType;
import org.benti.process.UserType;
import org.benti.ui.dialogs.AlertDialog;

import java.util.concurrent.TimeUnit;

public class AlertManager extends Thread {

    private int alertDelay = 1;
    private UserType userType;
    private ResetType resetType;
    private String character;
    private long lastAlert = 0;
    private volatile boolean running = false;
    private AlertDialog alertDialog;

    public AlertManager(int alertDelay, String character, UserType userType, ResetType resetType) {
        super("Alert Manager");
        this.alertDelay = alertDelay;
        this.userType = userType;
        this.resetType = resetType;
        this.character = character;
    }

    @Override
    public void run() {
        running = true;
        SimpleLogger.log("Process started");
        AlertHandler alertHandler = AlertHandlerFactory.getAlertHandler(userType, resetType, character);
        while (running) {
            SimpleLogger.log("Running...");
            if (alertHandler.hasAlert() && canAlert()) {
                SimpleLogger.log("Alerting user...");
                if (isDialogOpen()) {
                    alertDialog.bringToFront();
                } else {
                    alertPopUp(alertHandler.getAlert());
                }
                this.lastAlert = System.currentTimeMillis();
            }
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            SimpleLogger.log("Process stopped");
            this.join();
        } catch (Exception e) {
            SimpleLogger.log("ERROR WHILE STOPPING THREAD");
        }
    }

    private void alertPopUp(String alert) {
        alertDialog = new AlertDialog(alert);
    }

    private boolean canAlert() {
        long delayInMillis = TimeUnit.MINUTES.toMillis(alertDelay);
        return (this.lastAlert + delayInMillis) < System.currentTimeMillis();
    }

    private boolean isDialogOpen() {
        return alertDialog != null && alertDialog.isOpen();
    }

    @Override
    public void interrupt() {
        this.running = false;
    }
}
