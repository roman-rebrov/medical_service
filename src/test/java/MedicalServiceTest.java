import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.patient.repository.PatientInfoFileRepository;

import java.io.File;

public class MedicalServiceTest {
    PatientInfoFileRepository patient = Mockito.mock(PatientInfoFileRepository.class);

    @Test
    @DisplayName("проверка давления.")
    public void checkBloodPressureTest(){
        File repoFile = new File("patients.txt");


    }

    @Test
    @DisplayName("проверка вывода сообщения во время проверки температуры.")
    public void checkTemperatureTest(){

    }
}
