package es.iesjandula.manuel_carmona_taller.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO (Data Transfer Object) que representa la información de un mecánico para
 * respuestas (response) en la aplicación.
 * Contiene los datos básicos de un mecánico como NIF y nombre.
 * Esta clase utiliza Lombok para generar automáticamente los getters, setters,
 * constructores sin argumentos y constructores con todos los argumentos.
 * 
 * @author Manuel
 * @since 27/11/2025
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MecanicoResponseDto
{
    /**
     * NIF del mecánico.
     */
    private String nifMecanico;

    /**
     * Nombre del mecánico.
     */
    private String nombreMecanico;
}
