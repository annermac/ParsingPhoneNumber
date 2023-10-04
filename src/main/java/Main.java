import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("8\\d{10}");

    public static void main(String[] args) {
        String[] urls = {"https://hands.ru/company/about", "https://repetitors.info", "https://repetitors.info/about.php"};

        for (String url : urls) {
            try {
                String html = getHtml(url);
                findPhoneNumbers(html);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String getHtml(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        return doc.html();
    }

    private static void findPhoneNumbers(String html) {
        Matcher matcher = PHONE_NUMBER_PATTERN.matcher(html);
        while (matcher.find()) {
            String phoneNumber = matcher.group();
            System.out.println(phoneNumber);
        }
    }
}

