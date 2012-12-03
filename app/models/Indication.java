/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import play.db.jpa.Model;

/**
 *
 * @author Pluce
 */
@Entity
public class Indication extends Model{
    @Column
    public String label;
    
    @Column
    public String valeur;

    public Indication(String label, String valeur, Theme theme) {
        this.label = label;
        this.valeur = valeur;
        theme.indications.add(this);
    }
    
    
}
