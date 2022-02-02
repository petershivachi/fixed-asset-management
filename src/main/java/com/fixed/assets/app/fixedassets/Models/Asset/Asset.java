package com.fixed.assets.app.fixedassets.Models.Asset;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fixed.assets.app.fixedassets.Models.Category.Category;
import com.fixed.assets.app.fixedassets.Models.Depreciation.Depreciation;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   // @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonIgnore
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;
    @Column(length = 50, nullable = false)
    private String name;
    @Column(updatable = false,unique = true)
    private String lrNo;
    @Column(updatable = false)
    private Long size;
    @Column(nullable = false)
    private Long cost;
    @Column(unique = true)
    private Long serialNumber;
    @Column(updatable = false, nullable = false)
    private Date dateAcquired;
    @Column(length = 50, nullable = false)
    private String depreciationType;
    @Column(nullable = false)
    private int depreciationRate;
    @Column(length = 70, nullable = false)
    private String custodian;
    @Column(length = 50, nullable = false)
    private  String location;
    @Column(length = 100, nullable = false)
    private String remarks;
    @Column(length = 50)
    private String departmentUnit;
    @Column(length = 70)
    private String localAuthority;
    @Column(length = 100, unique = true)
    private String registrationNumber;
    @Column(length = 100, unique = true)
    private String engineNumber;
    @Column(length = 100, unique = true)
    private String chassisNumber;
    @Column(length = 100)
    private String make;
    @Column(length = 25)
    private String model;
    @Column(length = 30)
    private String type;
    private char deleteFlag = 'N';
    @Column(nullable = false, updatable = false)
    private Date rcre = new Date();
    @Column(nullable = false)
    private String categoryCode;
    @Column(nullable = false, unique = true, length = 6, updatable = false)
    private String assetCode;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "asset")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Depreciation depreciation;

    public Asset() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLrNo() {
        return lrNo;
    }

    public void setLrNo(String lrNo) {
        this.lrNo = lrNo;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public Long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Date getDateAcquired() {
        return dateAcquired;
    }

    public void setDateAcquired(Date dateAcquired) {
        this.dateAcquired = dateAcquired;
    }

    public String getDepreciationType() {
        return depreciationType;
    }

    public void setDepreciationType(String depreciationType) {
        this.depreciationType = depreciationType;
    }

    public int getDepreciationRate() {
        return depreciationRate;
    }

    public void setDepreciationRate(int depreciationRate) {
        this.depreciationRate = depreciationRate;
    }

    public String getCustodian() {
        return custodian;
    }

    public void setCustodian(String custodian) {
        this.custodian = custodian;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDepartmentUnit() {
        return departmentUnit;
    }

    public void setDepartmentUnit(String departmentUnit) {
        this.departmentUnit = departmentUnit;
    }

    public String getLocalAuthority() {
        return localAuthority;
    }

    public void setLocalAuthority(String localAuthority) {
        this.localAuthority = localAuthority;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public char getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(char deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Date getRcre() {
        return rcre;
    }

    public void setRcre(Date rcre) {
        this.rcre = rcre;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public Depreciation getDepreciation() {
        return depreciation;
    }

    public void setDepreciation(Depreciation depreciation) {
        this.depreciation = depreciation;
    }
}
