package hu.fenyvesvolgyimate.vehicleregisterapp.dao;

import hu.fenyvesvolgyimate.vehicleregisterapp.entity.Vehicle;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileVehicleRepositoryTests {
    @Test
    public void testSaveVehicle(){
        VehicleRepository repository = new FileVehicleRepository();
        Vehicle opel = createOpelAstra();
        repository.saveVehicle(opel);
        assertEquals(getLineFromFile(0), "AA:BC-123;Opel;Astra;5;m1");

        Vehicle volkswagen = createVolswagen();
        repository.saveVehicle(volkswagen);
        assertEquals(getLineFromFile(1), "BB:BC-123;Volkswagen;Golf;5;n1");
    }

    @Test
    public void testFindVehicleByRegistrationNumber(){
        FileVehicleRepository repository = new FileVehicleRepository();
        Vehicle vehicle = createOpelAstra();
        repository.saveVehicle(createOpelAstra());
        Vehicle result = repository.findVehicleByRegistrationNumber(vehicle.getRegistrationNumber());
        assertEquals(vehicle.getModel(), result.getModel());
    }

    private static Vehicle createVolswagen() {
        Vehicle volkswagen = new Vehicle();
        volkswagen.setMake("Volkswagen");
        volkswagen.setModel("Golf");
        volkswagen.setNumberOfSeats(5);
        volkswagen.setRegistrationNumber("BB:BC-123");
        volkswagen.setVehicleType("n1");
        return volkswagen;
    }

    private Vehicle createOpelAstra() {
        Vehicle vehicle = new Vehicle();
        vehicle.setMake("Opel");
        vehicle.setModel("Astra");
        vehicle.setNumberOfSeats(5);
        vehicle.setRegistrationNumber("AA:BC-123");
        vehicle.setVehicleType("m1");
        return vehicle;
    }

    private String getLineFromFile(int lineNumber){
        String line = null;
        try {
            BufferedReader bufferreader = new BufferedReader(new FileReader("vehicles.txt"));
            int currentLineNumber = 0;
            while(lineNumber != currentLineNumber){
                line = bufferreader.readLine();
                currentLineNumber++;
            }
            line = bufferreader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return line;
    }
}
