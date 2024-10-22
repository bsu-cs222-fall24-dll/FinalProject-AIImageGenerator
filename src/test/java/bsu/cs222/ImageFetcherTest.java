package bsu.cs222;

import bsu.cs222.finalprojectaiimagegenerator.ImageFetcher;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ImageFetcherTest {

    @Test
    void testCreateUrl() throws IOException, URISyntaxException {
        ImageFetcher fetcher = new ImageFetcher();
        String searchInput = "red xbox";
        URL url = fetcher.createUrl(searchInput);
        String expectedUrl = "https://image.pollinations.ai/prompt/red%20xbox";
        assertEquals(expectedUrl, url.toString());
    }
}