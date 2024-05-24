package co.edu.uco.pch.business.usecase.impl.departamento;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.pch.business.assembler.dto.impl.DepartamentoAssemblerDTO;
import co.edu.uco.pch.business.assembler.entity.impl.DepartamentoAssemblerEntity;
import co.edu.uco.pch.business.assembler.entity.impl.PaisAssemblerEntity;
import co.edu.uco.pch.business.domain.DepartamentoDomain;
import co.edu.uco.pch.business.usecase.UseCaseWithReturn;
import co.edu.uco.pch.crosscutting.exceptions.custom.BusinessPCHException;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.MessageCatalogStrategy;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.data.CodigoMensaje;
import co.edu.uco.pch.crosscutting.helpers.ObjectHelper;
import co.edu.uco.pch.crosscutting.helpers.TextHelper;
import co.edu.uco.pch.data.dao.factory.DAOFactory;
import co.edu.uco.pch.dto.DepartamentoDTO;
import co.edu.uco.pch.entity.CiudadEntity;
import co.edu.uco.pch.entity.DepartamentoEntity;
import co.edu.uco.pch.entity.PaisEntity;

public class ConsultarDepartamento implements  UseCaseWithReturn<DepartamentoDomain ,DepartamentoDTO>{

private DAOFactory factory;
	
	public ConsultarDepartamento (final DAOFactory factory){
		 if(ObjectHelper.isNull(factory)) {
			 var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00047);
			 var mensajeTecnico= MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00048);
			 throw new BusinessPCHException(mensajeUsuario, mensajeTecnico);
		 }
		 this.factory = factory;
	}
	@Override
	public DepartamentoDTO execute(DepartamentoDomain data) {
		validarIntegridadDato(data);
		validardepartamentoMismoNombreMismoPais(data.getNombre(), data.getPais().getId());
		var departamentoEntity = DepartamentoEntity.build().setId(data.getId()).setNombre(data.getNombre()).setPais(PaisAssemblerEntity.getinstace().toEntity(data.getPais()));

		//return factory.getCiudadDAO().consultar(ciudadEntity);
		return null;
	}
	public  final List<DepartamentoDTO>  convertToListDTO(final List<DepartamentoDomain> dto){
		List<DepartamentoDTO> resultados = new ArrayList<>();
		for (int i = 0; i < dto.size(); i++) {
			resultados.add(DepartamentoAssemblerDTO.getinstace().toDTO(dto.get(i)));
		}		
		return resultados;
	}
	public  final List<DepartamentoDomain>  convertToListDomain(final List<DepartamentoEntity> dto){
		List<DepartamentoDomain> resultados = new ArrayList<>();
		for (int i = 0; i < dto.size(); i++) {
			resultados.add(DepartamentoAssemblerEntity.getinstace().toDomain(dto.get(i)));
		}		
		return resultados;
	}
	
	private final void validardepartamentoMismoNombreMismoPais (final String nombredepartamento, final UUID idPais){
		var departamentoEntity = DepartamentoEntity.build().setNombre(nombredepartamento).setPais(PaisEntity.build().setId(idPais));
		var resultados = factory.getDepartamentoDAO().consultar(departamentoEntity);
		if(!resultados.isEmpty()) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00049);
			throw new BusinessPCHException(mensajeUsuario);
		}
	}
	

	public void validarIntegridadDato(DepartamentoDomain dato) {
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
