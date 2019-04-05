/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vertu.vrts.data;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SidorovEI
 */
@Entity
@Table(name = "gen_id", catalog = "tbhw_db", schema = "vertu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GenId.findAll", query = "SELECT g FROM GenId g")
    , @NamedQuery(name = "GenId.findByIdName", query = "SELECT g FROM GenId g WHERE g.idName = :idName")
    , @NamedQuery(name = "GenId.findByIdVal", query = "SELECT g FROM GenId g WHERE g.idVal = :idVal")})
public class GenId implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "id_name", nullable = false, length = 20)
    private String idName;
    @Column(name = "id_val")
    private BigInteger idVal;

    public GenId() {
    }

    public GenId(String idName) {
        this.idName = idName;
    }

    public String getIdName() {
        return idName;
    }

    public void setIdName(String idName) {
        this.idName = idName;
    }

    public BigInteger getIdVal() {
        return idVal;
    }

    public void setIdVal(BigInteger idVal) {
        this.idVal = idVal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idName != null ? idName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GenId)) {
            return false;
        }
        GenId other = (GenId) object;
        if ((this.idName == null && other.idName != null) || (this.idName != null && !this.idName.equals(other.idName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vertu.vrts.data.GenId[ idName=" + idName + " ]";
    }
    
}
