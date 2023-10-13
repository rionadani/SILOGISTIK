package apap.ti.silogistik2106750995.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "karyawan")
public class Karyawan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Long idKaryawan;

    @NotNull
    @Column(name = "nama", nullable = false)
    private String namaKaryawan;

    @NotNull
    @Column(name = "jenis_kelamin", nullable = false)
    private Integer jenisKelamin;

    @NotNull
    @Column(name = "tanggal_lahir", nullable = false)
    private Date tanggalLahir;

    @OneToMany(mappedBy = "karyawan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<PermintaanPengiriman> permintaanPengiriman;
}
