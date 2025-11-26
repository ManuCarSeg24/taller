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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mecanico")
public class Mecanico
{
	@Id
	@Column
	private String nifMecanico;
	
	@Column(nullable = false)
	private String nombreMecanico;
	
	@OneToMany(mappedBy = "mecanico")
    private List<Reparacion> reparaciones;
}
