package io.bibuti.opennews.data.apiresponses

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("status")
    val status: String?, // ok
    @SerializedName("totalResults")
    val totalResults: Int?, // 70
    @SerializedName("articles")
    val articles: List<Article?>?
) {
    data class Article(
        @SerializedName("source")
        val source: Source?,
        @SerializedName("author")
        val author: String?, // null
        @SerializedName("title")
        val title: String?, // China orders Ant to rein in expansion, regulators put up fences around financial risks - South China Morning Post
        @SerializedName("description")
        val description: String?, // Ant has been instructed to rein in the influence of technology on its financial services as China’s financial regulators ring fence the industry to prevent uncontrolled growth in the industry from leading to financial risks.
        @SerializedName("url")
        val url: String?, // https://www.scmp.com/business/companies/article/3115466/beijing-outlines-concerns-ant-group-vows-fully-implement
        @SerializedName("urlToImage")
        val urlToImage: String?, // https://cdn.i-scmp.com/sites/default/files/styles/og_image_scmp_generic/public/d8/images/methode/2020/12/27/18c5048e-4823-11eb-be92-09cd005df0bf_image_hires_202359.jpg?itok=mgUGxoJN&v=1609071846
        @SerializedName("publishedAt")
        val publishedAt: String?, // 2020-12-27T12:05:57Z
        @SerializedName("content")
        val content: String? // Published: 5:06pm, 27 Dec, 2020Updated: 8:24pm, 27 Dec, 2020
    ) {
        data class Source(
            @SerializedName("id")
            val id: Any?, // null
            @SerializedName("name")
            val name: String? // Post Magazine
        )
    }
}