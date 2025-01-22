/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package settings;

/**
 *
 * @author egorm
 */
public class FileSettings {
    private final String inputFileName;
    private final String inputDirectoryPath;
    private final String inputFileArchive;
    private final String inputFileKey;
    private final String inputFileType;
    private final String outputFileName;
    private final String outputFileType;
    private final String outputDirectoryPath;
    private final String outputFileArchive;
    private final String outputFileKey;

    private FileSettings(Builder builder) {
        this.inputFileName = builder.inputFileName;
        this.inputDirectoryPath = builder.inputDirectoryPath;
        this.inputFileArchive = builder.inputFileArchived;
        this.inputFileKey = builder.inputFileKey;
        this.inputFileType = builder.inputFileType;
        this.outputFileName = builder.outputFileName;
        this.outputFileType = builder.outputFileType;
        this.outputDirectoryPath = builder.outputDirectoryPath;
        this.outputFileArchive = builder.outputFileArchived;
        this.outputFileKey = builder.outputFileKey;
    }

    public static class Builder {
        private String inputFileName;
        private String inputDirectoryPath;
        private String inputFileArchived;
        private String inputFileKey;
        private String inputFileType;
        private String outputFileName;
        private String outputFileType;
        private String outputDirectoryPath;
        private String outputFileArchived;
        private String outputFileKey;

        public Builder setInputFileName(String inputFileName) {
            this.inputFileName = inputFileName;
            return this;
        }

        public Builder setInputDirectoryPath(String inputDirectoryPath) {
            this.inputDirectoryPath = inputDirectoryPath;
            return this;
        }

        public Builder setInputFileArchive(String inputFileArchive) {
            this.inputFileArchived = inputFileArchive;
            return this;
        }

        public Builder setInputFileKey(String inputFileKey) {
            this.inputFileKey = inputFileKey;
            return this;
        }
        
        public Builder setInputFileType(String inputFileType) {
            this.inputFileType = inputFileType;
            return this;
        }

        public Builder setOutputFileName(String outputFileName) {
            this.outputFileName = outputFileName;
            return this;
        }

        public Builder setOutputFileType(String outputFileType) {
            this.outputFileType = outputFileType;
            return this;
        }

        public Builder setOutputDirectoryPath(String outputDirectoryPath) {
            this.outputDirectoryPath = outputDirectoryPath;
            return this;
        }

        public Builder setOutputFileArchive(String outputFileArchive) {
            this.outputFileArchived = outputFileArchive;
            return this;
        }

        public Builder setOutputFileKey(String outputFileKey) {
            this.outputFileKey = outputFileKey;
            return this;
        }

        public FileSettings build() {
            return new FileSettings(this);
        }
    }

    // Геттеры для всех полей
    public String getInputFileName() { return inputFileName; }
    public String getInputDirectoryPath() { return inputDirectoryPath; }
    public String getInputFileArchive() { return inputFileArchive; }
    public String getInputFileKey() { return inputFileKey; }
    public String getInputFileType() { return inputFileType; }
    public String getOutputFileName() { return outputFileName; }
    public String getOutputFileType() { return outputFileType; }
    public String getOutputDirectoryPath() { return outputDirectoryPath; }
    public String getOutputFileArchive() { return outputFileArchive; }
    public String getOutputFileKey() { return outputFileKey; }
}
