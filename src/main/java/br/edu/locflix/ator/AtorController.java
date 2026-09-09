package br.edu.locflix.ator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.locflix.filme.Filme;
import br.edu.locflix.filme.FilmeService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/ator")
public class AtorController {

	@Autowired
	private AtorService atorService;

	@Autowired
	private FilmeService filmeService;

	@GetMapping ("/formulario")
	public String carregaPaginaFormulario (Long id, Model model){
		model.addAttribute("filmes", filmeService.getAllFilme());
		if(id != null) {
	        var ator = atorService.getAtorById(id);
	        model.addAttribute("ator", ator);
	    }
	    return "ator/formulario";
	}

	// Para editar ator com ID
	@GetMapping ("/formulario/{id}")
	public String carregaPaginaFormulario (@PathVariable("id") Long id,HttpSession session,RedirectAttributes redirectAttributes,Model model){

		try {
			Ator ator = atorService.getAtorById(id);

			model.addAttribute("filmes", filmeService.getAllFilme());
			model.addAttribute("filmesAtor", filmeService.buscarFilmesAtor(id));
			model.addAttribute("ator", ator);

			return "ator/formulario";

		} catch (EntityNotFoundException e) {
			redirectAttributes.addFlashAttribute("error", e.getMessage());
			return "redirect:/ator";
		}
	}


	@GetMapping
	public String carregaPaginaListagem (Model model){

	    model.addAttribute("lista", atorService.getAllAtors());
	    return "ator/listagem";
	}

	@PostMapping
	@Transactional
	public String cadastrar ( @Valid DadosCadastroAtor dados, @RequestParam("filmesIds") List<Long> filmesIds) {
		Ator ator = new Ator(dados);

		// Busca os Filmes pelos IDs cria uma lista de Filmes
		List<Filme> filmes = filmeService.findAllById(filmesIds);

		ator.setFilmes(filmes);
		atorService.save(ator);
		return "redirect:ator/formulario";
	}

	@PutMapping
	@Transactional
	public String atualizar (DadosAtualizacaoAtor dados) {
		var ator = atorService.getAtorById(dados.id());
		ator.atualizarInformacoes(dados);
		return "redirect:ator";
	}

	@DeleteMapping
	@Transactional
	public String removeAtor (Long id) {
		atorService.deleteById (id);
		return "redirect:ator";
	}

}
