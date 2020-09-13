package br.com.quixote.server.data.service

import br.com.quixote.server.data.model.Volume
import br.com.quixote.server.data.repository.SimilarProductRepository
import br.com.quixote.server.data.repository.VolumeRepository
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.lang.Exception

@Service
class VolumeService(
        private val volumeRepository: VolumeRepository,
        private val similarProductRepository: SimilarProductRepository
) {
    private val logger = LoggerFactory.getLogger(VolumeService::class.java)

    fun findAll(): Iterable<Volume> = volumeRepository.findAll()

    fun findByAsin(asin: String) = volumeRepository.findByAsin(asin)

    fun save(volume: Volume) = volumeRepository.save(volume)

    fun parseVolumes(file: MultipartFile) {
        val mapper = jacksonObjectMapper()
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        mapper.propertyNamingStrategy = PropertyNamingStrategy.UPPER_CAMEL_CASE
        val volumes: List<Volume> = mapper.readValue(String(file.bytes))

        volumes.forEach { volume ->
            volume.similarProducts.forEach {
                try {
                    similarProductRepository.save(it)
                } catch (e: Exception) {
                    logger.error(e.message)
                }
            }
            try {
                volumeRepository.save(volume)
            } catch (e: Exception) {
                logger.error("${volume.asin} ${volume.itemAttributes.ean}", e)
            }
        }
    }
}