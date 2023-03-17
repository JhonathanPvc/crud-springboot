package com.jhonathancordeiro.spring.crudspringboot.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.jhonathancordeiro.spring.crudspringboot.model.Pessoa;
import com.jhonathancordeiro.spring.crudspringboot.repository.PessoaRepository;

@RestController
@RequestMapping("/v1/pessoa")
public class PessoaController {
	private PessoaRepository repository;

	public PessoaController(PessoaRepository pessoaRepository) {
		this.repository = pessoaRepository;
	}

	@GetMapping("/listarTodos")
	public String listarPessoas() {
		return new Gson().toJson(repository.findAll());
	}

	@PostMapping("/salvar")
	public String salvar(@RequestBody Pessoa pessoa) {
		Pessoa pessoaNew = repository.save(pessoa);

		return new Gson().toJson(pessoaNew);

	}

	@DeleteMapping("{id}")
	public String deletar(@PathVariable("id") Long pessoaId) {
		String retorno;
		try {
			repository.deleteById(pessoaId);
			retorno = "Registro apagado com sucesso!";
		} catch (Exception e) {
			retorno = "Erro ao apagar registro!" + e.getMessage();
		}

		return retorno;
	}
}