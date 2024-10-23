package bsu.edu.cs222;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class ImageFetcher {
    public URL createUrl(String searchInput) throws IOException, URISyntaxException {
        String encodedSearchInput = URLEncoder.encode(searchInput, StandardCharsets.UTF_8);
        String apiUrl = "https://image.pollinations.ai/prompt/" + encodedSearchInput;
        URI uri = new URI(apiUrl);
        return uri.toURL();
    }

    public InputStream fetchImage(String searchInput) throws IOException, URISyntaxException {
        URL url = createUrl(searchInput);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        return connection.getInputStream();
    }
}
