package com.hexagonal.domain.common.mapper;

import java.util.UUID;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface ExternalIdMapper {

  default String map(UUID uuid) {
    return uuid.toString();
  }

  default UUID map(String string) {
    return UUID.fromString(string);
  }
}
