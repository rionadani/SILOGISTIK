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
@Table(name = "barang")
public class Barang {
    @Id
    private String sku;

    @NotNull
    @Column(name = "tipe_barang", nullable = false)
    private Integer tipeBarang;

    @NotNull
    @Column(name = "merk", nullable = false)
    private String merkBarang;

    @NotNull
    @Column(name = "harga_barang", nullable = false)
    private Long hargaBarang;

    @OneToMany(mappedBy = "barang", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<GudangBarang> listGudangBarang;

    @OneToMany(mappedBy = "barang", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<PermintaanPengirimanBarang> listPengirimanBarang;
}
