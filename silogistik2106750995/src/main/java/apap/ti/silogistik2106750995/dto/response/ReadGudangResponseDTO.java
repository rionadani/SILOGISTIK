package apap.ti.silogistik2106750995.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import apap.ti.silogistik2106750995.model.GudangBarang;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReadGudangResponseDTO {
    private Long idGudang;

    private String namaGudang;

    private String alamatGudang;
    
    private List<GudangBarang> listGudangBarang;
}

