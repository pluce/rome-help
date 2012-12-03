package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;
import play.test.Fixtures;

public class Application extends Controller {

    public static void index() {
      /*  Fixtures.deleteDatabase();
        Theme t1 = new Theme("Vie Quotidienne");
        Theme t2 = new Theme("Transport");
        Theme st1 = new Theme("Distances parcourues en 1 journée",t2);
        Theme st2 = new Theme("Durée des voyages par mer",t2);
        
        st1.save();
        st2.save();
        t1.save();
        t2.save();
        
        (new Indication("Prix d'un pain","0.2 as",t1)).save();
        (new Indication("Prix d'un cheval","200 as",t1)).save();
        (new Indication("À cheval","40km",st1)).save();
        (new Indication("À pied","20km",st1)).save();
        (new Indication("En birème","100km",st1)).save();
        (new Indication("Rome - Carthage","20 jours",st2)).save();
        (new Indication("Rome - Massalia","10 jours",st2)).save();
        (new Indication("Nombre de personne dans une galère","200 pers.",t2)).save();
        */
        List<Theme> themes = Theme.find("byRoot",true).fetch();
        render(themes);
    }
    
    public static void admin(){
        List<Theme> rootThemes = Theme.find("byRoot",true).fetch();
        List<Theme> allThemes = Theme.findAll();
        render(rootThemes,allThemes);
    }
    
    public static void addTheme(String titre,Long parent){
        Theme t;
        if(parent != 0){
            Theme pa = Theme.findById(parent);
            t = new Theme(titre,pa);
			t.save();
			pa.save();
        } else {
            t = new Theme(titre);
			t.save();
        }
		
        Application.admin();
    }
    public static void addIndication(String titre,String valeur,Long theme){
        Theme t = Theme.findById(theme);
        Indication i = new Indication(titre,valeur,t);
		
        i.save();
        t.save();
		Application.admin();
    }
    
    public static void theme(String actualTheme) {
        List<Theme> themes = Theme.find("byRoot",true).fetch();
        Theme theActualTheme = Theme.find("byLabel", actualTheme).first();
        //List<Indication> indications = Indication.find("byTheme",theActualTheme).fetch();
        
        render(theActualTheme,themes);
    }

}