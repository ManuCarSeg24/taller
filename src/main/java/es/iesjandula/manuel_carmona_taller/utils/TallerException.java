package es.iesjandula.manuel_carmona_taller.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * Clase de excepción personalizada para la gestión de errores en el taller.
 * Permite almacenar un código de error, un mensaje y la excepción original.
 * Proporciona un método para obtener la información de la excepción en formato de mapa,
 * útil para enviar respuestas estructuradas en APIs REST.
 * 
 * Permite capturar y mostrar la traza completa de la excepción si está presente.
 * 
 * @author Manuel
 * @since 27/11/2025
 */
public class TallerException extends Exception
{
    private static final long serialVersionUID = 7636795678494164129L;

    private Integer codigo;
    private String mensaje;
    private Throwable excepcion;

    /**
     * Constructor de la excepción con código y mensaje.
     * 
     * @param codigo Código de error de la excepción.
     * @param mensaje Mensaje descriptivo de la excepción.
     */
    public TallerException (Integer codigo, String mensaje)
    {
        super();
        this.codigo = codigo;
        this.mensaje = mensaje;
    }

    /**
     * Constructor de la excepción con código, mensaje y la excepción original.
     * 
     * @param movieErrorId Código de error de la excepción.
     * @param message Mensaje descriptivo de la excepción.
     * @param excepcion Excepción original que causó el error.
     */
    public TallerException(Integer movieErrorId, String message, Throwable excepcion)
    {
        super(message, excepcion);
        this.codigo = movieErrorId;
        this.mensaje = message;
        this.excepcion = excepcion;
    }

    /**
     * Devuelve la información de la excepción en un objeto Map.
     * Incluye el código, el mensaje y, si existe, la traza de la excepción original.
     * 
     * @return Mapa con la información estructurada de la excepción.
     */
    public Object getBodyExceptionMessage()
    {
        Map<String, Object> mapBodyException = new HashMap<>();
        mapBodyException.put("codigo", this.codigo);
        mapBodyException.put("message", this.mensaje);

        if (this.excepcion != null)
        {
            String stackTrace = ExceptionUtils.getStackTrace(this.excepcion);
            mapBodyException.put("excepcion", stackTrace);
        }

        return mapBodyException;
    }

    /**
     * Devuelve el código de error de la excepción.
     * 
     * @return Código de la excepción.
     */
    public Integer getCodigo()
    {
        return this.codigo;
    }
}
