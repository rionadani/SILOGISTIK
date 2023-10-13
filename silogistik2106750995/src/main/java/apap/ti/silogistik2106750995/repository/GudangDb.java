package apap.ti.silogistik2106750995.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.ti.silogistik2106750995.model.Gudang;

@Repository
public interface GudangDb extends JpaRepository<Gudang, Long>{
    List<Gudang> findByOrderByNamaGudang();
}