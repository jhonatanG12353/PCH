package co.edu.uco.pch.business.facade.impl.ciudad;

import java.util.List;

import co.edu.uco.pch.business.assembler.dto.impl.CiudadAssemblerDTO;
import co.edu.uco.pch.business.facade.FacadeWithReturn;
import co.edu.uco.pch.business.usecase.impl.ciudad.ConsultarCiudades;
import co.edu.uco.pch.crosscutting.exceptions.PCHException;
import co.edu.uco.pch.crosscutting.exceptions.custom.BusinessPCHException;
import co.edu.uco.pch.data.dao.factory.DAOFactory;
import co.edu.uco.pch.dto.CiudadDTO;

public final class ConsultarCiudadesFacade implements FacadeWithReturn<CiudadDTO, List<CiudadDTO> >{

	private DAOFactory daoFactory;
	
	public ConsultarCiudadesFacade () {
		daoFactory = DAOFactory.getFactory();
	}
	@Override
	public  List<CiudadDTO> execute( CiudadDTO dto) {

		try {
			var useCase = new ConsultarCiudades(daoFactory);
			var ciudadDomain = CiudadAssemblerDTO.getinstace().toDomain(dto);
			var resultadosDomain =useCase.execute(ciudadDomain);

			return CiudadAssemblerDTO.getinstace().toDTOCollection(resultadosDomain);
			
			
		}catch(final PCHException exception) {
			throw exception;
		}catch (final Exception excepcion) {
			var mensajeUsuario= "Se ha presentado un problema tratando de consultar la informacion de la ciudad";
			var mensajeTecnico = "Se ha presentado un problema  INESPERADO tratando de consultar la informacion de la ciudad";
			throw new BusinessPCHException(mensajeUsuario, mensajeTecnico);
		} finally{
			daoFactory.cerrarConexion();
		}
		
	} 

}
