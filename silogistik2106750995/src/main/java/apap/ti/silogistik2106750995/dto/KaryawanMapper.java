package apap.ti.silogistik2106750995.dto;

import org.mapstruct.Mapper;

import apap.ti.silogistik2106750995.dto.request.CreateKaryawanRequestDTO;
import apap.ti.silogistik2106750995.model.Karyawan;

@Mapper(componentModel = "spring")
public interface KaryawanMapper {
    Karyawan createKaryawanRequestDTOToKaryawan(CreateKaryawanRequestDTO createKaryawanRequestDTO);
}
