package br.com.quixote.server.data.repository

import br.com.quixote.server.data.model.Volume
import org.springframework.data.repository.CrudRepository

interface VolumeRepository : CrudRepository<Volume, String> {
    fun findByAsin(asin: String): Volume?
}