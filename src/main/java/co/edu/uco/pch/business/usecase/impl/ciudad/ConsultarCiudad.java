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
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.MessageCatalogStrategy;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.data.CodigoMensaje;
import co.edu.uco.pch.crosscutting.helpers.ObjectHelper;
import co.edu.uco.pch.crosscutting.helpers.TextHelper;
import co.edu.uco.pch.data.dao.factory.DAOFactory;
import co.edu.uco.pch.dto.CiudadDTO;
import co.edu.uco.pch.entity.CiudadEntity;
import co.edu.uco.pch.entity.DepartamentoEntity;

public class ConsultarCiudad implements  UseCaseWithReturn<CiudadDomain ,CiudadDTO>{

private DAOFactory factory;
	
	public ConsultarCiudad (final DAOFactory factory){
		 if(ObjectHelper.isNull(factory)) {
			 var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00047);
			 var mensajeTecnico= MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00048);
			 throw new BusinessPCHException(mensajeUsuario, mensajeTecnico);
		 }
		 this.factory = factory;
	}
	@Override
	public CiudadDTO execute(CiudadDomain data) {
		validarIntegridadDato(data);
		validarCiudadMismoNombreMismoDepartamento(data.getNombre(), data.getDepartamento().getId());
		var ciudadEntity = CiudadEntity.build().setid(data.getId()).setNombre(data.getNombre()).setDepartamento(DepartamentoAssemblerEntity.getinstace().toEntity(data.getDepartamento()));

		//return factory.getCiudadDAO().consultar(ciudadEntity);
		return null;
	}
	public  final List<CiudadDTO>  convertToListDTO(final List<CiudadDomain> dto){
		List<CiudadDTO> resultados = new ArrayList<>();
		for (int i = 0; i < dto.size(); i++) {
			resultados.add(CiudadAssemblerDTO.getinstace().toDTO(dto.get(i)));
		}		
		return resultados;
	}
	public  final List<CiudadDomain>  convertToListDomain(final List<CiudadEntity> dto){
		List<CiudadDomain> resultados = new ArrayList<>();
		for (int i = 0; i < dto.size(); i++) {
			resultados.add(CiudadAssemblerEntity.getinstace().toDomain(dto.get(i)));
		}		
		return resultados;
	}
	
	private final void validarCiudadMismoNombreMismoDepartamento (final String nombreCiudad, final UUID idDepartamento){
		var ciudadEntity = CiudadEntity.build().setNombre(nombreCiudad).setDepartamento(DepartamentoEntity.build().setId(idDepartamento));
		var resultados = factory.getCiudadDAO().consultar(ciudadEntity);
		if(!resultados.isEmpty()) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00049);
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
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00050);
			throw new BusinessPCHException(mensajeUsuario);
		}
	}
	
	private final void validarFormato(final String dato) {
		if(!TextHelper.contieneSoloLetras(dato)) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00051);
			throw new BusinessPCHException(mensajeUsuario);
		}
	}

}
