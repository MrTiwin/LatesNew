package pe.edu.utp.latesnew.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Source {
    private String id;
    private String name;
    private String description;
    private String url;
    private String category;
    private String language;
    private String country;
    private Map<String, String> urlsToLogos;
    private List<String> sortBysAvailable;

    public Source() {
    }

    public String getId() {
        return id;
    }

    public Source setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Source setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Source setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Source setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public Source setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getLanguage() {
        return language;
    }

    public Source setLanguage(String language) {
        this.language = language;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public Source setCountry(String country) {
        this.country = country;
        return this;
    }

    public Map<String, String> getUrlsToLogos() {
        return urlsToLogos;
    }

        public String getUrlToSmallLogo(){
            return urlsToLogos.get("small");
        }
        public String getUrlToMediumLogo(){
            return urlsToLogos.get("medium");
        }
        public String getUrlToLargeLogo(){
            return urlsToLogos.get("large");
        }

    public Source setUrlsToLogos(Map<String, String> urlsToLogos) {
        this.urlsToLogos = urlsToLogos;
        return this;
    }

    public List<String> getSortBysAvailable() {
        return sortBysAvailable;
    }

    public Source setSortBysAvailable(List<String> sortBysAvailable) {
        this.sortBysAvailable = sortBysAvailable;
        return this;
    }

    public static Source buildFromJsonObject(JSONObject jsonSource){
        Source source = new Source();
        try {
            Map<String,String> urlTologos = new HashMap<>();
            urlTologos.put("small", jsonSource.getJSONObject("urlToLogos").getString("small"));
            urlTologos.put("medium", jsonSource.getJSONObject("urlToLogos").getString("medium"));
            urlTologos.put("large", jsonSource.getJSONObject("urlToLogos").getString("large"));

            List<String> sortBysAvailable = new ArrayList<>();
            for(int i=0; i < jsonSource.getJSONArray("sorBysAvailable").length(); i++){
                sortBysAvailable
                        .add(jsonSource.getJSONArray("sortBysAvailable").getString(i));
            }
            source.setId(jsonSource.getString("id"))
            .setName(jsonSource.getString("name"))
            .setDescription(jsonSource.getString("description"))
            .setUrl(jsonSource.getString("url"))
            .setCategory(jsonSource.getString("category"))
            .setLanguage(jsonSource.getString("language"))
            .setCountry(jsonSource.getString("country"))
            .setUrlsToLogos(urlTologos)
            .setSortBysAvailable(sortBysAvailable);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return source;
    }

    public static List<Source> buildFromJSONArray(JSONArray jsonSources) {
        List<Source> sources = new ArrayList<>();
        int sourcesCount = jsonSources.length();
        for (int i = 0; i < sourcesCount; i++) {
            JSONObject jsonSource;
            try{
                jsonSource = (JSONObject) jsonSources.get(i);
                Source source = Source.buildFromJsonObject(jsonSource);
                sources.add(source);
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
     return sources;
    }
}
