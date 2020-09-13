package br.com.quixote.server.business

import br.com.quixote.server.data.service.VolumeService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/volume")
class VolumeController(
        private val volumeService: VolumeService
) {

    @GetMapping("/")
    fun findAll() = volumeService.findAll()

    @GetMapping("/{asin}")
    fun findByAsin(@PathVariable asin: String) =
            volumeService.findByAsin(asin) ?: ResponseStatusException(HttpStatus.NOT_FOUND, "This volume doesn't exist")

    @PostMapping("/", consumes = ["multipart/form-data"])
    fun saveVolumes(@RequestParam("file") file: MultipartFile) {
        volumeService.parseVolumes(file)
    }
}