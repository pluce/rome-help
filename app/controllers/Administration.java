/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.List;
import models.Indication;
import models.Theme;
import play.mvc.Controller;
import play.mvc.With;

/**
 *
 * @author Pluce
 */

@With(Secure.class)
public class Administration extends Controller {
    
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
        Administration.admin();
    }
    public static void removeTheme(Long id,Long parentId){
        Theme t = Theme.findById(id);
        if(parentId != null){
            Theme pa = Theme.findById(parentId);
            pa.sousThemes.remove(t);
            pa.save();
            t.delete();
            Application.theme(pa.label);
        } else {
            t.delete();
            Application.index();
        }
    }
    public static void removeIndication(Long id,Long themeId){
        Theme t = Theme.findById(themeId);
        Indication i = Indication.findById(id);
        t.indications.remove(i);
        t.save();
        i.delete();
        Application.theme(t.label);
    }
    public static void addIndication(String titre,String valeur,Long theme){
        Theme t = Theme.findById(theme);
        Indication i = new Indication(titre,valeur,t);
		
        i.save();
        t.save();
	Administration.admin();
    }
}
