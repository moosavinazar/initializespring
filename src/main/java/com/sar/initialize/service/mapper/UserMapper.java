package com.sar.initialize.service.mapper;


import com.sar.initialize.domain.User;
import com.sar.initialize.service.dto.UserDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Service
@Mapper(componentModel = "spring", uses = {})
public interface UserMapper extends EntityMapper<UserDTO, User>{
}
