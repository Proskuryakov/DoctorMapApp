package ru.vsu.cs.app.services.internal;

import ru.vsu.cs.app.services.models.Address;

public interface AddressService {

    Address create(Address address);

    boolean delete(Long id);

    Address update(Address address);

    Address get(Long id);

    Long getSickCount(Long addressId);

}
