package com.fixed.assets.app.fixedassets.Models.Depreciation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fixed.assets.app.fixedassets.Models.Asset.Asset;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Depreciation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 6, unique = true)
    private String depreciationCode;
    @Column(nullable = false)
    private Date startDate;
    @Column(nullable = false)
    private Date endDate;
    private String depreciationType;
    private Long cost;
    private Long depreciation;
    private Long newValue;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "asset_id")
    @JsonIgnore
    private Asset asset;


    public Depreciation() {
    }

    public String getDepreciationCode() {
        return depreciationCode;
    }

    public void setDepreciationCode(String depreciationCode) {
        this.depreciationCode = depreciationCode;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDepreciationType() {
        return depreciationType;
    }

    public void setDepreciationType(String depreciationType) {
        this.depreciationType = depreciationType;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public Long getDepreciation() {
        return depreciation;
    }

    public void setDepreciation(Long depreciation) {
        this.depreciation = depreciation;
    }

    public Long getNewValue() {
        return newValue;
    }

    public void setNewValue(Long newValue) {
        this.newValue = newValue;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }
}
