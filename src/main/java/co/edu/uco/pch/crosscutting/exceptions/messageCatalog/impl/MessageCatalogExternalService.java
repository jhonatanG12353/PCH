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
		mensajes.put(CodigoMensaje.M00041.getIdentificador(), new Mensaje(CodigoMensaje.M00041, "Se ha presentado un problema tratando de llevar a cabo el registro de la ciudad"));
		mensajes.put(CodigoMensaje.M00042.getIdentificador(), new Mensaje(CodigoMensaje.M00042, "Se ha presentado un problema tratando de llevar a cabo el registro de la ciudad"));
		mensajes.put(CodigoMensaje.M00043.getIdentificador(), new Mensaje(CodigoMensaje.M00043, "Ya existe una ciudad con el nombre \\\"${1}\\\" asociado al departamento deseado"));
		mensajes.put(CodigoMensaje.M00044.getIdentificador(), new Mensaje(CodigoMensaje.M00044, "La longitud del codigo del Registrar Ciudad no es valida. la longitud Maxima son 50 caracteres"));
		mensajes.put(CodigoMensaje.M00045.getIdentificador(), new Mensaje(CodigoMensaje.M00045, "Es necesarios que ingreses \\\"${1}\\\" para optener la transacción..."));
		mensajes.put(CodigoMensaje.M00046.getIdentificador(), new Mensaje(CodigoMensaje.M00046, "El dato \\\"${1}\\\" solo puede contener letras..."));
		mensajes.put(CodigoMensaje.M00047.getIdentificador(), new Mensaje(CodigoMensaje.M00047, "Se ha presentado un prolema tratando de llevar a cabo el consultar de ciudad"));
		mensajes.put(CodigoMensaje.M00048.getIdentificador(), new Mensaje(CodigoMensaje.M00048, "El dao factory para consultar la ciudad llego nulo"));
		mensajes.put(CodigoMensaje.M00049.getIdentificador(), new Mensaje(CodigoMensaje.M00049, "Ya existe una ciudad con el nombre \\\"${1}\\\" asociado al departamento deseado"));
		mensajes.put(CodigoMensaje.M00050.getIdentificador(), new Mensaje(CodigoMensaje.M00050, "La longitud del codigo Consultar ciudad  no es valida. la longitud Maxima son 50 caracteres"));
		mensajes.put(CodigoMensaje.M00051.getIdentificador(), new Mensaje(CodigoMensaje.M00051, "Se ha presentado un prolema tratando de llevar a cabo la modificacion de ciudad"));
		mensajes.put(CodigoMensaje.M00052.getIdentificador(), new Mensaje(CodigoMensaje.M00052, "Se ha presentado un prolema tratando de llevar a cabo el eliminar de ciudad"));
		mensajes.put(CodigoMensaje.M00053.getIdentificador(), new Mensaje(CodigoMensaje.M00053, "El dao factory para eliminar la ciudad llego nulo"));
		mensajes.put(CodigoMensaje.M00054.getIdentificador(), new Mensaje(CodigoMensaje.M00054, "El dao factory para modificar la ciudad llego nulo"));
		mensajes.put(CodigoMensaje.M00055.getIdentificador(), new Mensaje(CodigoMensaje.M00055, "Ya existe una ciudad con el nombre \\\"${1}\\\" asociado al departamento deseado"));
		mensajes.put(CodigoMensaje.M00056.getIdentificador(), new Mensaje(CodigoMensaje.M00056, "La longitud del codigo del ciudad no es valida. la longitud Maxima son 50 caracteres"));
		mensajes.put(CodigoMensaje.M00057.getIdentificador(), new Mensaje(CodigoMensaje.M00057, "Es necesarios que ingreses \\\"${1}\\\" para optener la transacción..."));
		mensajes.put(CodigoMensaje.M00058.getIdentificador(), new Mensaje(CodigoMensaje.M00058, "El dato \\\"${1}\\\" solo puede contener letras..."));
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
