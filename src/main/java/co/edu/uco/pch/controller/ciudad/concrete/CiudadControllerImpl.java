package co.edu.uco.pch.controller.ciudad.concrete;

import java.util.UUID;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.slf4j.Logger;

import co.edu.uco.pch.business.assembler.dto.impl.CiudadAssemblerDTO;
import co.edu.uco.pch.business.usecase.impl.ciudad.RegistrarCiudad;
import co.edu.uco.pch.controller.ciudad.CiudadController;
import co.edu.uco.pch.controller.response.Respuesta;
import co.edu.uco.pch.crosscutting.exceptions.PCHException;
import co.edu.uco.pch.crosscutting.exceptions.custom.DataPCHException;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.MessageCatalogStrategy;
import co.edu.uco.pch.crosscutting.exceptions.messageCatalog.data.CodigoMensaje;
import co.edu.uco.pch.data.dao.factory.concrete.AzureSQLDAOFactory;
import co.edu.uco.pch.dto.CiudadDTO;


@RestController
@RequestMapping("/api/v1/ciudad")
public class CiudadControllerImpl implements CiudadController{
	
	private static final Logger LOGGER =  LoggerFactory.getLogger(CiudadController.class);
	
	@GetMapping("/dummy")
	@Override
	public CiudadDTO obtenerDumy() {
		LOGGER.info("El Dummy de tipo de identificacion se genero exitosamente");
		return CiudadDTO.build();
	}
	
	@PostMapping
	@Override
	public ResponseEntity<Respuesta<CiudadDTO>> consultar(CiudadDTO dto) {
		Respuesta<CiudadDTO> respuesta = new Respuesta<CiudadDTO>();
		HttpStatus codigoHttp = HttpStatus.BAD_REQUEST;
		try {
			AzureSQLDAOFactory factory = new AzureSQLDAOFactory();
			RegistrarCiudad facade = new RegistrarCiudad(factory);
				facade.execute(CiudadAssemblerDTO.getinstace().toDomain(dto));
			codigoHttp = HttpStatus.OK;
			respuesta.getMensajes().add("El tipo de identificacion fue registrado exitosamente");
			LOGGER.info("El tipo de identificacion fue registrado exitosamente");
		}catch(final PCHException excepcion) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = "Se ha presentado un problema INESPERADO tratando consultar la Ciudad esperada en los controller";
			LOGGER.error(mensajeTecnico, excepcion);
			throw new DataPCHException(mensajeTecnico, mensajeUsuario, excepcion);

		}catch(final Exception excepcion) {
			codigoHttp = HttpStatus.INTERNAL_SERVER_ERROR;
			respuesta.getMensajes().add("se ha presentado un problema inesperado tratando de registrar la ciudad");
			LOGGER.error("se ha presentado un problema inesperado tratando de registrar el tipo de identificacion", excepcion);
		}
		return new ResponseEntity<>(respuesta, codigoHttp);
	}

	@Override
	public ResponseEntity<Respuesta<CiudadDTO>> consultarPorId(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Respuesta<CiudadDTO>> registrar(CiudadDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Respuesta<CiudadDTO>> modificar(UUID id, CiudadDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Respuesta<CiudadDTO>> eliminar(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

}
