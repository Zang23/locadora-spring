package br.edu.locflix.filme;

import java.time.Year;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class FilmeService {
	@Autowired
	private FilmeRepository filmeRepository;


	public List<Filme> getAllFilme() {
		return filmeRepository.findAll(Sort.by("titulo").ascending());
	}

	public Filme getFilmeById(Long id) {
		return filmeRepository.getReferenceById(id);
	}
	public List<Filme> findAllById(List<Long> filmesIds) {
		return filmeRepository.findAllById(filmesIds);
	}

	public List<Filme> buscarLancamentosDoAno() {
        int anoAtual = Year.now().getValue();
        return filmeRepository.findByDataLancamento(anoAtual);
    }
	public List<Filme> buscarFilmesDestaque() {
        // Implementar lógica específica para filmes em destaque
        // Exemplo: 5 filmes mais recentes ou mais avaliados
        return filmeRepository.findTop5ByOrderByDataLancamentoDesc();
    }
	public List<Filme> buscarFilmesAtor(Long id){
		return filmeRepository.findByIdWithFilmes(id);
	}

	public boolean existeImdb(String id){
	    return filmeRepository.existsByImdbId(id);
	}

	public Filme salvarFilme(Filme filme){
	    return filmeRepository.save(filme);
	}
}
