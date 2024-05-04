package co.edu.uco.pch.crosscutting.exceptions.custom;

import co.edu.uco.pch.crosscutting.exceptions.PCHException;
import co.edu.uco.pch.crosscutting.exceptions.enums.Lugar;

public class DTOPCHException extends PCHException{

	
	private static final long serialVersionUID = 4880724831403018987L;

	protected DTOPCHException(String mensajeTecnico, String mensajeUsuario, Lugar lugar, Throwable excepcionRaiz) {
		super(mensajeTecnico, mensajeUsuario, lugar, excepcionRaiz);
		
	}
	protected DTOPCHException(String mensajeTecnico, String mensajeUsuario) {
		super(mensajeTecnico, mensajeUsuario, Lugar.DTO);
		
	}
	protected DTOPCHException(String mensajeTecnico) {
		super(mensajeTecnico, Lugar.DTO);
		
	}

}
