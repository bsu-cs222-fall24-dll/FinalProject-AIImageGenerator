package bsu.edu.cs222;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ImageFetcherTest {

    @Test
    void testCreateUnderscoreUrl() throws IOException, URISyntaxException {
        ImageFetcher fetcher = new ImageFetcher();
        String searchInput = "red_xbox";
        URL url = fetcher.createUrl(searchInput);
        String expectedUrl = "https://image.pollinations.ai/prompt/red_xbox";
        assertEquals(expectedUrl, url.toString());
    }

    @Test
    void testCreateSpaceUrl() throws IOException, URISyntaxException {
        ImageFetcher fetcher = new ImageFetcher();
        String searchInput = "red xbox";
        URL url = fetcher.createUrl(searchInput);
        String expectedUrl = "https://image.pollinations.ai/prompt/red+xbox";
        assertEquals(expectedUrl, url.toString());
    }
    
    @Test
    void testCreateEmptyUrl() throws IOException, URISyntaxException {
        ImageFetcher fetcher = new ImageFetcher();
        String searchInput = "";
        URL url = fetcher.createUrl(searchInput);
        String expectedUrl = "https://image.pollinations.ai/prompt/";
        assertEquals(expectedUrl, url.toString());
    }
}