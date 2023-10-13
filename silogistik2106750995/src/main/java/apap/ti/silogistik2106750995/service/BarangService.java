package apap.ti.silogistik2106750995.service;

import java.util.List;

import apap.ti.silogistik2106750995.model.Barang;

public interface BarangService {
    int countBarang();

    List<Barang> getAllBarang();

    List<Barang> getAllBarangOrdered();

    Barang createBarang(Barang barang);

    String generateSKU(Barang barang);
    
    Barang getBarangBySku(String sku);

    Barang updateBarang(Barang barang);

}
