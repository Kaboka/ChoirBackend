/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.choir.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author
 * kasper
 */
@Entity
@Table(name = "MEMBER")
@NamedQueries({
    @NamedQuery(name = "Member1.findAll", query = "SELECT m FROM Member1 m"),
    @NamedQuery(name = "Member1.findById", query = "SELECT m FROM Member1 m WHERE m.id = :id"),
    @NamedQuery(name = "Member1.findByStreet", query = "SELECT m FROM Member1 m WHERE m.street = :street"),
    @NamedQuery(name = "Member1.findByZipcode", query = "SELECT m FROM Member1 m WHERE m.zipcode = :zipcode"),
    @NamedQuery(name = "Member1.findByCity", query = "SELECT m FROM Member1 m WHERE m.city = :city"),
    @NamedQuery(name = "Member1.findByPhone", query = "SELECT m FROM Member1 m WHERE m.phone = :phone"),
    @NamedQuery(name = "Member1.findByEmail", query = "SELECT m FROM Member1 m WHERE m.email = :email"),
    @NamedQuery(name = "Member1.findByPassword", query = "SELECT m FROM Member1 m WHERE m.password = :password")})
public class ChoirMember extends Person {
    private static final long serialVersionUID = 1L;
    
    @Size(max = 40)
    @Column(name = "STREET")
    private String street;
    @Column(name = "ZIPCODE")
    private Integer zipcode;
    @Size(max = 20)
    @Column(name = "CITY")
    private String city;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 30)
    @Column(name = "PHONE")
    private String phone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 40)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 30)
    @Column(name = "PASSWORD")
    private String password;
    @ManyToMany(mappedBy = "member1Collection")
    private Collection<ChoirRole> choirRoleCollection;
    @JoinColumn(name = "CODE", referencedColumnName = "CODE")
    @ManyToOne
    private Voice code;
    @JoinColumn(name = "ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Person person;

    public ChoirMember() {
    }

    public ChoirMember(Integer id) {
        super(id);
    }


    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getZipcode() {
        return zipcode;
    }

    public void setZipcode(Integer zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<ChoirRole> getChoirRoleCollection() {
        return choirRoleCollection;
    }

    public void setChoirRoleCollection(Collection<ChoirRole> choirRoleCollection) {
        this.choirRoleCollection = choirRoleCollection;
    }

    public Voice getCode() {
        return code;
    }

    public void setCode(Voice code) {
        this.code = code;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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
//        if (!(object instanceof ChoirMember)) {
//            return false;
//        }
//        ChoirMember other = (ChoirMember) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "dk.cphbusiness.choir.model.Member1[ id=" + id + " ]";
//    }
    
}
