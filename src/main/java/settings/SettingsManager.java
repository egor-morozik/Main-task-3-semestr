/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package settings;

/**
 *
 * @author egorm
 */
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SettingsManager {
    private static SettingsManager instance;
    private FileSettings fileSettings;
    private final PropertyChangeSupport support;

    private SettingsManager() {
        support = new PropertyChangeSupport(this);
    }

    public static SettingsManager getInstance() {
        if (instance == null) {
            instance = new SettingsManager();
        }
        return instance;
    }

    public void addObserver(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removeObserver(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    public void setFileSettings(FileSettings fileSettings) {
        FileSettings oldSettings = this.fileSettings;
        this.fileSettings = fileSettings;
        support.firePropertyChange("fileSettings", oldSettings, fileSettings);
    }

    public FileSettings getFileSettings() {
        return fileSettings;
    }
}
