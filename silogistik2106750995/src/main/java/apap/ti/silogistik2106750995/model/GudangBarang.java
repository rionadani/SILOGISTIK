package apap.ti.silogistik2106750995.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "gudang_barang")
public class GudangBarang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Long idGudangBarang;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_gudang", referencedColumnName = "idGudang")
    private Gudang gudang;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sku_barang", referencedColumnName = "sku")
    private Barang barang;

    @NotNull
    @Column(name = "stok", nullable = false)
    private Integer stok;
}
