package es.iesjandula.manuel_carmona_taller.utils;

import java.security.PublicKey;

/**
 * Clase que centraliza todas las constantes utilizadas en el proyecto del taller.
 * Incluye códigos de error, mensajes de error y mensajes generales para operaciones CRUD.
 * Facilita la gestión uniforme de mensajes y códigos de estado a lo largo de la aplicación.
 * 
 * @author Manuel
 * @since 27/11/2025
 */
public class Constants
{
	/* Errores relacionados con cliente */
	public static final Integer ERR_CLIENTE_NOT_FOUND_CODE = 1;
	public static final String ERR_CLIENTE_NOT_FOUND = "El cliente no fue encontrada";
	
	public static final Integer ERR_CLIENTE_ALREADY_EXISTS_CODE = 409;
	public static final String ERR_CLIENTE_ALREADY_EXISTS = "El cliente ya existe";
	
	public static final Integer CLIENTE_AGREGADO_CODE = 204;
	public static final String  CLIENTE_AGREGADO = "Mecanico creado con éxito";
	
	public static final Integer ERR_CLIENTE_EMPTY_CODE = 3;
	public static final String ERR_CLIENTE_EMPTY = "Los datos del cliente están vacíos o son nulos";

	/* Errores relacionados con mecanico */
	public static final Integer ERR_MECANICO_NOT_FOUND_CODE = 4;
	public static final String ERR_MECANICO_NOT_FOUND = "El mecanico no fue encontrado";

	public static final Integer ERR_MECANICO_ALREADY_EXISTS_CODE = 409;
	public static final String ERR_MECANICO_ALREADY_EXISTS = "El mecanico ya existe";
	
	public static final Integer MECANICO_AGREGADO_CODE = 204;
	public static final String MECANICO_AGREGADO = "Mecanico creado con éxito";
	
	public static final Integer ERR_MECANICO_EMPTY_CODE = 6;
	public static final String ERR_MECANICO_EMPTY = "Los datos del mecanico están vacíos o son nulos";

	/* Errores relacionados con reservas */
	public static final Integer ERR_REPARACION_NIFS_NULOS_O_VACIOS_CODE = 7;
	public static final String ERR_REPARACION_NIFS_NULOS_O_VACIOS_DESC = "Uno o ambos NIF son incorrectos debido a que viene nulos o vacíos";
	
	/** */
	public static final Integer ERROR_REPARACION_NIF_CLIENTE_NO_ENCONTRADO_INTERNAL_CODE = 8 ;
	public static final Integer ERROR_REPARACION_NIF_CLIENTE_NO_ENCONTRADO_HTTP_CODE = 402 ;
	public static final String ERROR_REPARACION_NIF_CLIENTE_NO_ENCONTRADO_DESC = "El NIF del cliente no ha sido encontrado en BBDD";
	
	public static final String ERR_REPARACION_NOT_AVAILABLE = "La reparación ya está registrada";
	public static final String ERR_REPARACION_NOT_FOUND = "La reparación no fue encontrada";
	
	public static final Integer ERR_MECANICO_NO_EXISTS_CODE = 404;
	public static final String ERR_MECANICO_NO_EXISTS = "El mecanico no existe";
	
	public static final Integer ERR_CLIENTE_NO_EXISTS_CODE = 405;
	public static final String ERR_CLIENTE_NO_EXISTS = "El cliente no existe";
	
	public static final Integer ERR_REPARACION_INVALIDA_CODE = 406;
	public static final String ERR_REPARACION_INVALIDA = "Los datos de la reparación no son válidos";


	/* Mensajes generales */
	public static final Integer GENERIC_CODE = 99;
	public static final String ELEMENTO_AGREGADO = "Elemento agregado correctamente";
	public static final String ELEMENTO_MODIFICADO = "Elemento modificado correctamente";
	public static final String ELEMENTO_ELIMINADO = "Elemento eliminado correctamente";
}
