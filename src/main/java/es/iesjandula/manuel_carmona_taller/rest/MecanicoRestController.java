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

/**
 * Controlador REST para gestionar las operaciones relacionadas con la entidad Mecanico.
 * Proporciona endpoints para crear, modificar, borrar y obtener mecánicos.
 * Se integra con IMecanicoRepository para acceder a la base de datos.
 * Maneja excepciones específicas mediante TallerException y loguea información relevante.
 * 
 * @author Manuel
 * @since 27/11/2025
 */
@Slf4j
@RestController
@RequestMapping("/taller/mecanico")
public class MecanicoRestController
{
	@Autowired
	private IMecanicoRepository mecanicoRepository;
	
	/**
     * Crea un nuevo mecánico en la base de datos.
     * Valida que el NIF no sea nulo ni existente previamente.
     * 
     * @param mecanicoRequestDto Objeto DTO con los datos del mecánico a crear.
     * @return ResponseEntity indicando el resultado de la operación.
     */
	@PostMapping(value = "/", consumes = "application/json")
	public ResponseEntity<?> crearMecanicio(@RequestBody MecanicoRequestDto mecanicoRequestDto) 
	{
		try
        {
            if (mecanicoRequestDto.getNifMecanico() == null || mecanicoRequestDto.getNifMecanico().isEmpty())
            {
                log.error(Constants.ERR_MECANICO_VACIO_NULO);
                throw new TallerException(Constants.ERR_MECANICO_VACIO_NULO_CODE, Constants.ERR_MECANICO_VACIO_NULO);
            }

            if (mecanicoRequestDto.getNifMecanico() != null && this.mecanicoRepository.existsById(mecanicoRequestDto.getNifMecanico()))
            {
                log.error(Constants.ERR_MECANICO_EXISTE);
                throw new TallerException(Constants.ERR_MECANICO_EXISTE_INTERNAL_CODE, Constants.ERR_MECANICO_EXISTE);
            }

            Mecanico mecanico = new Mecanico();
            mecanico.setNifMecanico(mecanicoRequestDto.getNifMecanico());
            mecanico.setNombreMecanico(mecanicoRequestDto.getNombreMecanico());           

            this.mecanicoRepository.saveAndFlush(mecanico);

            log.info(Constants.MECANICO_AGREGADO);
            return ResponseEntity.status(204).build();
        }
        catch (TallerException exception)
        {
        	return ResponseEntity.status(409).body(exception.getBodyExceptionMessage());
        }
        catch (Exception exception)
        {
            log.error("Error al crear mecanico: " + exception.getMessage());
            
            TallerException tallerException = new TallerException(Constants.GENERIC_CODE, "Error al guardar el mecanico.");
            		
            return ResponseEntity.status(500).body(tallerException.getBodyExceptionMessage());
        }
	}
	
	/**
     * Modifica un mecánico existente en la base de datos.
     * Valida que el mecánico exista y que el NIF no sea nulo.
     * 
     * @param mecanicoRequestDto Objeto DTO con los datos del mecánico a modificar.
     * @return ResponseEntity indicando el resultado de la operación.
     */
	@PutMapping(value = "/", consumes = "application/json")
    public ResponseEntity<?> modificarMecanico(@RequestBody MecanicoRequestDto mecanicoRequestDto)
    {
        try
        {
            if (mecanicoRequestDto.getNifMecanico() == null)
            {
            	log.error(Constants.ERR_MECANICO_VACIO_NULO);
                throw new TallerException(Constants.ERR_MECANICO_VACIO_NULO_CODE, Constants.ERR_MECANICO_VACIO_NULO);
            }

            Optional<Mecanico> mecanidoOptional = this.mecanicoRepository.findById(mecanicoRequestDto.getNifMecanico());
            if (!mecanidoOptional.isPresent())
            {
                log.error(Constants.ERR_MECANICO_NO_ENCONTRADO);
                throw new TallerException(Constants.ERR_MECANICO_NO_ENCONTRADO_CODE, Constants.ERR_MECANICO_NO_ENCONTRADO);
            }

            Mecanico mecanico = mecanidoOptional.get();
            if (mecanicoRequestDto.getNombreMecanico() != null && !mecanicoRequestDto.getNombreMecanico().isEmpty()) {
                mecanico.setNombreMecanico(mecanicoRequestDto.getNombreMecanico());
            }

            this.mecanicoRepository.saveAndFlush(mecanico);

            log.info(Constants.MECANICO_MODIFICADO);
            return ResponseEntity.status(200).build();
        }
        catch (TallerException exception)
        {
        	return ResponseEntity.status(409).body(exception.getBodyExceptionMessage());
        }
        catch (Exception exception)
        {
            log.error("Error al modificar mecanico: " + exception.getMessage());
            
            TallerException tallerException = new TallerException(Constants.GENERIC_CODE, "Error al modificar el mecanico.");
            		
            return ResponseEntity.status(500).body(tallerException.getBodyExceptionMessage());
        }
    }
	
	/**
     * Borra un mecánico de la base de datos dado su NIF.
     * Valida que el mecánico exista antes de eliminarlo.
     * 
     * @param nifMecanico NIF del mecánico a borrar.
     * @return ResponseEntity indicando el resultado de la operación.
     */
	@DeleteMapping(value = "/{nifMecanico}")
    public ResponseEntity<?> borrarMecanico(@PathVariable("nifMecanico") String nifMecanico)
    {
        try
        {
            if (!this.mecanicoRepository.existsById(nifMecanico))
            {
                log.error(Constants.ERR_MECANICO_NO_ENCONTRADO);
                throw new TallerException(Constants.ERR_MECANICO_NO_ENCONTRADO_CODE, Constants.ERR_MECANICO_NO_ENCONTRADO);
            }

            this.mecanicoRepository.deleteById(nifMecanico);

            log.info(Constants.MECANICO_ELIMINADO);
            return ResponseEntity.status(200).build();
        }
        catch (TallerException exception)
        {
        	return ResponseEntity.status(409).body(exception.getBodyExceptionMessage());
        }
        catch (Exception exception)
        {
            log.error("Error al elminar mecanico: " + exception.getMessage());
            
            TallerException tallerException = new TallerException(Constants.GENERIC_CODE, "Error al eliminar el mecanico.");
            		
            return ResponseEntity.status(500).body(tallerException.getBodyExceptionMessage());
        }
    }

	/**
     * Obtiene todos los mecánicos de la base de datos.
     * 
     * @return ResponseEntity con la lista de mecánicos proyectada como MecanicoResponseDto.
     */
    @GetMapping(value = "/")
    public ResponseEntity<?> obtenerMecanicos()
    {
    	try 
    	{
    		return ResponseEntity.status(200).body(this.mecanicoRepository.buscarMecanicos());
    	}
        catch (Exception exception)
        {
            log.error("Error al obtener la lista de mecanicos: " + exception.getMessage());
            
            TallerException tallerException = new TallerException(Constants.GENERIC_CODE, "Error al obtener la lista de mecanicos.");
            		
            return ResponseEntity.status(500).body(tallerException.getBodyExceptionMessage());
        }
    }
}
