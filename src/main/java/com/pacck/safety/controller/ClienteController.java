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
import com.pacck.safety.Repository.ClienteRepository;
import com.pacck.safety.model.Cliente;

@RestController
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping("/clientes")
	public List<Cliente> listaClientes(){
		return clienteRepository.findAll();
	}
	
	@GetMapping("/clientes/{id}")
	public Resource<Cliente> selecionarCliente(@PathVariable int id) {
		Optional<Cliente> clientes = clienteRepository.findById(id);

		if (!clientes.isPresent()) {
			throw new MessageException("id " +id);
		}
			
		Resource<Cliente> resource = new Resource<Cliente>(clientes.get());

		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listaClientes());

		resource.add(linkTo.withRel("todos-clientes"));

		return resource;
	}
	
}
