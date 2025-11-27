package es.iesjandula.manuel_carmona_taller.models;

import java.time.LocalDate;

import es.iesjandula.manuel_carmona_taller.models.ids.ReparacionId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase que representa una reparación en la aplicación y mapea la tabla "reparacion"
 * en la base de datos.
 * Contiene la información de la reparación incluyendo el mecánico, el cliente,
 * la fecha de la reparación, la descripción del problema y el coste.
 * Se utiliza una clave compuesta representada por la clase ReparacionId.
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
@Entity
@Table(name = "reparacion") 
public class Reparacion
{
    /**
     * Clave compuesta de la reparación, incluyendo NIF del mecánico y NIF del cliente.
     */
    @EmbeddedId
    private ReparacionId reparacionId;

    /**
     * Mecánico que realiza la reparación.
     * Se mapea con la clave "nifMecanico" de la clave compuesta.
     */
    @ManyToOne
    @MapsId("nifMecanico") 
    @JoinColumn(name = "nif_mecanico")
    private Mecanico mecanico;

    /**
     * Cliente que solicita la reparación.
     * Se mapea con la clave "nifCliente" de la clave compuesta.
     */
    @ManyToOne
    @MapsId("nifCliente")
    @JoinColumn(name = "nif_cliente")
    private Cliente cliente;

    /**
     * Fecha en que se realiza la reparación.
     */
    @Column
    private LocalDate fechaReparacion;

    /**
     * Descripción del problema que se va a reparar.
     */
    @Column
    private String descripcionProblema;

    /**
     * Coste de la reparación.
     */
    @Column
    private Double coste;
}
