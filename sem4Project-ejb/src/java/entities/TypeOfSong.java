/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
@Table(name = "TypeOfSong")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypeOfSong.findAll", query = "SELECT t FROM TypeOfSong t")
    , @NamedQuery(name = "TypeOfSong.findByTypeID", query = "SELECT t FROM TypeOfSong t WHERE t.typeID = :typeID")
    , @NamedQuery(name = "TypeOfSong.findByTypeName", query = "SELECT t FROM TypeOfSong t WHERE t.typeName = :typeName")
    , @NamedQuery(name = "TypeOfSong.findByThumbnail", query = "SELECT t FROM TypeOfSong t WHERE t.thumbnail = :thumbnail")})
public class TypeOfSong implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TypeID")
    private Integer typeID;
    @Size(max = 100)
    @Column(name = "TypeName")
    private String typeName;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "Description")
    private String description;
    @Size(max = 150)
    @Column(name = "Thumbnail")
    private String thumbnail;
    @OneToMany(mappedBy = "typeID")
    private Collection<Songs> songsCollection;

    public TypeOfSong() {
    }

    public TypeOfSong(Integer typeID) {
        this.typeID = typeID;
    }

    public Integer getTypeID() {
        return typeID;
    }

    public void setTypeID(Integer typeID) {
        this.typeID = typeID;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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
        hash += (typeID != null ? typeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypeOfSong)) {
            return false;
        }
        TypeOfSong other = (TypeOfSong) object;
        if ((this.typeID == null && other.typeID != null) || (this.typeID != null && !this.typeID.equals(other.typeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TypeOfSong[ typeID=" + typeID + " ]";
    }
    
}
