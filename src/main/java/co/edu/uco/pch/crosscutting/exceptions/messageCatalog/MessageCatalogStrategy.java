package co.edu.uco.pch.crosscutting.exceptions.messageCatalog;

import co.edu.uco.pch.crosscutting.exceptions.custom.CrosscuttingPCHException;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.data.CodigoMensaje;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.data.Mensaje;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.impl.MessageCatalogBase;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.impl.MessageCatalogExternalService;
import co.edu.uco.pch.crosscutting.helpers.ObjectHelper;
import co.edu.uco.pch.crosscutting.helpers.TextHelper;

public final class MessageCatalogStrategy {

	private static final MessageCatalog base = new MessageCatalogBase();
	private static final MessageCatalog externalService = new MessageCatalogExternalService();
	
	static {
		inicializar ();
	}
	
	private MessageCatalogStrategy() {
		super();
	}
	
	public static void inicializar() {
		base.inicializar();
		externalService.inicializar();
	}
	private static final MessageCatalog getStrategy(final boolean isBase) {
		return isBase  ? base : externalService;
	}

	public static final Mensaje getMensaje(final CodigoMensaje codigo, final String... parametros) {
		
		if(ObjectHelper.isNull(codigo)) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00001);
			throw new CrosscuttingPCHException (mensajeTecnico,mensajeUsuario);
		}
		
		return getStrategy(codigo.esBase()).obtenerMensaje(codigo, parametros);
	}
	
	public static final String getContenidoMensaje (final CodigoMensaje codigo, final String... parametros) {
		return TextHelper.reemplazarParametro(getMensaje(codigo, parametros).getContenido(), parametros) ;
	}
	
}
