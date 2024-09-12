package br.com.isiflix.veiculos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.isiflix.veiculos.dto.VeiculoDTO;
import br.com.isiflix.veiculos.entity.VeiculoEntity;
import br.com.isiflix.veiculos.exceptions.NaoEncontradoException;
import br.com.isiflix.veiculos.exceptions.ValidacaoException;
import br.com.isiflix.veiculos.repo.VeiculoH2Repo;
import br.com.isiflix.veiculos.exceptions.ConflitoException;

@Service
public class VeiculoServiceImpl implements IVeiculoService{

//	@Autowired
//	private VeiculoRepo repo;
	
	@Autowired
	private VeiculoH2Repo repo;
	
	@Override
	public VeiculoDTO adicionarNovo(VeiculoDTO novo) {		
		if (novo.id() == null || novo.marca() == null) {
			throw new ValidacaoException("Valores Invalidos");
		}
		VeiculoEntity veiculoExistente = repo.findById(Integer.parseInt(novo.id()));
		if (veiculoExistente != null) {
			throw new ConflitoException("O id já está em uso: " + novo.id());
		}

		repo.save(dtoToEntity(novo));
		return novo;
	}

	@Override
	public List<VeiculoDTO> recuperarTodos() {
		return ((List<VeiculoEntity>) repo.findAll()).stream().map(e -> entityToDto(e)).toList();
	}

	@Override
	public List<VeiculoDTO> buscarVeiculosPorAnoECor(String anoFabricacao, String cor) {
		List<VeiculoEntity> veiculosFiltrados = repo.findByAnoFabricacaoAndCor(Integer.parseInt(anoFabricacao), cor);
		if (veiculosFiltrados.isEmpty()) {
			throw new NaoEncontradoException("Veículo não encontrado");
		}
		return veiculosFiltrados.stream()
				.map(this::entityToDto).toList();
	}

	@Override
	public List<VeiculoDTO> buscarVeiculoPorMarcaEAno(String marca, int inicio_ano, int fim_ano) {
		List<VeiculoEntity> veiculosFiltrados = repo.findByMarcaAndAnoFabricacaoBetween(marca, inicio_ano, fim_ano);
		if (veiculosFiltrados.isEmpty()) {
			throw new NaoEncontradoException("Veículo não encontrado");
		}
		return veiculosFiltrados.stream()
								.map(this::entityToDto)
								.toList();
	}
	
	@Override
	public Double buscarVeiculoVelocidadeMediaPorMarca(String marca) {
		Double velocidadeMediaVeiculos = repo.calcularVelocidadeMediaPorMarca(marca);
		if (velocidadeMediaVeiculos == 0.0) {
			throw new NaoEncontradoException("Veículo não encontrado");
		}
		return velocidadeMediaVeiculos;
	}

	private VeiculoEntity dtoToEntity(VeiculoDTO dto) {
		return new VeiculoEntity(Integer.parseInt(dto.id()),
				                 dto.marca(),
				                 dto.modelo(),
								 dto.cor(),
				                 Integer.parseInt(dto.ano()),
				                 Double.parseDouble(dto.preco()),
				                 Integer.parseInt(dto.capacidade()),
				                 Double.parseDouble(dto.cilindradas()),
								 Double.parseDouble(dto.velocidade()));
	}
	private VeiculoDTO entityToDto(VeiculoEntity entity) {
		return new VeiculoDTO(String.valueOf(entity.getId()), 
				              entity.getMarca(), 
				              entity.getModelo(), 
							  entity.getCor(),
				              String.valueOf(entity.getAnoFabricacao()),
				              String.valueOf(entity.getPreco()), 
				              String.valueOf(entity.getCapacidadePassageiros()), 
							  String.valueOf(entity.getCilindradas()),
							  String.valueOf(entity.getVelocidade()));
	}

}
