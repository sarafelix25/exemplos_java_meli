package br.com.isiflix.veiculos.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.isiflix.veiculos.entity.VeiculoEntity;

public interface VeiculoH2Repo extends CrudRepository<VeiculoEntity, Integer>{

    @Query("SELECT v FROM VeiculoEntity v WHERE v.anoFabricacao = :ano AND v.marca = :marca")
    List<VeiculoEntity> filtrarPorAnoEMarca(@Param("ano") int anoFabricacao, @Param("marca") String marca);
}
