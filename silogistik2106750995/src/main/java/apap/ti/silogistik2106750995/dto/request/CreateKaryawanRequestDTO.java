package apap.ti.silogistik2106750995.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateKaryawanRequestDTO {  
    private Long idKaryawan;

    private String namaKaryawan;

    private Integer jenisKelamin;

    private Date tanggalLahir;
}
