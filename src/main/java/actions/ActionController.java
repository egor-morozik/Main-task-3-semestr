/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package actions;

/**
 *
 * @author egorm
 */
public class ActionController {
    private final ActionCommand readCommand;
    private final ActionCommand analyzeCommand;
    private final ActionCommand writeCommand;

    public ActionController(ActionCommand readCommand, ActionCommand analyzeCommand, ActionCommand writeCommand) {
        this.readCommand = readCommand;
        this.analyzeCommand = analyzeCommand;
        this.writeCommand = writeCommand;
    }

    public void onReadButtonClick() {
        readCommand.execute();
    }

    public void onAnalyzeButtonClick() {
        analyzeCommand.execute();
    }

    public void onWriteButtonClick() {
        writeCommand.execute();
    }
}

