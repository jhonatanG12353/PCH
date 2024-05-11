package co.edu.uco.pch.crosscutting.exceptions.custom;

import co.edu.uco.pch.crosscutting.exceptions.PCHException;
import co.edu.uco.pch.crosscutting.exceptions.enums.Lugar;

public class CrosscuttingPCHException extends PCHException {

	private static final long serialVersionUID = 4526879296082603189L;

	public CrosscuttingPCHException(String mensajeTecnico, String mensajeUsuario, Lugar lugar,
			Throwable excepcionRaiz) {
		super(mensajeTecnico, mensajeUsuario, lugar, excepcionRaiz);
	}
	public CrosscuttingPCHException(String mensajeTecnico, String mensajeUsuario) {
		super(mensajeTecnico, mensajeUsuario, Lugar.CROSSCUTTING );
	}
	public CrosscuttingPCHException(String mensajeTecnico, String mensajeUsuario, Throwable excepcioRaiz) {
		super(mensajeTecnico, mensajeUsuario, Lugar.CROSSCUTTING, excepcioRaiz );
	}
	
	public CrosscuttingPCHException(String mensajeTecnico) {
		super(mensajeTecnico, Lugar.CROSSCUTTING);
	}

}
