package com.ecommerce.backend.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "produto")
public class Produto {

    @Id
    @ApiModelProperty(notes = "Identificador do produto")
    String codigo;

    @ApiModelProperty(notes = "Nome do produto")
    String nome;

    @ApiModelProperty(notes = "Preço do produto")
    String preco;


}
