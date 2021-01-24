package com.example.projetoLoja.controllers;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.projetoLoja.models.Carro;
import com.example.projetoLoja.repositories.CarroRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class CarroController {

	@Autowired
	CarroRepository carroRepository;

	@GetMapping("/carros")
	public ResponseEntity<List<Carro>> getAllCarros() {
		try {
			List<Carro> carros = new ArrayList<Carro>();

			carroRepository.findAll().forEach(carros::add);

			if (carros.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			for (int i = 0; i < carros.size(); i++) {
				carros.get(i).setFoto(decompressBytes(carros.get(i).getFoto()));
			}
			
			return new ResponseEntity<>(carros, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/carros/{id}")
	public ResponseEntity<Carro> getCarroById(@PathVariable("id") long id) {
		Optional<Carro> carroData = carroRepository.findById(id);

		if (carroData.isPresent()) {
			
			carroData.get().setFoto(decompressBytes(carroData.get().getFoto()));
			
			return new ResponseEntity<>(carroData.get(), HttpStatus.OK);
			
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/carros")
	public ResponseEntity<Carro> createCarro(@RequestBody Carro carro, @RequestParam(required=false) byte[] foto) {
		try {
			
			if(foto == null) {
				File imgPath = new File("src/main/resources/carroImages/notFound.png");
				BufferedImage bufferedImage = ImageIO.read(imgPath);
				WritableRaster raster = bufferedImage .getRaster();
				DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();
				carro.setFoto(compressBytes(data.getData()));
			} else {
				carro.setFoto(compressBytes(foto));
			}
			
			Carro tempCarro = carroRepository
					.save(new Carro(carro.getFoto(), carro.getTipoVeiculo(), carro.getMarca(), carro.getModelo(), carro.getAno(), carro.getPreco(), carro.getDescricao()));
			
			return new ResponseEntity<>(tempCarro, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/carros/{id}")
	public ResponseEntity<Carro> updateCarro(@PathVariable("id") long id, @RequestBody Carro carro) {
		Optional<Carro> carroData = carroRepository.findById(id);

		if (carroData.isPresent()) {
			
			Carro tempCarro = carroData.get();
			
			if(carro.getFoto() != null) {
				tempCarro.setFoto(compressBytes(carro.getFoto()));
			}
			
			tempCarro.setTipoVeiculo(carro.getTipoVeiculo());
			tempCarro.setMarca(carro.getMarca());
			tempCarro.setModelo(carro.getModelo());
			tempCarro.setAno(carro.getAno());
			tempCarro.setPreco(carro.getPreco());
			tempCarro.setDescricao(carro.getDescricao());
			
			return new ResponseEntity<>(carroRepository.save(tempCarro), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/carros/{id}")
	public ResponseEntity<HttpStatus> deleteCarro(@PathVariable("id") long id) {
		try {
			carroRepository.deleteById(id);
			
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/carros")
	public ResponseEntity<HttpStatus> deleteAllCarros() {
		try {
			carroRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	public static byte[] compressBytes(byte[] data) {

	    Deflater deflater = new Deflater();
	    deflater.setInput(data);
	    deflater.finish();

	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

	    byte[] buffer = new byte[1024];

	    while (!deflater.finished()) {
	        int count = deflater.deflate(buffer);
	        outputStream.write(buffer, 0, count);
	    }

	    try {
	        outputStream.close();
	    } catch (IOException e) {

	    }

	    return outputStream.toByteArray();

	}

	public static byte[] decompressBytes(byte[] data) {

	    Inflater inflater = new Inflater();
	    inflater.setInput(data);
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
	    byte[] buffer = new byte[1024];

	    try {

	        while (!inflater.finished()) {
	            int count = inflater.inflate(buffer);
	            outputStream.write(buffer, 0, count);
	        }

	        outputStream.close();

	    } catch (IOException ioe) {

	    } catch (DataFormatException e) {

	    }

	    return outputStream.toByteArray();

	}
	
}


