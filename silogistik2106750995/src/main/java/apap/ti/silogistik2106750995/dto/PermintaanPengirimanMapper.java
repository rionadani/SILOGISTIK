package apap.ti.silogistik2106750995.dto;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import apap.ti.silogistik2106750995.dto.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106750995.dto.response.ReadPermintaanPengirimanResponseDTO;
import apap.ti.silogistik2106750995.model.PermintaanPengiriman;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Mapper(componentModel = "spring")
public interface PermintaanPengirimanMapper {
    ReadPermintaanPengirimanResponseDTO permintaanPengirimanToReadPermintaanPengirimanResponseDTO(PermintaanPengiriman permintaanPengiriman);
    List<ReadPermintaanPengirimanResponseDTO> listPermintaanPengirimanToReadPermintaanPengirimanResponseDTO(List<PermintaanPengiriman> listPermintaanPengiriman);

    PermintaanPengiriman createPermintaanPengirimanRequestDTOToPermintaanPengiriman(CreatePermintaanPengirimanRequestDTO createPermintaanPengirimanRequestDTO);

    // After mapping untuk set Date di PermintaanPengiriman
    @AfterMapping
    default void setTanggalPengiriman(CreatePermintaanPengirimanRequestDTO createPermintaanPengirimanRequestDTO, @MappingTarget PermintaanPengiriman permintaanPengiriman){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
        try{
            Date date = formatter.parse(createPermintaanPengirimanRequestDTO.getTanggalPengirimanDTO());
            permintaanPengiriman.setTanggalPengiriman(date);
        } catch (ParseException e){
            e.printStackTrace();
        }
    }

    // After mapping untuk memformat tanggal untuk 
    @AfterMapping
    default void formatTanggal(PermintaanPengiriman permintaanPengiriman, @MappingTarget ReadPermintaanPengirimanResponseDTO readPermintaanPengirimanResponseDTO){
        SimpleDateFormat formatterOne = new SimpleDateFormat("dd-MM-yyyy");
        String formattedTanggalPengiriman = formatterOne.format(permintaanPengiriman.getTanggalPengiriman());
        readPermintaanPengirimanResponseDTO.setTanggalPengiriman(formattedTanggalPengiriman);

        SimpleDateFormat formatterTwo = new SimpleDateFormat("dd-MM-yyyy, kk:mm");
        String formattedWaktuPermintaan = formatterTwo.format(permintaanPengiriman.getWaktuPermintaan());
        readPermintaanPengirimanResponseDTO.setWaktuPermintaan(formattedWaktuPermintaan);

    }

}
