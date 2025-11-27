package es.iesjandula.manuel_carmona_taller.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO (Data Transfer Object) que representa la información de un cliente para
 * solicitudes (request) en la aplicación.
 * Contiene los datos básicos de un cliente como NIF, nombre y teléfono.
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
public class ClienteRequestDto
{
    /**
     * NIF del cliente.
     */
    private String nifCliente;

    /**
     * Nombre del cliente.
     */
    private String nombreCliente;

    /**
     * Teléfono de contacto del cliente.
     */
    private String telefono;
}
