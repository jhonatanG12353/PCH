package co.edu.uco.pch.crosscutting.exceptions.custom;

import co.edu.uco.pch.crosscutting.exceptions.PCHException;
import co.edu.uco.pch.crosscutting.exceptions.enums.Lugar;

public class BusinessPCHException extends PCHException{

	private static final long serialVersionUID = -772840292783193403L;
	
	protected BusinessPCHException(String mensajeTecnico, String mensajeUsuario, Lugar lugar, Throwable excepcionRaiz) {
		super(mensajeTecnico, mensajeUsuario, lugar, excepcionRaiz);
	}
	protected BusinessPCHException(String mensajeTecnico, String mensajeUsuario) {
		super(mensajeTecnico, mensajeUsuario, Lugar.BUSINESS);
	}
	protected BusinessPCHException(String mensajeTecnico) {
		super(mensajeTecnico, Lugar.BUSINESS);
	}
	

}
