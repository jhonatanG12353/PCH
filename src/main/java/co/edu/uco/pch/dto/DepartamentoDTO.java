package co.edu.uco.pch.dto;

import java.util.UUID;

import co.edu.uco.pch.crosscutting.helpers.ObjectHelper;
import co.edu.uco.pch.crosscutting.helpers.TextHelper;
import co.edu.uco.pch.crosscutting.helpers.UUIDHelper;

public class DepartamentoDTO {
	private UUID id;
	private String nombre;
	private PaisDTO pais;
	
	
	private DepartamentoDTO() {
		super();
		setid(UUIDHelper.generarUUIDDefecto());
		setNombre(TextHelper.EMPTY);
		setPais(PaisDTO.build());
	}
	private DepartamentoDTO(final UUID id, final String nombre, final PaisDTO pais) {
		setid(id);
		setNombre(nombre);
		setPais(pais);
	}
	public static final DepartamentoDTO build () {
		return new DepartamentoDTO();
	} 
	public final DepartamentoDTO setid( final UUID id) {
		this.id = UUIDHelper.obtenerValorDefecto(id);
		return this;
	}
	public final DepartamentoDTO setNombre(final String nombre) {
		this.nombre  = TextHelper.applyTrim(nombre);
		return this;
	}
	public final DepartamentoDTO setPais( final PaisDTO pais) {
		this.pais = ObjectHelper.getObjectHelper().obtenerValorDefecto(pais);
		return this;
	}
	public final UUID getid() {
		return this.id;
	}
	public final String getNombre() {
		return this.nombre;
	}
	public final PaisDTO getPais() {
		return this.pais;
	}
	

	
	
}
