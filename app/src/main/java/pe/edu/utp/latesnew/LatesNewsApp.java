package pe.edu.utp.latesnew;

import android.app.Application;

import pe.edu.utp.latesnew.models.Source;
import pe.edu.utp.latesnew.services.NewsService;


public class LatesNewsApp extends Application {
    static LatesNewsApp instace;
    NewsService service = new NewsService();

    public LatesNewsApp(){
        super();
        instace = this;
    }

    public static LatesNewsApp getInstace(){
        return instace;
    }

    public NewsService getService(){
        return service;
    }

    public Source getCurrentSource(){
        return service.getCurrentSource();
    }

    public void setCurrentSource (Source source){
        service.setCurrentSource(source);
    }
}
