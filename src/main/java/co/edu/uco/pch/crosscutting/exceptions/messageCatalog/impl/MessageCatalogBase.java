package co.edu.uco.pch.crosscutting.exceptions.messageCatalog.impl;

import java.util.HashMap;
import java.util.Map;

import co.edu.uco.pch.crosscutting.exceptions.custom.CrosscuttingPCHException;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.MessageCatalog;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.data.CodigoMensaje;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.data.Mensaje;
import co.edu.uco.pch.crosscutting.helpers.ObjectHelper;

public final class MessageCatalogBase  implements MessageCatalog{

	private final Map<String, Mensaje> mensajes = new HashMap<>();
	@Override
	public void inicializar() {
		mensajes.clear();
		mensajes.put(CodigoMensaje.M00001.getIdentificador(), new Mensaje(CodigoMensaje.M00001, "El codigo del mensaje que se quiere obtener del catalogo de mensajes llego nulo..."));
		mensajes.put(CodigoMensaje.M00002.getIdentificador(), new Mensaje(CodigoMensaje.M00002, "Se ha presentado un problema de llevar a cabo la operacion deseada..."));
		mensajes.put(CodigoMensaje.M00003.getIdentificador(), new Mensaje(CodigoMensaje.M00003, "El identificador del mensaje \"${1}\" que se intento obtener no está en el catalogo de mensaje base"));
		mensajes.put(CodigoMensaje.M00004.getIdentificador(), new Mensaje(CodigoMensaje.M00004, "El mensaje con identificador \"${1}\" que se intento obtener no está configurado para recibir en el catalogo base"));
	}

	@Override
	public String obtenerContenidoMensaje(final CodigoMensaje codigo, String... parametros) {
		
		return obtenerMensaje(codigo, parametros).getContenido();
	}

	@Override
	public Mensaje obtenerMensaje(final CodigoMensaje codigo, String... parametros) {
		if(ObjectHelper.isNull(codigo)) {
			var mensajeUsuario = obtenerContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = obtenerContenidoMensaje(CodigoMensaje.M00001);
			throw new CrosscuttingPCHException (mensajeTecnico,mensajeUsuario);
		}
		if(!codigo.esBase()) {
			var mensajeUsuario = obtenerContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = obtenerContenidoMensaje(CodigoMensaje.M00004, codigo.getIdentificador());
			throw new CrosscuttingPCHException (mensajeTecnico,mensajeUsuario);
		}
		if(!mensajes.containsKey(codigo.getIdentificador())) {
			var mensajeUsuario = obtenerContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = obtenerContenidoMensaje(CodigoMensaje.M00003, codigo.getIdentificador());
			throw new CrosscuttingPCHException (mensajeTecnico,mensajeUsuario);
		}
		//TODO: asegurarse que si tiene parametros el contenido de mensaje se retorno con los parametro reemplazado
		// {1}, {2}
		return mensajes.get(codigo.getIdentificador());
	}

	

}
