package co.edu.uco.pch.business.facade.impl.ciudad;

import co.edu.uco.pch.business.facade.FacadeWithoutReturn;
import co.edu.uco.pch.data.dao.factory.DAOFactory;
import co.edu.uco.pch.dto.CiudadDTO;

public class EliminarCiudadFacade implements FacadeWithoutReturn<CiudadDTO>{
	
	private DAOFactory daoFactory;
	
	public EliminarCiudadFacade () {
		daoFactory = DAOFactory.getFactory();
	}
	@Override
	public void execute(CiudadDTO dto) {
		// TODO Auto-generated method stub
		
	}

}
