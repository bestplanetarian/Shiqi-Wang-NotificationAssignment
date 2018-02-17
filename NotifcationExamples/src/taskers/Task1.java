/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskers;

import javafx.application.Platform;
import javafx.scene.control.Button;
import notifcationexamples.setTaskState;

/**
 *
 * @author Shiqi Wang
 * 
 * This example uses an object passed in with a notify()
 * method that gets called when a notification is to occur.
 * To accomplish this the Notifiable interface is needed.
 * 
 */
public class Task1 extends Thread {
    
    private int maxValue, notifyEvery;
    boolean exit = false;
    
    private Notifiable notificationTarget;
    private setTaskState task;
    private Button button;
    public Task1(int maxValue, int notifyEvery, Button button )  {
        this.maxValue = maxValue;
        this.notifyEvery = notifyEvery;
        this.task = task.STOP;
        this.button = button;
    }

   
    
    @Override
    public void run() {
        task = task.RUN;
        doNotify("Task1 start.");
        for (int i = 0; i < maxValue; i++) {
            
            if (i % notifyEvery == 0) {
                doNotify("It happened in Task1: " + i + "State" + task);
            }
            
            if (exit) {
                return;
            }
        }
        task = task.terminate;
        doNotify("Task1 done. State" + task);
        Platform.runLater(() -> {
                button.setText("Task 1");
            });
}
    
    public void end() {
        exit = true;
    }
    
    public void setNotificationTarget(Notifiable notificationTarget) {
        this.notificationTarget = notificationTarget;
    }
    
    private void doNotify(String message) {
        // this provides the notification through a method on a passed in object (notificationTarget)
        if (notificationTarget != null) {
            Platform.runLater(() -> {
                notificationTarget.notify(message);
            });
        }
    }
public setTaskState getTaskState(){
    return this.task;
}
    
    
    public void setTaskState(setTaskState task1) {
       this.task = task1;
    }
}
