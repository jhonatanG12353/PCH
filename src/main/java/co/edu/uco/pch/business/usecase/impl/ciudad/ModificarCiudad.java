package co.edu.uco.pch.business.usecase.impl.ciudad;

import java.util.UUID;

import co.edu.uco.pch.business.assembler.entity.impl.DepartamentoAssemblerEntity;
import co.edu.uco.pch.business.domain.CiudadDomain;
import co.edu.uco.pch.business.usecase.UseCaseWithoutReturn;
import co.edu.uco.pch.crosscutting.exceptions.custom.BusinessPCHException;
import co.edu.uco.pch.crosscutting.helpers.ObjectHelper;
import co.edu.uco.pch.crosscutting.helpers.TextHelper;
import co.edu.uco.pch.crosscutting.helpers.UUIDHelper;
import co.edu.uco.pch.data.dao.factory.DAOFactory;
import co.edu.uco.pch.entity.CiudadEntity;
import co.edu.uco.pch.entity.DepartamentoEntity;

public class ModificarCiudad implements UseCaseWithoutReturn<CiudadDomain> {
	
	private DAOFactory factory;
	
	public ModificarCiudad (final DAOFactory factory){
		 if(ObjectHelper.isNull(factory)) {
			 var mensajeUsuario = "Se ha presentado un prolema tratando de llevar a cabo la modificacion de ciudad";
			 var mensajeTecnico= "El dao factory para modificar la ciudad llego nulo";
			 throw new BusinessPCHException(mensajeUsuario, mensajeTecnico);
		 }
		 this.factory = factory;
	}
	@Override
	public void execute(final CiudadDomain data) {
		validarIntegridadDato(data);
		validarCiudadMismoNombreMismoDepartamento(data.getNombre(), data.getDepartamento().getId());
		var ciudadEntity = CiudadEntity.build().setid(data.getId()).setNombre(data.getNombre()).setDepartamento(DepartamentoAssemblerEntity.getinstace().toEntity(data.getDepartamento()));
		factory.getCiudadDAO().modificar(ciudadEntity);
		
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
			validarObligatoriedad(dato.getNombre());
			validarFormato(dato.getNombre());
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
		if(TextHelper.longitudMaximaValida(dato,50)) {
			var mensajeUsuario = "La longitud del codigo del tipo de identificacion no es valida. la longitud Maxima son 50 caracteres";
			throw new BusinessPCHException(mensajeUsuario);
		}
	}
	private final void validarObligatoriedad(final String dato) {
		if(TextHelper.isNull(dato)) {
			var mensajeUsuario = "Es necesarios que ingreses \"${1}\" para optener la transacción...";
			throw new BusinessPCHException(mensajeUsuario);
		}
	}
	private final void validarFormato(final String dato) {
		if(!TextHelper.contieneSoloLetras(dato)) {
			var mensajeUsuario = "El dato \"${1}\" solo puede contener letras...";
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
