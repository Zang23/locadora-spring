package br.edu.locflix.ator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AtorService {
	@Autowired
	private AtorRepository repository;

	public List<Ator> getAllAtors() {
		return repository.findAll(Sort.by("nome").ascending());
	}

	public Ator getAtorById (Long id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Ator nao encontrado"));
	}

	public Ator save(Ator ator) {

		if (ator.getNome() == null || ator.getNome().trim().isEmpty()) {
			return null; // Não salva atores sem nome
		}

		return repository.save(ator);
	}

	public Ator findByNome(String nome) {
		return repository.findByNome(nome);
	}

	public void deleteById (Long id){
	    repository.deleteById(id);
	}

}
