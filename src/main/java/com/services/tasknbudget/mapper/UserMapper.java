package com.services.tasknbudget.mapper;

import com.services.tasknbudget.dto.UserDTO;
import com.services.tasknbudget.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
	@Mapping(source = "userId", target = "id")
	User toEntity(UserDTO dto);

	@Mapping(source = "id", target = "userId")
	UserDTO toDto(User entity);
}
