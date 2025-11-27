package es.iesjandula.manuel_carmona_taller.utils;

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
	
	public static final Integer ERR_CLIENTE_VACIO_NULO_CODE = 1;
	public static final String ERR_CLIENTE_VACIO_NULO = "Los datos del cliente están vacíos o son nulos";

	public static final Integer ERR_CLIENTE_EXISTE_INTERNAL_CODE = 2;
	public static final Integer ERR_CLIENTE_EXISTE_HTTP_CODE = 409;
	public static final String ERR_CLIENTE_EXISTE = "El cliente ya existe";
	
	public static final Integer CLIENTE_AGREGADO_CODE = 204;
	public static final String CLIENTE_AGREGADO = "Cliente creado con éxito";
	
	public static final String CLIENTE_MODIFICADO = "Cliente modificado con éxito";
	public static final String CLIENTE_ELIMINADO = "Cliente borrado con éxito";
	
	public static final Integer ERR_CLIENTE_NO_ENCONTRADO_CODE = 3;
	public static final String ERR_CLIENTE_NO_ENCONTRADO = "El cliente no fue encontrado";
	
	public static final Integer ERR_CLIENTE_NO_EXISTE_CODE = 405;
	public static final String ERR_CLIENTE_NO_EXISTE = "El cliente no existe";
	
	/* Errores relacionados con mecanico */
	public static final Integer ERR_MECANICO_VACIO_NULO_CODE = 4;
	public static final String ERR_MECANICO_VACIO_NULO = "Los datos del mecánico están vacíos o son nulos";

	public static final Integer ERR_MECANICO_EXISTE_INTERNAL_CODE = 5;
	public static final Integer ERR_MECANICO_EXISTE_HTTP_CODE = 409;
	public static final String ERR_MECANICO_EXISTE = "El mecánico ya existe";
	
	public static final Integer MECANICO_AGREGADO_CODE = 204;
	public static final String MECANICO_AGREGADO = "Mecánico creado con éxito";
	
	public static final String MECANICO_MODIFICADO = "Mecánico modificado con éxito";
	public static final String MECANICO_ELIMINADO = "Mecánico borrado con éxito";
	
	public static final Integer ERR_MECANICO_NO_ENCONTRADO_CODE = 6;
	public static final String ERR_MECANICO_NO_ENCONTRADO = "El mecánico no fue encontrado";
	
	public static final Integer ERR_MECANICO_NO_EXISTE_CODE = 404;
	public static final String ERR_MECANICO_NO_EXISTE = "El mecanico no existe";
	
	// Errores relacionados con reservas 
	public static final Integer ERR_REPARACION_NIFS_NULOS_O_VACIOS_CODE = 7;
	public static final String ERR_REPARACION_NIFS_NULOS_O_VACIOS = "Uno o ambos NIF son incorrectos debido a que viene nulos o vacíos";
	
	public static final Integer ERR_REPARACION_YA_REGISTRADA_CODE = 8;
	public static final String ERR_REPARACION_YA_REGISTRADA = "La reparación ya está registrada";
	
	public static final Integer REPARACION_AGREGADOA_CODE = 204;
	public static final String REPARACION_AGREGADA = "Reparación creada con éxito";
	
	public static final String REPARACION_MODIFICADA = "Reparación modificada con éxito";
	public static final String REPARACION_ELIMINADA = "Reparación borrada con éxito";
	
	public static final Integer ERR_REPARACION_INVALIDA_CODE = 406;
	public static final String ERR_REPARACION_INVALIDA = "Los datos de la reparación no son válidos";
	
	public static final Integer ERR_REPARACION_NO_ENCONTRADA_CODE = 9;
	public static final String ERR_REPARACION_NO_ENCONTRADA = "La reparación no fue encontrada";
	
	public static final Integer ERROR_REPARACION_NIFS_NO_ENCONTRADOS_CODE = 10;
	public static final String ERROR_REPARACION_NIFS_NO_ENCONTRADOS = "Uno o ambos NIF no han sido encontrados.";
	
	// Mensajes generales 
	public static final Integer GENERIC_CODE = 99;
	public static final String ELEMENTO_AGREGADO = "Elemento agregado correctamente";
	public static final String ELEMENTO_MODIFICADO = "Elemento modificado correctamente";
	public static final String ELEMENTO_ELIMINADO = "Elemento eliminado correctamente";
	
}
