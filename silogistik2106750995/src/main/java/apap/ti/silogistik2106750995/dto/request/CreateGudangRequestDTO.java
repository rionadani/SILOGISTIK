package apap.ti.silogistik2106750995.dto.request;

import java.util.List;

import apap.ti.silogistik2106750995.model.GudangBarang;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateGudangRequestDTO {

    private String namaGudang;

    private String alamatGudang;

    List<GudangBarang> listGudangBarang;

}
