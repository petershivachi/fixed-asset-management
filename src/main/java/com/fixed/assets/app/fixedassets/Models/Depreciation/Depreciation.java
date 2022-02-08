package com.fixed.assets.app.fixedassets.Models.Depreciation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fixed.assets.app.fixedassets.Models.Asset.Asset;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Depreciation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 6, unique = true, updatable = true)
    private String depreciationCode;
    @Column(nullable = false)
    private Date startDate;
    @Column(nullable = false)
    private Date endDate;
    private String depreciationType;
    private Double cost;
    private String assetName;
    private Double depreciation;
    private Double newValue;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "asset_id", nullable = false)
    @JsonIgnore
    private Asset asset;
//    @Column(nullable = false, length = 1)
//    private Character deleteFlag = 'N';
    @Column(nullable = false)
    private Date rcre = new Date();
    @Column(nullable = false, length = 6)
    private String assetCode;
    @JsonProperty(value = "value")
    private Double value;


    public Depreciation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getDepreciation() {
        return depreciation;
    }

    public void setDepreciation(Double depreciation) {
        this.depreciation = depreciation;
    }

    public Double getNewValue() {
        return newValue;
    }

    public void setNewValue(Double newValue) {
        this.newValue = newValue;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

//    public Character getDeleteFlag() {
//        return deleteFlag;
//    }
//
//    public void setDeleteFlag(Character deleteFlag) {
//        this.deleteFlag = deleteFlag;
//    }

    public Date getRcre() {
        return rcre;
    }

    public void setRcre(Date rcre) {
        this.rcre = rcre;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
