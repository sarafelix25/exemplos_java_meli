package br.com.isiflix.veiculos.repo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import br.com.isiflix.veiculos.entity.VeiculoEntity;

@Repository
public class VeiculoRepo {
	
	private List<VeiculoEntity> database;
	
	public VeiculoRepo() {
		System.out.println("Initializing database...");
		this.database = new ArrayList<VeiculoEntity>();
	}
	
	public VeiculoEntity save(VeiculoEntity novoVeiculo) {
		this.database.add(novoVeiculo);
		return novoVeiculo;
	}
	public List<VeiculoEntity> findAll(){
		return this.database;
	}

	public List<VeiculoEntity> filtrarPorAnoECor(int anoFabricacao, String cor) {
		return this.database.stream()
							.filter(v -> v.getAnoFabricacao() == anoFabricacao && v.getCor().equalsIgnoreCase(cor))
							.collect(Collectors.toList());
	}

	// public List<VeiculoEntity> filtrarPorMarcaEAno(String marca, int inicio_ano, int fim_ano) {
	// 	return this.database.stream().filter(v -> v.getMarca().equalsIgnoreCase(marca) && 
	// 											  v.getAnoFabricacao() > inicio_ano && 
	// 										      v.getAnoFabricacao() < fim_ano)
	// 											  .collect(Collectors.toList());
	// }
}
