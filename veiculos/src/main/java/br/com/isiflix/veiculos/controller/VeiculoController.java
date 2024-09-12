package br.com.isiflix.veiculos.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.isiflix.veiculos.dto.VeiculoDTO;
import br.com.isiflix.veiculos.service.IVeiculoService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class VeiculoController {
	
	private IVeiculoService service;
	
	public VeiculoController(IVeiculoService service) {
		this.service = service;
	}
	
	@GetMapping("/veiculos")
	public ResponseEntity<?> recuperarTodos(){
		return new ResponseEntity<List<VeiculoDTO>>(this.service.recuperarTodos(), HttpStatus.OK);
	}
	
	@PostMapping("/veiculos")
	public ResponseEntity<?> adicionarNovo(@RequestBody VeiculoDTO novo){
		return new ResponseEntity<VeiculoDTO>(this.service.adicionarNovo(novo), HttpStatus.CREATED);
	}

	@GetMapping("veiculos/cor/{cor}/ano/{ano}")
	public ResponseEntity<?> obterVeiculosPorAnoECor(@PathVariable String ano, @PathVariable String cor) {
		List<VeiculoDTO> veiculos = this.service.buscarVeiculosPorAnoECor(ano, cor);
    	return new ResponseEntity<>(veiculos, HttpStatus.OK);
	}

	@GetMapping("/veiculos/marca/{marca}/between/{inicio_ano}/{fim_ano}")
	public ResponseEntity<?> obterVeiculosPorMarcaEIntervaloAnos(@PathVariable String marca, @PathVariable int inicio_ano, @PathVariable int fim_ano) {
		return new ResponseEntity<>(this.service.buscarVeiculoPorMarcaEAno(marca, inicio_ano, fim_ano), HttpStatus.OK);
	}

	@GetMapping("/veiculos/velocidade_media/marca/{marca}")
	public ResponseEntity<?> obterVelocidadeMediaPorMarca(@PathVariable String marca) {
		return new ResponseEntity<>(this.service.buscarVeiculoVelocidadeMediaPorMarca(marca), HttpStatus.OK);
	}
	
}