/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author asus
 */
@Entity
@Table(name = "SongTracking")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SongTracking.findAll", query = "SELECT s FROM SongTracking s")
    , @NamedQuery(name = "SongTracking.findBySongChartID", query = "SELECT s FROM SongTracking s WHERE s.songChartID = :songChartID")
    , @NamedQuery(name = "SongTracking.findByDateTracking", query = "SELECT s FROM SongTracking s WHERE s.dateTracking = :dateTracking")
    , @NamedQuery(name = "SongTracking.findByListens", query = "SELECT s FROM SongTracking s WHERE s.listens = :listens")})
public class SongTracking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SongChartID")
    private Integer songChartID;
    @Column(name = "DateTracking")
    private String dateTracking;
    @Column(name = "Listens")
    private Integer listens;
    @JoinColumn(name = "SongID", referencedColumnName = "SongID")
    @ManyToOne
    private Songs songID;

    public SongTracking() {
    }

    public SongTracking(Integer songChartID) {
        this.songChartID = songChartID;
    }

    public Integer getSongChartID() {
        return songChartID;
    }

    public void setSongChartID(Integer songChartID) {
        this.songChartID = songChartID;
    }

    public String getDateTracking() {
        return dateTracking;
    }

    public void setDateTracking(String dateTracking) {
        this.dateTracking = dateTracking;
    }

    public Integer getListens() {
        return listens;
    }

    public void setListens(Integer listens) {
        this.listens = listens;
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
        hash += (songChartID != null ? songChartID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SongTracking)) {
            return false;
        }
        SongTracking other = (SongTracking) object;
        if ((this.songChartID == null && other.songChartID != null) || (this.songChartID != null && !this.songChartID.equals(other.songChartID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.SongTracking[ songChartID=" + songChartID + " ]";
    }
    
}
