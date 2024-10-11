import com.google.gson.annotations.SerializedName

data class InitialPageResponse(
    @SerializedName("resource") val resource: Resource,
    @SerializedName("content_type") val contentType: ContentType,
    @SerializedName("feed") val feed: Feed
)

data class FeedResponse(
    @SerializedName("feed") val feed: Feed
)

data class Resource(
    @SerializedName("bastianInstance") val bastianInstance: String?,
    @SerializedName("brandedContent") val brandedContent: Boolean?,
    @SerializedName("brandedContentCampaign") val brandedContentCampaign: String?,
    @SerializedName("category") val category: String?,
    @SerializedName("contentType") val resourceContentType: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("status") val status: Status?,
    @SerializedName("title") val title: String?,
    @SerializedName("created") val created: String?,
    @SerializedName("modified") val modified: String?,
    @SerializedName("createdBy") val createdBy: String?,
    @SerializedName("versionId") val versionId: String?,
    @SerializedName("id") val id: String?,
    @SerializedName("tenantId") val tenantId: String?,
    @SerializedName("scheduled") val scheduled: Boolean?,
    @SerializedName("scheduler") val scheduler: String?,
    @SerializedName("issued") val issued: String?
)

data class Status(
    @SerializedName("slug") val slug: String?,
    @SerializedName("type") val type: String?,
    @SerializedName("name") val name: String?
)

data class ContentType(
    @SerializedName("api") val api: String?,
    @SerializedName("class") val classType: String?,
    @SerializedName("created") val created: String?,
    @SerializedName("createdBy") val createdBy: String?,
    @SerializedName("customName") val customName: String?,
    @SerializedName("id") val id: String?,
    @SerializedName("modified") val modified: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("schema") val schema: String?,
    @SerializedName("tenantId") val tenantId: String?,
    @SerializedName("urlType") val urlType: String?,
    @SerializedName("versionId") val versionId: String?
)

data class Feed(
    @SerializedName("oferta") val oferta: String?,
    @SerializedName("falkor") val falkor: Falkor?
)

data class Falkor(
    @SerializedName("items") val items: List<Item>?,
    @SerializedName("lastPublication") val lastPublication: String?,
    @SerializedName("publication") val publication: String?,
    @SerializedName("content") val content: Content?,
    @SerializedName("config") val config: Config?,
    @SerializedName("nextPage") val nextPage: Int?
)

data class Config(
    @SerializedName("cardsPosition") val cardsPosition: List<Any>?,
    @SerializedName("componentPosition") val componentPosition: Int?,
    @SerializedName("cutRange") val cutRange: Int?,
    @SerializedName("disableDateTime") val disableDateTime: Boolean?,
    @SerializedName("feedType") val feedType: String?,
    @SerializedName("forePosts") val forePosts: Int?,
    @SerializedName("photoOverVideo") val photoOverVideo: Boolean?,
    @SerializedName("recommendation") val recommendation: Recommendation?,
    @SerializedName("strategies") val strategies: List<String>?
)

data class Recommendation(
    @SerializedName("active") val active: Boolean?,
    @SerializedName("contentId") val contentId: String?,
    @SerializedName("start") val start: Int?
)

data class Item(
    @SerializedName("id") val id: String?,
    @SerializedName("type") val type: String?,
    @SerializedName("regularItem") val regularItem: Boolean?,
    @SerializedName("feedId") val feedId: String?,
    @SerializedName("lastPublication") val lastPublication: String?,
    @SerializedName("publication") val publication: String?,
    @SerializedName("content") val content: Content?,
    @SerializedName("draft") val draft: Boolean?,
    @SerializedName("area") val area: String?,
    @SerializedName("postHash") val postHash: String?,
    @SerializedName("parentId") val parentId: String?,
    @SerializedName("children") val children: String?,
    @SerializedName("isPublishing") val isPublishing: Boolean?,
    @SerializedName("created") val created: String?,
    @SerializedName("modified") val modified: String?,
    @SerializedName("tenantId") val tenantId: String?,
    @SerializedName("versionId") val versionId: String?,
    @SerializedName("age") val age: Int?,
    @SerializedName("metadata") val metadata: String?,
    @SerializedName("aggregatedPosts") val aggregatedPosts: List<AggregatedPost>?,
    @SerializedName("data") val data: AdData?,
    @SerializedName("cobertura") val cobertura: Cobertura?,
    @SerializedName("customPost") val customPost: Boolean?
)

data class Content(
    @SerializedName("chapeu") val chapeu: Chapeu?,
    @SerializedName("identifier") val identifier: String?,
    @SerializedName("image") val image: Image?,
    @SerializedName("section") val section: String?,
    @SerializedName("summary") val summary: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("type") val type: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("video") val video: Any?
)

data class Chapeu(
    @SerializedName("label") val label: String?
)

data class Image(
    @SerializedName("cropOptions") val cropOptions: CropOptions?,
    @SerializedName("rightsHolder") val rightsHolder: String?,
    @SerializedName("size") val size: Size?,
    @SerializedName("sizes") val sizes: Sizes?,
    @SerializedName("url") val url: String?
)

data class CropOptions(
    @SerializedName("landscape") val landscape: CropDetails?,
    @SerializedName("portrait") val portrait: CropDetails?,
    @SerializedName("type") val type: String?
)

data class CropDetails(
    @SerializedName("bottom") val bottom: Int?,
    @SerializedName("left") val left: Int?,
    @SerializedName("right") val right: Int?,
    @SerializedName("top") val top: Int?
)

data class Size(
    @SerializedName("height") val height: Int?,
    @SerializedName("width") val width: Int?
)

data class Sizes(
    @SerializedName("L") val l: SizeDetails?,
    @SerializedName("M") val m: SizeDetails?,
    @SerializedName("Q") val q: SizeDetails?,
    @SerializedName("S") val s: SizeDetails?,
    @SerializedName("V") val v: SizeDetails?,
    @SerializedName("VL") val vl: SizeDetails?,
    @SerializedName("VM") val vm: SizeDetails?,
    @SerializedName("VS") val vs: SizeDetails?,
    @SerializedName("VXL") val vxl: SizeDetails?,
    @SerializedName("VXS") val vxs: SizeDetails?,
    @SerializedName("XS") val xs: SizeDetails?
)

data class SizeDetails(
    @SerializedName("height") val height: Int?,
    @SerializedName("url") val url: String?,
    @SerializedName("width") val width: Int?
)

data class AggregatedPost(
    @SerializedName("type") val type: String?,
    @SerializedName("content") val content: Content?
)

data class AdData(
    @SerializedName("tvg_pos") val tvgPos: String?,
    @SerializedName("tvg_pgStr") val tvgPgStr: String?,
    @SerializedName("adUnit") val adUnit: String?,
    @SerializedName("adCMS") val adCMS: String?,
    @SerializedName("adAccount") val adAccount: String?,
    @SerializedName("homeSuica") val homeSuica: Boolean?
)

data class Cobertura(
    @SerializedName("isLive") val isLive: Boolean?
)