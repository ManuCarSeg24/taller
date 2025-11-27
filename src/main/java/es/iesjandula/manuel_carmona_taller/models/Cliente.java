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
 * Clase que representa a un cliente en la aplicación y mapea la tabla "cliente"
 * en la base de datos.
 * Contiene los datos básicos de un cliente como NIF, nombre y teléfono,
 * así como la lista de reparaciones asociadas a este cliente.
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
@Table(name = "cliente")
public class Cliente
{
    /**
     * NIF del cliente. Es la clave primaria de la tabla.
     */
    @Id
    @Column
    private String nifCliente;

    /**
     * Nombre del cliente. No puede ser nulo.
     */
    @Column(nullable = false)
    private String nombreCliente;

    /**
     * Teléfono de contacto del cliente. No puede ser nulo.
     */
    @Column(nullable = false)
    private String telefono;

    /**
     * Lista de reparaciones asociadas a este cliente.
     * Se mapea con la propiedad "cliente" en la clase Reparacion.
     */
    @OneToMany(mappedBy = "cliente")
    private List<Reparacion> reparaciones;
}
