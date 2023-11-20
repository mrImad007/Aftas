package com.example.sb.model.mappers;

public interface Mapper<Entity, Dto> {

    Dto mapTo(final Entity a);
    Entity mapFrom(final Dto b);

}