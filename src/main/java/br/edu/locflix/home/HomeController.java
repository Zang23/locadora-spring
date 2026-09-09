package br.edu.locflix.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	 // Página pública (opcional)
    @GetMapping("/home")
    public String homePublica() {
        return "index"; // Página genérica (sem login necessário)
    }

}
