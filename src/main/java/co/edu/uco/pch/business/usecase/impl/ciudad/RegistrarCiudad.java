package co.edu.uco.pch.business.usecase.impl.ciudad;

import java.util.UUID;

import co.edu.uco.pch.business.assembler.entity.impl.DepartamentoAssemblerEntity;
import co.edu.uco.pch.business.domain.CiudadDomain;
import co.edu.uco.pch.business.usecase.UseCaseWithoutReturn;
import co.edu.uco.pch.crosscutting.exceptions.custom.BusinessPCHException;
import co.edu.uco.pch.crosscutting.helpers.ObjectHelper;
import co.edu.uco.pch.crosscutting.helpers.UUIDHelper;
import co.edu.uco.pch.data.dao.factory.DAOFactory;
import co.edu.uco.pch.entity.CiudadEntity;
import co.edu.uco.pch.entity.DepartamentoEntity;

public final class RegistrarCiudad implements UseCaseWithoutReturn<CiudadDomain>{
	
	private DAOFactory factory;
	
	
	public RegistrarCiudad (final DAOFactory factory){
		 if(ObjectHelper.isNull(factory)) {
			 var mensajeUsuario = "Se ha presentado unprolema tratando de llevar a cabo el registro de la ciudad";
			 var mensajeTecnico= "El dao factory para crear la ciudad llego nulo";
			 throw new BusinessPCHException(mensajeUsuario, mensajeTecnico);
		 }
		 this.factory = factory;
	}
	 
	@Override
	public final void execute(final CiudadDomain data) {
		// 1. Validar que los datos requeridos por el caso de uso sean correctos de tipio de dato, longitud, obligatoriedad, formato, tango
		// 2. Validar que no exista otra ciudad con el mismo nombre para el mismo departamento
		validarCiudadMismoNombreMismoDepartamento(data.getNombre(), data.getDepartamento().getId());
		//3. validar quye no exista otra ciudad con el mismop identificador
		var ciudadEntity = CiudadEntity.build().setid(generarIdentificadorCiudad()).setNombre(data.getNombre()).setDepartamento(DepartamentoAssemblerEntity.getinstace().toEntity(data.getDepartamento()));
		//4. guardar la nueva ciudad

		factory.getCiudadDAO().crear(ciudadEntity);
		
	}
	
	private final UUID generarIdentificadorCiudad() {
		UUID id = UUIDHelper.generarUUIDAleatorio();
		boolean existeId= true;
		while(existeId) {
			id  = UUIDHelper.generarUUIDAleatorio();
			var ciudadEntity = CiudadEntity.build().setid(id);
			var resultados = factory.getCiudadDAO().consultar(null);
			existeId = !resultados.isEmpty();
		}
		return id;
	}
	private final void validarCiudadMismoNombreMismoDepartamento (final String nombreCiudad, final UUID idDepartamento){
		var ciudadEntity = CiudadEntity.build().setNombre(nombreCiudad).setDepartamento(DepartamentoEntity.build().setid(idDepartamento));
		var resultados = factory.getCiudadDAO().consultar(ciudadEntity);
		if(!resultados.isEmpty()) {
			var mensajeUsuario = "Ya existe una ciudad con el nombre \"${1}\" asociado al departamento deseado";
		}
	}

}
