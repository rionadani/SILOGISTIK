package apap.ti.silogistik2106750995.dto;

import org.mapstruct.Mapper;

import apap.ti.silogistik2106750995.dto.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106750995.dto.request.UpdateGudangRequestDTO;
import apap.ti.silogistik2106750995.dto.response.ReadGudangResponseDTO;
import apap.ti.silogistik2106750995.model.Gudang;

@Mapper(componentModel = "spring")
public interface GudangMapper {
    CreateGudangRequestDTO gudangToCreateGudangRequestDTO(Gudang gudang);
    ReadGudangResponseDTO gudangToReadGudangResponseDTO(Gudang gudang);
    UpdateGudangRequestDTO gudangToUpdateGudangRequestDTO(Gudang gudang);

    Gudang createGudangRequestDTOToGudang(CreateGudangRequestDTO createGudangRequestDTO);
    Gudang updateGudangRequestDTOToGudang(UpdateGudangRequestDTO updateGudangRequestDTO);
}
