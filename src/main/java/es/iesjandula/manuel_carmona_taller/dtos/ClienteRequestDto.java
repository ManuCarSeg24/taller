package es.iesjandula.manuel_carmona_taller.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequestDto
{
	private String nifCliente;
	private String nombreCliente;
	private String telefono;
}
