package co.edu.uco.pch.crosscutting.exceptions.custom;

import co.edu.uco.pch.crosscutting.exceptions.PCHException;
import co.edu.uco.pch.crosscutting.exceptions.enums.Lugar;

public class EntityPCHException extends PCHException{
	
	private static final long serialVersionUID = 2986087650664440709L;
	
	protected EntityPCHException(String mensajeTecnico, String mensajeUsuario, Lugar lugar, Throwable excepcionRaiz) {
		super(mensajeTecnico, mensajeUsuario, lugar, excepcionRaiz);
	}

	protected EntityPCHException(String mensajeTecnico) {
		super(mensajeTecnico, Lugar.ENTITY);
	}

}
