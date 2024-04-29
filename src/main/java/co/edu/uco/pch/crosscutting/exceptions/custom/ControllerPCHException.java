package co.edu.uco.pch.crosscutting.exceptions.custom;

import co.edu.uco.pch.crosscutting.exceptions.PCHException;
import co.edu.uco.pch.crosscutting.exceptions.enums.Lugar;

public class ControllerPCHException extends PCHException{

	private static final long serialVersionUID = 3291470665555273037L;
	
	protected ControllerPCHException(String mensajeTecnico, String mensajeUsuario, Lugar lugar,
			Throwable excepcionRaiz) {
		super(mensajeTecnico, mensajeUsuario, lugar, excepcionRaiz);
	
	}
	
	protected ControllerPCHException(String mensajeTecnico) {
		super(mensajeTecnico, Lugar.CONTROLLER);
	}
	

}
