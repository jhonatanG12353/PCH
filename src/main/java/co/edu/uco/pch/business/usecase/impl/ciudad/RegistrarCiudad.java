package co.edu.uco.pch.business.usecase.impl.ciudad;

import java.util.UUID;


import co.edu.uco.pch.business.assembler.entity.impl.DepartamentoAssemblerEntity;
import co.edu.uco.pch.business.domain.CiudadDomain;
import co.edu.uco.pch.business.usecase.UseCaseWithoutReturn;
import co.edu.uco.pch.crosscutting.exceptions.custom.BusinessPCHException;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.MessageCatalogStrategy;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.data.CodigoMensaje;
import co.edu.uco.pch.crosscutting.helpers.ObjectHelper;
import co.edu.uco.pch.crosscutting.helpers.TextHelper;
import co.edu.uco.pch.crosscutting.helpers.UUIDHelper;
import co.edu.uco.pch.data.dao.factory.DAOFactory;
import co.edu.uco.pch.entity.CiudadEntity;
import co.edu.uco.pch.entity.DepartamentoEntity;

public final class RegistrarCiudad implements UseCaseWithoutReturn<CiudadDomain>{
	
	private DAOFactory factory;
	
	
	public RegistrarCiudad (final DAOFactory factory){
		 if(ObjectHelper.isNull(factory)) {
			 var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00041);
			 var mensajeTecnico= MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00042);
			 throw new BusinessPCHException(mensajeUsuario, mensajeTecnico);
		 }
		 this.factory = factory;
	}
	 
	@Override
	public final void execute(final CiudadDomain data) {
		// 1. Validar que los datos requeridos por el caso de uso sean correctos de tipo de dato, longitud, obligatoriedad, formato, rango
		validarIntegridadDato(data);
		// 2. Validar que no exista otra ciudad con el mismo nombre para el mismo departamento
		validarCiudadMismoNombreMismoDepartamento(data.getNombre(), data.getDepartamento().getId());
		//3. validar quye no exista otra ciudad con el mismop identificador
		var ciudadEntity = CiudadEntity.build().setid(UUIDHelper.generarUUIDAleatorio()).setNombre(data.getNombre()).setDepartamento(DepartamentoAssemblerEntity.getinstace().toEntity(data.getDepartamento()));
		//4. guardar la nueva ciudad
		
		factory.getCiudadDAO().crear(ciudadEntity);
		
	}
	
	private final UUID generarIdentificadorCiudad() {
		UUID id = UUIDHelper.generarUUIDAleatorio() ;
		boolean existeId= true;
		while(existeId) {
			id  = UUIDHelper.generarUUIDAleatorio();
			var ciudadEntity = CiudadEntity.build().setid(id);
			var resultados = factory.getCiudadDAO().consultar(ciudadEntity);
			existeId = !resultados.isEmpty();
		}
		return id;
	}
	
	private final void validarCiudadMismoNombreMismoDepartamento (final String nombreCiudad, final UUID idDepartamento){
		var ciudadEntity = CiudadEntity.build().setNombre(nombreCiudad).setDepartamento(DepartamentoEntity.build().setId(idDepartamento));
		var resultados = factory.getCiudadDAO().consultar(ciudadEntity);
		if(!resultados.isEmpty()) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00043);
			throw new BusinessPCHException(mensajeUsuario);
		}
	}
	

	public void validarIntegridadDato(CiudadDomain dato) {
		if(!ObjectHelper.esNulooVacio(dato)) {
			validarLongitud(dato.getNombre());
			validarObligatoriedad(dato.getNombre());
			//validarFormato(dato.getNombre());
		}
		if(!UUIDHelper.isNull(dato.getId())) {
			String uuidString = dato.getId().toString();
			validarUUID(uuidString);
		}
		if(!UUIDHelper.isNull(dato.getId())) {
			String uuidString = dato.getDepartamento().getId().toString();
			validarUUID(uuidString);
		}
				
	}
	
	private final void validarLongitud(final String dato) {
		if(!TextHelper.longitudMaximaValida(dato,50)) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00044);
			throw new BusinessPCHException(mensajeUsuario);
		}
	}
	private final void validarObligatoriedad(final String dato) {
		if(TextHelper.isNull(dato)) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00045);
			throw new BusinessPCHException(mensajeUsuario);
		}
	}

	public static boolean validarUUID(String s) {
        try {
            UUID.fromString(s);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
	
	            
}
