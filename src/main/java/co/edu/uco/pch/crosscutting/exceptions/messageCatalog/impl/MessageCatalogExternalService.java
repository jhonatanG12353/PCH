package co.edu.uco.pch.crosscutting.exceptions.messageCatalog.impl;

import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.MessageCatalog;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.data.CodigoMensaje;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.data.Mensaje;

public final class MessageCatalogExternalService implements MessageCatalog {

	@Override
	public void inicializar() {
		
	}

	@Override
	public String obtenerContenidoMensaje(final CodigoMensaje codigo, String... parametros) {
		return null;
	}

	@Override
	public Mensaje obtenerMensaje(final CodigoMensaje codigo, String... parametros) {
		return null;
	}

}
