package co.edu.uco.pch.data.dao.entity.concrete.azuresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import co.edu.uco.pch.crosscutting.exceptions.custom.DataPCHException;
import co.edu.uco.pch.data.dao.entity.CiudadDAO;
import co.edu.uco.pch.data.dao.entity.concrete.SqlConnection;
import co.edu.uco.pch.entity.CiudadEntity;

public final class CiudadAzureSqlDAO extends SqlConnection implements CiudadDAO {

	public CiudadAzureSqlDAO(final Connection conexion) {
		super(conexion);
	}

	@Override
	public final void crear(CiudadEntity data) {
		final StringBuilder sentenciaSql = new StringBuilder();
		
		sentenciaSql.append("INSERT INTO Ciudad (id, nombre, departamento) ");
		sentenciaSql.append("SELECT ? , ? ,?");
		
		try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())){
			sentenciaSqlPreparada.setObject(1, data.getid());
			sentenciaSqlPreparada.setString(2, data.getNombre());
			sentenciaSqlPreparada.setObject(2, data.getDepartamento().getid());
			
			sentenciaSqlPreparada.executeUpdate();
			
		} catch(SQLException exception) {
			var mensajeUsuario = "Se ha presentado un problema tratando de crear la ciudad \"${1}\". Por favor intente de nuevo y si el problema persiste contacte al administrador";
			var mensajeTecnico = "Se ha presentado una excepcion de tipo SQL exception tratando realizar  el insert de la ciudad \"${1}\" en la tabla \"Pais\" de la tabla AzureSQL, para mas detalles, revise de forma completa la excepcion raíz presentada... ";
			throw new DataPCHException(mensajeUsuario, mensajeTecnico, exception);
		}
		catch(Exception exception) {
			var mensajeUsuario = "Se ha presentado un problema tratando de crear la ciudad \"${1}\". Por favor intente de nuevo y si el problema persiste contacte al administrador";
			var mensajeTecnico = "Se ha presentado  un problema INESPERADO con una excepcion de tipo SQL exception tratando realizar  el insert de la ciudad \"${1}\" en la tabla \"Pais\" de la tabla AzureSQL, para mas detalles, revise de forma completa la excepcion raíz presentada... ";
			throw new DataPCHException(mensajeUsuario, mensajeTecnico, exception);
		}
		
	}

	@Override
	public final List<CiudadEntity> consultar(CiudadEntity data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public final void modificar(CiudadEntity data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public final void eliminar(UUID id) {
		// TODO Auto-generated method stub
		
	}

}
