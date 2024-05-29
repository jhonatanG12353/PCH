package co.edu.uco.pch.business.assembler.entity.impl;


import static co.edu.uco.pch.crosscutting.helpers.ObjectHelper.getObjectHelper;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.pch.business.assembler.entity.AssemblerEntity;
import co.edu.uco.pch.business.domain.DepartamentoDomain;
import co.edu.uco.pch.business.domain.PaisDomain;
import co.edu.uco.pch.crosscutting.helpers.ObjectHelper;
import co.edu.uco.pch.entity.DepartamentoEntity;
import co.edu.uco.pch.entity.PaisEntity;

public class DepartamentoAssemblerEntity implements AssemblerEntity<DepartamentoDomain, DepartamentoEntity>{
	
	private static final AssemblerEntity<PaisDomain, PaisEntity> paisAssembler =  PaisAssemblerEntity.getinstace();
	private static final AssemblerEntity<DepartamentoDomain, DepartamentoEntity> instance = new DepartamentoAssemblerEntity();
	
	private DepartamentoAssemblerEntity() {
		super();
	}
	
	public static final AssemblerEntity<DepartamentoDomain, DepartamentoEntity>  getinstace(){
		return instance;
	}
	@Override
	public DepartamentoDomain toDomain(DepartamentoEntity data) {
		//var DepartamentoEntityTmp = getObjectHelper().obtenerValorDefecto(data, DepartamentoEntity.build());
		var paisDomain = paisAssembler.toDomain(data.getPais());
		return DepartamentoDomain.build(data.getId(),data.getNombre() , paisDomain);
	}

	@Override
	public DepartamentoEntity toEntity(DepartamentoDomain domain) {
		//var DepartamentoDomainTmp = getObjectHelper().obtenerValorDefecto(domain, DepartamentoDomain.build());
		var paisEntity = paisAssembler.toEntity(domain.getPais());
		return DepartamentoEntity.build().setId(domain.getId()).setNombre(domain.getNombre()).setPais(paisEntity.setId(domain.getPais().getId()));
	}

	@Override
	public List<DepartamentoDomain> toDomainCollection(List<DepartamentoEntity> entityCollection) {
		var entityCollectionTmp = ObjectHelper.getObjectHelper().obtenerValorDefecto(entityCollection, new ArrayList<DepartamentoEntity>());		
		return entityCollectionTmp.stream().map(this::toDomain).toList();
	}

}
