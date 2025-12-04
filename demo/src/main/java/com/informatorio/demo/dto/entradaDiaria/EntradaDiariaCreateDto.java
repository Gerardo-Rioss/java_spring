package com.informatorio.demo.dto.entradaDiaria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EntradaDiariaCreateDto {

    @NotNull(message = "El id del usuario es obligatorio.")
    private UUID usuarioId;

    @NotNull(message = "La fecha es obligatoria.")
    private LocalDate fecha;

    @NotBlank(message = "La reflexion no puede estar vacia.")
    @Size(max = 500, message = "La reflexion no puede superar los 500 caracteres.")
    private String reflexion;

    @NotBlank(message = "La emoción no puede estar vacia.")
    @Size(max = 100, message = "La emoción no puede superar los 100 caracteres.")
    private String emocion;

    private List<Long> habitosIds;
}
