/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vertu.vrts.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Version;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SidorovEI
 */
@Entity
@Table(name = "prpt_type", catalog = "tbhw_db", schema = "vertu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrptType.findAll", query = "SELECT p FROM PrptType p")
    , @NamedQuery(name = "PrptType.findByPrptpId", query = "SELECT p FROM PrptType p WHERE p.prptpId = :prptpId")
    , @NamedQuery(name = "PrptType.findByVersnum", query = "SELECT p FROM PrptType p WHERE p.versnum = :versnum")
    , @NamedQuery(name = "PrptType.findByPrpName", query = "SELECT p FROM PrptType p WHERE p.prpName = :prpName")
    , @NamedQuery(name = "PrptType.findByPrpKf", query = "SELECT p FROM PrptType p WHERE p.prpKf = :prpKf")})
public class PrptType implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableGenerator(name = "prptTypeGen", table = "GEN_ID", pkColumnName = "ID_NAME",
            valueColumnName = "ID_VAL", pkColumnValue = "prptp_pk", initialValue = 1, allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "prptTypeGen")
    @Basic(optional = false)
    @NotNull
    @Column(name = "prptp_id", nullable = false)
    private Long prptpId;
    @Version
    private Integer versnum;
    @Size(max = 100)
    @Column(name = "prp_name", length = 100)
    private String prpName;
    @Min(value=1)
    @Column(name = "prp_kf", precision = 2, scale = 1)
    private BigDecimal prpKf;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prptpFk")
    private Collection<InsContr> insContrCollection;

    public PrptType() {
    }

    public PrptType(Long prptpId) {
        this.prptpId = prptpId;
    }

    public Long getPrptpId() {
        return prptpId;
    }

    public void setPrptpId(Long prptpId) {
        this.prptpId = prptpId;
    }

    public Integer getVersnum() {
        return versnum;
    }

    public void setVersnum(Integer versnum) {
        this.versnum = versnum;
    }

    public String getPrpName() {
        return prpName;
    }

    public void setPrpName(String prpName) {
        this.prpName = prpName;
    }

    public BigDecimal getPrpKf() {
        return prpKf;
    }

    public void setPrpKf(BigDecimal prpKf) {
        this.prpKf = prpKf;
    }

    @XmlTransient
    public Collection<InsContr> getInsContrCollection() {
        return insContrCollection;
    }

    public void setInsContrCollection(Collection<InsContr> insContrCollection) {
        this.insContrCollection = insContrCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prptpId != null ? prptpId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrptType)) {
            return false;
        }
        PrptType other = (PrptType) object;
        if ((this.prptpId == null && other.prptpId != null) || (this.prptpId != null && !this.prptpId.equals(other.prptpId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vertu.vrts.data.PrptType[ prptpId=" + prptpId + " ]";
    }
    
}
