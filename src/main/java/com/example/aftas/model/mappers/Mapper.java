package com.example.aftas.model.mappers;

public interface Mapper<Entity, EntityDto> {

    EntityDto mapTo(final Entity entity);
    Entity mapFrom(final EntityDto dto);

}
