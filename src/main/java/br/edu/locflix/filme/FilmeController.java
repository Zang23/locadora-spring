package br.edu.locflix.filme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/filme")
public class FilmeController {

	@Autowired
	private FilmeService filmeService;

	//listar todos os filmes
	@GetMapping
	public String carregaPaginaListagem (Model model, HttpSession session){

		model.addAttribute("activePage", "filmes");

		model.addAttribute("lista", filmeService.getAllFilme());
		return "filme/listagem";
	}
}
