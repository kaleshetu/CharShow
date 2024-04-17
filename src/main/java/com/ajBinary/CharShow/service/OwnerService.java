package com.ajBinary.CharShow.service;

import com.ajBinary.CharShow.entity.Owner;

import java.util.List;

public interface OwnerService {
    Owner getOwner(Long id);
    Owner getOwner(String firstName);
    List<Owner> getOwners();
    void deleteOwner(Long id);
    Owner saveOwner(Owner owner);
    Owner updateOwner(Long id, Owner owner);
}
