package apap.ti.silogistik2106750995.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "gudang")
public class Gudang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGudang;

    @NotNull
    @Column(name = "nama", nullable = false)
    private String namaGudang;

    @NotNull
    @Column(name = "alamat_gudang", nullable = false)
    private String alamatGudang;

    @OneToMany(mappedBy = "gudang", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<GudangBarang> listGudangBarang;
}
