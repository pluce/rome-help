/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import play.db.jpa.Model;

/**
 *
 * @author Pluce
 */
@Entity
public class Theme extends Model{
    @Column
    public String label;
    
    @Column
    public boolean root = true;
    
    @OneToMany(cascade=CascadeType.REMOVE)
    public List<Indication> indications = new ArrayList<Indication>();
    
    @OneToMany(cascade=CascadeType.REMOVE)
    public List<Theme> sousThemes = new ArrayList<Theme>();

    public Theme(String label) {
        this.label = label;
    }
    
    public Theme(String label, Theme parent) {
        this.label = label;
        parent.sousThemes.add(this);
        this.root = false;
    }
    
}
