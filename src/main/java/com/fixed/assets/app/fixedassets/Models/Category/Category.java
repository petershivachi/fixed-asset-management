package com.fixed.assets.app.fixedassets.Models.Category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fixed.assets.app.fixedassets.Models.Asset.Asset;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @OneToMany(mappedBy = "category")
    private List<Asset> assets = new ArrayList<Asset>();
    @Column(nullable = false, length = 25)
    private String categoryName;
    @Column(length = 150)
    private String categoryDescription;
    @Column(nullable = false, unique = true, length = 6, updatable = false)
    private  String categoryCode;
    private char deleteFlag = 'N';
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false, nullable = false)
    @JsonIgnore
    private Date rcre = new Date();

    public Category() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        assets.forEach(asset -> asset.setCategory(this));
        this.assets = assets;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
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


}
