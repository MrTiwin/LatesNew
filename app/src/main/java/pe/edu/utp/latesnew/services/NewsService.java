package pe.edu.utp.latesnew.services;

import pe.edu.utp.latesnew.models.Source;

/**
 * Created by GrupoUTP on 05/11/2016.
 */

public class NewsService {
    public static String SOURCE_URL ="https://newsapi.org/v1/sources";
    public static String ARTICLES_URL=" https://newsapi.org/v1/articles";
    private Source currentSource;

    public Source getCurrentSource() {
        return currentSource;
    }

    public void setCurrentSource(Source currentSource) {
        this.currentSource = currentSource;
    }
}
