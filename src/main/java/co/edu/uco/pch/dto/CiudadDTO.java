package co.edu.uco.pch.dto;

import java.util.UUID;

import co.edu.uco.pch.crosscutting.helpers.ObjectHelper;
import co.edu.uco.pch.crosscutting.helpers.TextHelper;
import co.edu.uco.pch.crosscutting.helpers.UUIDHelper;

public class CiudadDTO {
	private UUID id;
	private String nombre;
	private DepartamentoDTO departamento;
	
	private CiudadDTO() {
		super();
		setid(UUIDHelper.generarUUIDDefecto());
		setNombre(TextHelper.EMPTY);
		setDepartamento(DepartamentoDTO.build());
	}
	
	private CiudadDTO(final UUID id, final String nombre, final DepartamentoDTO departamento) {
		setid(id);
		setNombre(nombre);
		setDepartamento(departamento);
	}
	public static final CiudadDTO build () {
		return new CiudadDTO();
	} 
	public final CiudadDTO setid(final UUID id) {
		this.id = UUIDHelper.obtenerValorDefecto(id);
		return this;
	}
	public final CiudadDTO setNombre(final String nombre) {
		this.nombre  = TextHelper.applyTrim(nombre);
		return this;
	}
	public final CiudadDTO setDepartamento(final DepartamentoDTO departamento) {
		this.departamento = ObjectHelper.getObjectHelper().obtenerValorDefecto(departamento);
		return this;
	}
	public final UUID getid() {
		return id;
	}
	public final String getNombre() {
		return nombre;
	}
	public final DepartamentoDTO getDepartamento() {
		return departamento;
	}
	
}
