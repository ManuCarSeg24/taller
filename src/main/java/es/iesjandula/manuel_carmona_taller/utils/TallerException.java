package es.iesjandula.manuel_carmona_taller.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;


public class TallerException extends Exception
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7636795678494164129L;
	
	private Integer codigo;
	
	private String mensaje;
	
	private Throwable excepcion ;
	
	public TallerException (Integer codigo, String mensaje)
	{
		super();
		
		this.codigo=codigo;
		this.mensaje= mensaje;
	}
	
	public TallerException(Integer movieErrorId, String message, Throwable excepcion)
	{
		super(message, excepcion);
		
		this.codigo=movieErrorId;
		this.mensaje= message;
		this.excepcion= excepcion ;
	}
	
	public Object getBodyExceptionMessage()
	{
		Map<String, Object> mapBodyException = new HashMap<>() ;
		
		mapBodyException.put("codigo", this.codigo) ;
		mapBodyException.put("message", this.mensaje) ;
		
		if (this.excepcion != null)
		{
			String stackTrace = ExceptionUtils.getStackTrace(this.excepcion) ;
			mapBodyException.put("excepcion", stackTrace) ;
		}
		
		return mapBodyException ;
	}
	
	public Integer getCodigo()
	{
		return this.codigo ;
	}
}
