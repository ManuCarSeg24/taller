package es.iesjandula.manuel_carmona_taller.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.iesjandula.manuel_carmona_taller.models.Reparacion;
import es.iesjandula.manuel_carmona_taller.models.ids.ReparacionId;

public interface IReparacionRepository extends JpaRepository<Reparacion, ReparacionId>
{

}
