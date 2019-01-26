package com.pacck.safety.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.paack.safety.exception.MessageException;
import com.pacck.safety.Repository.UtilizadorRepository;
import com.pacck.safety.model.Utilizador;



@RestController
public class UtilizadorController {

	@Autowired
	private UtilizadorRepository utilizadorRepository;
	
	@GetMapping("/utilizadores")
	public List<Utilizador> listaFuncionarios(){
		return utilizadorRepository.findAll();
	}
	
	@GetMapping("/utilizadores/{id}")
	public Resource<Utilizador> selecionarUtilizadores(@PathVariable int id) {
		Optional<Utilizador> utilizadores = utilizadorRepository.findById(id);

		if (!utilizadores.isPresent()) {
			throw new MessageException("id " +id);
		}
			
		Resource<Utilizador> resource = new Resource<Utilizador>(utilizadores.get());

		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listaFuncionarios());

		resource.add(linkTo.withRel("todos-utilizadores"));

		return resource;
	}
	
	@DeleteMapping("/utilizadores/{id}")
	public void apagarUtilizador(@PathVariable int id) {
		utilizadorRepository.deleteById(id);
	}
	
	@PostMapping("/utilizadores")
	public ResponseEntity<Object> adicionarUtilizador(@Valid @RequestBody Utilizador utilizador) {
		

		Utilizador guardarUtilizador = utilizadorRepository.save(utilizador);
		URI location = ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(guardarUtilizador.getIdUtilizador())
						.toUri();

		return ResponseEntity.created(location).build();

	}
	
	@GetMapping("/utilizadores")
	public ResponseEntity<Object> loginUtilizador(@Valid @RequestBody Utilizador utilizador) {
		

		Utilizador guardarUtilizador = utilizadorRepository.save(utilizador);
		URI location = ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(guardarUtilizador.getIdUtilizador())
						.toUri();

		return ResponseEntity.created(location).build();

	}
	
	
	
	
	
//	@PutMapping("/utilizadores/{id}")
//	ResponseEntity<?> atualizarUtilizador(@RequestBody Utilizador utilizador, @PathVariable int id){
//		utilizador.setIdUtilizador(id);
//		Utilizador atualizarUtilizador = utilizadorRepository.save(utilizador);
//		
//		Resource<Utilizador> utilizadorResource  = new Resource<>(atualizarUtilizador, getUnicoUtilizador(atualizarUtilizador.getIdUtilizador()));
//		
//		try {
//			return ResponseEntity.created(new URI(utilizadorResource.getLink(Link.REL_SELF).getHref()))
//					.body(utilizadorResource);
//		} catch (Exception e) {
//			return ResponseEntity.badRequest().body("Erro ao atualizar o " + utilizador);
//		}
//	}
//	
//	
//	private List<Link> getUnicoUtilizador(int id){
//		return Arrays.asList(linkTo(methodOn(UtilizadorController.class).selecionarUtilizadores(id)).withSelfRel()
//				.andAffordance(afford(methodOn(UtilizadorController.class).atualizarUtilizador(null, id)))
//				//.andAffordance(afford(methodOn(UtilizadorController.class).apagarUtilizador(id)))
//				,
//				linkTo(methodOn(UtilizadorController.class).listaFuncionarios()).withRel("utilizadores"));
//	}
}
