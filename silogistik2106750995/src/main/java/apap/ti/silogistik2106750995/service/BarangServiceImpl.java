package apap.ti.silogistik2106750995.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106750995.model.Barang;
import apap.ti.silogistik2106750995.repository.BarangDb;

@Service
public class BarangServiceImpl implements BarangService{
    @Autowired
    BarangDb barangDb;

    @Override
    public int countBarang(){
        List<Barang> listBarang = getAllBarang();
        return listBarang.size();
    }

    @Override
    public List<Barang> getAllBarang(){
        return barangDb.findAll();
    }

    @Override
    public List<Barang> getAllBarangOrdered(){
        return barangDb.findAllByOrderByMerkBarang();
    }

    @Override
    public Barang createBarang(Barang barang){
        String sku = generateSKU(barang);
        barang.setSku(sku);
        barangDb.save(barang);

        return barang;
    }

    @Override
    public String generateSKU(Barang barang){
        String sku = "";
        int tipeBarang = barang.getTipeBarang();
        int countBarang = countBarang();

        if(tipeBarang == 1){
            sku += "ELEC";
        } else if(tipeBarang == 2){
            sku += "CLOT";
        } else if(tipeBarang == 3){
            sku += "FOOD";
        } else if(tipeBarang == 4){
            sku += "COSM";
        } else{
            sku += "TOOL";
        }

        sku += String.format("%03d", ++countBarang);
        
        return sku;
    }

    @Override
    public Barang getBarangBySku(String sku){
        for(Barang barang : getAllBarang()){
            if(barang.getSku().equals(sku)){
                return barang;
            }
        }

        return null;
    }

    @Override
    public Barang updateBarang(Barang barangFromDTO){
        Barang barang = getBarangBySku(barangFromDTO.getSku());
        if(barang != null){
            barang.setMerkBarang(barangFromDTO.getMerkBarang());
            barang.setHargaBarang(barangFromDTO.getHargaBarang());
            barangDb.save(barang);

            return barang;
        }
        return null;
    }
}
