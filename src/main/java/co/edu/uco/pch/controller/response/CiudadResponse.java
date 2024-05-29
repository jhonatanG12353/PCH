package co.edu.uco.pch.controller.response;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.pch.dto.CiudadDTO;

public class CiudadResponse extends Response <CiudadDTO>{
	
	public CiudadResponse () {
		setMensajes(new ArrayList<>());
		setDatos(new ArrayList<>());
	}
	public CiudadResponse (final List<CiudadDTO> datos, final List<String> mensajes) {
		setMensajes(mensajes);
		setDatos(datos);
	}
	

}
