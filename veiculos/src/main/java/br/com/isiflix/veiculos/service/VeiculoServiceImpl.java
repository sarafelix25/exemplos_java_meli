package br.com.isiflix.veiculos.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.isiflix.veiculos.dto.VeiculoDTO;
import br.com.isiflix.veiculos.entity.VeiculoEntity;
import br.com.isiflix.veiculos.exceptions.NaoEncontradoException;
import br.com.isiflix.veiculos.exceptions.ValidacaoException;
import br.com.isiflix.veiculos.repo.VeiculoH2Repo;

@Service
public class VeiculoServiceImpl implements IVeiculoService{

//	@Autowired
//	private VeiculoRepo repo;
	
	@Autowired
	private VeiculoH2Repo repo;
	
	@Override
	public VeiculoDTO adicionarNovo(VeiculoDTO novo) {
		// primeiro de tudo!!! Validacao!
		
		if (novo.id() == null || novo.marca() == null) {
			throw new ValidacaoException("Valores Invalidos");
		}
		
		repo.save(dtoToEntity(novo));
		return novo;
	}

	@Override
	public List<VeiculoDTO> recuperarTodos() {
		// TODO Auto-generated method stub
		// versao 1.0 trabalhando direto com listas
//		List<VeiculoDTO> resultado = new ArrayList<VeiculoDTO>();
//		for (VeiculoEntity e: repo.findAll()) {
//			resultado.add(entityToDto(e));
//		}
//		return resultado;
		
		// versao 2.0 - agora ao invés de listas, STREAMS
		return ((List<VeiculoEntity>) repo.findAll()).stream().map(e -> entityToDto(e)).toList();
	}

	@Override
	public List<VeiculoDTO> buscarVeiculosPorAnoECor(String anoFabricacao, String cor) {
		List<VeiculoEntity> veiculosFiltrados = repo.filtrarPorAnoECor(Integer.parseInt(anoFabricacao), cor);
		if (veiculosFiltrados.isEmpty()) {
			throw new NaoEncontradoException("Veículo não encontrado");
		}
		return veiculosFiltrados.stream()
				.map(this::entityToDto).toList();
	}

	// @Override
	// public List<VeiculoDTO> buscarVeiculoPorMarcaEAno(String marca, int inicio_ano, int fim_ano) {
	// 	List<VeiculoEntity> veiculosFiltrados = repo.filtrarPorMarcaEAno(marca, inicio_ano, fim_ano);
	// 	if (veiculosFiltrados.isEmpty()) {
	// 		throw new NaoEncontradoException("Veículo não encontrado");
	// 	}
	// 	return veiculosFiltrados.stream()
	// 							.map(this::entityToDto)
	// 							.toList();
	// }

	private VeiculoEntity dtoToEntity(VeiculoDTO dto) {
		return new VeiculoEntity(Integer.parseInt(dto.id()),
				                 dto.marca(),
				                 dto.modelo(),
								 dto.cor(),
				                 Integer.parseInt(dto.ano()),
				                 Double.parseDouble(dto.preco()),
				                 Integer.parseInt(dto.capacidade()),
				                 Double.parseDouble(dto.cilindradas()));
	}
	private VeiculoDTO entityToDto(VeiculoEntity entity) {
		return new VeiculoDTO(String.valueOf(entity.getId()), 
				              entity.getMarca(), 
				              entity.getModelo(), 
							  entity.getCor(),
				              String.valueOf(entity.getAnoFabricacao()),
				              String.valueOf(entity.getPreco()), 
				              String.valueOf(entity.getCapacidadePassageiros()), 
							  String.valueOf(entity.getCilindradas()));
	}

}
