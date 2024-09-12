package br.com.isiflix.veiculos.service;

import java.util.List;

import br.com.isiflix.veiculos.dto.VeiculoDTO;

public interface IVeiculoService {
	public VeiculoDTO adicionarNovo(VeiculoDTO novo);
	public List<VeiculoDTO> recuperarTodos();
	public List<VeiculoDTO> buscarVeiculosPorAnoECor(String ano, String cor);
	public List<VeiculoDTO> buscarVeiculoPorMarcaEAno(String marca, int inicio_ano, int fim_ano);
	public Double buscarVeiculoVelocidadeMediaPorMarca(String marca);
}
