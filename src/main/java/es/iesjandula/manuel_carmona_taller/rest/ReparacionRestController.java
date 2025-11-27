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

import es.iesjandula.manuel_carmona_taller.dtos.ReparacionRequestDto;
import es.iesjandula.manuel_carmona_taller.models.Cliente;
import es.iesjandula.manuel_carmona_taller.models.Mecanico;
import es.iesjandula.manuel_carmona_taller.models.Reparacion;
import es.iesjandula.manuel_carmona_taller.models.ids.ReparacionId;
import es.iesjandula.manuel_carmona_taller.repository.IClienteRepository;
import es.iesjandula.manuel_carmona_taller.repository.IMecanicoRepository;
import es.iesjandula.manuel_carmona_taller.repository.IReparacionRepository;
import es.iesjandula.manuel_carmona_taller.utils.Constants;
import es.iesjandula.manuel_carmona_taller.utils.TallerException;
import lombok.extern.slf4j.Slf4j;

/**
 * Controlador REST para gestionar las operaciones relacionadas con la entidad Reparacion.
 * Proporciona endpoints para crear, modificar, borrar y obtener reparaciones.
 * Se integra con IReparacionRepository, IMecanicoRepository e IClienteRepository
 * para acceder a la base de datos.
 * Maneja excepciones específicas mediante TallerException y loguea información relevante.
 * 
 * 
 * @author Manuel
 * @since 27/11/2025
 */
@Slf4j
@RestController
@RequestMapping("/taller/reparacion")
public class ReparacionRestController
{
	@Autowired
	private IReparacionRepository reparacionRepository;
	
	@Autowired
	private IMecanicoRepository mecanicoRepository;
	
	@Autowired
	private IClienteRepository clienteRepository;
	
	/**
     * Crea una nueva reparación en la base de datos.
     * Valida que el mecánico y el cliente existan y que la reparación no exista previamente.
     * 
     * @param reparacionRequestDto Objeto DTO con los datos de la reparación a crear.
     * @return ResponseEntity indicando el resultado de la operación.
     */
	@PostMapping(value = "/", consumes = "application/json")
    public ResponseEntity<?> crearReparacion(@RequestBody ReparacionRequestDto reparacionRequestDto)
    {
        try
        {
            String nifMecanico = reparacionRequestDto.getNifMecanico();
            String nifCliente = reparacionRequestDto.getNifCliente();

            if (nifMecanico == null || nifCliente == null || nifMecanico.isEmpty() || nifCliente.isEmpty())
            {
                log.error(Constants.ERR_REPARACION_NIFS_NULOS_O_VACIOS);
                throw new TallerException(Constants.ERR_REPARACION_NIFS_NULOS_O_VACIOS_CODE, Constants.ERR_REPARACION_NIFS_NULOS_O_VACIOS);
            }

            Optional<Mecanico> mecanicoOptional = this.mecanicoRepository.findById(nifMecanico);
            if (!mecanicoOptional.isPresent())
            {
                log.error(Constants.ERR_MECANICO_NO_ENCONTRADO);
                throw new TallerException(Constants.ERR_MECANICO_NO_ENCONTRADO_CODE, Constants.ERR_MECANICO_NO_ENCONTRADO);
            }

            Optional<Cliente> clienteOptional = this.clienteRepository.findById(nifCliente);
            if (!clienteOptional.isPresent())
            {
                log.error(Constants.ERR_CLIENTE_NO_ENCONTRADO);
                throw new TallerException(Constants.ERR_CLIENTE_NO_ENCONTRADO_CODE, Constants.ERR_CLIENTE_NO_ENCONTRADO);
            }           

            ReparacionId reparacionId = new ReparacionId(nifMecanico, nifCliente);
            Optional<Reparacion> reparacionOptional = this.reparacionRepository.findById(reparacionId);
            if (reparacionOptional.isPresent())
            {
                log.error(Constants.ERR_REPARACION_YA_REGISTRADA);
                throw new TallerException(Constants.ERR_REPARACION_YA_REGISTRADA_CODE, Constants.ERR_REPARACION_YA_REGISTRADA);
            }
   
            Reparacion reparacion = new Reparacion();
            reparacion.setReparacionId(reparacionId); 
            reparacion.setMecanico(mecanicoOptional.get());
            reparacion.setCliente(clienteOptional.get());
            reparacion.setFechaReparacion(reparacionRequestDto.getFechaReparacion());
            reparacion.setDescripcionProblema(reparacionRequestDto.getDescripcionProblema());
            reparacion.setCoste(reparacionRequestDto.getCoste());

            this.reparacionRepository.saveAndFlush(reparacion);
            
            log.info(Constants.REPARACION_AGREGADA);
            return ResponseEntity.status(204).build();
        }
        catch (TallerException exception)
        {
        	int responseCode = 406;
        	if (exception.getCodigo() == Constants.ERR_MECANICO_NO_EXISTE_CODE)
        	{
        		responseCode = 404;
        	}
        	else if (exception.getCodigo() == Constants.ERR_CLIENTE_NO_EXISTE_CODE)
			{
        		responseCode = 405;
			}
        	
        	return ResponseEntity.status(responseCode).body(exception.getBodyExceptionMessage());
        }
        catch (Exception exception)
        {
            log.error(exception.getMessage(), exception);
            return ResponseEntity.status(500).body(new TallerException(Constants.GENERIC_CODE, Constants.ERR_REPARACION_NO_ENCONTRADA, exception).getBodyExceptionMessage());
        }
    }
	
	/**
     * Modifica una reparación existente en la base de datos.
     * Valida que el mecánico, el cliente y la reparación existan.
     * 
     * @param reparacionRequestDto Objeto DTO con los datos de la reparación a modificar.
     * @return ResponseEntity indicando el resultado de la operación.
     */
	@PutMapping(value = "/", consumes = "application/json")
	public ResponseEntity<?> modificarReparacion(@RequestBody ReparacionRequestDto reparacionRequestDto)
	{
	    try
	    {
	        String nifMecanico = reparacionRequestDto.getNifMecanico();
	        String nifCliente = reparacionRequestDto.getNifCliente();

	        if (nifMecanico == null || nifCliente == null)
	        {
	            log.error(Constants.ERR_REPARACION_NIFS_NULOS_O_VACIOS);
	            throw new TallerException(Constants.ERR_REPARACION_NIFS_NULOS_O_VACIOS_CODE, Constants.ERR_REPARACION_NIFS_NULOS_O_VACIOS);
	        }
	        
	        Optional<Mecanico> mecanicoOptional = this.mecanicoRepository.findById(nifMecanico);
	        if (!mecanicoOptional.isPresent())
	        {
	            log.error(Constants.ERR_MECANICO_NO_ENCONTRADO);
	            throw new TallerException(Constants.ERR_MECANICO_NO_ENCONTRADO_CODE, Constants.ERR_MECANICO_NO_ENCONTRADO);
	        }

	        Optional<Cliente> clienteOptional = this.clienteRepository.findById(nifCliente);
	        if (!clienteOptional.isPresent())
	        {
	            log.error(Constants.ERR_CLIENTE_NO_ENCONTRADO);
	            throw new TallerException(Constants.ERR_CLIENTE_NO_ENCONTRADO_CODE, Constants.ERR_CLIENTE_NO_ENCONTRADO);
	        }

	        ReparacionId reparacionId = new ReparacionId(nifMecanico, nifCliente);
	        Optional<Reparacion> reparacionOptional = this.reparacionRepository.findById(reparacionId);

	        if (!reparacionOptional.isPresent())
	        {
	            log.error(Constants.ERROR_REPARACION_NIFS_NO_ENCONTRADOS);
	            throw new TallerException(Constants.ERROR_REPARACION_NIFS_NO_ENCONTRADOS_CODE, Constants.ERROR_REPARACION_NIFS_NO_ENCONTRADOS);
	        }

	        Reparacion reparacion = reparacionOptional.get();

	        reparacion.setMecanico(mecanicoOptional.get());
	        reparacion.setCliente(clienteOptional.get());
	        reparacion.setFechaReparacion(reparacionRequestDto.getFechaReparacion());
	        reparacion.setDescripcionProblema(reparacionRequestDto.getDescripcionProblema());
	        reparacion.setCoste(reparacionRequestDto.getCoste());

	        this.reparacionRepository.saveAndFlush(reparacion);

	        log.info(Constants.REPARACION_MODIFICADA);
            return ResponseEntity.status(200).build();
        }
        catch (TallerException exception)
        {
        	int responseCode = 406;
        	if (exception.getCodigo() == Constants.ERR_MECANICO_NO_EXISTE_CODE)
        	{
        		responseCode = 404;
        	}
        	else if (exception.getCodigo() == Constants.ERR_CLIENTE_NO_EXISTE_CODE)
			{
        		responseCode = 405;
			}
        	
        	return ResponseEntity.status(responseCode).body(exception.getBodyExceptionMessage());
        }
        catch (Exception exception)
        {
            log.error(exception.getMessage(), exception);
            return ResponseEntity.status(500).body(new TallerException(Constants.GENERIC_CODE, Constants.ERR_REPARACION_NO_ENCONTRADA, exception).getBodyExceptionMessage());
        }
	}

	/**
     * Borra una reparación de la base de datos dado el NIF del mecánico y del cliente.
     * Valida que la reparación exista antes de eliminarla.
     * 
     * @param nifMecanico NIF del mecánico asociado a la reparación.
     * @param nifCliente NIF del cliente asociado a la reparación.
     * @return ResponseEntity indicando el resultado de la operación.
     */
	@DeleteMapping(value = "/{nifMecanico}/{nifCliente}") 
    public ResponseEntity<?> borrarReparacion(@PathVariable("nifMecanico") String nifMecanico, @PathVariable("nifCliente") String nifCliente)
    {
        try
        {
            ReparacionId reparacionId = new ReparacionId(nifMecanico, nifCliente); 

            if (!this.reparacionRepository.existsById(reparacionId))
            {
                log.error(Constants.ERR_REPARACION_NO_ENCONTRADA);
                throw new TallerException(Constants.ERR_REPARACION_NO_ENCONTRADA_CODE, Constants.ERR_REPARACION_NO_ENCONTRADA);
            }

            this.reparacionRepository.deleteById(reparacionId);
            
            
            log.info(Constants.REPARACION_ELIMINADA);
            return ResponseEntity.status(200).build();
        }
        catch (TallerException exception)
        {
        	int responseCode = 406;
        	if (exception.getCodigo() == Constants.ERR_MECANICO_NO_EXISTE_CODE)
        	{
        		responseCode = 404;
        	}
        	else if (exception.getCodigo() == Constants.ERR_CLIENTE_NO_EXISTE_CODE)
			{
        		responseCode = 405;
			}
        	
        	return ResponseEntity.status(responseCode).body(exception.getBodyExceptionMessage());
        }
        catch (Exception exception)
        {
            log.error(exception.getMessage(), exception);
            return ResponseEntity.status(500).body(new TallerException(Constants.GENERIC_CODE, Constants.ERR_REPARACION_NO_ENCONTRADA, exception).getBodyExceptionMessage());
        }
    }
	
	/**
     * Obtiene todas las reparaciones de la base de datos.
     * 
     * @return ResponseEntity con la lista de reparaciones proyectada como ReparacionResponseDto.
     */
	@GetMapping(value = "/")
    public ResponseEntity<?> obtenerReparaciones()
    {
		try
		{
			return ResponseEntity.status(200).body(this.reparacionRepository.buscarReparaciones());
		}
        catch (Exception exception)
        {
            log.error(exception.getMessage(), exception);
            return ResponseEntity.status(500).body(new TallerException(Constants.GENERIC_CODE, Constants.ERR_REPARACION_NO_ENCONTRADA, exception).getBodyExceptionMessage());
        }
    }
}
