package apap.ti.silogistik2106750995.service;

import java.util.Date;
import java.util.List;

import apap.ti.silogistik2106750995.model.PermintaanPengiriman;

public interface PermintaanPengirimanService {
    int countPengiriman();

    List<PermintaanPengiriman> getAllPermintaan();

    String createPermintaan(PermintaanPengiriman permintaanPengiriman);

    String generateNomorPengiriman(PermintaanPengiriman permintaanPengiriman);
    
    PermintaanPengiriman getPermintaanById(Long id);

    Boolean cancelPermintaan(PermintaanPengiriman permintaanPengiriman);

    Boolean isNomorExist(String nomorPengiriman);

    long calculateHourDifference(Date date1, Date date2);

}
