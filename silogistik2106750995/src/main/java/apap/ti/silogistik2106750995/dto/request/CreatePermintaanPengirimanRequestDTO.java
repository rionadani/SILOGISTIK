package apap.ti.silogistik2106750995.dto.request;

import apap.ti.silogistik2106750995.model.Karyawan;
import apap.ti.silogistik2106750995.model.PermintaanPengirimanBarang;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePermintaanPengirimanRequestDTO {
    private String nomorPengiriman;

    @NotBlank(message = "Field alamat penerima tidak boleh kosong")
    private String alamatPenerima;

    private String tanggalPengirimanDTO;

    @NotBlank(message = "Field nama penerima tidak boleh kosong")
    private String namaPenerima;

    @Min(value = 1, message = "Harga harus lebih dari 0")
    private Integer biayaPengiriman;

    private Integer jenisLayanan;

    private Karyawan karyawan;

    @NotNull(message = "Harus ada barang yang dipilih")
    private List<PermintaanPengirimanBarang> listPermintaanPengirimanBarang;
}
