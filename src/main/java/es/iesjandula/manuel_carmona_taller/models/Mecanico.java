package es.iesjandula.manuel_carmona_taller.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase que representa a un mecánico en la aplicación y mapea la tabla "mecanico"
 * en la base de datos.
 * Contiene los datos básicos de un mecánico como NIF y nombre,
 * así como la lista de reparaciones que ha realizado.
 * Esta clase utiliza Lombok para generar automáticamente los getters, setters
 * y constructores.
 * 
 * @author Manuel
 * @since 27/11/2025
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mecanico")
public class Mecanico
{
    /**
     * NIF del mecánico. Es la clave primaria de la tabla.
     */
    @Id
    @Column
    private String nifMecanico;

    /**
     * Nombre del mecánico. No puede ser nulo.
     */
    @Column(nullable = false)
    private String nombreMecanico;

    /**
     * Lista de reparaciones realizadas por este mecánico.
     * Se mapea con la propiedad "mecanico" en la clase Reparacion.
     */
    @OneToMany(mappedBy = "mecanico")
    private List<Reparacion> reparaciones;
}
