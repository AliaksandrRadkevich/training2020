package hospitalProject.by.epam.domain;

import java.util.Date;

public class SicknessRecord extends Entity{
    private String medicalAssessment;
    private Long patientId;
    private Long physicianId;
    private Date hospitalizationDate;
    private Date dischargeDate;
    
    public String getMedicalAssessment() {
        return medicalAssessment;
    }
    public void setMedicalAssessment(String medicalAssessment) {
        this.medicalAssessment = medicalAssessment;
    }
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
    public Date getHospitalizationDate() {
        return hospitalizationDate;
    }
    public void setHospitalizationDate(Date hospitalizationDate) {
        this.hospitalizationDate = hospitalizationDate;
    }
    public Date getDischargeDate() {
        return dischargeDate;
    }
    public void setDischargeDate(Date dischargeDate) {
        this.dischargeDate = dischargeDate;
    }
    @Override
    public String toString() {
        return "SicknessRecord [medicalAssessment=" + medicalAssessment + ", patientId=" + patientId + ", physicianId="
                + physicianId + ", hospitalizationDate=" + hospitalizationDate + ", dischargeDate=" + dischargeDate
                + ", getId()=" + getId() + "]";
    }

}
