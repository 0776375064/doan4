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
@Table(name = "SongLanguage")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SongLanguage.findAll", query = "SELECT s FROM SongLanguage s")
    , @NamedQuery(name = "SongLanguage.findByLangID", query = "SELECT s FROM SongLanguage s WHERE s.langID = :langID")
    , @NamedQuery(name = "SongLanguage.findByLangName", query = "SELECT s FROM SongLanguage s WHERE s.langName = :langName")
    , @NamedQuery(name = "SongLanguage.findByDescription", query = "SELECT s FROM SongLanguage s WHERE s.description = :description")})
public class SongLanguage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "LangID")
    private Integer langID;
    @Size(max = 70)
    @Column(name = "LangName")
    private String langName;
    @Size(max = 150)
    @Column(name = "Description")
    private String description;
    @OneToMany(mappedBy = "langID")
    private Collection<Songs> songsCollection;

    public SongLanguage() {
    }

    public SongLanguage(Integer langID) {
        this.langID = langID;
    }

    public Integer getLangID() {
        return langID;
    }

    public void setLangID(Integer langID) {
        this.langID = langID;
    }

    public String getLangName() {
        return langName;
    }

    public void setLangName(String langName) {
        this.langName = langName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        hash += (langID != null ? langID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SongLanguage)) {
            return false;
        }
        SongLanguage other = (SongLanguage) object;
        if ((this.langID == null && other.langID != null) || (this.langID != null && !this.langID.equals(other.langID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.SongLanguage[ langID=" + langID + " ]";
    }
    
}
