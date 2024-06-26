package co.edu.uco.pch.data.dao.entity.concrete.azuresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.pch.crosscutting.exceptions.custom.DataPCHException;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.MessageCatalogStrategy;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.data.CodigoMensaje;
import co.edu.uco.pch.crosscutting.helpers.ObjectHelper;
import co.edu.uco.pch.crosscutting.helpers.TextHelper;
import co.edu.uco.pch.crosscutting.helpers.UUIDHelper;
import co.edu.uco.pch.data.dao.entity.CiudadDAO;
import co.edu.uco.pch.data.dao.entity.concrete.SqlConnection;
import co.edu.uco.pch.entity.CiudadEntity;
import co.edu.uco.pch.entity.DepartamentoEntity;

public final class CiudadAzureSqlDAO extends SqlConnection implements CiudadDAO {

	public CiudadAzureSqlDAO(final Connection conexion) {
		super(conexion);
	}

	@Override
	public final void crear(CiudadEntity data) { 
		final StringBuilder sentenciaSql = new StringBuilder();
		
		sentenciaSql.append("INSERT INTO Ciudad ( id , nombre , departamento ) ");
		sentenciaSql.append(" VAlUES ( ? , ? , ? )");
		
		try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())){
			sentenciaSqlPreparada.setObject(1, data.getid());
			sentenciaSqlPreparada.setString(2, data.getNombre());
			sentenciaSqlPreparada.setObject(3, data.getDepartamento().getId());
			
			sentenciaSqlPreparada.executeUpdate();
			
		} catch(SQLException exception) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00030);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00031);
			throw new DataPCHException(mensajeUsuario, mensajeTecnico, exception);
		}
		catch(Exception exception) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00030);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00032);
			throw new DataPCHException(mensajeUsuario, mensajeTecnico, exception);
		}
		
	}
	
	@Override
	public final List<CiudadEntity> consultar(CiudadEntity data) {
		List<CiudadEntity> resultado = new ArrayList<>();
		var parametros = new ArrayList<Object>();
		
		String sentenciaSql = formarSentenciaConsulta(data,parametros);
			
	
				try (final PreparedStatement sentenciaPreparada = getConexion().prepareStatement(sentenciaSql)) {
					colocarParametrosConsulta(sentenciaPreparada, parametros );					        
				        try (final ResultSet resultadoConsulta = sentenciaPreparada.executeQuery()) {
		
				            while (resultadoConsulta.next()) {
				                CiudadEntity ciudad = new CiudadEntity();
				                ciudad.setid( UUIDHelper.convertToUUID(resultadoConsulta.getString("id")) );
				                ciudad.setNombre(resultadoConsulta.getString("nombre"));
				                DepartamentoEntity departamento = new DepartamentoEntity();
				                departamento.setId( UUIDHelper.convertToUUID(resultadoConsulta.getString("id")) );
				                
				                resultado.add(ciudad);
				            }
				        }				
				}
				 catch (final SQLException excepcion) {
			        var mensajeUsuario = "Se ha presentado un problema tratando de consultar las ciudades...";
			        var mensajeTecnico = "Se ha presentado un problema de tipo SQLException en el método consultar de la clase CiudadAzureSqlDAO tratando de realizar la consulta de ciudades \"${1}\". Por favor, revise la traza completa del problema presentado para identificar lo que sucedió...";
			        throw new DataPCHException(mensajeUsuario, mensajeTecnico, excepcion);
			    } 
				catch (final Exception excepcion) {
			        var mensajeUsuario = "Se ha presentado un problema tratando de consultar las ciudades...";
			        var mensajeTecnico = "Se ha presentado un problema de tipo Exception en el método consultar de la clase CiudadAzureSqlDAO tratando de realizar la consulta de ciudades \"${1}\". Por favor, revise la traza completa del problema presentado para identificar lo que sucedió...";
			        throw new DataPCHException(mensajeUsuario, mensajeTecnico, excepcion);
			    }
			
		    return resultado;
		}
	private final void colocarParametrosConsulta(final PreparedStatement sentenciaPreparada, final List<Object> parametros ) {
		try {
			for (int indice = 0; indice < parametros.size(); indice++) {
				sentenciaPreparada.setObject(indice + 1 , parametros.get(indice));
			}
			
		}catch(final SQLException excepcion) {
			var mensajeUsuario = "Se ha presentado un problema tratando de consultar las ciudades...";
			var mensajeTecnico = "Se ha presentado un problema tratando de consultar las ciudades...";
			throw new DataPCHException(mensajeUsuario, mensajeTecnico, excepcion);
		}
		catch(final Exception excepcion) {
			var mensajeUsuario = "Se ha presentado un problema tratando de consultar las ciudades...";
			var mensajeTecnico =  "Se ha presentado un problema tratando de consultar las ciudades...";
			throw new DataPCHException(mensajeUsuario, mensajeTecnico, excepcion);
		}
	}
	private final String formarSentenciaConsulta(CiudadEntity data , List<Object> parametros) {
		
		final StringBuilder sentenciaSql = new StringBuilder();
		String operadorCondicional ="WHERE";
		
		sentenciaSql.append("SELECT id , nombre , departamento FROM Ciudad ");
		if(!ObjectHelper.isNull(data)) {
			
			if(!ObjectHelper.isNull(data.getid())&& !UUIDHelper.isDefault(data.getid())) {
				sentenciaSql.append(operadorCondicional).append(" id = ? ");
				operadorCondicional = " AND";
				parametros.add(data.getid());
			}
			if(!TextHelper.isNullOrEmpty(data.getNombre())) {
				sentenciaSql.append(operadorCondicional).append(" nombre = ? ");
				operadorCondicional = " AND";
				parametros.add(data.getNombre());
			}
			
			if(!UUIDHelper.isNull(data.getDepartamento().getId())&& !UUIDHelper.isDefault(data.getDepartamento().getId())) {
				sentenciaSql.append(operadorCondicional).append(" departamento = ? ");
				parametros.add(data.getDepartamento().getId());
			}
		}
		sentenciaSql.append("ORDER BY nombre ");
		
		return sentenciaSql.toString();

	}

	@Override
	public final void modificar(CiudadEntity data) {
		final StringBuilder sentenciaSql = new StringBuilder();
		
		sentenciaSql.append("UPDATE Ciudad ");
		sentenciaSql.append ("SET nombre = ? , departamento = ? ");
		sentenciaSql.append ("WHERE id = ?");
		
		try(final var sentenciaPreparada = getConexion().prepareStatement(sentenciaSql.toString()) ) {
	        
	        sentenciaPreparada.setString(1, data.getNombre());
	        sentenciaPreparada.setObject(2, data.getDepartamento().getId());
	        sentenciaPreparada.setObject(3, data.getid());
	        
	        
	        sentenciaPreparada.executeUpdate();
	    }
	    catch(final SQLException excepcion){
	        var mensajeUsuario = "se ha presentado un problema tratando de modificar una Ciudad";
	        var mensajeTecnico = "Se ha presentado un problema de tipo SQLException en el método modificar de la clase CiudadAzureSqlDAO tratando de llevar a cabo la modificación del Ciudad \"${1}\". Por favor, revise la traza completa del problema presentado para identificar lo que sucedió...";
	        throw new DataPCHException(mensajeUsuario, mensajeTecnico, excepcion);
	    } catch (final Exception excepcion) {
	        var mensajeUsuario = "se ha presentado un problema tratando de modificar una Ciudad";
	        var mensajeTecnico = "Se ha presentado un problema de tipo Exception en el método modificar de la clase CiudadAzureSqlDAO tratando de llevar a cabo la modificación del Ciudad \"${1}\". Por favor, revise la traza completa del problema presentado para identificar lo que sucedió...";
	        throw new DataPCHException(mensajeUsuario, mensajeTecnico, excepcion);
	    }
	}

	@Override
	public final void eliminar(UUID id) {
		final StringBuilder sentenciaSql = new StringBuilder();
		
		sentenciaSql.append("DELETE FROM Ciudad ");
		sentenciaSql.append ("WHERE id = ?");
	    
	    try(final var sentenciaPreparada = getConexion().prepareStatement(sentenciaSql.toString()) ) {
	        sentenciaPreparada.setObject(1, id);
	        sentenciaPreparada.executeUpdate();
	    } catch (final SQLException excepcion) {
	        var mensajeUsuario = "Se ha presentado un problema tratando de eliminar la información de la Ciudad...";
	        var mensajeTecnico = "Se ha presentado un problema de tipo SQLException en el método eliminar de la clase CiudadAzureSqlDAO tratando de eliminar la Ciudad \"${1}\". Por favor, revise la traza completa del problema presentado para identificar lo que sucedió...";
	        throw new DataPCHException(mensajeUsuario, mensajeTecnico, excepcion);
	    } catch (final Exception excepcion) {
	        var mensajeUsuario = "Se ha presentado un problema tratando de eliminar la información del cliente...";
	        var mensajeTecnico = "Se ha presentado un problema de tipo Exception en el método eliminar de la clase CiudadAzureSqlDAO tratando de eliminar la Ciudad \"${1}\". Por favor, revise la traza completa del problema presentado para identificar lo que sucedió...";
	        throw new DataPCHException(mensajeUsuario, mensajeTecnico, excepcion);
	    }
		
	}

}
