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
@Table(name = "FavouriteAlbum")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FavouriteAlbum.findAll", query = "SELECT f FROM FavouriteAlbum f")
    , @NamedQuery(name = "FavouriteAlbum.findByFvID", query = "SELECT f FROM FavouriteAlbum f WHERE f.fvID = :fvID")
    , @NamedQuery(name = "FavouriteAlbum.findByAlbumName", query = "SELECT f FROM FavouriteAlbum f WHERE f.albumName = :albumName")})
public class FavouriteAlbum implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "FvID")
    private Integer fvID;
    @Size(max = 150)
    @Column(name = "AlbumName")
    private String albumName;
    @JoinColumn(name = "AccountID", referencedColumnName = "AccountID")
    @ManyToOne
    private Accounts accountID;
    @JoinColumn(name = "SongID", referencedColumnName = "SongID")
    @ManyToOne
    private Songs songID;

    public FavouriteAlbum() {
    }

    public FavouriteAlbum(Integer fvID) {
        this.fvID = fvID;
    }

    public Integer getFvID() {
        return fvID;
    }

    public void setFvID(Integer fvID) {
        this.fvID = fvID;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public Accounts getAccountID() {
        return accountID;
    }

    public void setAccountID(Accounts accountID) {
        this.accountID = accountID;
    }

    public Songs getSongID() {
        return songID;
    }

    public void setSongID(Songs songID) {
        this.songID = songID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fvID != null ? fvID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FavouriteAlbum)) {
            return false;
        }
        FavouriteAlbum other = (FavouriteAlbum) object;
        if ((this.fvID == null && other.fvID != null) || (this.fvID != null && !this.fvID.equals(other.fvID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.FavouriteAlbum[ fvID=" + fvID + " ]";
    }
    
}
