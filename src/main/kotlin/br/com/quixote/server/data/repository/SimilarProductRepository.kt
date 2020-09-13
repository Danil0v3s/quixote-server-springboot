package br.com.quixote.server.data.repository

import br.com.quixote.server.data.model.SimilarProduct
import org.springframework.data.repository.CrudRepository

interface SimilarProductRepository : CrudRepository<SimilarProduct, String>