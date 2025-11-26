package es.iesjandula.manuel_carmona_taller.models.ids;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ReparacionId implements Serializable
{/**
	 * 
	 */
	private static final long serialVersionUID = -3193036884030678240L;

	private String nifMecanico;
	private String nifCliente;
}
