/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author asus
 */
@Entity
@Table(name = "Advertise")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Advertise.findAll", query = "SELECT a FROM Advertise a")
    , @NamedQuery(name = "Advertise.findByAdvertiseID", query = "SELECT a FROM Advertise a WHERE a.advertiseID = :advertiseID")
    , @NamedQuery(name = "Advertise.findByAdvertiseName", query = "SELECT a FROM Advertise a WHERE a.advertiseName = :advertiseName")
    , @NamedQuery(name = "Advertise.findByDescription", query = "SELECT a FROM Advertise a WHERE a.description = :description")
    , @NamedQuery(name = "Advertise.findByThumbnail", query = "SELECT a FROM Advertise a WHERE a.thumbnail = :thumbnail")
    , @NamedQuery(name = "Advertise.findByUrl", query = "SELECT a FROM Advertise a WHERE a.url = :url")})
public class Advertise implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "AdvertiseID")
    private Integer advertiseID;
    @Size(max = 100)
    @Column(name = "AdvertiseName")
    private String advertiseName;
    @Size(max = 250)
    @Column(name = "Description")
    private String description;
    @Size(max = 100)
    @Column(name = "Thumbnail")
    private String thumbnail;
    @Size(max = 100)
    @Column(name = "Url")
    private String url;
    @JoinColumn(name = "AccountID", referencedColumnName = "AccountID")
    @ManyToOne
    private Accounts accountID;

    public Advertise() {
    }

    public Advertise(Integer advertiseID) {
        this.advertiseID = advertiseID;
    }

    public Integer getAdvertiseID() {
        return advertiseID;
    }

    public void setAdvertiseID(Integer advertiseID) {
        this.advertiseID = advertiseID;
    }

    public String getAdvertiseName() {
        return advertiseName;
    }

    public void setAdvertiseName(String advertiseName) {
        this.advertiseName = advertiseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Accounts getAccountID() {
        return accountID;
    }

    public void setAccountID(Accounts accountID) {
        this.accountID = accountID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (advertiseID != null ? advertiseID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Advertise)) {
            return false;
        }
        Advertise other = (Advertise) object;
        if ((this.advertiseID == null && other.advertiseID != null) || (this.advertiseID != null && !this.advertiseID.equals(other.advertiseID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Advertise[ advertiseID=" + advertiseID + " ]";
    }
    
}
