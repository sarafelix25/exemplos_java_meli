package br.com.isiflix.veiculos.dto;

public record VeiculoDTO(
	String id, 
	String marca, 
	String modelo,
	String cor,                  
	String ano, 
	String preco, 
	String capacidade, 
	String cilindradas,
	String velocidade
	) {

}
