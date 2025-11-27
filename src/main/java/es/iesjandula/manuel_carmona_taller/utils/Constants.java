package es.iesjandula.manuel_carmona_taller.utils;

public class Constants
{
	/* Errores relacionados con cliente */
	public static final Integer ERR_CLIENTE_CODE = 1;
	public static final String ERR_CLIENTE_NOT_FOUND = "El cliente no fue encontrada";
	
	public static final Integer ERR_CLIENTE_ALREADY_EXISTS_CODE = 2;
	public static final String ERR_CLIENTE_ALREADY_EXISTS = "El cliente ya existe";
	
	public static final Integer ERR_CLIENTE_EMPTY_CODE = 3;
	public static final String ERR_CLIENTE_EMPTY = "Los datos del cliente están vacíos o son nulos";

	/* Errores relacionados con mecanico */
	public static final Integer ERR_MECANICO_NOT_FOUND_CODE = 4;
	public static final String ERR_MECANICO_NOT_FOUND = "El mecanico no fue encontrado";

	public static final Integer ERR_MECANICO_ALREADY_EXISTS_CODE = 409;
	public static final String ERR_MECANICO_ALREADY_EXISTS = "El mecanico ya existe";
	
	public static final Integer MECANICO_AGREGADO_CODE = 204;
	public static final String MECANICO_AGREGADO = "Cliente creado con éxito";
	
	public static final Integer ERR_MECANICO_EMPTY_CODE = 6;
	public static final String ERR_MECANICO_EMPTY = "Los datos del mecanico están vacíos o son nulos";

	/* Errores relacionados con reservas */
	public static final Integer ERR_REPARACION_CODE = 7;
	public static final String ERR_REPARACION_NOT_AVAILABLE = "La reparación ya está registrada";
	public static final String ERR_REPARACION_NOT_FOUND = "La reparación no fue encontrada";

	/* Mensajes generales */
	public static final Integer GENERIC_CODE = 99;
	public static final String ELEMENTO_AGREGADO = "Elemento agregado correctamente";
	public static final String ELEMENTO_MODIFICADO = "Elemento modificado correctamente";
	public static final String ELEMENTO_ELIMINADO = "Elemento eliminado correctamente";
}
