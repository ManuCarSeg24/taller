package es.iesjandula.manuel_carmona_taller.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReparacionRequestDto
{
	private String nifMecanico;
	private String nifCliente;
	private LocalDate fechaReparacion;
    private String escripcionProblema;
    private Double coste;
}
