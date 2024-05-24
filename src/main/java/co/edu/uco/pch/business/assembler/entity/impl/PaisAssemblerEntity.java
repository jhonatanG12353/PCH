package co.edu.uco.pch.business.assembler.entity.impl;

import static co.edu.uco.pch.crosscutting.helpers.ObjectHelper.getObjectHelper;

import co.edu.uco.pch.business.assembler.entity.AssemblerEntity;
import co.edu.uco.pch.business.domain.PaisDomain;
import co.edu.uco.pch.entity.PaisEntity;

public class PaisAssemblerEntity  implements AssemblerEntity<PaisDomain,PaisEntity>{
	
	private static final AssemblerEntity<PaisDomain, PaisEntity> paisAssembler = new PaisAssemblerEntity();
	
	private PaisAssemblerEntity() {
		super();
	}
	
	public static final AssemblerEntity<PaisDomain, PaisEntity> getinstace(){
		return paisAssembler;
	}

	@Override
	public PaisDomain toDomain(PaisEntity data) {
		var paisEntityTmp = getObjectHelper().obtenerValorDefecto(data, PaisEntity.build());
		return PaisDomain.build(paisEntityTmp.getId(),paisEntityTmp.getNombre());
	}

	@Override
	public PaisEntity toEntity(PaisDomain domain) {
		var PaisDomainTmp = getObjectHelper().obtenerValorDefecto(domain, PaisDomain.build());
		return PaisEntity.build().setId(PaisDomainTmp.getId()).setNombre(PaisDomainTmp.getNombre());
	}
	
	


}
