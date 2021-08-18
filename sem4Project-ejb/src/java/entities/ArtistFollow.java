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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author asus
 */
@Entity
@Table(name = "ArtistFollow")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ArtistFollow.findAll", query = "SELECT a FROM ArtistFollow a")
    , @NamedQuery(name = "ArtistFollow.findByFollowID", query = "SELECT a FROM ArtistFollow a WHERE a.followID = :followID")})
public class ArtistFollow implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "FollowID")
    private Integer followID;
    @JoinColumn(name = "AccountID", referencedColumnName = "AccountID")
    @ManyToOne
    private Accounts accountID;
    @JoinColumn(name = "ArtistID", referencedColumnName = "ArtistID")
    @ManyToOne
    private Artists artistID;

    public ArtistFollow() {
    }

    public ArtistFollow(Integer followID) {
        this.followID = followID;
    }

    public Integer getFollowID() {
        return followID;
    }

    public void setFollowID(Integer followID) {
        this.followID = followID;
    }

    public Accounts getAccountID() {
        return accountID;
    }

    public void setAccountID(Accounts accountID) {
        this.accountID = accountID;
    }

    public Artists getArtistID() {
        return artistID;
    }

    public void setArtistID(Artists artistID) {
        this.artistID = artistID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (followID != null ? followID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArtistFollow)) {
            return false;
        }
        ArtistFollow other = (ArtistFollow) object;
        if ((this.followID == null && other.followID != null) || (this.followID != null && !this.followID.equals(other.followID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ArtistFollow[ followID=" + followID + " ]";
    }

}
