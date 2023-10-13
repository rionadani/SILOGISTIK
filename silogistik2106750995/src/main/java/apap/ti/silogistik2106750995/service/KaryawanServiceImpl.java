package apap.ti.silogistik2106750995.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106750995.model.Karyawan;
import apap.ti.silogistik2106750995.repository.KaryawanDb;

import java.util.List;

@Service
public class KaryawanServiceImpl implements KaryawanService{
    @Autowired
    KaryawanDb karyawanDb;

    @Override
    public List<Karyawan> getAllKaryawan(){
        return karyawanDb.findAll();
    }

    @Override
    public Karyawan saveKaryawan(Karyawan karyawan){
        karyawanDb.save(karyawan);
        
        return karyawan;
    }

    @Override
    public int countKaryawayan() {
        return getAllKaryawan().size();
    }
}
