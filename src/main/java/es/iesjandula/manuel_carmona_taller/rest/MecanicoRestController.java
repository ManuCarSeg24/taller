package es.iesjandula.manuel_carmona_taller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
           

            this.mecanicoRepository.saveAndFlush(mecanico);

            log.info(Constants.ELEMENTO_AGREGADO);
            return ResponseEntity.ok().build();
        }
        catch (TallerException exception)
        {
        	int responseCode = -1 ;
        	if (exception.getCodigo() == Constants.ERR_MECANICO_EMPTY_CODE)
        	{
        		responseCode = 401 ;
        	}
        	else
        	{
        		responseCode = 402 ;
        	}
        	
        	return ResponseEntity.status(responseCode).body(exception.getBodyExceptionMessage());
        }
        catch (Exception exception)
        {
            log.error("Error al crear usuario: " + exception.getMessage());
            
            TallerException videoClubException = new TallerException(Constants.GENERIC_CODE, "Error al guardar el usuario.");
            		
            return ResponseEntity.status(500).body(videoClubException.getBodyExceptionMessage());
        }
	}
}
