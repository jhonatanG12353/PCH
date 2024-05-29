package co.edu.uco.pch.business.domain;

import java.util.UUID;

import co.edu.uco.pch.crosscutting.helpers.ObjectHelper;
import co.edu.uco.pch.crosscutting.helpers.TextHelper;
import co.edu.uco.pch.crosscutting.helpers.UUIDHelper;

public final class DepartamentoDomain {
	
	private UUID id;
	private String nombre;
	private PaisDomain pais;
	
	protected DepartamentoDomain(final UUID id, final String nombre , final PaisDomain pais) {
		setId(id);
		setNombre(nombre);
		setPais(pais);
	}
	public static DepartamentoDomain build(final UUID id, final String nombre , final PaisDomain pais) {
		return new DepartamentoDomain(id,nombre, pais);
	}
	public static DepartamentoDomain build(final UUID id, final PaisDomain pais) {
		return new DepartamentoDomain(id,TextHelper.EMPTY,PaisDomain.build());
	}
	public static DepartamentoDomain build() {
		return new DepartamentoDomain(UUIDHelper.generarUUIDDefecto(),TextHelper.EMPTY, PaisDomain.build());
	}
	
	private void setId(final UUID id) {
		this.id = UUIDHelper.obtenerValorDefecto(id);
	}
	private void setNombre(final String nombre) {
		this.nombre = TextHelper.applyTrim(nombre);
	}
	
	private void setPais(PaisDomain pais) {
		//this.pais = ObjectHelper.getObjectHelper().obtenerValorDefecto(pais,PaisDomain.build());
		this.pais = pais;
	}
	public  PaisDomain getPais() {
		return pais;
	}
	
	public UUID getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	
}
