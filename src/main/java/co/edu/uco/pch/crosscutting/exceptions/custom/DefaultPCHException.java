package co.edu.uco.pch.crosscutting.exceptions.custom;

import co.edu.uco.pch.crosscutting.exceptions.PCHException;
import co.edu.uco.pch.crosscutting.exceptions.enums.Lugar;

public class DefaultPCHException extends  PCHException {

	private static final long serialVersionUID = 8373546155402014400L;

	protected DefaultPCHException(String mensajeTecnico, String mensajeUsuario, Lugar lugar, Throwable excepcionRaiz) {
		super(mensajeTecnico, mensajeUsuario, lugar, excepcionRaiz);
	}
	
	protected DefaultPCHException(String mensajeTecnico) {
		super(mensajeTecnico, Lugar.DEFAULT);
	}

}
