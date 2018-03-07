/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Source;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Kasperi
 */
@Entity
@Table(name = "rights")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rights.findAll", query = "SELECT r FROM Rights r")
    , @NamedQuery(name = "Rights.findByRightsID", query = "SELECT r FROM Rights r WHERE r.rightsID = :rightsID")
    , @NamedQuery(name = "Rights.findByRightsDetails", query = "SELECT r FROM Rights r WHERE r.rightsDetails = :rightsDetails")})
public class Rights implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rightsID")
    private Integer rightsID;
    @Basic(optional = false)   
    @Size(min = 1, max = 50)
    @Column(name = "rightsDetails")
    private String rightsDetails;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rights")
    private Collection<Users> usersCollection;

    public Rights() {
    }

    public Rights(Integer rightsID) {
        this.rightsID = rightsID;
    }

    public Rights(Integer rightsID, String rightsDetails) {
        this.rightsID = rightsID;
        this.rightsDetails = rightsDetails;
    }

    public Integer getRightsID() {
        return rightsID;
    }

    public void setRightsID(Integer rightsID) {
        this.rightsID = rightsID;
    }

    public String getRightsDetails() {
        return rightsDetails;
    }

    public void setRightsDetails(String rightsDetails) {
        this.rightsDetails = rightsDetails;
    }

    @XmlTransient
    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rightsID != null ? rightsID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rights)) {
            return false;
        }
        Rights other = (Rights) object;
        if ((this.rightsID == null && other.rightsID != null) || (this.rightsID != null && !this.rightsID.equals(other.rightsID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Source.Rights[ rightsID=" + rightsID + " ]";
    }
    
}
