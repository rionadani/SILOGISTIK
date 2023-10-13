package apap.ti.silogistik2106750995.service;

import java.util.List;

import apap.ti.silogistik2106750995.model.Gudang;
import apap.ti.silogistik2106750995.model.GudangBarang;

public interface GudangService {
    int countGudang();

    List<Gudang> getAllGudang();

    Gudang getGudangById(Long id);

    void saveGudang(Gudang gudang);

    Gudang updateGudang(Gudang gudang);
    
    GudangBarang cekBarangExist(List<GudangBarang> listGudangBarang, String sku);
}
