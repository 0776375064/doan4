/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author asus
 */
@Entity
@Table(name = "Albums")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Albums.findAll", query = "SELECT a FROM Albums a")
    , @NamedQuery(name = "Albums.findByAlbumID", query = "SELECT a FROM Albums a WHERE a.albumID = :albumID")
    , @NamedQuery(name = "Albums.findByAlbumName", query = "SELECT a FROM Albums a WHERE a.albumName = :albumName")
    , @NamedQuery(name = "Albums.findByRelease", query = "SELECT a FROM Albums a WHERE a.release = :release")
    , @NamedQuery(name = "Albums.findByThumbnail", query = "SELECT a FROM Albums a WHERE a.thumbnail = :thumbnail")})
public class Albums implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "AlbumID")
    private Integer albumID;
    @Size(max = 100)
    @Column(name = "AlbumName")
    private String albumName;
    @Column(name = "Release")
    @Temporal(TemporalType.DATE)
    private Date release;
    @Size(max = 150)
    @Column(name = "Thumbnail")
    private String thumbnail;
    @JoinColumn(name = "ArtistID", referencedColumnName = "ArtistID")
    @ManyToOne(optional = false)
    private Artists artistID;
    @OneToMany(mappedBy = "albumID")
    private Collection<Songs> songsCollection;

    public Albums() {
    }

    public Albums(Integer albumID) {
        this.albumID = albumID;
    }

    public Integer getAlbumID() {
        return albumID;
    }

    public void setAlbumID(Integer albumID) {
        this.albumID = albumID;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public Date getRelease() {
        return release;
    }

    public void setRelease(Date release) {
        this.release = release;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Artists getArtistID() {
        return artistID;
    }

    public void setArtistID(Artists artistID) {
        this.artistID = artistID;
    }

    @XmlTransient
    public Collection<Songs> getSongsCollection() {
        return songsCollection;
    }

    public void setSongsCollection(Collection<Songs> songsCollection) {
        this.songsCollection = songsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (albumID != null ? albumID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Albums)) {
            return false;
        }
        Albums other = (Albums) object;
        if ((this.albumID == null && other.albumID != null) || (this.albumID != null && !this.albumID.equals(other.albumID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Albums[ albumID=" + albumID + " ]";
    }
    
}
