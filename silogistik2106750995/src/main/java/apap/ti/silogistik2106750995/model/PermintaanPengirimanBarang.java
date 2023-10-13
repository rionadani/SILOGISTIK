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
@Table(name = "permintaan_pengiriman_barang")
public class PermintaanPengirimanBarang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Long idPerimntaanPengirimanBarang;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_permintaan_pengiriman", referencedColumnName = "idPermintaanPengiriman")
    private PermintaanPengiriman permintaanPengiriman;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sku_barang", referencedColumnName = "sku")
    private Barang barang;

    @NotNull
    @Column(name = "kuantitas_pengiriman", nullable = false)
    private Integer kuantitasPengiriman;
}
