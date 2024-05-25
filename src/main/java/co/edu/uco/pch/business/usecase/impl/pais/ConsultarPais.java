package co.edu.uco.pch.business.usecase.impl.pais;


import java.util.ArrayList;
import java.util.List;

import co.edu.uco.pch.business.assembler.dto.impl.PaisAssemblerDTO;
import co.edu.uco.pch.business.assembler.entity.impl.PaisAssemblerEntity;
import co.edu.uco.pch.business.domain.PaisDomain;
import co.edu.uco.pch.business.usecase.UseCaseWithReturn;
import co.edu.uco.pch.crosscutting.exceptions.custom.BusinessPCHException;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.MessageCatalogStrategy;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.data.CodigoMensaje;
import co.edu.uco.pch.crosscutting.helpers.ObjectHelper;
import co.edu.uco.pch.crosscutting.helpers.TextHelper;
import co.edu.uco.pch.data.dao.factory.DAOFactory;
import co.edu.uco.pch.dto.PaisDTO;
import co.edu.uco.pch.entity.PaisEntity;

public class ConsultarPais implements  UseCaseWithReturn<PaisDomain ,List<PaisDomain>>{

private DAOFactory factory;
	
	public ConsultarPais (final DAOFactory factory){
		 if(ObjectHelper.isNull(factory)) {
			 var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00047);
			 var mensajeTecnico= MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00048);
			 throw new BusinessPCHException(mensajeUsuario, mensajeTecnico);
		 }
		 this.factory = factory;
	}
	@Override
	public List<PaisDomain> execute(PaisDomain data) {
		validarIntegridadDato(data);
		validarPaisMismoNombre(data.getNombre());
		var paisEntity = PaisEntity.build().setId(data.getId()).setNombre(data.getNombre());
		var paisToDomain = convertToListDomain(factory.getPaisDAO().consultar(paisEntity));	
		return paisToDomain;
	}
	public  final List<PaisDTO>  convertToListDTO(final List<PaisDomain> dto){
		List<PaisDTO> resultados = new ArrayList<>();
		for (int i = 0; i < dto.size(); i++) {
			resultados.add(PaisAssemblerDTO.getinstace().toDTO(dto.get(i)));
		}		
		return resultados;
	}
	public  final List<PaisDomain>  convertToListDomain(final List<PaisEntity> dto){
		List<PaisDomain> resultados = new ArrayList<>();
		for (int i = 0; i < dto.size(); i++) {
			resultados.add(PaisAssemblerEntity.getinstace().toDomain(dto.get(i)));
		}		
		return resultados;
	}
	
	private final void validarPaisMismoNombre (final String nombreCiudad){
		var paisEntity = PaisEntity.build().setNombre(nombreCiudad);
		var resultados = factory.getPaisDAO().consultar(paisEntity);
		if(!resultados.isEmpty()) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00049);
			throw new BusinessPCHException(mensajeUsuario);
		}
	}
	

	public void validarIntegridadDato(PaisDomain dato) {
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
