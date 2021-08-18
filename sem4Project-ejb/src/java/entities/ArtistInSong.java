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
@Table(name = "ArtistInSong")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ArtistInSong.findAll", query = "SELECT a FROM ArtistInSong a")
    , @NamedQuery(name = "ArtistInSong.findByWorkID", query = "SELECT a FROM ArtistInSong a WHERE a.workID = :workID")
    , @NamedQuery(name = "ArtistInSong.findByRole", query = "SELECT a FROM ArtistInSong a WHERE a.role = :role")})
public class ArtistInSong implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "WorkID")
    private Integer workID;
    @Size(max = 50)
    @Column(name = "Role")
    private String role;
    @JoinColumn(name = "ArtistID", referencedColumnName = "ArtistID")
    @ManyToOne
    private Artists artistID;
    @JoinColumn(name = "SongID", referencedColumnName = "SongID")
    @ManyToOne
    private Songs songID;

    public ArtistInSong() {
    }

    public ArtistInSong(Integer workID) {
        this.workID = workID;
    }

    public Integer getWorkID() {
        return workID;
    }

    public void setWorkID(Integer workID) {
        this.workID = workID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Artists getArtistID() {
        return artistID;
    }

    public void setArtistID(Artists artistID) {
        this.artistID = artistID;
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
        hash += (workID != null ? workID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArtistInSong)) {
            return false;
        }
        ArtistInSong other = (ArtistInSong) object;
        if ((this.workID == null && other.workID != null) || (this.workID != null && !this.workID.equals(other.workID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ArtistInSong[ workID=" + workID + " ]";
    }
    
}
