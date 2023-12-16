package com.example.aftas.Services;

public interface ServiceDao<Dto,Identifier> {
    Dto save(final Dto dto);
    Dto update(final Identifier identifier, final Dto dto);
    void delete(final Identifier identifier);
    Dto partialUpdate(final Identifier identifier, final Dto dto);
    boolean isExisting(final Identifier identifier);
    void deleteAll();
}
