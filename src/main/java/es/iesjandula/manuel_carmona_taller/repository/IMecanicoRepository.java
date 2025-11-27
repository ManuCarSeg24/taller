package es.iesjandula.manuel_carmona_taller.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.iesjandula.manuel_carmona_taller.dtos.MecanicoResponseDto;
import es.iesjandula.manuel_carmona_taller.models.Mecanico;

public interface IMecanicoRepository extends JpaRepository<Mecanico, String>
{
	@Query(value = "SELECT new es.iesjandula.manuel_carmona_taller.dtos.MecanicoResponseDto(m.nifMecanico, m.nombreMecanico) " +
		       "FROM Mecanico m")
	List<MecanicoResponseDto> buscarMecanicos();
}
