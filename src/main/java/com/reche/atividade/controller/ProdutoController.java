package com.reche.atividade.controller;

import com.reche.atividade.model.Produto;
import com.reche.atividade.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    private final ProdutoRepository repository;

    public ProdutoController(ProdutoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Produto cadastrar(@RequestBody Produto produto) {
        return repository.save(produto);
    }

    @GetMapping
    public List<Produto> listar(){
        return repository.findAll();
    }

    @PostMapping("/{id}")
    public Produto atualizar(@PathVariable Long id, @RequestBody Produto produto){

        Produto p = repository.findById(id).orElseThrow();

        p.setNome(produto.getNome());
        p.setPreco(produto.getPreco());
        p.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque());
        p.setStatus(produto.getStatus());

        return repository.save(p);
    }
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        repository.deleteById(id);
    }
}
