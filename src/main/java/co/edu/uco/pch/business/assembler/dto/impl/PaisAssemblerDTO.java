package co.edu.uco.pch.business.assembler.dto.impl;

import co.edu.uco.pch.business.assembler.dto.AssemblerDTO;
import co.edu.uco.pch.business.domain.PaisDomain;
import co.edu.uco.pch.dto.PaisDTO;
import static  co.edu.uco.pch.crosscutting.helpers.ObjectHelper.getObjectHelper;

import java.util.List;

public final class PaisAssemblerDTO implements AssemblerDTO< PaisDomain, PaisDTO> {
	
	private static final AssemblerDTO<PaisDomain, PaisDTO> instance = new PaisAssemblerDTO();
	
	private  PaisAssemblerDTO() {
		super();
	}
	
	public static final AssemblerDTO<PaisDomain, PaisDTO> getinstace(){
		return instance;
	}

	@Override
	public final  PaisDomain toDomain(final PaisDTO data) {
		//var paisDtoTmp = getObjectHelper().obtenerValorDefecto(data, PaisDTO.build());
		return PaisDomain.build(data.getid(),data.getNombre());
	}

	@Override
	public final PaisDTO toDTO(final PaisDomain domain) {
		//var PaisDomainTmp = getObjectHelper().obtenerValorDefecto(domain, PaisDomain.build());
		return PaisDTO.build().setid(domain.getId()).setNombre(domain.getNombre());
	}

	@Override
	public List<PaisDomain> toDomainCollection(List<PaisDTO> entityCollection) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PaisDTO> toDTOCollection(List<PaisDomain> domain) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
