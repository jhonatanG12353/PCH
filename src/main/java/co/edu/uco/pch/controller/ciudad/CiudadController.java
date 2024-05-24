package co.edu.uco.pch.controller.ciudad;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import co.edu.uco.pch.controller.response.Respuesta;
import co.edu.uco.pch.dto.CiudadDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "CiudadAPI", description = "Ofrece la api de consumo para todas las operaciones relacionadas con Ciudad")
public interface CiudadController {
	
	@Operation(summary = "Obtener Dummy", description = "Servicio encargado de obtener la estructura JSON basica de una Ciudad")
	CiudadDTO obtenerDumy();
	
	@Operation(summary = "Consultar", description = "Obtener la informacion de todas las Ciudad que cumplen los parametros de filtrado enviado")
	 ResponseEntity<Respuesta<CiudadDTO>> consultar( @RequestBody CiudadDTO dto);
	
	@Operation(summary = "Consultar por  id", description = "servicio encargado de obtener las ciudades que cumplen con el parametro de id")
	 ResponseEntity<Respuesta<CiudadDTO>> consultarPorId(@PathVariable ("id") UUID id);
	
	@Operation(summary = "Registrar", description = "servicio encargado de registrar la informacion de nueva ciudad enviado")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "ciudad registrada exitosamente"), 
			@ApiResponse(responseCode = "400", description = "Ciudad no registrada exitosamente por algun problema desconocido"),
			 @ApiResponse(responseCode = "500", description = "Ciudad no registrada exitosamente por un problema inesperado")})
	ResponseEntity<Respuesta<CiudadDTO>> registrar(@RequestBody CiudadDTO dto);
	
	@Operation(summary = "Modificar", description = "servicio encargado de modificar la informacion  de la ciudad correspondiente enviado como parametro")
	 ResponseEntity<Respuesta<CiudadDTO>> modificar(@PathVariable ("id") UUID id, @RequestBody CiudadDTO dto);
	@Operation(summary = "Eliminar", description = "servicio encargado de eliminar la informacion de la ciudad correspondiente enviado como parametro")
	 ResponseEntity<Respuesta<CiudadDTO>> eliminar(@PathVariable ("id") UUID id);
}
