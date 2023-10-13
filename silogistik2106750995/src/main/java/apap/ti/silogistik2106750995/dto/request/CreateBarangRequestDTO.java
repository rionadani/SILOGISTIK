package apap.ti.silogistik2106750995.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBarangRequestDTO {
    private Integer tipeBarang;

    @NotBlank(message = "Field judul tidak boleh kosong")
    private String merkBarang;

    @Min(value = 1, message = "Harga harus lebih dari 0")
    private Long hargaBarang;
}
