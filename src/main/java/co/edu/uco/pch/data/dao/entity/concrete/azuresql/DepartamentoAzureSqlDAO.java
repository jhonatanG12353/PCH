package co.edu.uco.pch.data.dao.entity.concrete.azuresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.pch.crosscutting.exceptions.custom.DataPCHException;
import co.edu.uco.pch.crosscutting.helpers.ObjectHelper;
import co.edu.uco.pch.crosscutting.helpers.TextHelper;
import co.edu.uco.pch.crosscutting.helpers.UUIDHelper;
import co.edu.uco.pch.data.dao.entity.DepartamentoDAO;
import co.edu.uco.pch.data.dao.entity.concrete.SqlConnection;
import co.edu.uco.pch.entity.DepartamentoEntity;
import co.edu.uco.pch.entity.PaisEntity;

public class DepartamentoAzureSqlDAO extends SqlConnection implements DepartamentoDAO{
	
	
	public DepartamentoAzureSqlDAO(Connection conexion) {
		super(conexion);
	}

	
	@Override
	public final List<DepartamentoEntity> consultar(DepartamentoEntity data) {
		List<DepartamentoEntity> resultado = new ArrayList<>();
		var parametros = new ArrayList<Object>();
		
		String sentenciaSql = formarSentenciaConsulta(data,parametros);
			
	
				try (final PreparedStatement sentenciaPreparada = getConexion().prepareStatement(sentenciaSql)) {
					colocarParametrosConsulta(sentenciaPreparada, parametros );					        
				        try (final ResultSet resultadoConsulta = sentenciaPreparada.executeQuery()) {
		
				            while (resultadoConsulta.next()) {
				            	DepartamentoEntity departamento = new DepartamentoEntity();
				            	departamento.setId( UUIDHelper.convertToUUID(resultadoConsulta.getString("id")) );
				            	departamento.setNombre(resultadoConsulta.getString("nombre"));
				                PaisEntity pais = new PaisEntity();
				                pais.setId( UUIDHelper.convertToUUID(resultadoConsulta.getString("id")) );
				                
				                resultado.add(departamento);
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
	private final String formarSentenciaConsulta(DepartamentoEntity data , List<Object> parametros) {
		
		final StringBuilder sentenciaSql = new StringBuilder();
		String operadorCondicional ="WHERE";
		
		sentenciaSql.append("SELECT id , nombre , pais FROM Departamento ");
		if(!ObjectHelper.isNull(data)) {
			
			if(!ObjectHelper.isNull(data.getId())&& !UUIDHelper.isDefault(data.getId())) {
				sentenciaSql.append(operadorCondicional).append(" id = ? ");
				operadorCondicional = " AND";
				parametros.add(data.getId());
			}
			if(!TextHelper.isNullOrEmpty(data.getNombre())) {
				sentenciaSql.append(operadorCondicional).append(" nombre = ? ");
				operadorCondicional = " AND";
				parametros.add(data.getNombre());
			}
			
			if(!UUIDHelper.isNull(data.getPais().getId())&& !UUIDHelper.isDefault(data.getPais().getId())) {
				sentenciaSql.append(operadorCondicional).append(" departamento = ? ");
				parametros.add(data.getPais().getId());
			}
		}
		sentenciaSql.append("ORDER BY nombre ");
		
		return sentenciaSql.toString();

	}

}
