package co.edu.uco.pch.crosscutting.exceptions.custom;

import co.edu.uco.pch.crosscutting.exceptions.PCHException;
import co.edu.uco.pch.crosscutting.exceptions.enums.Lugar;

public class BusinessPCHException extends PCHException{

	private static final long serialVersionUID = -772840292783193403L;
	
	public BusinessPCHException(String mensajeTecnico, String mensajeUsuario, Lugar lugar, Throwable excepcionRaiz) {
		super(mensajeTecnico, mensajeUsuario, lugar, excepcionRaiz);
	}
	public BusinessPCHException(String mensajeTecnico, String mensajeUsuario) {
		super(mensajeTecnico, mensajeUsuario, Lugar.BUSINESS);
	}
	public BusinessPCHException(String mensajeTecnico) {
		super(mensajeTecnico, Lugar.BUSINESS);
	}
	

}
