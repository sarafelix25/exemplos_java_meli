package br.com.isiflix.veiculos.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.isiflix.veiculos.entity.VeiculoEntity;

public interface VeiculoH2Repo extends CrudRepository<VeiculoEntity, Integer>{

    @Query("SELECT v FROM VeiculoEntity v WHERE v.anoFabricacao = :ano")
    List<VeiculoEntity> filtrarPorAno(@Param("ano") int anoFabricacao);

    @Query("SELECT v FROM VeiculoEntity v WHERE v.marca = :marca")
    List<VeiculoEntity> filtrarPorMarca(@Param("marca") String marca);
}
