package gr8.imb3.progra3.controller;

import java.util.List;

public class APIResponse<T> {

    private int status;          // Código de estado HTTP (por ejemplo: 200, 201, 400)
    private List<String> messages; // Lista de mensajes (para múltiples mensajes o uno solo)
    private T data;               // Los datos de la respuesta (puede ser cualquier tipo)

    // Constructor con status, mensajes y datos
    public APIResponse(int status, List<String> messages, T data) {
        this.status = status;
        this.messages = messages;
        this.data = data;
    }

    // Constructor solo con status y mensajes (sin datos)
    public APIResponse(int status, List<String> messages) {
        this.status = status;
        this.messages = messages;
        this.data = null;
    }

    // Getters y setters
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    // Métodos estáticos para crear respuestas
    public static <T> APIResponse<T> success(T data) {
        return new APIResponse<>(200, List.of("Operación exitosa"), data);
    }

    public static <T> APIResponse<T> error(int status, List<String> messages) {
        return new APIResponse<>(status, messages, null);
    }

    public static <T> APIResponse<T> error(int status, String message) {
        return new APIResponse<>(status, List.of(message), null);
    }
}
