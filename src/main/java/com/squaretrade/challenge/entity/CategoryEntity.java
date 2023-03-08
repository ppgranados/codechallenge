package com.squaretrade.challenge.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "category")
public class CategoryEntity {
    @Id
    @GeneratedValue
    private Integer categoryId;

    @Column(name = "category_name")
    private String name;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "category", fetch = FetchType.EAGER)
    private List<KeywordEntity> keywords;

    @OneToMany(mappedBy = "parent")
    private List<CategoryEntity> subCategories;

    @ManyToOne
    private CategoryEntity parent;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer id) {
        this.categoryId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<KeywordEntity> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<KeywordEntity> keywords) {
        this.keywords = keywords;
    }

    public List<CategoryEntity> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<CategoryEntity> subCategories) {
        this.subCategories = subCategories;
    }

    public CategoryEntity getParent() {
        return parent;
    }

    public void setParent(CategoryEntity parent) {
        this.parent = parent;
    }

    public static class Builder {
        private Integer id;
        private String name;
        private List<KeywordEntity> keywords;

        private List<CategoryEntity> subCategories;

        private CategoryEntity parent;

        public Builder id(final Integer val) {
            this.id = val;
            return this;
        }

        public Builder name(final String val) {
            this.name = val;
            return this;
        }

        public Builder keywords(final List<KeywordEntity> val) {
            this.keywords = val;
            return this;
        }

        public Builder subCategories(final List<CategoryEntity> val) {
            this.subCategories = val;
            return this;
        }

        public Builder parent(final CategoryEntity val) {
            this.parent = val;
            return this;
        }

        public CategoryEntity build() {
            final CategoryEntity category = new CategoryEntity();
            category.setCategoryId(this.id);
            category.setKeywords(this.keywords);
            category.setName(this.name);
            category.setSubCategories(this.subCategories);
            category.setParent(this.parent);
            return category;
        }
    }
}
