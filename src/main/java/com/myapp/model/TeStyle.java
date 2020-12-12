package com.myapp.model;


import com.myapp.TEException.TEException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TE_STYLE")
public class TeStyle {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "te_style_generator")
    @SequenceGenerator(name = "te_style_generator", sequenceName = "te_style_seq", allocationSize = 1)
    @NotNull(message = "Id of text editor style cannot be null.")
    @Column(name = "ID", updatable = false, nullable = false, unique = true)
    private long id;

    @Column(name = "TITLE", nullable = false)
    @Size(min = 1, max = 20)
    @NotNull(message = "Title of text editor style cannot be null.")
    private String title;

    @Column(name = "BG_COLOR", nullable = false)
    @NotNull(message = "Background color of text editor style cannot be null.")
    private String bgColor;

    @Column(name = "PRIMARY_L", nullable = false)
    @NotNull(message = "Primary light color of text editor style cannot be null.")
    private String primaryL;

    @Column(name = "PRIMARY_M", nullable = false)
    @NotNull(message = "Primary medium color of text editor style cannot be null.")
    private String primaryM;

    @Column(name = "PRIMARY_D", nullable = false)
    @NotNull(message = "Primary dark color of text editor style cannot be null.")
    private String primaryD;

    @Column(name = "PRIMARY_T", nullable = false)
    @NotNull(message = "Primary contrast text color of text editor style cannot be null.")
    private String primaryT;

    @Column(name = "SECONDARY_L", nullable = false)
    @NotNull(message = "Secondary light color of text editor style cannot be null.")
    private String seconadryL;

    @Column(name = "SECONDARY_M", nullable = false)
    @NotNull(message = "Secondary medium color of text editor style cannot be null.")
    private String seconadryM;

    @Column(name = "SECONDARY_D", nullable = false)
    @NotNull(message = "Secondary dark color of text editor style cannot be null.")
    private String seconadryD;

    @Column(name = "SECONDARY_T", nullable = false)
    @NotNull(message = "Secondary contrast text color of text editor style cannot be null.")
    private String seconadryT;

    public TeStyle() {}

    public void validate() throws TEException {
        if (this.getTitle() == null || this.getTitle().equals("")) {
            throw new TEException("Title of the style cannot be null or empty.");
        }

        if (this.getBgColor() == null || this.getBgColor().equals("")) {
            throw new TEException("Background color of the style cannot be null or empty.");
        }

        if (this.getPrimaryL() == null || this.getPrimaryL().equals("")) {
            throw new TEException("Primary light color of the style cannot be null or empty.");
        }

        if (this.getPrimaryM() == null || this.getPrimaryM().equals("")) {
            throw new TEException("Primary medium color of the style cannot be null or empty.");
        }

        if (this.getPrimaryD() == null || this.getPrimaryD().equals("")) {
            throw new TEException("Primary dark color of the style cannot be null or empty.");
        }

        if (this.getPrimaryT() == null || this.getPrimaryT().equals("")) {
            throw new TEException("Primary contrast text color of the style cannot be null or empty.");
        }

        if (this.getSeconadryL() == null || this.getSeconadryL().equals("")) {
            throw new TEException("Secondary light color of the style cannot be null or empty.");
        }

        if (this.getSeconadryM() == null || this.getSeconadryM().equals("")) {
            throw new TEException("Secondary medium color of the style cannot be null or empty.");
        }

        if (this.getSeconadryD() == null || this.getSeconadryD().equals("")) {
            throw new TEException("Secondary dark color of the style cannot be null or empty.");
        }

        if (this.getSeconadryT() == null || this.getSeconadryT().equals("")) {
            throw new TEException("Secondary contrast text color of the style cannot be null or empty.");
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public String getPrimaryL() {
        return primaryL;
    }

    public void setPrimaryL(String primaryL) {
        this.primaryL = primaryL;
    }

    public String getPrimaryM() {
        return primaryM;
    }

    public void setPrimaryM(String primaryM) {
        this.primaryM = primaryM;
    }

    public String getPrimaryD() {
        return primaryD;
    }

    public void setPrimaryD(String primaryD) {
        this.primaryD = primaryD;
    }

    public String getPrimaryT() {
        return primaryT;
    }

    public void setPrimaryT(String primaryT) {
        this.primaryT = primaryT;
    }

    public String getSeconadryL() {
        return seconadryL;
    }

    public void setSeconadryL(String seconadryL) {
        this.seconadryL = seconadryL;
    }

    public String getSeconadryM() {
        return seconadryM;
    }

    public void setSeconadryM(String seconadryM) {
        this.seconadryM = seconadryM;
    }

    public String getSeconadryD() {
        return seconadryD;
    }

    public void setSeconadryD(String seconadryD) {
        this.seconadryD = seconadryD;
    }

    public String getSeconadryT() {
        return seconadryT;
    }

    public void setSeconadryT(String seconadryT) {
        this.seconadryT = seconadryT;
    }
}
