package com.ecommerce.backend.controller;

import com.ecommerce.backend.model.Produto;
import com.ecommerce.backend.repository.ProdutoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/****
Link para testar no Swagger:  http://localhost:8080/swagger-ui/
****/

@RestController
@RequestMapping("/api")
@Api(tags = "API de produto")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/produtos")
    @ApiOperation(value = "Listar produtos")
    public ResponseEntity<List<Produto>> getProdutos(){
        try{
            List<Produto> produtos = produtoRepository.findAll();
            if(!produtos.isEmpty()){
                return new ResponseEntity<>(produtos, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/produto/cadastrar")
    @ApiOperation(value = "Cadastrar um produto")
    public ResponseEntity<Produto> createProduto(@Valid @RequestBody Produto produto){
        Produto produtoCadastrado = produtoRepository.save(produto);
        return new ResponseEntity<>(produto, HttpStatus.CREATED);
    }

    @PutMapping("/produto/editar/{codigo}")
    @ApiOperation(value = "Editar um produto")
    public ResponseEntity<Produto> editProduto(@Valid @RequestBody Produto produto, @PathVariable("codigo") String codigo){
        Optional<Produto> verificaCodigo = produtoRepository.findById(codigo);
        if(verificaCodigo.isPresent()){
            Produto produtoEditado;
            produtoEditado = verificaCodigo.get();
            produtoEditado.setCodigo(produtoEditado.getCodigo());
            produtoEditado.setNome(produto.getNome());
            produtoEditado.setPreco(produto.getPreco());
            produtoEditado = produtoRepository.save(produtoEditado);
            return new ResponseEntity<>(produtoEditado, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/produto/{codigo}")
    @ApiOperation(value = "Remove um produto")
    public ResponseEntity<Produto> deleteProduto(@Valid @PathVariable("codigo") String codigo){
        Optional<Produto> verificaCodigo = produtoRepository.findById(codigo);
        try{
            if (verificaCodigo.isPresent()){
                produtoRepository.deleteById(codigo);
                Produto produtoRemovido = verificaCodigo.get();
                return new ResponseEntity<>(produtoRemovido, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/produto/{codigo}")
    @ApiOperation(value = "Busca um produto por código")
    public ResponseEntity<Produto> getProdutoByCodigo(@Valid @PathVariable("codigo") String codigo){
        Optional<Produto> verificaCodigo = produtoRepository.findById(codigo);
        try {
            if (verificaCodigo.isPresent()){
                Produto produto = verificaCodigo.get();
                return new ResponseEntity<>(produto, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
