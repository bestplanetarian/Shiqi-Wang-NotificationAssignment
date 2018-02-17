/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskers;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javafx.application.Platform;
import javafx.scene.control.Button;
import notifcationexamples.setTaskState;

/**
 *
 * @author Shiqi Wang
 * 
 * This example uses PropertyChangeSupport to implement
 * property change listeners.
 * 
 */
public class Task3 extends Thread {
    
    private int maxValue, notifyEvery;
    boolean exit = false;
    private Button button;
    private setTaskState task3;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
    public Task3(int maxValue, int notifyEvery, Button button)  {
        this.maxValue = maxValue;
        this.notifyEvery = notifyEvery;
        this.button = button;
        this.task3 = task3.STOP;
    }
    
    @Override
    public void run() {
        doNotify("Task3 start.");
        task3 = task3.RUN;
        for (int i = 0; i < maxValue; i++) {
            
            if (i % notifyEvery == 0) {
                doNotify("It happened in Task3: " + i + "State: " + task3);
            }
            
            if (exit) {
                return;
            }
        }
        task3 = task3.terminate;
        doNotify("Task3 done. State: "  + task3);
        Platform.runLater(() -> {
            button.setText("Task 3");
        });
        
    }
    
    public void end() {
        exit = true;
    }
    
    // the following two methods allow property change listeners to be added
    // and removed
    public void addPropertyChangeListener(PropertyChangeListener listener) {
         pcs.addPropertyChangeListener(listener);
     }

     public void removePropertyChangeListener(PropertyChangeListener listener) {
         pcs.removePropertyChangeListener(listener);
     }
    
    private void doNotify(String message) {
        // this provides the notification through the property change listener
        Platform.runLater(() -> {
            // I'm choosing not to send the old value (second param).  Sending "" instead.
            pcs.firePropertyChange("message", "", message);
        });
    }
    
    public setTaskState getTaskState() {
        return this.task3;
    }
    
    public void setTaskState(setTaskState state) {
        this.task3 = state;
    }
}