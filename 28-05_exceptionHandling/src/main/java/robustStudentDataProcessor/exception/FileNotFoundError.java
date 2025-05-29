package robustStudentDataProcessor.exception;

public class FileNotFoundError extends RuntimeException {
    public FileNotFoundError(String message) {
        super(message);
    }
}
