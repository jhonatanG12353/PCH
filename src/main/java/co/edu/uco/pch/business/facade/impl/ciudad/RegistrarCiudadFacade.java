package co.edu.uco.pch.business.facade.impl.ciudad;

import co.edu.uco.pch.business.assembler.dto.impl.CiudadAssemblerDTO;
import co.edu.uco.pch.business.domain.CiudadDomain;
import co.edu.uco.pch.business.facade.FacadeWithoutReturn;
import co.edu.uco.pch.business.usecase.UseCaseWithoutReturn;
import co.edu.uco.pch.business.usecase.impl.ciudad.RegistrarCiudad;
import co.edu.uco.pch.crosscutting.exceptions.PCHException;
import co.edu.uco.pch.crosscutting.exceptions.custom.BusinessPCHException;
import co.edu.uco.pch.data.dao.factory.DAOFactory;
import co.edu.uco.pch.dto.CiudadDTO;

public final class RegistrarCiudadFacade implements FacadeWithoutReturn<CiudadDTO>{
	
	private DAOFactory daoFactory;
	
	public RegistrarCiudadFacade () {
		daoFactory = DAOFactory.getFactory();
	}
	@Override
	public void execute(final CiudadDTO dto) {
		daoFactory.iniciarTransaccion();
		try {
			UseCaseWithoutReturn<CiudadDomain> useCase = new RegistrarCiudad(daoFactory);
			var ciudadDomain = CiudadAssemblerDTO.getinstace().toDomain(dto);
			useCase.execute(ciudadDomain);
			
			daoFactory.confirmarTransaccion();
		}catch(final PCHException exception) {
			daoFactory.cancelarTransaccion();
			throw exception;
		}catch (final Exception excepcion) {
			daoFactory.cancelarTransaccion();
			var mensajeUsuario= "Se ha presentado un problema tratando de ingresar la informacion de la ciudad";
			var mensajeTecnico = "Se ha presentado un problema  INESPERADO tratando de ingresar la informacion de la ciudad";
			throw new BusinessPCHException(mensajeUsuario, mensajeTecnico);
		} finally{
			daoFactory.cerrarConexion();
		}
		
	}

}
