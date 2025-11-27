package es.iesjandula.manuel_carmona_taller.models.ids;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase que representa la clave compuesta de la entidad Reparacion.
 * Contiene los campos NIF del mecánico y NIF del cliente.
 * Es serializable y se utiliza como clave primaria embebida en la entidad Reparacion.
 * Esta clase utiliza Lombok para generar automáticamente los getters, setters
 * y constructores.
 * 
 * @author Manuel
 * @since 27/11/2025
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ReparacionId implements Serializable
{
    /**
     * Serial version UID para la serialización.
     */
    private static final long serialVersionUID = -3193036884030678240L;

    /**
     * NIF del mecánico asociado a la reparación.
     */
    private String nifMecanico;

    /**
     * NIF del cliente asociado a la reparación.
     */
    private String nifCliente;
}
