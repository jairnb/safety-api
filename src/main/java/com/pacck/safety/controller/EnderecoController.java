package com.pacck.safety.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.paack.safety.exception.MessageException;
import com.pacck.safety.Repository.EnderecoRepository;
import com.pacck.safety.model.Endereco;

@RestController
public class EnderecoController {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@GetMapping("/enderecos")
	public List<Endereco> listaEnderecos(){
		return enderecoRepository.findAll();
	}
	
	@GetMapping("/enderecos/{id}")
	public Resource<Endereco> selecionarEndereco(@PathVariable int id) {
		Optional<Endereco> endereco = enderecoRepository.findById(id);

		if (!endereco.isPresent()) {
			throw new MessageException("id " +id);
		}
			
		Resource<Endereco> resource = new Resource<Endereco>(endereco.get());

		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listaEnderecos());

		resource.add(linkTo.withRel("todos-enderecos"));

		return resource;
	}
}
