package br.com.quixote.server.configuration

import br.com.quixote.server.data.model.ItemAttributes
import br.com.quixote.server.data.model.Volume
import br.com.quixote.server.data.service.VolumeService
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ServerConfiguration {

    @Bean
    fun databaseInitializer(volumeService: VolumeService) = ApplicationRunner {
        val itemAttributes = ItemAttributes(
                language = "Português",
                title = "Monalise Overdrive",
                studio = "Aleph",
                releaseDate = "2017-07-22",
                publisher = "Aleph",
                publicationDate = "2017-07-17",
                productTypeName = "ABIS_BOOK",
                productGroup = "Book",
                numberOfPages = 320,
                numberOfItems = 1,
                manufacturer = "Aleph",
                label =  "Aleph",
                isbn = "8576573024",
                format = "Edição padrão",
                edition = "2",
                ean = "9788576573029",
                brand = "Aleph",
                binding = "Capa comum",
                author = "William Gibson"
        )

        val volume = Volume(
                asin = "8576573024",
                mediumImage = "https://images-na.ssl-images-amazon.com/images/I/61LlsiGYkUL._SL160_.jpg",
                largeImage = "https://images-na.ssl-images-amazon.com/images/I/61LlsiGYkUL.jpg",
                editorialReview = "As Inteligências Artificiais assombram a matrix. O ciberespaço, essa espécie de alucinação coletiva, está cada vez mais perigoso. As Inteligências Artificiais atingiram a autoconsciência e dividem esse espaço com os mais inusitados personagens, movidos por interesses diversos e intenções nem sempre lícitas. Nesse cenário, três diferentes histórias se entrelaçam e trazem o leitor de volta para o universo de Neuromacer, em uma última e impactante aventura. Monalisa Overdrive é o terceiro volume da Trilogia do Sprawl.",
                itemAttributes = itemAttributes,
                similarProducts = mutableListOf()
        )
//        volumeService.save(volume)
    }
}