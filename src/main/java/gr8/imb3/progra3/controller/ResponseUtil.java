package gr8.imb3.progra3.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import gr8.imb3.progra3.entity.Producto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

public class ResponseUtil {

    private ResponseUtil() {
        // Constructor privado para evitar instanciación
    }

    // Respuesta exitosa con datos
    public static <T> ResponseEntity<APIResponse<T>> success(T data) {
        APIResponse<T> response = new APIResponse<>(HttpStatus.OK.value(), null, data);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // Respuesta exitosa con un mensaje
    public static <T> ResponseEntity<APIResponse<T>> success(String message) {
        APIResponse<T> response = new APIResponse<>(HttpStatus.OK.value(), addSingleMessage(message), null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // Respuesta exitosa con creación de recursos (código 201)
    public static <T> ResponseEntity<APIResponse<T>> created(T data) {
        APIResponse<T> response = new APIResponse<>(HttpStatus.CREATED.value(), null, data);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Respuesta de error general
    public static <T> ResponseEntity<APIResponse<T>> error(HttpStatus status, String message) {
        APIResponse<T> response = new APIResponse<>(status.value(), addSingleMessage(message), null);
        return ResponseEntity.status(status).body(response);
    }

    // Respuesta no encontrada (404)
    public static <T> ResponseEntity<APIResponse<T>> notFound(String message) {
        APIResponse<T> response = new APIResponse<>(HttpStatus.NOT_FOUND.value(), addSingleMessage(message), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // Respuesta con Bad Request (400)
    public static <T> ResponseEntity<APIResponse<T>> badRequest(String message) {
        APIResponse<T> response = new APIResponse<>(HttpStatus.BAD_REQUEST.value(), addSingleMessage(message), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // Manejo de excepciones de validación (ConstraintViolationException)
    public static <T> ResponseEntity<APIResponse<T>> handleConstraintException(ConstraintViolationException ex) {
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getMessage());
        }
        APIResponse<T> response = new APIResponse<>(HttpStatus.BAD_REQUEST.value(), errors, null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // Método privado para agregar un solo mensaje a la lista
    private static List<String> addSingleMessage(String message) {
        List<String> messages = new ArrayList<>();
        messages.add(message);
        return messages;
    }

    // Método para manejar errores internos del servidor (500)
    public static <T> ResponseEntity<APIResponse<T>> internalServerError(String message) {
        APIResponse<T> response = new APIResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), addSingleMessage(message), null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
