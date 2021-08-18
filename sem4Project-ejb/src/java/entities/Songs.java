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
import javax.persistence.Lob;
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
@Table(name = "Songs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Songs.findAll", query = "SELECT s FROM Songs s")
    , @NamedQuery(name = "Songs.findBySongID", query = "SELECT s FROM Songs s WHERE s.songID = :songID")
    , @NamedQuery(name = "Songs.findBySongName", query = "SELECT s FROM Songs s WHERE s.songName = :songName")
    , @NamedQuery(name = "Songs.findByThumbnail", query = "SELECT s FROM Songs s WHERE s.thumbnail = :thumbnail")
    , @NamedQuery(name = "Songs.findByPath", query = "SELECT s FROM Songs s WHERE s.path = :path")
    , @NamedQuery(name = "Songs.findByPrice", query = "SELECT s FROM Songs s WHERE s.price = :price")
    , @NamedQuery(name = "Songs.findByRelease", query = "SELECT s FROM Songs s WHERE s.release = :release")
    , @NamedQuery(name = "Songs.findByTypeAlbum", query = "SELECT s FROM Songs s WHERE s.typeAlbum = :typeAlbum")
    , @NamedQuery(name = "Songs.findByViews", query = "SELECT s FROM Songs s WHERE s.views = :views")})
public class Songs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SongID")
    private Integer songID;
    @Size(max = 70)
    @Column(name = "SongName")
    private String songName;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "Description")
    private String description;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "Lyrics")
    private String lyrics;
    @Size(max = 150)
    @Column(name = "Thumbnail")
    private String thumbnail;
    @Size(max = 100)
    @Column(name = "Path")
    private String path;
    @Column(name = "Price")
    private Integer price;
    @Column(name = "Release")
    @Temporal(TemporalType.DATE)
    private Date release;
    @Column(name = "TypeAlbum")
    private Integer typeAlbum;
    @Column(name = "Views")
    private Integer views;
    @OneToMany(mappedBy = "songID")
    private Collection<SongTracking> songTrackingCollection;
    @OneToMany(mappedBy = "songID")
    private Collection<SongLikes> songLikesCollection;
    @OneToMany(mappedBy = "songID")
    private Collection<OrderDetails> orderDetailsCollection;
    @OneToMany(mappedBy = "songID")
    private Collection<FavouriteAlbum> favouriteAlbumCollection;
    @OneToMany(mappedBy = "songID")
    private Collection<ArtistInSong> artistInSongCollection;
    @JoinColumn(name = "AlbumID", referencedColumnName = "AlbumID")
    @ManyToOne
    private Albums albumID;
    @JoinColumn(name = "ArtistID", referencedColumnName = "ArtistID")
    @ManyToOne
    private Artists artistID;
    @JoinColumn(name = "LangID", referencedColumnName = "LangID")
    @ManyToOne
    private SongLanguage langID;
    @JoinColumn(name = "SupplierID", referencedColumnName = "SupplierID")
    @ManyToOne
    private Suppliers supplierID;
    @JoinColumn(name = "TypeID", referencedColumnName = "TypeID")
    @ManyToOne
    private TypeOfSong typeID;
    @OneToMany(mappedBy = "songID")
    private Collection<SongReview> songReviewCollection;

    public Songs() {
    }

    public Songs(Integer songID) {
        this.songID = songID;
    }

    public Integer getSongID() {
        return songID;
    }

    public void setSongID(Integer songID) {
        this.songID = songID;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getRelease() {
        return release;
    }

    public void setRelease(Date release) {
        this.release = release;
    }

    public Integer getTypeAlbum() {
        return typeAlbum;
    }

    public void setTypeAlbum(Integer typeAlbum) {
        this.typeAlbum = typeAlbum;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    @XmlTransient
    public Collection<SongTracking> getSongTrackingCollection() {
        return songTrackingCollection;
    }

    public void setSongTrackingCollection(Collection<SongTracking> songTrackingCollection) {
        this.songTrackingCollection = songTrackingCollection;
    }

    @XmlTransient
    public Collection<SongLikes> getSongLikesCollection() {
        return songLikesCollection;
    }

    public void setSongLikesCollection(Collection<SongLikes> songLikesCollection) {
        this.songLikesCollection = songLikesCollection;
    }

    @XmlTransient
    public Collection<OrderDetails> getOrderDetailsCollection() {
        return orderDetailsCollection;
    }

    public void setOrderDetailsCollection(Collection<OrderDetails> orderDetailsCollection) {
        this.orderDetailsCollection = orderDetailsCollection;
    }

    @XmlTransient
    public Collection<FavouriteAlbum> getFavouriteAlbumCollection() {
        return favouriteAlbumCollection;
    }

    public void setFavouriteAlbumCollection(Collection<FavouriteAlbum> favouriteAlbumCollection) {
        this.favouriteAlbumCollection = favouriteAlbumCollection;
    }

    @XmlTransient
    public Collection<ArtistInSong> getArtistInSongCollection() {
        return artistInSongCollection;
    }

    public void setArtistInSongCollection(Collection<ArtistInSong> artistInSongCollection) {
        this.artistInSongCollection = artistInSongCollection;
    }

    public Albums getAlbumID() {
        return albumID;
    }

    public void setAlbumID(Albums albumID) {
        this.albumID = albumID;
    }

    public Artists getArtistID() {
        return artistID;
    }

    public void setArtistID(Artists artistID) {
        this.artistID = artistID;
    }

    public SongLanguage getLangID() {
        return langID;
    }

    public void setLangID(SongLanguage langID) {
        this.langID = langID;
    }

    public Suppliers getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(Suppliers supplierID) {
        this.supplierID = supplierID;
    }

    public TypeOfSong getTypeID() {
        return typeID;
    }

    public void setTypeID(TypeOfSong typeID) {
        this.typeID = typeID;
    }

    @XmlTransient
    public Collection<SongReview> getSongReviewCollection() {
        return songReviewCollection;
    }

    public void setSongReviewCollection(Collection<SongReview> songReviewCollection) {
        this.songReviewCollection = songReviewCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (songID != null ? songID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Songs)) {
            return false;
        }
        Songs other = (Songs) object;
        if ((this.songID == null && other.songID != null) || (this.songID != null && !this.songID.equals(other.songID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Songs[ songID=" + songID + " ]";
    }
    
}
