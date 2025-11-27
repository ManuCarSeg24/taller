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

import es.iesjandula.manuel_carmona_taller.dtos.ClienteRequestDto;
import es.iesjandula.manuel_carmona_taller.models.Cliente;
import es.iesjandula.manuel_carmona_taller.repository.IClienteRepository;
import es.iesjandula.manuel_carmona_taller.utils.Constants;
import es.iesjandula.manuel_carmona_taller.utils.TallerException;
import lombok.extern.slf4j.Slf4j;

/**
 * Controlador REST para gestionar las operaciones relacionadas con la entidad Cliente.
 * Proporciona endpoints para crear, modificar, borrar y obtener clientes.
 * Se integra con IClienteRepository para acceder a la base de datos.
 * Maneja excepciones específicas mediante TallerException y loguea información relevante.
 * 
 * @author Manuel
 * @since 27/11/2025
 */

@Slf4j
@RestController
@RequestMapping("/taller/cliente")
public class ClienteRestController
{
	@Autowired
	private IClienteRepository clienteRepository;
	
	/**
     * Crea un nuevo cliente en la base de datos.
     * Valida que el NIF no sea nulo ni existente previamente.
     * 
     * @param clinteRequestDto Objeto DTO con los datos del cliente a crear.
     * @return ResponseEntity indicando el resultado de la operación.
     */
	@PostMapping(value = "/", consumes = "application/json")
	public ResponseEntity<?> crearCliente(@RequestBody ClienteRequestDto clinteRequestDto) 
	{
		try
        {
            if (clinteRequestDto.getNifCliente() == null || clinteRequestDto.getNifCliente().isEmpty())
            {
                log.error(Constants.ERR_CLIENTE_VACIO_NULO);
                throw new TallerException(Constants.ERR_CLIENTE_VACIO_NULO_CODE, Constants.ERR_CLIENTE_VACIO_NULO);
            }

            if (clinteRequestDto.getNifCliente() != null && this.clienteRepository.existsById(clinteRequestDto.getNifCliente()))
            {
                log.error(Constants.ERR_CLIENTE_EXISTE);
                throw new TallerException(Constants.ERR_CLIENTE_EXISTE_INTERNAL_CODE, Constants.ERR_CLIENTE_EXISTE);
            }

            Cliente cliente = new Cliente();
            cliente.setNifCliente(clinteRequestDto.getNifCliente());
            cliente.setNombreCliente(clinteRequestDto.getNombreCliente());
            cliente.setTelefono(clinteRequestDto.getTelefono());

            this.clienteRepository.saveAndFlush(cliente);

            log.info(Constants.CLIENTE_AGREGADO);
            return ResponseEntity.status(204).build();
        }
        catch (TallerException exception)
        {
        	return ResponseEntity.status(409).body(exception.getBodyExceptionMessage());
        }
        catch (Exception exception)
        {
            log.error("Error al crear cliente: " + exception.getMessage());
            
            TallerException tallerException = new TallerException(Constants.GENERIC_CODE, "Error al guardar el cliente.");
            		
            return ResponseEntity.status(500).body(tallerException.getBodyExceptionMessage());
        }
	}
	
	/**
     * Modifica un cliente existente en la base de datos.
     * Valida que el cliente exista y que el NIF no sea nulo.
     * 
     * @param clienteRequestDto Objeto DTO con los datos del cliente a modificar.
     * @return ResponseEntity indicando el resultado de la operación.
     */
	@PutMapping(value = "/", consumes = "application/json")
    public ResponseEntity<?> modificarCliente(@RequestBody ClienteRequestDto clienteRequestDto)
    {
        try
        {
            if (clienteRequestDto.getNifCliente() == null)
            {
                log.error(Constants.ERR_CLIENTE_VACIO_NULO);
                throw new TallerException(Constants.ERR_CLIENTE_VACIO_NULO_CODE, Constants.ERR_CLIENTE_VACIO_NULO);
            }

            Optional<Cliente> clienteOptional = this.clienteRepository.findById(clienteRequestDto.getNifCliente());
            if (!clienteOptional.isPresent())
            {
                log.error(Constants.ERR_CLIENTE_NO_ENCONTRADO);
                throw new TallerException(Constants.ERR_CLIENTE_NO_ENCONTRADO_CODE, Constants.ERR_CLIENTE_NO_ENCONTRADO);
            }

            Cliente cliente = clienteOptional.get();
            if (clienteRequestDto.getNombreCliente() != null && !clienteRequestDto.getNombreCliente().isEmpty()) {
                cliente.setNombreCliente(clienteRequestDto.getNombreCliente());
            }
            
            if (clienteRequestDto.getTelefono() != null && !clienteRequestDto.getTelefono().isEmpty()) {
                cliente.setTelefono(clienteRequestDto.getTelefono());
            }

            this.clienteRepository.saveAndFlush(cliente);

            log.info(Constants.CLIENTE_MODIFICADO);
            return ResponseEntity.status(200).build();
        }
        catch (TallerException exception)
        {
        	return ResponseEntity.status(409).body(exception.getBodyExceptionMessage());
        }
        catch (Exception exception)
        {
            log.error("Error al modificar cliente: " + exception.getMessage());
            
            TallerException tallerException = new TallerException(Constants.GENERIC_CODE, "Error al modificar el cliente.");
            		
            return ResponseEntity.status(500).body(tallerException.getBodyExceptionMessage());
        }
    }
	
	/**
     * Borra un cliente de la base de datos dado su NIF.
     * Valida que el cliente exista antes de eliminarlo.
     * 
     * @param nifCliente NIF del cliente a borrar.
     * @return ResponseEntity indicando el resultado de la operación.
     */
	@DeleteMapping(value = "/{nifCliente}")
    public ResponseEntity<?> borrarCliente(@PathVariable("nifCliente") String nifCliente)
    {
        try
        {
            if (!this.clienteRepository.existsById(nifCliente))
            {
                log.error(Constants.ERR_CLIENTE_NO_ENCONTRADO);
                throw new TallerException(Constants.ERR_CLIENTE_NO_ENCONTRADO_CODE, Constants.ERR_CLIENTE_NO_ENCONTRADO);
            }

            this.clienteRepository.deleteById(nifCliente);

            log.info(Constants.CLIENTE_ELIMINADO);
            return ResponseEntity.status(200).build();
        }
        catch (TallerException exception)
        {
        	return ResponseEntity.status(409).body(exception.getBodyExceptionMessage());
        }
        catch (Exception exception)
        {
            log.error("Error al borrar cliente: " + exception.getMessage());
            
            TallerException tallerException = new TallerException(Constants.GENERIC_CODE, "Error al borrar el cliente.");
            		
            return ResponseEntity.status(500).body(tallerException.getBodyExceptionMessage());
        }
    }
	
	/**
     * Obtiene todos los clientes de la base de datos.
     * 
     * @return ResponseEntity con la lista de clientes proyectada como ClienteResponseDto.
     */
	@GetMapping(value = "/")
    public ResponseEntity<?> obtenerClientes()
    {
		try
		{
			return ResponseEntity.status(200).body(this.clienteRepository.buscarClientes());
		}
		catch (Exception exception)
        {
            log.error("Error al obtener la lista de clientes: " + exception.getMessage());
            
            TallerException tallerException = new TallerException(Constants.GENERIC_CODE, "Error al obtener la lista de clientes.");
            		
            return ResponseEntity.status(500).body(tallerException.getBodyExceptionMessage());
        }
    }
}
