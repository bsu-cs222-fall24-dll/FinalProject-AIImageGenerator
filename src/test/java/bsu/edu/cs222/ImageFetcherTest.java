package bsu.edu.cs222;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ImageFetcherTest {

    @Test
    void testCreateUrl() throws IOException, URISyntaxException {
        ImageFetcher fetcher = new ImageFetcher();
        String searchInput = "red_xbox";
        URL url = fetcher.createUrl(searchInput);
        String expectedUrl = "https://image.pollinations.ai/prompt/red_xbox";
        assertEquals(expectedUrl, url.toString());
    }
}