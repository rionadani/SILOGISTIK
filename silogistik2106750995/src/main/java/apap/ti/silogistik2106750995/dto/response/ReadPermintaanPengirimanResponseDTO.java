package apap.ti.silogistik2106750995.dto.response;

import java.util.List;

import apap.ti.silogistik2106750995.model.Karyawan;
import apap.ti.silogistik2106750995.model.PermintaanPengirimanBarang;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReadPermintaanPengirimanResponseDTO {
    private Long idPermintaanPengiriman;

    private String nomorPengiriman;

    private Boolean isCancelled;

    private String namaPenerima;

    private String alamatPenerima;

    private String tanggalPengiriman;

    private Integer biayaPengiriman;

    private Integer jenisLayanan;
    
    private String waktuPermintaan;

    private Karyawan karyawan;

    private List<PermintaanPengirimanBarang> listPermintaanPengirimanBarang;
}
