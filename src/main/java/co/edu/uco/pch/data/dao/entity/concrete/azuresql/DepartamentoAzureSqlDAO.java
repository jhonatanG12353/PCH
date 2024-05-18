package co.edu.uco.pch.data.dao.entity.concrete.azuresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.pch.crosscutting.exceptions.custom.DataPCHException;
import co.edu.uco.pch.crosscutting.helpers.ObjectHelper;
import co.edu.uco.pch.crosscutting.helpers.TextHelper;
import co.edu.uco.pch.data.dao.entity.DepartamentoDAO;
import co.edu.uco.pch.data.dao.entity.concrete.SqlConnection;
import co.edu.uco.pch.entity.DepartamentoEntity;
import co.edu.uco.pch.entity.PaisEntity;

public class DepartamentoAzureSqlDAO extends SqlConnection implements DepartamentoDAO{
	
	
	protected DepartamentoAzureSqlDAO(Connection conexion) {
		super(conexion);
	}

	
	@Override
	public final List<DepartamentoEntity> consultar(DepartamentoEntity data) {
		List<DepartamentoEntity> resultado = new ArrayList<>();
		
		String sentenciaSql = formarSentenciaConsulta(data);
			
			
	
				try (final PreparedStatement sentenciaPreparada = getConexion().prepareStatement(sentenciaSql)) {
			       								        
				        try (final ResultSet resultadoConsulta = sentenciaPreparada.executeQuery()) {
		
				            while (resultadoConsulta.next()) {
				            	DepartamentoEntity departamento = new DepartamentoEntity();
				            	departamento.setid((UUID) resultadoConsulta.getObject("id"));
				            	departamento.setNombre(resultadoConsulta.getString("nombre"));
				            	departamento.setPais((PaisEntity)resultadoConsulta.getObject("departamento"));
				                
				                resultado.add(departamento);
				            }
				        }				
				}
				 catch (final SQLException excepcion) {
			        var mensajeUsuario = "Se ha presentado un problema tratando de consultar las ciudades...";
			        var mensajeTecnico = "Se ha presentado un problema de tipo SQLException en el método consultar de la clase DepartamentoAzureSqlDAO tratando de realizar la consulta de departamento \"${1}\". Por favor, revise la traza completa del problema presentado para identificar lo que sucedió...";
			        throw new DataPCHException(mensajeUsuario, mensajeTecnico, excepcion);
			    } 
				catch (final Exception excepcion) {
			        var mensajeUsuario = "Se ha presentado un problema tratando de consultar las ciudades...";
			        var mensajeTecnico = "Se ha presentado un problema de tipo Exception en el método consultar de la clase DepartamentoAzureSqlDAO tratando de realizar la consulta de departamento \"${1}\". Por favor, revise la traza completa del problema presentado para identificar lo que sucedió...";
			        throw new DataPCHException(mensajeUsuario, mensajeTecnico, excepcion);
			    }
			
		    return resultado;
		}
	private final String formarSentenciaConsulta(DepartamentoEntity data) {
		final var parametros = new ArrayList<Object>();
		final StringBuilder sentenciaSql = new StringBuilder();
		String operadorCondicional ="WHERE";
		
		sentenciaSql.append("SELECT id, nombre, pais FROM Departamento ");
		if(!ObjectHelper.isNull(data)) {
			
			if(!ObjectHelper.esNulooVacio(data.getid())) {
				sentenciaSql.append(operadorCondicional).append(" id = ? ");
				operadorCondicional = " AND";
				parametros.add(data.getid());
			}
			if(!TextHelper.isNullOrEmpty(data.getNombre())) {
				sentenciaSql.append(operadorCondicional).append(" nombre = ? ");
				operadorCondicional = " AND";
				parametros.add(data.getNombre());
			}

			if(!ObjectHelper.isNull(data.getPais())) {
				sentenciaSql.append(operadorCondicional).append(" pais = ? ");
				parametros.add(data.getPais());
			}
		}
		sentenciaSql.append("ORDER BY codigo");
		
		return sentenciaSql.toString();

	}

}
