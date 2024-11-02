package fe.embed.resolve

import assertk.assertThat
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import fe.embed.resolve.config.ConfigType.Bundled
import fe.embed.resolve.resolver.ResolverV1
import org.junit.Test

class ResolverV1BlueskyTest {
    @Test
    fun testBlueskyRegex() {
        val bundled = Bundled.load()
        val service = bundled.config.services.first { it.name == "Bluesky" }

        val validHandles = arrayOf(
            "jay.bsky.social",
            "8.cn",
            "name.t--t",
            "XX.LCS.MIT.EDU",
            "a.co",
            "xn--notarealidn.com",
            "xn--fiqa61au8b7zsevnm8ak20mc4a87e.xn--fiqs8s",
            "xn--ls8h.test",
            "example.t"
        )

        val validUrls = validHandles.map { "/profile/$it/post/3l7txdjwws62j" }
        assertEach(validUrls) { url ->
            assertThat(ResolverV1.isMatch(service, url)).isNotNull()
        }

        val invalidHandles = arrayOf(
            "jo@hn.test",
            "💩.test",
            "john..test",
            "xn--bcher-.tld",
            "john.0",
            "cn.8",
            "www.masełkowski.pl.com",
            "org",
            "name.org.",
        )

        val invalidUrls = invalidHandles.map { "/profile/$it/post/3l7txdjwws62j" }
        assertEach(invalidUrls) { url ->
            assertThat(ResolverV1.isMatch(service, url)).isNull()
        }
    }
}
