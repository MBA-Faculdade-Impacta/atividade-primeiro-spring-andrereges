package br.com.impacta.lab.controller;

import java.util.ArrayList;
import java.util.List;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	//http://localhost:8080/produtos
	public static List<Produto> bancoDeDados = new ArrayList<>();
	
	@PostMapping
	public ResponseEntity<Produto> create(@RequestBody Produto produto) {
		bancoDeDados.add(produto);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(produto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(produto);
	}
	
	@GetMapping
	public ResponseEntity<List<Produto>> list() {
		return ResponseEntity.ok(bancoDeDados);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> find(@PathVariable("id") int id) {
		
		for (int contador = 0; contador < bancoDeDados.size(); contador++) {
			Produto produto = bancoDeDados.get(contador);
			if (produto.getId() == id) {
				return ResponseEntity.ok(produto);
			}
		}
		return ResponseEntity.noContent().build();
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Produto> update(@PathVariable("id") int id,
			@RequestBody Produto newProduto) {
		
		for (int contador = 0; contador < bancoDeDados.size(); contador++) {
			Produto produto = bancoDeDados.get(contador);
			if (produto.getId() == id) {
				produto.setDescricao(newProduto.getDescricao());
				produto.setValor(newProduto.getValor());
				
				return ResponseEntity.ok(produto);
			}
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Produto> delete(@PathVariable("id") int id) {
		
		for (int contador = 0; contador < bancoDeDados.size(); contador++) {
			Produto produto = bancoDeDados.get(contador);
			if (produto.getId() == id) {
				bancoDeDados.remove(contador);
				return ResponseEntity.ok(produto);
			}
		}
		return ResponseEntity.notFound().build();
	}
	
}
