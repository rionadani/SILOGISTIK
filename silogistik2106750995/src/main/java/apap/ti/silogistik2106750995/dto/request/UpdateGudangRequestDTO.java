package apap.ti.silogistik2106750995.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateGudangRequestDTO extends CreateGudangRequestDTO{
    private Long idGudang;
}
