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
@Table(name = "Suppliers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Suppliers.findAll", query = "SELECT s FROM Suppliers s")
    , @NamedQuery(name = "Suppliers.findBySupplierID", query = "SELECT s FROM Suppliers s WHERE s.supplierID = :supplierID")
    , @NamedQuery(name = "Suppliers.findByCompanyName", query = "SELECT s FROM Suppliers s WHERE s.companyName = :companyName")
    , @NamedQuery(name = "Suppliers.findByContactName", query = "SELECT s FROM Suppliers s WHERE s.contactName = :contactName")
    , @NamedQuery(name = "Suppliers.findByAddress", query = "SELECT s FROM Suppliers s WHERE s.address = :address")
    , @NamedQuery(name = "Suppliers.findByPhone", query = "SELECT s FROM Suppliers s WHERE s.phone = :phone")
    , @NamedQuery(name = "Suppliers.findByFax", query = "SELECT s FROM Suppliers s WHERE s.fax = :fax")
    , @NamedQuery(name = "Suppliers.findByHompage", query = "SELECT s FROM Suppliers s WHERE s.hompage = :hompage")})
public class Suppliers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SupplierID")
    private Integer supplierID;
    @Size(max = 70)
    @Column(name = "CompanyName")
    private String companyName;
    @Size(max = 100)
    @Column(name = "ContactName")
    private String contactName;
    @Size(max = 150)
    @Column(name = "Address")
    private String address;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 30)
    @Column(name = "Phone")
    private String phone;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 30)
    @Column(name = "Fax")
    private String fax;
    @Size(max = 50)
    @Column(name = "Hompage")
    private String hompage;
    @OneToMany(mappedBy = "supplierID")
    private Collection<Songs> songsCollection;

    public Suppliers() {
    }

    public Suppliers(Integer supplierID) {
        this.supplierID = supplierID;
    }

    public Integer getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(Integer supplierID) {
        this.supplierID = supplierID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getHompage() {
        return hompage;
    }

    public void setHompage(String hompage) {
        this.hompage = hompage;
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
        hash += (supplierID != null ? supplierID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Suppliers)) {
            return false;
        }
        Suppliers other = (Suppliers) object;
        if ((this.supplierID == null && other.supplierID != null) || (this.supplierID != null && !this.supplierID.equals(other.supplierID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Suppliers[ supplierID=" + supplierID + " ]";
    }
    
}
