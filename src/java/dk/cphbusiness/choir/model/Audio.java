/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.choir.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author
 * kasper
 */
@Entity
@Table(name = "AUDIO")
@NamedQueries({
    @NamedQuery(name = "Audio.findAll", query = "SELECT a FROM Audio a"),
    @NamedQuery(name = "Audio.findById", query = "SELECT a FROM Audio a WHERE a.id = :id"),
    @NamedQuery(name = "Audio.findByPlayingTime", query = "SELECT a FROM Audio a WHERE a.playingTime = :playingTime")})
public class Audio extends Material {
    private static final long serialVersionUID = 1L;
    
    
    @Column(name = "PLAYING_TIME")
    private Integer playingTime;
    @JoinColumn(name = "ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Material material;

    public Audio() {
    }

    public Audio(Integer id) {
        super(id);
    }

    public Integer getPlayingTime() {
        return playingTime;
    }

    public void setPlayingTime(Integer playingTime) {
        this.playingTime = playingTime;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (id != null ? id.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof Audio)) {
//            return false;
//        }
//        Audio other = (Audio) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "dk.cphbusiness.choir.model.Audio[ id=" + id + " ]";
//    }
    
}
