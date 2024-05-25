package co.edu.uco.pch.business.usecase.impl.ciudad;


import java.util.List;
import java.util.UUID;

import co.edu.uco.pch.business.assembler.entity.impl.CiudadAssemblerEntity;
import co.edu.uco.pch.business.domain.CiudadDomain;
import co.edu.uco.pch.business.usecase.UseCaseWithReturn;
import co.edu.uco.pch.crosscutting.exceptions.custom.BusinessPCHException;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.MessageCatalogStrategy;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.data.CodigoMensaje;
import co.edu.uco.pch.crosscutting.helpers.ObjectHelper;
import co.edu.uco.pch.crosscutting.helpers.TextHelper;
import co.edu.uco.pch.data.dao.factory.DAOFactory;
import co.edu.uco.pch.entity.CiudadEntity;
import co.edu.uco.pch.entity.DepartamentoEntity;

public class ConsultarCiudades implements  UseCaseWithReturn<CiudadDomain , List<CiudadDomain>> {

private DAOFactory factory;
	
	public ConsultarCiudades (final DAOFactory factory){
		 if(ObjectHelper.isNull(factory)) {
			 var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00047);
			 var mensajeTecnico= MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00048);
			 throw new BusinessPCHException(mensajeUsuario, mensajeTecnico);
		 }
		 this.factory = factory;
	}
	@Override
	public List<CiudadDomain> execute(CiudadDomain data) {
		validarIntegridadDato(data);
		validarCiudadMismoNombreMismoDepartamento(data.getNombre(), data.getDepartamento().getId());
		var ciudadEntityfilter= CiudadAssemblerEntity.getinstace().toEntity(data);
		var resultadosEntity= factory.getCiudadDAO().consultar(ciudadEntityfilter);

		return CiudadAssemblerEntity.getinstace().toDomainCollection(resultadosEntity);
		
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
			//validarLongitud(dato.getNombre());
			//validarFormato(dato.getNombre());
		}		
	}
	
	private final void validarLongitud(final String dato) {
		if(TextHelper.longitudMaximaValida(dato,150)) {
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
