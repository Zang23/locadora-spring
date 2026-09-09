package br.edu.locflix.filme;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {

    @Query("SELECT f FROM Filme f JOIN FETCH f.atores WHERE f.id = :id")
    List<Filme> findByIdWithFilmes(@Param("id") Long id);

    @Query("SELECT f FROM Filme f JOIN FETCH f.atores WHERE f.id = :id")
    Optional<Filme> findByAtores(@Param("id") Long id);

    boolean existsByImdbId(String imdbId);

    @Modifying
    @Transactional
    @Query("UPDATE Filme f SET f.desabilitado = :desabilitado WHERE f.id = :id")
    void atualizaStatus(
        @Param("id") Long id,
        @Param("desabilitado") boolean desabilitado
    );

    @Query("SELECT f FROM Filme f WHERE YEAR(f.dataLancamento) = :ano")
    List<Filme> findByDataLancamento(@Param("ano") Integer ano);

    List<Filme> findTop5ByOrderByDataLancamentoDesc();
}
