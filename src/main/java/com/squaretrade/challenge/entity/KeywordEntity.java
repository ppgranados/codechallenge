package com.squaretrade.challenge.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "keyword")
public class KeywordEntity {
    @Id
    @GeneratedValue
    private Integer keywordId;

    @Column(name = "keyword_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    public Integer getKeywordId() {
        return keywordId;
    }

    public void setKeywordId(Integer keywordId) {
        this.keywordId = keywordId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public static class Builder {
        private Integer id;
        private String name;
        private CategoryEntity category;

        public Builder id(final Integer val) {
            this.id = val;
            return this;
        }

        public Builder name(final String val) {
            this.name = val;
            return this;
        }

        public Builder category(final CategoryEntity val) {
            this.category = val;
            return this;
        }

        public KeywordEntity build() {
            final KeywordEntity entity = new KeywordEntity();
            entity.setName(this.name);
            entity.setKeywordId(this.id);
            entity.setCategory(category);

            return entity;
        }
    }
}
