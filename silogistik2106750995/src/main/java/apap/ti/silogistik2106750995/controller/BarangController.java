package apap.ti.silogistik2106750995.controller;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import apap.ti.silogistik2106750995.dto.BarangMapper;
import apap.ti.silogistik2106750995.dto.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106750995.dto.request.UpdateBarangRequestDTO;
import apap.ti.silogistik2106750995.dto.response.ReadBarangResponseDTO;
import apap.ti.silogistik2106750995.model.Barang;
import apap.ti.silogistik2106750995.model.GudangBarang;
import apap.ti.silogistik2106750995.service.BarangService;
import apap.ti.silogistik2106750995.service.GudangService;
import apap.ti.silogistik2106750995.service.KaryawanService;
import apap.ti.silogistik2106750995.service.PermintaanPengirimanService;

@Controller
public class BarangController {

    @Autowired
    BarangService barangService;

    @Autowired
    BarangMapper barangMapper;

    @Autowired
    GudangService gudangService;

    @Autowired
    PermintaanPengirimanService permintaanService;

    @Autowired
    KaryawanService karyawanService;
    
    @GetMapping("/")
    public String home(Model model){
        int jumlahBarang = barangService.countBarang();
        int jumlahPermintaan = permintaanService.countPengiriman();
        int jumlahGudang = gudangService.countGudang();
        int jumlahKaryawan = karyawanService.countKaryawayan();

        model.addAttribute("jumlahBarang", jumlahBarang);
        model.addAttribute("jumlahPermintaan", jumlahPermintaan);
        model.addAttribute("jumlahGudang", jumlahGudang);
        model.addAttribute("jumlahKaryawan", jumlahKaryawan);

        return "home";
    }

    @GetMapping("/barang")
    public String listBarang(Model model){
        List<Barang> listBarang = barangService.getAllBarang();

        List<ReadBarangResponseDTO> listBarangDTO = barangMapper.listBarangToListReadBarangResponseDTO(listBarang);

        model.addAttribute("listBarangDTO", listBarangDTO);

        return "viewall-barang";

    }

    @GetMapping("barang/tambah")
    public String formTambahBarang(Model model){
        var barangDTO = new CreateBarangRequestDTO();

        model.addAttribute("barangDTO", barangDTO);
        
        return "tambah-barang";
    }

    @PostMapping("barang/tambah")
    public String tambahBarang(@Valid @ModelAttribute CreateBarangRequestDTO createBarangRequestDTO, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            List<String> errorMessage = bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());

            model.addAttribute("errorMessage", errorMessage);
            return "error-view";
        }
        
        var barang = barangMapper.createBarangRequestDTOToBarang(createBarangRequestDTO);

        barang = barangService.createBarang(barang);

        model.addAttribute("barang", barang);

        return "success-create-barang";
    }

    @GetMapping("barang/{idBarang}")
    public String detailBarang(@PathVariable("idBarang") String id, Model model){
        var barang = barangService.getBarangBySku(id);

        var responseDTO = barangMapper.barangToReadBarangResponseDTO(barang);

        model.addAttribute("responseDTO", responseDTO);

        return "detail-barang";
    }

    @GetMapping("barang/{idBarang}/ubah")
    public String formUbahBarang(@PathVariable("idBarang") String id, Model model){
        var barang = barangService.getBarangBySku(id);

        var barangDTO = barangMapper.barangToUpdateBarangRequestDTO(barang);

        model.addAttribute("barangDTO", barangDTO);

        return "update-barang";
    }

    @PostMapping("barang/{idBarang}/ubah")
    public String ubahBarang(@Valid @ModelAttribute UpdateBarangRequestDTO barangDTO, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            List<String> errorMessage = bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());

            model.addAttribute("errorMessage", errorMessage);
            return "error-view";
        }
        
        var barangFromDTO = barangMapper.updateBarangRequestDTOToBarang(barangDTO);

        var barang = barangService.updateBarang(barangFromDTO);

        model.addAttribute("barang", barang);

        return "success-update-barang";
    }

    @GetMapping("/barang/cari-barang")
    public String cariBarang(Model model){
        return "cari-barang-by-kategori";
    }

    @GetMapping(value = "/barang/cari-barang", params = {"kategori"})
    public String cariBarangHasil(@RequestParam("kategori") Integer kategori, Model model){
        var listBarang = barangService.getListBarangByKategori(kategori);
    
        model.addAttribute("listBarang", listBarang);

        return "cari-barang-by-kategori";
    }
}
