package es.iesjandula.manuel_carmona_taller.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO (Data Transfer Object) que representa la información de una reparación para
 * respuestas (response) en la aplicación.
 * Contiene los datos básicos de la reparación como NIF del mecánico, NIF del cliente,
 * fecha de la reparación, descripción del problema y coste.
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
public class ReparacionResponseDto
{
    /**
     * NIF del mecánico que realiza la reparación.
     */
    private String nifMecanico;

    /**
     * NIF del cliente que solicita la reparación.
     */
    private String nifCliente;

    /**
     * Fecha en que se realiza la reparación.
     */
    private LocalDate fechaReparacion;

    /**
     * Descripción del problema que se va a reparar.
     */
    private String descripcionProblema;

    /**
     * Coste de la reparación.
     */
    private Double coste;
}
