package co.edu.uco.pch.crosscutting.exceptions.custom;

import co.edu.uco.pch.crosscutting.exceptions.PCHException;
import co.edu.uco.pch.crosscutting.exceptions.enums.Lugar;

public class InitializerPCHException extends PCHException {

	private static final long serialVersionUID = 7531504679363475368L;
	
	protected InitializerPCHException(String mensajeTecnico, String mensajeUsuario, Lugar lugar,
			Throwable excepcionRaiz) {
		super(mensajeTecnico, mensajeUsuario, lugar, excepcionRaiz);
	}

	protected InitializerPCHException(String mensajeTecnico) {
		super(mensajeTecnico, Lugar.INITIALIZER);
	}
	
}
