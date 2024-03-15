package com.damian.ecommerce.backend.infrastructure.Mapper;

import com.damian.ecommerce.backend.domain.model.User;
import com.damian.ecommerce.backend.infrastructure.Entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "username", target = "username"),
            @Mapping(source = "firstName", target = "firstName"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "address", target = "address"),
            @Mapping(source = "cellphone", target = "cellphone"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "userType", target = "userType"),
            @Mapping(source = "dateCreatedAt", target = "dateCreatedAt"),
            @Mapping(source = "dateUpdatedAt", target = "dateUpdatedAt")
    })
    User toUser(UserEntity userEntity);

    Iterable<User> toUsers(Iterable<UserEntity> userEntities);

    UserEntity toUserEntity(User user);
}
