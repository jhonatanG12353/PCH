package co.edu.uco.pch.entity;

import java.util.UUID;

import co.edu.uco.pch.crosscutting.helpers.ObjectHelper;
import co.edu.uco.pch.crosscutting.helpers.TextHelper;
import co.edu.uco.pch.crosscutting.helpers.UUIDHelper;

public class DepartamentoEntity {
	private UUID id;
	private String nombre;
	private PaisEntity pais;

	public DepartamentoEntity() {
		setId(UUIDHelper.generarUUIDDefecto());
		setNombre(TextHelper.EMPTY);
		setPais(PaisEntity.build());
	}

	public DepartamentoEntity(final UUID id, final String nombre, final PaisEntity pais) {
		setId(id);
		setNombre(nombre);
		setPais(pais);
	}

	public static final DepartamentoEntity build() {
		return new DepartamentoEntity();
	}
	public static final DepartamentoEntity build(final UUID id, final String nombre, final PaisEntity pais) {
		return new DepartamentoEntity(id,nombre,pais);
	}

	public final UUID getId() {
		return id;
	}

	public final DepartamentoEntity setId(final UUID id) {
		this.id = id;
		return this;
	}

	public final String getNombre() {
		return nombre;
	}

	public final DepartamentoEntity setNombre(final String nombre) {
		this.nombre = TextHelper.applyTrim(nombre);
		return this;
	}

	public final PaisEntity getPais() {
		return pais;
	}

	public final DepartamentoEntity setPais(final PaisEntity pais) {
		//this.pais = ObjectHelper.getObjectHelper().obtenerValorDefecto(pais, new PaisEntity());
		this.pais = pais;
		return this;
	}

	
	
}
