package apap.ti.silogistik2106750995.controller;

import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import apap.ti.silogistik2106750995.dto.GudangMapper;
import apap.ti.silogistik2106750995.dto.request.UpdateGudangRequestDTO;
import apap.ti.silogistik2106750995.model.Barang;
import apap.ti.silogistik2106750995.model.Gudang;
import apap.ti.silogistik2106750995.model.GudangBarang;
import apap.ti.silogistik2106750995.service.BarangService;
import apap.ti.silogistik2106750995.service.GudangService;

@Controller
public class GudangController {
    @Autowired
    GudangService gudangService;

    @Autowired
    GudangMapper gudangMapper;

    @Autowired
    BarangService barangService;

    @GetMapping("/gudang")
    public String listGudang(Model model){
        List<Gudang> listGudang = gudangService.getAllGudang();

        model.addAttribute("listGudang", listGudang);

        return "viewall-gudang";
    }

    @GetMapping("gudang/{idGudang}")
    public String detailGudang(@PathVariable("idGudang") Long id, Model model){
        var gudang = gudangService.getGudangById(id);

        var responseDTO = gudangMapper.gudangToReadGudangResponseDTO(gudang);

        model.addAttribute("responseDTO", responseDTO);

        return "detail-gudang";
    }

    @GetMapping("/gudang/cari-barang")
    public String cariBarang(Model model){
        List<Barang> listBarang = barangService.getAllBarangOrdered();

        model.addAttribute("listBarang", listBarang);

        return "cari-barang";
    }

    @GetMapping(value = "/gudang/cari-barang", params = {"sku"})
    public String cariBarangHasil(@RequestParam("sku") String sku, Model model){
        var barang = barangService.getBarangBySku(sku);
    
        List<GudangBarang> listGudangBarang = barang.getListGudangBarang();
        listGudangBarang.sort((g1, g2) -> g1.getGudang().getNamaGudang().compareToIgnoreCase(g2.getGudang().getNamaGudang()));

        List<Barang> listBarang = barangService.getAllBarangOrdered();

        model.addAttribute("listGudangBarang", listGudangBarang);
        model.addAttribute("listBarang", listBarang);
        model.addAttribute("selectedBarang", barang);

        return "cari-barang";
    }

    @GetMapping("/gudang/{idGudang}/restock-barang")
    public String formRestockBarang(@PathVariable("idGudang") Long idGudang, Model model){
        var gudang = gudangService.getGudangById(idGudang);
        var gudangDTO = gudangMapper.gudangToUpdateGudangRequestDTO(gudang);

        var listBarang = barangService.getAllBarang();
        
        model.addAttribute("listBarang", listBarang);
        model.addAttribute("gudangDTO", gudangDTO);

        return "restock-barang";
    }

    @PostMapping(value = "/gudang/{idGudang}/restock-barang", params = {"addRow"})
    public String addRowBarang(@ModelAttribute UpdateGudangRequestDTO gudangDTO, Model model){
        if(gudangDTO.getListGudangBarang() == null || gudangDTO.getListGudangBarang().size() == 0){
            gudangDTO.setListGudangBarang(new ArrayList<>());
        }
        
        gudangDTO.getListGudangBarang().add(new GudangBarang());

        model.addAttribute("listBarang", barangService.getAllBarang());
        model.addAttribute("gudangDTO", gudangDTO);
        
        return "restock-barang";
    }

    @PostMapping("/gudang/{idGudang}/restock-barang")
    public String restockBarang(@Valid @ModelAttribute UpdateGudangRequestDTO gudangDTO, Model model){
        
        var gudangFromDTO = gudangMapper.updateGudangRequestDTOToGudang(gudangDTO);

        var gudang = gudangService.updateGudang(gudangFromDTO);

        model.addAttribute("gudang", gudang);
        
        return "success-restock-barang";
    }


}
