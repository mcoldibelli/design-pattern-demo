package com.coldibelli.challenge.util;


import com.coldibelli.challenge.dto.ApiResponseDTO;
import com.coldibelli.challenge.model.Address;

public class AddressMapper {

    public static Address convertToAddress(ApiResponseDTO apiResponseDTO) {
        Address address = new Address();
        address.setCep(apiResponseDTO.getCep());
        address.setAddress(apiResponseDTO.getLogradouro());
        address.setComplement(apiResponseDTO.getComplemento());
        address.setUnity(apiResponseDTO.getUnidade());
        address.setNeighborhood(apiResponseDTO.getBairro());
        address.setCity(apiResponseDTO.getLocalidade());
        address.setState(apiResponseDTO.getUf());
        address.setIbge(apiResponseDTO.getIbge());
        address.setGia(apiResponseDTO.getGia());
        address.setDdd(apiResponseDTO.getDdd());
        address.setSiafi(apiResponseDTO.getSiafi());
        return address;
    }
}
