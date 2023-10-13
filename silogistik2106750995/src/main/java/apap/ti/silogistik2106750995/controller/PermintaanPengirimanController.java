package apap.ti.silogistik2106750995.controller;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import apap.ti.silogistik2106750995.dto.PermintaanPengirimanMapper;
import apap.ti.silogistik2106750995.dto.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106750995.dto.response.ReadPermintaanPengirimanResponseDTO;
import apap.ti.silogistik2106750995.model.PermintaanPengiriman;
import apap.ti.silogistik2106750995.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2106750995.service.BarangService;
import apap.ti.silogistik2106750995.service.KaryawanService;
import apap.ti.silogistik2106750995.service.PermintaanPengirimanService;


@Controller
public class PermintaanPengirimanController {
    @Autowired
    PermintaanPengirimanService permintaanService;

    @Autowired
    PermintaanPengirimanMapper permintaanMapper;

    @Autowired
    KaryawanService karyawanService;

    @Autowired 
    BarangService barangService;

    @GetMapping("/permintaan-pengiriman")
    public String listPermintaanPengiriman(Model model){

        List<PermintaanPengiriman> listPermintaanPengiriman = permintaanService.getAllPermintaan();

        List<ReadPermintaanPengirimanResponseDTO> listPermintaanPengirimanDTO = permintaanMapper.listPermintaanPengirimanToReadPermintaanPengirimanResponseDTO(listPermintaanPengiriman);

        model.addAttribute("listPermintaanPengirimanDTO", listPermintaanPengirimanDTO);

        return "viewall-permintaan-pengiriman";
    }

    @GetMapping("/permintaan-pengiriman/{idPermintaanPengiriman}")
    public String detailPermintaanPengiriman(@PathVariable("idPermintaanPengiriman") Long id, Model model){
        var permintaanPengiriman = permintaanService.getPermintaanById(id);

        var responseDTO = permintaanMapper.permintaanPengirimanToReadPermintaanPengirimanResponseDTO(permintaanPengiriman);

        model.addAttribute("responseDTO", responseDTO);

        return "detail-permintaan-pengiriman";
    }

    @GetMapping("/permintaan-pengiriman/tambah")
    public String formPermintaanPengiriman(Model model){
       
        var permintaanDTO = new CreatePermintaanPengirimanRequestDTO();

        var listKaryawan = karyawanService.getAllKaryawan();

        var listBarang = barangService.getAllBarang();
        
        model.addAttribute("listKaryawan", listKaryawan);
        model.addAttribute("permintaanDTO", permintaanDTO);
        model.addAttribute("listBarang", listBarang);

        return "tambah-permintaan-pengiriman";
    }

    @PostMapping(value = "/permintaan-pengiriman/tambah", params = {"addRow"})
    public String addRowBarang(@ModelAttribute CreatePermintaanPengirimanRequestDTO permintaanDTO, Model model){
        if(permintaanDTO.getListPermintaanPengirimanBarang() == null || permintaanDTO.getListPermintaanPengirimanBarang().size() == 0){
            permintaanDTO.setListPermintaanPengirimanBarang(new ArrayList<>());
        }
        
        permintaanDTO.getListPermintaanPengirimanBarang().add(new PermintaanPengirimanBarang());

        model.addAttribute("listBarang", barangService.getAllBarang());
        model.addAttribute("listKaryawan", karyawanService.getAllKaryawan());
        model.addAttribute("permintaanDTO", permintaanDTO);
        
        return "tambah-permintaan-pengiriman";
    }

    @PostMapping("/permintaan-pengiriman/tambah")
    public String tambahPermintaanPengiriman(@Valid @ModelAttribute CreatePermintaanPengirimanRequestDTO permintaanDTO, BindingResult bindingResult, Model model){
        
        if (bindingResult.hasErrors()) {
            List<String> errorMessage = bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());

            model.addAttribute("errorMessage", errorMessage);
            return "error-view";
        }
        
        var permintaanFromDTO = permintaanMapper.createPermintaanPengirimanRequestDTOToPermintaanPengiriman(permintaanDTO);

        String statusCreate = permintaanService.createPermintaan(permintaanFromDTO);

        if(statusCreate.equals("berhasil")){
            model.addAttribute("permintaanDTO", permintaanService.getPermintaanById(permintaanFromDTO.getIdPermintaanPengiriman()));
            
            return "success-create-permintaan-pengiriman";
        } else if(statusCreate.equals("barang kosong")){
            ArrayList<String> errorMessage = new ArrayList<>();
            errorMessage.add("Terdapat barang yang tidak tersedia.");
            model.addAttribute("errorMessage", errorMessage);
            return "error-view";
        } else{
            ArrayList<String> errorMessage = new ArrayList<>();
            errorMessage.add("Gagal membuat permintaan, silahkan coba lagi.");
            model.addAttribute("errorMessage", errorMessage);
            return "error-view";
        }
        
    }

    @GetMapping("/permintaan-pengiriman/{id}/cancel")
    public String cancelPermintaan(@PathVariable("id") Long id, Model model){
        var permintaanPengiriman = permintaanService.getPermintaanById(id);

        Boolean isCancelSuccess = permintaanService.cancelPermintaan(permintaanPengiriman);

        if(isCancelSuccess){
            model.addAttribute("permintaanPengiriman", permintaanPengiriman);

            return "success-cancel-permintaan";
        } else{
            ArrayList<String> errorMessage = new ArrayList<>();
            errorMessage.add("Maaf kamu sudah melewati batas waktu");
            model.addAttribute("errorMessage", errorMessage);
            return "error-view";
        }

        
    }
}
