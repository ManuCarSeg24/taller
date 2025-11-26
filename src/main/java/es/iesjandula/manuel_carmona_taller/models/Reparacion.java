package es.iesjandula.manuel_carmona_taller.models;

import java.time.LocalDate;

import es.iesjandula.manuel_carmona_taller.models.ids.ReparacionId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reparacion") 
public class Reparacion
{
	@EmbeddedId
    private ReparacionId reparacionId;
	
	@ManyToOne
    @MapsId("nifMecanico") 
    @JoinColumn(name = "nif_mecanico")
    private Mecanico mecanico;

    @ManyToOne
    @MapsId("nifCliente")
    @JoinColumn(name = "nif_cliente")
    private Cliente cliente;
    
    @Column
    private LocalDate fechaReparacion;
    
    @Column
    private String escripcionProblema;
    
    @Column
    private Double coste;
}
