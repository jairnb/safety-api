package com.pacck.safety.controller;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.paack.safety.exception.MessageException;
import com.pacck.safety.Repository.ServicoRepository;
import com.pacck.safety.model.Servico;

@RestController
public class ServicoController {

	@Autowired
	private ServicoRepository servicoRepository;
	
	@GetMapping("/servicos")
	public List<Servico> listaServicos(){
		return servicoRepository.findAll();
	}
	
	@GetMapping("/servicos/{id}")
	public Resource<Servico> selecionarServico(@PathVariable int id) {
		Optional<Servico> servicos = servicoRepository.findById(id);

		if (!servicos.isPresent()) {
			throw new MessageException("id " +id);
		}
			
		Resource<Servico> resource = new Resource<Servico>(servicos.get());

		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listaServicos());

		resource.add(linkTo.withRel("todos-clientes"));

		return resource;
	}
}
