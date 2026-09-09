package br.edu.locflix.filme;

import java.time.Year;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

	private final FilmeService filmeService;

	public DashboardController(FilmeService filmeService) {
		this.filmeService = filmeService;
	}

	@GetMapping()
	public String dashboard(HttpSession session, Model model) {

		// Adiciona dados específicos do dashboard
		List<Filme> lancamentos = filmeService.buscarLancamentosDoAno();
		List<Filme> destaques = filmeService.buscarFilmesDestaque();

		model.addAttribute("lancamentos", lancamentos);
		model.addAttribute("destaques", destaques);
		model.addAttribute("anoAtual", Year.now().getValue());
		return "home/dashboard";
	}
}
