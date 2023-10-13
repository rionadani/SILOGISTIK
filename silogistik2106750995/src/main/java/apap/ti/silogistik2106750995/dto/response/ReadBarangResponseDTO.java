package apap.ti.silogistik2106750995.dto.response;

import java.util.List;

import apap.ti.silogistik2106750995.model.GudangBarang;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReadBarangResponseDTO {
    private String sku;

    private String merkBarang;

    private int tipeBarang;

    private Long hargaBarang;

    private List<GudangBarang> listGudangBarang;

    private int stok;
}
