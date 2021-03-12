package br.com.impacta.lab.controllers;

import java.util.ArrayList;
import java.util.List;

import java.net.URI;

import br.com.impacta.lab.models.Produto;

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

	public static List<Produto> bancoDeDados = new ArrayList<>();
	
	@GetMapping
	public ResponseEntity<List<Produto>> index() {
		return ResponseEntity.ok(bancoDeDados);
	}

	@PostMapping("/create-many")
	public ResponseEntity<List<Produto>> createMany(@RequestBody List<Produto> produtos) {
		bancoDeDados.addAll(produtos);		
		return ResponseEntity.ok(produtos);
	}

	@PostMapping
	public ResponseEntity<Produto> create(@RequestBody Produto produto) {
		bancoDeDados.add(produto);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigo}")
				.buildAndExpand(produto.getCodigo()).toUri();
		
		return ResponseEntity.created(uri).body(produto);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Produto> retrieve(@PathVariable("codigo") int codigo) {
		
		for (int contador = 0; contador < bancoDeDados.size(); contador++) {
			Produto produto = bancoDeDados.get(contador);
			if (produto.getCodigo() == codigo) {
				return ResponseEntity.ok(produto);
			}
		}

		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/e{codigo:\\d+}")
	public ResponseEntity<Produto> update(@PathVariable("codigo") int codigo,
			@RequestBody Produto newProduto) {
		
		for (int contador = 0; contador < bancoDeDados.size(); contador++) {
			Produto produto = bancoDeDados.get(contador);
			if (produto.getCodigo() == codigo) {
				produto.setDescricao(newProduto.getDescricao());
				produto.setValor(newProduto.getValor());
				
				return ResponseEntity.ok(produto);
			}
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Produto> delete(@PathVariable("codigo") int codigo) {
		
		for (int contador = 0; contador < bancoDeDados.size(); contador++) {
			Produto produto = bancoDeDados.get(contador);
			if (produto.getCodigo() == codigo) {
				bancoDeDados.remove(contador);
				return ResponseEntity.ok(produto);
			}
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/delete-all")
	public ResponseEntity<Produto> deleteAll() {
		
		this.bancoDeDados.removeAll(this.bancoDeDados);
		return ResponseEntity.noContent().build();
	}
	
}
