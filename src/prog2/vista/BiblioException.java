package prog2.vista;

import java.io.Serializable;

public class BiblioException extends RuntimeException implements Serializable {
    public BiblioException(String message) {
        super(message);
    }
}
