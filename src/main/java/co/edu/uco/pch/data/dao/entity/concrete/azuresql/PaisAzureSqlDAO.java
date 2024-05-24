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
import co.edu.uco.pch.data.dao.entity.PaisDAO;
import co.edu.uco.pch.data.dao.entity.concrete.SqlConnection;
import co.edu.uco.pch.entity.PaisEntity;

public class PaisAzureSqlDAO extends SqlConnection implements PaisDAO {

	public PaisAzureSqlDAO(Connection conexion) {
		super(conexion);
	}

	@Override
	public final List<PaisEntity> consultar(PaisEntity data) {
		List<PaisEntity> resultado = new ArrayList<>();
		var parametros = new ArrayList<Object>();
		String sentenciaSql = formarSentenciaConsulta(data, parametros);

		try (final PreparedStatement sentenciaPreparada = getConexion().prepareStatement(sentenciaSql)) {

			// Setear los parámetros
			for (int indice = 0; indice < parametros.size(); indice++) {
				sentenciaPreparada.setObject(indice + 1, parametros.get(indice));
			}

			try (final ResultSet resultadoConsulta = sentenciaPreparada.executeQuery()) {

				while (resultadoConsulta.next()) {
					PaisEntity departamento = new PaisEntity();
					departamento.setId((UUID) resultadoConsulta.getObject("id"));
					departamento.setNombre(resultadoConsulta.getString("nombre"));

					resultado.add(departamento);
				}
			}
		} catch (final SQLException excepcion) {
			var mensajeUsuario = "Se ha presentado un problema tratando de consultar los paises...";
			var mensajeTecnico = "Se ha presentado un problema de tipo SQLException en el método consultar de la clase PaisAzureSqlDAO tratando de realizar la consulta de pais \"${1}\". Por favor, revise la traza completa del problema presentado para identificar lo que sucedió...";
			throw new DataPCHException(mensajeUsuario, mensajeTecnico, excepcion);
		} catch (final Exception excepcion) {
			var mensajeUsuario = "Se ha presentado un problema tratando de consultar las ciudades...";
			var mensajeTecnico = "Se ha presentado un problema de tipo Exception en el método consultar de la clase PaisAzureSqlDAO tratando de realizar la consulta de pais \"${1}\". Por favor, revise la traza completa del problema presentado para identificar lo que sucedió...";
			throw new DataPCHException(mensajeUsuario, mensajeTecnico, excepcion);
		}

		return resultado;
	}

	private final String formarSentenciaConsulta(PaisEntity data, List<Object> parametros) {

		final StringBuilder sentenciaSql = new StringBuilder();
		String operadorCondicional = "WHERE";

		sentenciaSql.append("SELECT id, nombre FROM Pais ");
		if (!ObjectHelper.isNull(data)) {

			if (!ObjectHelper.esNulooVacio(data.getId())) {
				sentenciaSql.append(operadorCondicional).append(" id = ? ");
				operadorCondicional = " AND";
				parametros.add(data.getId());
			}
			if (!TextHelper.isNullOrEmpty(data.getNombre())) {
				sentenciaSql.append(operadorCondicional).append(" nombre = ? ");
				parametros.add(data.getNombre());
			}

		}
		sentenciaSql.append("ORDER BY codigo");

		return sentenciaSql.toString();

	}

}
