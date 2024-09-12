package br.com.isiflix.veiculos.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.isiflix.veiculos.entity.VeiculoEntity;

public interface VeiculoH2Repo extends CrudRepository<VeiculoEntity, Integer>{

    List<VeiculoEntity> findByAnoFabricacaoAndCor(int anoFabricacao, String cor);
    List<VeiculoEntity> findByMarcaAndAnoFabricacaoBetween(String marca, Integer inicio_ano, Integer fim_ano);
    
    @Query ("SELECT AVG(v.velocidade) FROM VeiculoEntity v WHERE v.marca = :marca")
    Double calcularVelocidadeMediaPorMarca(@Param("marca") String marca);

    VeiculoEntity findById(int id);
}
