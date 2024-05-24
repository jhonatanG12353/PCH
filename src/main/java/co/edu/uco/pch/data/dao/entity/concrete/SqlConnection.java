package co.edu.uco.pch.data.dao.entity.concrete;

import java.sql.Connection;

import co.edu.uco.pch.crosscutting.exceptions.custom.DataPCHException;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.MessageCatalogStrategy;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.data.CodigoMensaje;
import co.edu.uco.pch.crosscutting.helpers.SQLHelper;

public class SqlConnection {

	private Connection conexion;
	
	protected SqlConnection(Connection conexion) {
		setConexion(conexion);
	}
	protected SqlConnection() {
		super();
	}

	protected Connection getConexion() {
		return conexion;
	}

	protected final void setConexion(Connection conexion) {
		if(!SQLHelper.isOpen(conexion)) {
			var mensajeUsuario =MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00001);
			var mensajeTecnicoo =MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00023);			
			throw new DataPCHException(mensajeTecnicoo, mensajeUsuario);
		}
		this.conexion = conexion;
	}

}
