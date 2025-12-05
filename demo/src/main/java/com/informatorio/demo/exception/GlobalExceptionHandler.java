package com.informatorio.demo.exception; // Ajusta tu paquete

import com.informatorio.demo.dto.errores.ApiError; // Asegúrate de que este import sea correcto
//import com.informatorio.demo.exception.ResourceNotFoundException; // Asumo el nombre de tu excepción
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // -------------------------------------------------------------------------
    // 1. Manejo de Errores de Validación (@Valid o @Validated en DTOs)
    // Este método captura errores cuando un objeto de entrada (DTO) no cumple
    // con las restricciones definidas por las anotaciones Bean Validation (ej. @NotNull).
    // Devuelve un 400 Bad Request.
    // -------------------------------------------------------------------------
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationErrors(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        Map<String, String> errors = new HashMap<>();

        // Extrae todos los errores de campo y sus mensajes
        ex.getBindingResult().getFieldErrors().forEach(fieldError ->
                errors.put(fieldError.getField(), fieldError.getDefaultMessage())
        );

        ApiError apiError = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Validación fallida")
                .message("Uno o más campos no son válidos")
                .path(request.getRequestURI())
                .validationErrors(errors) // Incluye la lista detallada de errores
                .build();

        log.warn("Error de validación en {}: {}", request.getRequestURI(), errors);

        return ResponseEntity.badRequest().body(apiError);
    }

    // -------------------------------------------------------------------------
    // 2. Manejo de Errores de Tipos de Argumento (Parámetros de Ruta/Query)
    // Captura errores cuando un parámetro de URL (PathVariable o RequestParam)
    // no puede ser convertido al tipo de dato esperado (ej. un String 'abc' a un UUID).
    // Devuelve un 400 Bad Request.
    // -------------------------------------------------------------------------
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError> handleTypeMismatch(
            MethodArgumentTypeMismatchException ex,
            HttpServletRequest request
    ) {
        String mensaje = "Parámetro inválido: " + ex.getName();

        if (ex.getRequiredType() != null) {
            mensaje += " (se esperaba tipo: " + ex.getRequiredType().getSimpleName() + ")";

            // Clarificación especial para el formato de fechas
            if (ex.getRequiredType().equals(LocalDate.class)) {
                mensaje += " con formato yyyy-MM-dd";
            }
        }

        ApiError apiError = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Tipo de dato inválido")
                .message(mensaje)
                .path(request.getRequestURI())
                .build();

        log.warn("Error de tipo en {}: {}", request.getRequestURI(), mensaje);

        return ResponseEntity.badRequest().body(apiError);
    }

    // -------------------------------------------------------------------------
    // 3. Manejo de Errores de Parseo de Fechas (Formato Inválido)
    // Captura errores cuando una cadena de texto tiene el formato de fecha incorrecto
    // y no puede ser parseada (convertida) a un objeto como LocalDate o LocalDateTime.
    // Esto suele complementar al error de tipo anterior.
    // Devuelve un 400 Bad Request.
    // -------------------------------------------------------------------------
    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<ApiError> handleDateTimeParse(
            DateTimeParseException ex,
            HttpServletRequest request
    ) {
        ApiError apiError = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Fecha inválida")
                .message("Formato de fecha incorrecto. Usar: yyyy-MM-dd")
                .path(request.getRequestURI())
                .build();

        log.warn("Fecha inválida en {}: String recibido: {}", request.getRequestURI(), ex.getParsedString());

        return ResponseEntity.badRequest().body(apiError);
    }

    // -------------------------------------------------------------------------
    // 4. Manejo de Recurso No Encontrado (Errores de Lógica de Negocio 404)
    // Captura tu excepción personalizada ResourceNotFoundException (o la RecursoNoEncontradoException)
    // lanzada desde el servicio cuando una entidad (Usuario, Entrada, etc.) no existe en la base de datos.
    // Devuelve un 404 Not Found.
    // -------------------------------------------------------------------------
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFound(
            ResourceNotFoundException ex,
            HttpServletRequest request
    ) {
        ApiError apiError = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error("Recurso no encontrado")
                .message(ex.getMessage()) // Usa el mensaje que se pasó al lanzar la excepción
                .path(request.getRequestURI())
                .build();

        log.warn("Recurso no encontrado en {}: {}", request.getRequestURI(), ex.getMessage());

        // Es mejor usar 'ResponseEntity.status(HttpStatus.NOT_FOUND)' para mayor claridad
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    // ---------------------------------------------------------------------
    // 5- Captura errores cuando la URL NO coincide con ningún endpoint real.
    //    Ejemplo:
    //       - /api/v1/usuarios//entradas
    //       - /api/v1/cualquier-cosa-que-no-exista
    //
    //    Importante:
    //    Para que entre acá, deben estar activadas estas propiedades:
    //       spring.mvc.throw-exception-if-no-handler-found=true
    //       spring.web.resources.add-mappings=false
    //
    //    Sin eso, Spring devuelve un 404 por su cuenta y NO entra al handler.
    // ---------------------------------------------------------------------

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiError> handleNoHandlerFound(NoHandlerFoundException ex,
                                                         HttpServletRequest request) {

        ApiError apiError = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error("Endpoint no encontrado")
                .message("La ruta solicitada no existe: " + request.getRequestURI())
                .path(request.getRequestURI())
                .build();

        log.warn("Ruta no encontrada: {}", request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

}