package com.sar.initialize.service.mapper;

import com.sar.initialize.domain.Role;
import com.sar.initialize.service.dto.RoleDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface RoleMapper extends EntityMapper<RoleDTO, Role> {
}
