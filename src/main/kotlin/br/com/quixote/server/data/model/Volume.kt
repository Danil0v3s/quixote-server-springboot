package br.com.quixote.server.data.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
class Volume(
        @Id
        var asin: String,
        var mediumImage: String?,
        var largeImage: String?,
        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "attributes_isbn")
        var itemAttributes: ItemAttributes,
        @OneToMany(cascade = [CascadeType.PERSIST])
        @JoinColumn(name = "similar_asin")
        var similarProducts: MutableList<SimilarProduct> = mutableListOf(),
        @Column(columnDefinition = "text") var editorialReview: String?
)

@Entity
class ItemAttributes(
        var author: String,
        var brand: String?,
        var binding: String,
        var edition: String?,
        var ean: String,
        var format: String?,
        @Id var isbn: String,
        var label: String?,
        var language: String?,
        var manufacturer: String?,
        var numberOfPages: Int,
        var numberOfItems: Int,
        var publisher: String?,
        var publicationDate: String?,
        var productTypeName: String,
        var productGroup: String,
        var releaseDate: String?,
        var studio: String?,
        var title: String,
        @OneToOne(mappedBy = "itemAttributes")
        @JsonIgnore
        var volume: Volume? = null
)

@Entity
class SimilarProduct(
        @Id var asin: String,
        var title: String
)