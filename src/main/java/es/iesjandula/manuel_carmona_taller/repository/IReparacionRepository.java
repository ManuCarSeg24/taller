package es.iesjandula.manuel_carmona_taller.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.iesjandula.manuel_carmona_taller.dtos.ReparacionResponseDto;
import es.iesjandula.manuel_carmona_taller.models.Reparacion;
import es.iesjandula.manuel_carmona_taller.models.ids.ReparacionId;

/**
 * Interfaz de repositorio para la entidad Reparacion.
 * Extiende JpaRepository para proporcionar operaciones CRUD básicas.
 * Contiene métodos personalizados para acceder a los datos de las reparaciones.
 * 
 * Permite obtener una lista de reparaciones proyectada como ReparacionResponseDto.
 * 
 * @author Manuel
 * @since 27/11/2025
 */
public interface IReparacionRepository extends JpaRepository<Reparacion, ReparacionId>
{
    /**
     * Devuelve una lista de todas las reparaciones de la base de datos.
     * La información de cada reparación se proyecta en un ReparacionResponseDto.
     * 
     * @return Lista de ReparacionResponseDto con NIF del mecánico, NIF del cliente,
     *         fecha, descripción del problema y coste de cada reparación.
     */
    @Query(value = "SELECT new es.iesjandula.manuel_carmona_taller.dtos.ReparacionResponseDto(r.mecanico.nifMecanico, r.cliente.nifCliente, r.fechaReparacion, r.descripcionProblema, r.coste) " +
                   "FROM Reparacion r")
    List<ReparacionResponseDto> buscarReparaciones();
}
