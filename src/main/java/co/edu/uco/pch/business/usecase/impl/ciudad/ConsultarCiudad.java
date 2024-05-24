package co.edu.uco.pch.business.usecase.impl.ciudad;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.pch.business.assembler.dto.impl.CiudadAssemblerDTO;
import co.edu.uco.pch.business.assembler.entity.impl.CiudadAssemblerEntity;
import co.edu.uco.pch.business.assembler.entity.impl.DepartamentoAssemblerEntity;
import co.edu.uco.pch.business.domain.CiudadDomain;
import co.edu.uco.pch.business.usecase.UseCaseWithReturn;
import co.edu.uco.pch.crosscutting.exceptions.custom.BusinessPCHException;
import co.edu.uco.pch.crosscutting.helpers.ObjectHelper;
import co.edu.uco.pch.crosscutting.helpers.TextHelper;
import co.edu.uco.pch.crosscutting.helpers.UUIDHelper;
import co.edu.uco.pch.data.dao.factory.DAOFactory;
import co.edu.uco.pch.dto.CiudadDTO;
import co.edu.uco.pch.entity.CiudadEntity;
import co.edu.uco.pch.entity.DepartamentoEntity;

public class ConsultarCiudad implements  UseCaseWithReturn<CiudadDomain ,CiudadDTO>{

private DAOFactory factory;
	
	public ConsultarCiudad (final DAOFactory factory){
		 if(ObjectHelper.isNull(factory)) {
			 var mensajeUsuario = "Se ha presentado un prolema tratando de llevar a cabo el consultar de ciudad";
			 var mensajeTecnico= "El dao factory para consultar la ciudad llego nulo";
			 throw new BusinessPCHException(mensajeUsuario, mensajeTecnico);
		 }
		 this.factory = factory;
	}
	@Override
	public CiudadDTO execute(CiudadDomain data) {
		validarIntegridadDato(data);
		validarCiudadMismoNombreMismoDepartamento(data.getNombre(), data.getDepartamento().getId());
		var ciudadEntity = CiudadEntity.build().setid(data.getId()).setNombre(data.getNombre()).setDepartamento(DepartamentoAssemblerEntity.getinstace().toEntity(data.getDepartamento()));
		
		List<CiudadEntity> listaCiudades = factory.getCiudadDAO().consultar(ciudadEntity);

		for (CiudadEntity ciudad : listaCiudades) {
			var ciudadPasadaADomain = CiudadAssemblerEntity.getinstace().toDomain(ciudad);
			
		}
		return CiudadAssemblerDTO.getinstace().toDTO(ciudadPasadaADomain);
	}
	
	private final void validarCiudadMismoNombreMismoDepartamento (final String nombreCiudad, final UUID idDepartamento){
		var ciudadEntity = CiudadEntity.build().setNombre(nombreCiudad).setDepartamento(DepartamentoEntity.build().setId(idDepartamento));
		var resultados = factory.getCiudadDAO().consultar(ciudadEntity);
		if(!resultados.isEmpty()) {
			var mensajeUsuario = "Ya existe una ciudad con el nombre \"${1}\" asociado al departamento deseado";
			throw new BusinessPCHException(mensajeUsuario);
		}
	}
	

	public void validarIntegridadDato(CiudadDomain dato) {
		if(!ObjectHelper.esNulooVacio(dato)) {
			validarLongitud(dato.getNombre());
			validarFormato(dato.getNombre());
		}		
	}
	
	private final void validarLongitud(final String dato) {
		if(TextHelper.longitudMaximaValida(dato,50)) {
			var mensajeUsuario = "La longitud del codigo del tipo de identificacion no es valida. la longitud Maxima son 50 caracteres";
			throw new BusinessPCHException(mensajeUsuario);
		}
	}
	
	private final void validarFormato(final String dato) {
		if(!TextHelper.contieneSoloLetras(dato)) {
			var mensajeUsuario = "El dato \"${1}\" solo puede contener letras...";
			throw new BusinessPCHException(mensajeUsuario);
		}
	}

}
