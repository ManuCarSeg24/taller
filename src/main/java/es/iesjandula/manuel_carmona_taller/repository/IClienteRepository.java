package es.iesjandula.manuel_carmona_taller.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.iesjandula.manuel_carmona_taller.models.Cliente;

public interface IClienteRepository extends JpaRepository<Cliente, String>
{
	
}
