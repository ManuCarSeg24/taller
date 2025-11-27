package es.iesjandula.manuel_carmona_taller.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iesjandula.manuel_carmona_taller.dtos.MecanicoRequestDto;
import es.iesjandula.manuel_carmona_taller.models.Mecanico;
import es.iesjandula.manuel_carmona_taller.repository.IMecanicoRepository;
import es.iesjandula.manuel_carmona_taller.utils.Constants;
import es.iesjandula.manuel_carmona_taller.utils.TallerException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/taller/mecanico")
public class MecanicoRestController
{
	@Autowired
	private IMecanicoRepository mecanicoRepository;
	
	@PostMapping(value = "/", consumes = "application/json")
	public ResponseEntity<?> crearMecanicio(@RequestBody MecanicoRequestDto mecanicoRequestDto) 
	{
		try
        {
            if (mecanicoRequestDto.getNifMecanico() == null || mecanicoRequestDto.getNifMecanico().isEmpty())
            {
                log.error(Constants.ERR_MECANICO_EMPTY);
                throw new TallerException(Constants.ERR_MECANICO_EMPTY_CODE, Constants.ERR_MECANICO_EMPTY);
            }

            if (mecanicoRequestDto.getNifMecanico() != null && this.mecanicoRepository.existsById(mecanicoRequestDto.getNifMecanico()))
            {
                log.error(Constants.ERR_MECANICO_ALREADY_EXISTS);
                throw new TallerException(Constants.ERR_MECANICO_ALREADY_EXISTS_CODE, Constants.ERR_MECANICO_ALREADY_EXISTS);
            }

            Mecanico mecanico = new Mecanico();
            mecanico.setNifMecanico(mecanicoRequestDto.getNifMecanico());
            mecanico.setNombreMecanico(mecanicoRequestDto.getNombreMecanico());           

            this.mecanicoRepository.saveAndFlush(mecanico);

            log.info(Constants.ELEMENTO_AGREGADO);
            return ResponseEntity.status(204).build();
        }
        catch (TallerException exception)
        {
        	return ResponseEntity.status(409).body(exception.getBodyExceptionMessage());
        }
        catch (Exception exception)
        {
            log.error("Error al crear usuario: " + exception.getMessage());
            
            TallerException tallerException = new TallerException(Constants.GENERIC_CODE, "Error al guardar el usuario.");
            		
            return ResponseEntity.status(500).body(tallerException.getBodyExceptionMessage());
        }
	}
	
	@PutMapping(value = "/", consumes = "application/json")
    public ResponseEntity<?> modificarMecanico(@RequestBody MecanicoRequestDto mecanicoRequestDto)
    {
        try
        {
            if (mecanicoRequestDto.getNifMecanico() == null)
            {
                log.error(Constants.ERR_MECANICO_EMPTY);
                throw new TallerException(Constants.ERR_MECANICO_NOT_FOUND_CODE, Constants.ERR_MECANICO_EMPTY);
            }

            Optional<Mecanico> mecanidoOptional = this.mecanicoRepository.findById(mecanicoRequestDto.getNifMecanico());
            if (!mecanidoOptional.isPresent())
            {
                log.error(Constants.ERR_MECANICO_NOT_FOUND);
                throw new TallerException(Constants.ERR_MECANICO_NOT_FOUND_CODE, Constants.ERR_MECANICO_NOT_FOUND);
            }

            Mecanico mecanico = mecanidoOptional.get();
            if (mecanicoRequestDto.getNombreMecanico() != null && !mecanicoRequestDto.getNombreMecanico().isEmpty()) {
                mecanico.setNombreMecanico(mecanicoRequestDto.getNombreMecanico());
            }

            this.mecanicoRepository.saveAndFlush(mecanico);

            log.info(Constants.ELEMENTO_MODIFICADO);
            return ResponseEntity.status(200).build();
        }
        catch (TallerException exception)
        {
            return ResponseEntity.status(400).body(exception.getBodyExceptionMessage());
        }
    }
	
	@DeleteMapping(value = "/{nifMecanico}")
    public ResponseEntity<?> borrarMecanico(@PathVariable("nifMecanico") String nifMecanico)
    {
        try
        {
            if (!this.mecanicoRepository.existsById(nifMecanico))
            {
                log.error(Constants.ERR_MECANICO_NOT_FOUND);
                throw new TallerException(Constants.ERR_MECANICO_NOT_FOUND_CODE, Constants.ERR_MECANICO_NOT_FOUND);
            }

            this.mecanicoRepository.deleteById(nifMecanico);

            log.info(Constants.ELEMENTO_ELIMINADO);
            return ResponseEntity.status(200).build();
        }
        catch (TallerException exception)
        {
            return ResponseEntity.status(400).body(exception.getBodyExceptionMessage());
        }
    }

    @GetMapping(value = "/")
    public ResponseEntity<?> obtenerUsuarios()
    {
        return ResponseEntity.status(200).body(this.mecanicoRepository.buscarMecanicos());
    }
}
