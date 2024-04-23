import java.io.InputStream

object BundledJsonLoader {
    fun getBuiltIn(name: String = "bundled.json"): InputStream {
        return BundledJsonLoader::class.java.getResourceAsStream(name)!!
    }
}
