package apap.ti.silogistik2106750995;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.github.javafaker.Faker;

import apap.ti.silogistik2106750995.dto.BarangMapper;
import apap.ti.silogistik2106750995.dto.GudangMapper;
import apap.ti.silogistik2106750995.dto.KaryawanMapper;
import apap.ti.silogistik2106750995.dto.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106750995.dto.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106750995.dto.request.CreateKaryawanRequestDTO;
import apap.ti.silogistik2106750995.service.BarangService;
import apap.ti.silogistik2106750995.service.GudangService;
import apap.ti.silogistik2106750995.service.KaryawanService;
import jakarta.transaction.Transactional;

import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Silogistik2106750995Application {

	public static void main(String[] args) {
		SpringApplication.run(Silogistik2106750995Application.class, args);
	}

	@Bean
	@Transactional
	CommandLineRunner run(GudangService gudangService, BarangService barangService, KaryawanService karyawanService, 
							GudangMapper gudangMapper, BarangMapper barangMapper, KaryawanMapper karyawanMapper){
		return args -> {
			var faker = new Faker(new Locale("in-ID"));

			for(int i = 0; i < 10; i++){
				var gudangDTO = new CreateGudangRequestDTO();
				gudangDTO.setNamaGudang(faker.company().name());
				gudangDTO.setAlamatGudang(faker.address().fullAddress());

				var gudang = gudangMapper.createGudangRequestDTOToGudang(gudangDTO);

				gudangService.saveGudang(gudang);
			}

			for (int i = 0; i < 10; i++) {
                var barangDTO = new CreateBarangRequestDTO();
                barangDTO.setTipeBarang(ThreadLocalRandom.current().nextInt(1, 6));
                barangDTO.setHargaBarang(ThreadLocalRandom.current().nextLong(10000, 1000000));
                barangDTO.setMerkBarang(faker.animal().name());
                var barang = barangMapper.createBarangRequestDTOToBarang(barangDTO);
                barangService.createBarang(barang);
   
            }

			
			for(int i = 0; i < 10; i++){
				var karyawanDTO = new CreateKaryawanRequestDTO();
				karyawanDTO.setJenisKelamin(ThreadLocalRandom.current().nextInt(1,3));
				karyawanDTO.setNamaKaryawan(faker.name().fullName());
				karyawanDTO.setTanggalLahir(faker.date().birthday(30,50));

				var karyawan = karyawanMapper.createKaryawanRequestDTOToKaryawan(karyawanDTO);
				karyawanService.saveKaryawan(karyawan);
			}
			
		};
	}

}
