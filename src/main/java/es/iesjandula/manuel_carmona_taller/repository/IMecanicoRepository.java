package es.iesjandula.manuel_carmona_taller.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.iesjandula.manuel_carmona_taller.models.Mecanico;

public interface IMecanicoRepository extends JpaRepository<Mecanico, String>
{

}
