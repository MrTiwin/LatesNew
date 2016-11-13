package pe.edu.utp.latesnew.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Article {
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private Source source;

    public Article() {
    }

    public Article(String author, String title, String description, String url, String urlToImage, String publishedAt, Source source) {
        this.setAuthor(author);
        this.setTitle(title);
        this.setDescription(description);
        this.setUrl(url);
        this.setUrlToImage(urlToImage);
        this.setPublishedAt(publishedAt);
        this.setSource(source);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public static Article buildFromJSONObject(JSONObject jsonArticle, Source source) {

        Article article = new Article();
        try {
            article.setAuthor(jsonArticle.getString("Autor"));
            article.setTitle(jsonArticle.getString("title"));
            article.setDescription(jsonArticle.getString("title"));
            article.setPublishedAt(jsonArticle.getString("title"));
            article.setUrl(jsonArticle.getString("title"));
            article.setUrlToImage(jsonArticle.getString("title"));
            article.setSource(source);
            Log.d("LatesNews", article.getTitle());
            return article;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Article> buildFromJSONArray(JSONArray jsonArticles, Source source) {
        int articlesCount = jsonArticles.length();
        List<Article> articles = new ArrayList<>();
        for (int i = 0; i < articlesCount; i++) {
            JSONObject jsonArticle;
            try {
                jsonArticle = (JSONObject) jsonArticles.get(i);
                Article article = Article.buildFromJSONObject(jsonArticle, source);
                articles.add(article);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return articles;
    }
}
