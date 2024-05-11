package co.edu.uco.pch.crosscutting.exceptions.custom;

import co.edu.uco.pch.crosscutting.exceptions.PCHException;
import co.edu.uco.pch.crosscutting.exceptions.enums.Lugar;

public final class DataPCHException extends PCHException{

	private static final long serialVersionUID = -3786840150696753545L;
	
	public DataPCHException(String mensajeUsuario, String mensajeTecnico, Lugar lugar, Throwable excepcion) {
		super(mensajeTecnico, mensajeUsuario, Lugar.DATA, excepcion);
	}
	public DataPCHException(String mensajeUsuario, String mensajeTecnico) {
		super(mensajeTecnico, mensajeUsuario,Lugar.DATA);
	}
	public DataPCHException(String mensajeUsuario, String mensajeTecnico , Throwable excepcionRaiz) {
		super(mensajeTecnico, mensajeUsuario,Lugar.DATA, excepcionRaiz);
	}
	
	public DataPCHException(String mensajeUsuario ) {
		super(mensajeUsuario, Lugar.DATA);
	}

}
