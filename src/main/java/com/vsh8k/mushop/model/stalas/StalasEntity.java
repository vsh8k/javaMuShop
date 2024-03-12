package com.vsh8k.mushop.model.stalas;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "stalas", schema = "testas", catalog = "")
public class StalasEntity {
    @Basic
    @Column(name = "ass")
    private Integer ass;
    @Basic
    @Column(name = "bass")
    private Integer bass;
    @Basic
    @Column(name = "mass")
    private Integer mass;
    @Basic
    @Column(name = "grass")
    private Integer grass;

    public Integer getAss() {
        return ass;
    }

    public void setAss(Integer ass) {
        this.ass = ass;
    }

    public Integer getBass() {
        return bass;
    }

    public void setBass(Integer bass) {
        this.bass = bass;
    }

    public Integer getMass() {
        return mass;
    }

    public void setMass(Integer mass) {
        this.mass = mass;
    }

    public Integer getGrass() {
        return grass;
    }

    public void setGrass(Integer grass) {
        this.grass = grass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StalasEntity that = (StalasEntity) o;

        if (ass != null ? !ass.equals(that.ass) : that.ass != null) return false;
        if (bass != null ? !bass.equals(that.bass) : that.bass != null) return false;
        if (mass != null ? !mass.equals(that.mass) : that.mass != null) return false;
        if (grass != null ? !grass.equals(that.grass) : that.grass != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ass != null ? ass.hashCode() : 0;
        result = 31 * result + (bass != null ? bass.hashCode() : 0);
        result = 31 * result + (mass != null ? mass.hashCode() : 0);
        result = 31 * result + (grass != null ? grass.hashCode() : 0);
        return result;
    }
}
