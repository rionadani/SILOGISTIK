package apap.ti.silogistik2106750995.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106750995.model.Gudang;
import apap.ti.silogistik2106750995.model.GudangBarang;
import apap.ti.silogistik2106750995.repository.GudangDb;

@Service
public class GudangServiceImpl implements GudangService{

    @Autowired
    GudangDb gudangDb;

    @Override
    public int countGudang(){
        List<Gudang> listGudang = getAllGudang();
        return listGudang.size();
    }

    @Override
    public List<Gudang> getAllGudang(){
        return gudangDb.findAll();
    }

    @Override
    public Gudang getGudangById(Long id){
        for(Gudang gudang : getAllGudang()){
            if(gudang.getIdGudang().equals(id)){
                return gudang;
            }
        }

        return null;
    }

    @Override
    public void saveGudang(Gudang gudang){
        gudangDb.save(gudang);
    }

    @Override
    public Gudang updateGudang(Gudang gudangFromDTO){
        Gudang gudang = getGudangById(gudangFromDTO.getIdGudang());

        if(gudang != null){

            for (GudangBarang gudangBarangDTO : gudangFromDTO.getListGudangBarang()) {
                String sku = gudangBarangDTO.getBarang().getSku();
                
                GudangBarang existingGudangBarang = cekBarangExist(gudang.getListGudangBarang(), sku);
                if (existingGudangBarang != null) {
                    existingGudangBarang.setStok(existingGudangBarang.getStok() + gudangBarangDTO.getStok());
                } else {
                    gudangBarangDTO.setGudang(gudang);
                    gudang.getListGudangBarang().add(gudangBarangDTO);
                }
            }
        }
            
        gudangDb.save(gudang);

        return gudang;
    }

    @Override
    public GudangBarang cekBarangExist(List<GudangBarang> listGudangBarang, String sku){
        for(GudangBarang gudangBarang : listGudangBarang){
            if(gudangBarang.getBarang().getSku().equals(sku)){
                return gudangBarang;
            }
        }
        
        return null;
    }

}
