package co.edu.uco.pch.crosscutting.exceptions.messageCatalog.impl;


import java.util.HashMap;
import java.util.Map;

import co.edu.uco.pch.crosscutting.exceptions.custom.CrosscuttingPCHException;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.MessageCatalog;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.MessageCatalogStrategy;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.data.CodigoMensaje;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.data.Mensaje;
import co.edu.uco.pch.crosscutting.helpers.ObjectHelper;

public final class MessageCatalogExternalService implements MessageCatalog {
	
	private final Map<String, Mensaje> mensajes = new HashMap<>();

	@Override
	public void inicializar() {
		mensajes.clear();
		mensajes.put(CodigoMensaje.M00007.getIdentificador(), new Mensaje(CodigoMensaje.M00007, "La transaccion se ha completado de forma satisfactoria..."));
		
	}

	@Override
	public String obtenerContenidoMensaje(final CodigoMensaje codigo, String... parametros) {
		return obtenerMensaje(codigo, parametros).getContenido();
	}

	@Override
	public Mensaje obtenerMensaje(final CodigoMensaje codigo, String... parametros) {
		if(ObjectHelper.isNull(codigo)) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00001);
			throw new CrosscuttingPCHException (mensajeTecnico,mensajeUsuario);
		}
		if(codigo.esBase()) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00005, codigo.getIdentificador());
			throw new CrosscuttingPCHException (mensajeTecnico,mensajeUsuario);
		}
		if(!mensajes.containsKey(codigo.getIdentificador())) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00006, codigo.getIdentificador());
			throw new CrosscuttingPCHException (mensajeTecnico,mensajeUsuario);
		}

		return mensajes.get(codigo.getIdentificador());
	}

}
