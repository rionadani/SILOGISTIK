package apap.ti.silogistik2106750995.dto;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import apap.ti.silogistik2106750995.dto.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106750995.dto.request.UpdateBarangRequestDTO;
import apap.ti.silogistik2106750995.dto.response.ReadBarangResponseDTO;
import apap.ti.silogistik2106750995.model.Barang;
import apap.ti.silogistik2106750995.model.GudangBarang;


@Mapper(componentModel = "spring")
public interface BarangMapper {
    CreateBarangRequestDTO barangToReadGudangResponseDTO(Barang barang);

    Barang createBarangRequestDTOToBarang(CreateBarangRequestDTO createBarangRequestDTO);
    Barang updateBarangRequestDTOToBarang(UpdateBarangRequestDTO updateBarangRequestDTO);

    List<ReadBarangResponseDTO> listBarangToListReadBarangResponseDTO(List<Barang> listBarang);
    ReadBarangResponseDTO barangToReadBarangResponseDTO(Barang barang);

    UpdateBarangRequestDTO barangToUpdateBarangRequestDTO(Barang barang);

    @AfterMapping
    default void setStok(Barang barang, @MappingTarget ReadBarangResponseDTO readBarangResponseDTO){
        int stok = 0;
        List<GudangBarang> listGudangBarang = barang.getListGudangBarang();

        for(GudangBarang gudangBarang : listGudangBarang){
            stok += gudangBarang.getStok();
        }

        readBarangResponseDTO.setStok(stok);
    }
}
