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
@Table(name = "SHEET")
@NamedQueries({
    @NamedQuery(name = "Sheet.findAll", query = "SELECT s FROM Sheet s"),
    @NamedQuery(name = "Sheet.findById", query = "SELECT s FROM Sheet s WHERE s.id = :id"),
    @NamedQuery(name = "Sheet.findByPageCount", query = "SELECT s FROM Sheet s WHERE s.pageCount = :pageCount")})
public class Sheet extends Material {
    private static final long serialVersionUID = 1L;
   
    @Column(name = "PAGE_COUNT")
    private Integer pageCount;
    
    @JoinColumn(name = "ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Material material;

    public Sheet() {
    }

    public Sheet(Integer id) {
        super(id);
    }


    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
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
//        if (!(object instanceof Sheet)) {
//            return false;
//        }
//        Sheet other = (Sheet) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "dk.cphbusiness.choir.model.Sheet[ id=" + id + " ]";
//    }
    
}
