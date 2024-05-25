package co.edu.uco.pch.business.assembler.dto.impl;

import static co.edu.uco.pch.crosscutting.helpers.ObjectHelper.getObjectHelper;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.pch.business.assembler.dto.AssemblerDTO;
import co.edu.uco.pch.business.domain.CiudadDomain;
import co.edu.uco.pch.business.domain.DepartamentoDomain;
import co.edu.uco.pch.crosscutting.helpers.ObjectHelper;
import co.edu.uco.pch.dto.CiudadDTO;
import co.edu.uco.pch.dto.DepartamentoDTO;

public class CiudadAssemblerDTO implements AssemblerDTO<CiudadDomain, CiudadDTO>{
	
	private static final AssemblerDTO<DepartamentoDomain, DepartamentoDTO> departamentoAssembler =  DepartamentoAssemblerDTO.getinstace();
	private static final AssemblerDTO<CiudadDomain, CiudadDTO> instance = new CiudadAssemblerDTO();
	
	private CiudadAssemblerDTO() {
		super();
	}
	
	public static final AssemblerDTO<CiudadDomain, CiudadDTO> getinstace(){
		return instance;
	}
	
	@Override
	public CiudadDomain toDomain(CiudadDTO data) {
		var CiudadDtoTmp = getObjectHelper().obtenerValorDefecto(data, CiudadDTO.build());
		var departamentoDomain = departamentoAssembler.toDomain(CiudadDtoTmp.getDepartamento());
		return CiudadDomain.build(CiudadDtoTmp.getid(),CiudadDtoTmp.getNombre() , departamentoDomain);
	}

	@Override
	public  CiudadDTO toDTO(CiudadDomain domain) {
		var ciudadDomainTmp = getObjectHelper().obtenerValorDefecto(domain, CiudadDomain.build());
		var departamentoDTO = departamentoAssembler.toDTO(ciudadDomainTmp.getDepartamento());
		return CiudadDTO.build().setid(ciudadDomainTmp.getId()).setNombre(ciudadDomainTmp.getNombre()).setDepartamento(departamentoDTO);
	}

	@Override
	public List<CiudadDomain> toDomainCollection(List<CiudadDTO> dtoCollection) {
		var dtoCollectionTmp = ObjectHelper.getObjectHelper().obtenerValorDefecto(dtoCollection, new ArrayList<CiudadDTO>());		
		return dtoCollectionTmp.stream().map(this::toDomain).toList();
	}

	@Override
	public List<CiudadDTO> toDTOCollection(List<CiudadDomain> domainCollection) {
		var dtoCollectionTmp = ObjectHelper.getObjectHelper().obtenerValorDefecto(domainCollection, new ArrayList<CiudadDomain>());		
		return dtoCollectionTmp.stream().map(this::toDTO).toList();
	}
	


}
