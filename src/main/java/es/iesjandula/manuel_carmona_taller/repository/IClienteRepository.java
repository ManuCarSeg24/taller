package es.iesjandula.manuel_carmona_taller.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.iesjandula.manuel_carmona_taller.dtos.ClienteResponseDto;
import es.iesjandula.manuel_carmona_taller.models.Cliente;

/**
 * Interfaz de repositorio para la entidad Cliente.
 * Extiende JpaRepository para proporcionar operaciones CRUD básicas.
 * Contiene métodos personalizados para acceder a los datos de los clientes.
 * 
 * Permite obtener una lista de clientes proyectada como ClienteResponseDto.
 * 
 * @author Manuel
 * @since 27/11/2025
 */
public interface IClienteRepository extends JpaRepository<Cliente, String>
{
    /**
     * Devuelve una lista de todos los clientes de la base de datos.
     * La información de cada cliente se proyecta en un ClienteResponseDto.
     * 
     * @return Lista de ClienteResponseDto con NIF, nombre y teléfono de cada cliente.
     */
    @Query(value = "SELECT new es.iesjandula.manuel_carmona_taller.dtos.ClienteResponseDto(c.nifCliente, c.nombreCliente, c.telefono) " +
                   "FROM Cliente c")
    List<ClienteResponseDto> buscarClientes();
}
