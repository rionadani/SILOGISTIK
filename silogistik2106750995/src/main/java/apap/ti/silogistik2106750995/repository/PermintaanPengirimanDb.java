package apap.ti.silogistik2106750995.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import apap.ti.silogistik2106750995.model.PermintaanPengiriman;

public interface PermintaanPengirimanDb extends JpaRepository<PermintaanPengiriman, Long>{
    List<PermintaanPengiriman> findAllByOrderByWaktuPermintaanDesc();
}
