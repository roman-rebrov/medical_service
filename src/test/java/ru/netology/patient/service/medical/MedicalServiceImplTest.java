package ru.netology.patient.service.medical;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.patient.entity.BloodPressure;
import ru.netology.patient.entity.HealthInfo;
import ru.netology.patient.entity.PatientInfo;
import ru.netology.patient.repository.PatientInfoFileRepository;
import ru.netology.patient.service.alert.SendAlertService;

import java.math.BigDecimal;


public class MedicalServiceImplTest {


    @Test
    @DisplayName("проверка давления")
    public void checkBloodPressureTest(){

        BloodPressure pressure = Mockito.mock(BloodPressure.class);
        BloodPressure currentPressure = Mockito.mock(BloodPressure.class);

        PatientInfo patientInfo = Mockito.mock(PatientInfo.class);
        HealthInfo healthInfo = Mockito.mock(HealthInfo.class);
        SendAlertService alertService = Mockito.mock(SendAlertService.class);
        PatientInfoFileRepository patientInfoRepository = Mockito.mock(PatientInfoFileRepository.class);


        Mockito.when(patientInfo.getHealthInfo()).thenReturn(healthInfo);
        Mockito.when(healthInfo.getBloodPressure()).thenReturn(pressure);
        Mockito.when(patientInfoRepository.getById(Mockito.anyString())).thenReturn(patientInfo);


        MedicalServiceImpl medicalService = new MedicalServiceImpl(patientInfoRepository, alertService);
        medicalService.checkBloodPressure(Mockito.anyString(), currentPressure);


        Mockito.verify(alertService, Mockito.times(1)).send(Mockito.anyString());

    }

    @Test
    @DisplayName("проверка вывода сообщения во время проверки температуры")
    public void checkTemperatureTest(){

        PatientInfo patientInfo = Mockito.mock(PatientInfo.class);
        PatientInfoFileRepository patientInfoRepository = Mockito.mock(PatientInfoFileRepository.class);
        SendAlertService alertService = Mockito.mock(SendAlertService.class);
        HealthInfo healthInfo = Mockito.mock(HealthInfo.class);

        Mockito.when(patientInfo.getHealthInfo()).thenReturn(healthInfo);
        Mockito.when(patientInfoRepository.getById(Mockito.anyString())).thenReturn(patientInfo);
        Mockito.when(healthInfo.getNormalTemperature()).thenReturn(new BigDecimal("38"));

        MedicalServiceImpl medicalService = new MedicalServiceImpl(patientInfoRepository, alertService);
        medicalService.checkTemperature(Mockito.anyString(), new BigDecimal("1"));


        Mockito.verify(alertService, Mockito.times(1)).send(Mockito.anyString());

    }

    @Test
    @DisplayName("когда показатели в норме")
    public void normalIndicatorTest(){

        BloodPressure bloodPressure = new BloodPressure(120, 80);
        BigDecimal normalTemperature = new BigDecimal("36.6");

        PatientInfo patientInfo = Mockito.mock(PatientInfo.class);
        PatientInfoFileRepository patientInfoRepository = Mockito.mock(PatientInfoFileRepository.class);
        SendAlertService alertService = Mockito.mock(SendAlertService.class);
        HealthInfo healthInfo = Mockito.mock(HealthInfo.class);


        Mockito.when(patientInfo.getHealthInfo()).thenReturn(healthInfo);
        Mockito.when(patientInfoRepository.getById(Mockito.anyString())).thenReturn(patientInfo);
        Mockito.when(healthInfo.getNormalTemperature()).thenReturn(normalTemperature);
        Mockito.when(healthInfo.getBloodPressure()).thenReturn(bloodPressure);


        MedicalServiceImpl medicalService = new MedicalServiceImpl(patientInfoRepository, alertService);
        medicalService.checkTemperature(Mockito.anyString(), normalTemperature);
        medicalService.checkBloodPressure(Mockito.anyString(), bloodPressure);


        Mockito.verify(alertService, Mockito.times(0)).send(Mockito.anyString());
        Mockito.verify(alertService, Mockito.times(0)).send(Mockito.anyString());


    }
}
