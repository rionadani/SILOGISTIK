package apap.ti.silogistik2106750995.service;

import java.util.List;


import apap.ti.silogistik2106750995.model.Karyawan;

public interface KaryawanService {

    List<Karyawan> getAllKaryawan();

    Karyawan saveKaryawan(Karyawan karyawan);

    int countKaryawayan();
    
}