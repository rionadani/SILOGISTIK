package apap.ti.silogistik2106750995.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106750995.model.PermintaanPengiriman;
import apap.ti.silogistik2106750995.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2106750995.repository.PermintaanPengirimanDb;

@Service
public class PermintaanPengirimanServiceImpl implements PermintaanPengirimanService{
    @Autowired
    PermintaanPengirimanDb permintaanPengirimanDb;

    @Autowired
    BarangService barangService;

    @Override
    public int countPengiriman(){
        List<PermintaanPengiriman> listPermintaan = permintaanPengirimanDb.findAll();
        return listPermintaan.size();
    }

    @Override
    public List<PermintaanPengiriman> getAllPermintaan(){
        return permintaanPengirimanDb.findAllByOrderByWaktuPermintaanDesc();
    }

    @Override
    public String createPermintaan(PermintaanPengiriman permintaanPengirimanDTO){
        Date now = new Date();
        permintaanPengirimanDTO.setWaktuPermintaan(now); 
        
        String nomorPengiriman = generateNomorPengiriman(permintaanPengirimanDTO);

        if(isNomorExist(nomorPengiriman)){
            return "nomor exist";
        } 

        permintaanPengirimanDTO.setNomorPengiriman(nomorPengiriman);
        for(PermintaanPengirimanBarang permintaanPengirimanBarangDTO : permintaanPengirimanDTO.getListPermintaanPengirimanBarang()){
            permintaanPengirimanBarangDTO.setPermintaanPengiriman(permintaanPengirimanDTO);
        }
        permintaanPengirimanDb.save(permintaanPengirimanDTO);
        return "berhasil";
        
    }

    @Override
    public String generateNomorPengiriman(PermintaanPengiriman permintaanPengiriman){
        String nomor = "REQ";

        int kuantitas = 0;
        List<PermintaanPengirimanBarang> listPermintaan = permintaanPengiriman.getListPermintaanPengirimanBarang();
        for(PermintaanPengirimanBarang permintaan : listPermintaan){
            kuantitas += permintaan.getKuantitasPengiriman();
        }

        kuantitas = kuantitas % 100;
        nomor += String.format("%02d", kuantitas);

        int jenisLayanan = permintaanPengiriman.getJenisLayanan();
        if(jenisLayanan == 1){
            nomor += "SAM";
        } else if(jenisLayanan == 2){
            nomor += "KIL";
        } else if(jenisLayanan == 3){
            nomor += "REG";
        } else{
            nomor += "HEM";
        }
        
        SimpleDateFormat formatter = new SimpleDateFormat("kk:mm:ss");
        String formattedWaktu = formatter.format(permintaanPengiriman.getWaktuPermintaan());
        nomor += formattedWaktu;
        
        return nomor;
    }
    
    @Override
    public PermintaanPengiriman getPermintaanById(Long id){
        List<PermintaanPengiriman> listPermintaan = getAllPermintaan();

        for(PermintaanPengiriman permintaan : listPermintaan){
            if(permintaan.getIdPermintaanPengiriman() == id){
                return permintaan;
            }
        }
        return null;
    }

    @Override
    public Boolean isNomorExist(String nomorPengiriman){
        List<PermintaanPengiriman> listPermintaanPengiriman = getAllPermintaan();

        for (PermintaanPengiriman permintaan : listPermintaanPengiriman){
            if(permintaan.getNomorPengiriman().equals(nomorPengiriman)){
                return true;
            }
        }

        return false;
    }

    @Override
    public Boolean cancelPermintaan(PermintaanPengiriman permintaanPengiriman) {
        Date now = new Date();

        Date waktuPembuatan = permintaanPengiriman.getWaktuPermintaan();

        long timeDifference = calculateHourDifference(now, waktuPembuatan);

        if(timeDifference >= 24){
            return false;
        } else{
            permintaanPengiriman.setIsCancelled(true);
            permintaanPengirimanDb.save(permintaanPengiriman);
            return true;
        }
    }

    @Override
    public long calculateHourDifference(Date date1, Date date2) {
        long millisecondsDifference = date1.getTime() - date2.getTime();
        long hourDifference = millisecondsDifference / (60 * 60 * 1000);
        return hourDifference;
    }

}
