package es.iesjandula.manuel_carmona_taller.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.iesjandula.manuel_carmona_taller.dtos.MecanicoResponseDto;
import es.iesjandula.manuel_carmona_taller.models.Mecanico;

/**
 * Interfaz de repositorio para la entidad Mecanico.
 * Extiende JpaRepository para proporcionar operaciones CRUD básicas.
 * Contiene métodos personalizados para acceder a los datos de los mecánicos.
 * 
 * Permite obtener una lista de mecánicos proyectada como MecanicoResponseDto.
 * 
 * @author Manuel
 * @since 27/11/2025
 */
public interface IMecanicoRepository extends JpaRepository<Mecanico, String>
{
    /**
     * Devuelve una lista de todos los mecánicos de la base de datos.
     * La información de cada mecánico se proyecta en un MecanicoResponseDto.
     * 
     * @return Lista de MecanicoResponseDto con NIF y nombre de cada mecánico.
     */
    @Query(value = "SELECT new es.iesjandula.manuel_carmona_taller.dtos.MecanicoResponseDto(m.nifMecanico, m.nombreMecanico) " +
                   "FROM Mecanico m")
    List<MecanicoResponseDto> buscarMecanicos();
}
