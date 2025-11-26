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
@Table(name = "cliente")
public class Cliente
{
	@Id
	@Column
	private String nifCliente;
	
	@Column(nullable = false)
	private String nombreCliente;
	
	@Column(nullable = false)
	private String telefono;
	
	@OneToMany(mappedBy = "cliente")
    private List<Reparacion> reparaciones;
}
