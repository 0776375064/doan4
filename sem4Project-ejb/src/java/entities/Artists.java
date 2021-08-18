/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author asus
 */
@Entity
@Table(name = "Artists")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Artists.findAll", query = "SELECT a FROM Artists a")
    , @NamedQuery(name = "Artists.findByArtistID", query = "SELECT a FROM Artists a WHERE a.artistID = :artistID")
    , @NamedQuery(name = "Artists.findByNickname", query = "SELECT a FROM Artists a WHERE a.nickname = :nickname")
    , @NamedQuery(name = "Artists.findByFullname", query = "SELECT a FROM Artists a WHERE a.fullname = :fullname")
    , @NamedQuery(name = "Artists.findByBirthday", query = "SELECT a FROM Artists a WHERE a.birthday = :birthday")
    , @NamedQuery(name = "Artists.findByNationality", query = "SELECT a FROM Artists a WHERE a.nationality = :nationality")
    , @NamedQuery(name = "Artists.findByAvatar", query = "SELECT a FROM Artists a WHERE a.avatar = :avatar")})
public class Artists implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ArtistID")
    private Integer artistID;
    @Size(max = 70)
    @Column(name = "Nickname")
    private String nickname;
    @Size(max = 100)
    @Column(name = "Fullname")
    private String fullname;
    @Size(max = 90)
    @Column(name = "Birthday")
    private String birthday;
    @Size(max = 150)
    @Column(name = "Nationality")
    private String nationality;
    @Size(max = 150)
    @Column(name = "Avatar")
    private String avatar;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "Description")
    private String description;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "Achievement")
    private String achievement;
    @OneToMany(mappedBy = "artistID")
    private Collection<ArtistInSong> artistInSongCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artistID")
    private Collection<Albums> albumsCollection;
    @OneToMany(mappedBy = "artistID")
    private Collection<Songs> songsCollection;
    @OneToMany(mappedBy = "artistID")
    private Collection<ArtistFollow> artistFollowCollection;

    public Artists() {
    }

    public Artists(Integer artistID) {
        this.artistID = artistID;
    }

    public Integer getArtistID() {
        return artistID;
    }

    public void setArtistID(Integer artistID) {
        this.artistID = artistID;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement;
    }

    @XmlTransient
    public Collection<ArtistInSong> getArtistInSongCollection() {
        return artistInSongCollection;
    }

    public void setArtistInSongCollection(Collection<ArtistInSong> artistInSongCollection) {
        this.artistInSongCollection = artistInSongCollection;
    }

    @XmlTransient
    public Collection<Albums> getAlbumsCollection() {
        return albumsCollection;
    }

    public void setAlbumsCollection(Collection<Albums> albumsCollection) {
        this.albumsCollection = albumsCollection;
    }

    @XmlTransient
    public Collection<Songs> getSongsCollection() {
        return songsCollection;
    }

    public void setSongsCollection(Collection<Songs> songsCollection) {
        this.songsCollection = songsCollection;
    }

    @XmlTransient
    public Collection<ArtistFollow> getArtistFollowCollection() {
        return artistFollowCollection;
    }

    public void setArtistFollowCollection(Collection<ArtistFollow> artistFollowCollection) {
        this.artistFollowCollection = artistFollowCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (artistID != null ? artistID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Artists)) {
            return false;
        }
        Artists other = (Artists) object;
        if ((this.artistID == null && other.artistID != null) || (this.artistID != null && !this.artistID.equals(other.artistID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Artists[ artistID=" + artistID + " ]";
    }
    
}
