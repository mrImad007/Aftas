package com.example.aftas.service;

import java.util.List;
public interface Service <Dto,DtoRequest,Identifier> {
    Dto save(final DtoRequest request);
    List<Dto> getAll();
    Dto update(final Identifier identifier, final Dto dto);
    void delete(final Identifier identifier);
    Dto find(final Identifier identifier);
    Dto partialUpdate(final Identifier identifier, final Dto dto);
    boolean isExisting(final Identifier identifier);
    void deleteAll();
}