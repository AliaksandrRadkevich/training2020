package hospitalProject.by.epam.domain;

import java.util.Date;

public class Prescription extends Entity{
    private Long patientId;
    private Long physicianId;
    private String drugs;
    private String procedure;
    private String surgery;
    private Date date;

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getPhysicianId() {
        return physicianId;
    }

    public void setPhysicianId(Long physicianId) {
        this.physicianId = physicianId;
    }

    public String getDrugs() {
        return drugs;
    }

    public void setDrugs(String drugs) {
        this.drugs = drugs;
    }

    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    public String getSurgery() {
        return surgery;
    }

    public void setSurgery(String surgery) {
        this.surgery = surgery;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Prescription [id=" + super.getId() + 
                ", patientId=" + patientId + 
                ", physicianId=" + physicianId + 
                ", drugs=" + drugs + 
                ", procedure="+ procedure + 
                ", surgery=" + surgery +
                ", date=" + date +
                "]";
    }
}
