package br.com.isiflix.veiculos.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.isiflix.veiculos.entity.VeiculoEntity;

public interface VeiculoH2Repo extends CrudRepository<VeiculoEntity, Integer>{

    @Query("SELECT v FROM VeiculoEntity v WHERE v.anoFabricacao = :ano AND v.cor = :cor")
    List<VeiculoEntity> filtrarPorAnoECor(@Param("ano") int anoFabricacao, @Param("cor") String cor);

    // @Query("SELECT v FROM VeiculoEntity v WHERE v.marca = :marca AND v.inicio_ano = :inicio_ano AND v.fim_ano = fim_ano")
    // List<VeiculoEntity> filtrarPorMarcaEAno(@Param("marca") String marca, @Param("inicio_ano") int inicio_ano, @Param("fim_ano") int fim_ano);
}
