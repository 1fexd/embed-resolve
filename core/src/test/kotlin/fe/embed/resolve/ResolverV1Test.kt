package fe.embed.resolve

import assertk.assertThat
import assertk.assertions.isEqualTo
import fe.embed.resolve.config.ConfigType.Bundled
import fe.embed.resolve.resolver.ResolverV1
import org.junit.Test

class ResolverV1Test {

    @Test
    fun testTwitter() {
        val bundled = Bundled.load()
        val expected = "https://twitter.com/GrapheneOS/status/1852151214579851484"

        val urls = arrayOf("vxtwitter.com", "fxtwitter.com", "twittpr.com").map {
            "https://$it/GrapheneOS/status/1852151214579851484"
        }

        assertEach(urls) { url ->
            assertThat(ResolverV1.resolve(url, bundled.config)).isEqualTo(expected)
        }
    }

    @Test
    fun testX() {
        val bundled = Bundled.load()
        val expected = "https://x.com/GrapheneOS/status/1852151214579851484"

        val urls = arrayOf("fixvx.com", "fixupx.com", "yiffx.com", "stupidpenisx.com").map {
            "https://$it/GrapheneOS/status/1852151214579851484"
        }

        assertEach(urls) { url ->
            assertThat(ResolverV1.resolve(url, bundled.config)).isEqualTo(expected)
        }
    }

    @Test
    fun testInstagram() {
        val bundled = Bundled.load()
        val expected = "https://instagram.com/p/DAb9_aDtoW_"

        val urls = arrayOf("ddinstagram.com").map {
            "https://$it/p/DAb9_aDtoW_"
        }

        assertEach(urls) { url ->
            assertThat(ResolverV1.resolve(url, bundled.config)).isEqualTo(expected)
        }
    }

    @Test
    fun testReddit() {
        val bundled = Bundled.load()
        val expected = "https://reddit.com/r/shittymoviedetails/comments/160onpq/comment/jxnkq4g"

        val urls = arrayOf("rxddit.com").map {
            "https://$it/r/shittymoviedetails/comments/160onpq/comment/jxnkq4g"
        }

        assertEach(urls) { url ->
            assertThat(ResolverV1.resolve(url, bundled.config)).isEqualTo(expected)
        }
    }

    @Test
    fun testPixiv() {
        val bundled = Bundled.load()
        val expected = "https://pixiv.net/en/artworks/64616766"

        val urls = arrayOf("phixiv.net").map {
            "https://$it/en/artworks/64616766"
        }

        assertEach(urls) { url ->
            assertThat(ResolverV1.resolve(url, bundled.config)).isEqualTo(expected)
        }
    }

    @Test
    fun testBluesky() {
        val bundled = Bundled.load()
        val expected = "https://bsky.app/profile/grapheneos.org/post/3l7txdjwws62j"

        val urls = arrayOf("boobsky.app", "bsyy.app", "bskyx.app", "fxbsky.app").map {
            "https://$it/profile/grapheneos.org/post/3l7txdjwws62j"
        }

        assertEach(urls) { url ->
            assertThat(ResolverV1.resolve(url, bundled.config)).isEqualTo(expected)
        }
    }
}
