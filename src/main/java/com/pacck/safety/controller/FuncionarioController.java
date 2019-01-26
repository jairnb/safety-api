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
import com.pacck.safety.Repository.FuncionarioRepository;
import com.pacck.safety.model.Funcionario;


@RestController
public class FuncionarioController {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@GetMapping("/funcionarios")
	public List<Funcionario> todosFuncionarios() {
		return funcionarioRepository.findAll();
	}
	
	@GetMapping("/funcionarios/{id}")
	public Resource<Funcionario> selecionarFuncionarios(@PathVariable int id) {
		Optional<Funcionario> funcionario = funcionarioRepository.findById(id);

		if (!funcionario.isPresent()) {
			throw new MessageException("id " +id);
		}
			
		Resource<Funcionario> resource = new Resource<Funcionario>(funcionario.get());

		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).todosFuncionarios());

		resource.add(linkTo.withRel("todos-funcionarios"));

		return resource;
	}
	
	@DeleteMapping("/funcionarios/{id}")
	public void apagarFuncionarios(@PathVariable int id) {
		funcionarioRepository.deleteById(id);
	}
	
	@PostMapping("/funcionarios")
	public ResponseEntity<Object> adicionarFuncionario(@Valid @RequestBody Funcionario funcionario) {
		

		Funcionario guardarFuncionario = funcionarioRepository.save(funcionario);
		URI location = ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(guardarFuncionario.getIdFuncionario())
						.toUri();

		return ResponseEntity.created(location).build();

	}
}
