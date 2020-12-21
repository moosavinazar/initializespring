package com.sar.initialize.service.mapper;


import com.sar.initialize.domain.Role;
import com.sar.initialize.service.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface UserMapper extends EntityMapper<UserDTO, Role>{
}
