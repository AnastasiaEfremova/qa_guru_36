package utils;

public class FileData {
    private String fileName;
    private byte[] content;
    private String fileType;

    public FileData(String fileName, byte[] content) {
        this.fileName = fileName;
        this.content = content;
        this.fileType = determineFileType(fileName);
    }

    private String determineFileType(String fileName) {
        if (fileName.toLowerCase().endsWith(".pdf")) return "PDF";
        if (fileName.toLowerCase().endsWith(".xlsx")) return "XLSX";
        if (fileName.toLowerCase().endsWith(".json")) return "JSON";
        if (fileName.toLowerCase().endsWith(".csv")) return "CSV";
        return "BINARY";
    }

    public String getFileName() { return fileName; }
    public byte[] getContent() { return content; }
    public String getFileType() { return fileType; }
    public int getSize() { return content != null ? content.length : 0; }

    @Override
    public String toString() {
        return String.format("FileData{name='%s', type='%s', size=%d bytes}", 
                           fileName, fileType, getSize());
    }
}