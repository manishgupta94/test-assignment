package com.test.assignment;

import com.test.assignment.auth.config.WebSecurityConfig;
import com.test.assignment.user.domain.model.User;
import com.test.assignment.user.domain.model.WorkSector;
import com.test.assignment.user.domain.repository.UserRepository;
import com.test.assignment.user.domain.repository.WorkSectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AssignmentApplication implements CommandLineRunner {

    private UserRepository userRepository;
    private WorkSectorRepository workSectorRepository;
    private WebSecurityConfig webSecurityConfig;

    @Autowired
    public AssignmentApplication(UserRepository userRepository,
                                 WorkSectorRepository workSectorRepository,
                                 WebSecurityConfig webSecurityConfig) {
        this.userRepository = userRepository;
        this.workSectorRepository = workSectorRepository;
        this.webSecurityConfig = webSecurityConfig;
    }

    public AssignmentApplication() {
    }

    public static void main(String[] args) {
        SpringApplication.run(AssignmentApplication.class, args);
    }

    @Override
    public void run(String... args) {
        User guest = new User("guest", webSecurityConfig.passwordEncoder().encode("guest"));
        User test = new User("test", webSecurityConfig.passwordEncoder().encode("test"));
        userRepository.save(guest);
        userRepository.save(test);

        WorkSector manufacturing = new WorkSector("Manufacturing");
        manufacturing.setParentWorkSector(null);
        manufacturing.addChildWorkSectors("Construction materials").getParentWorkSector()
                .addChildWorkSectors("Electronics and Optics").getParentWorkSector()
                .addChildWorkSectors("Food and Beverage")
                .addChildWorkSectors("Bakery & confectionery products").getParentWorkSector()
                .addChildWorkSectors("Beverages").getParentWorkSector()
                .addChildWorkSectors("Fish & fish products").getParentWorkSector()
                .addChildWorkSectors("Meat & meat products").getParentWorkSector()
                .addChildWorkSectors("Milk & dairy products").getParentWorkSector()
                .addChildWorkSectors("Other").getParentWorkSector()
                .addChildWorkSectors("Sweets & snack food");

        manufacturing.addChildWorkSectors("Furniture")
                .addChildWorkSectors("Bathroom/sauna").getParentWorkSector()
                .addChildWorkSectors("Bedroom").getParentWorkSector()
                .addChildWorkSectors("Children's room").getParentWorkSector()
                .addChildWorkSectors("Kitchen").getParentWorkSector()
                .addChildWorkSectors("Living room").getParentWorkSector()
                .addChildWorkSectors("Office").getParentWorkSector()
                .addChildWorkSectors("Other (Furniture)").getParentWorkSector()
                .addChildWorkSectors("Outdoor").getParentWorkSector()
                .addChildWorkSectors("Project furniture");

        manufacturing.addChildWorkSectors("Machinery")
                .addChildWorkSectors("Machinery components").getParentWorkSector()
                .addChildWorkSectors("Machinery equipment/tools").getParentWorkSector()
                .addChildWorkSectors("Manufacture of machinery").getParentWorkSector()
                .addChildWorkSectors("Maritime")
                .addChildWorkSectors("Aluminium and steel workboats").getParentWorkSector()
                .addChildWorkSectors("Boat/Yacht building").getParentWorkSector()
                .addChildWorkSectors("Ship repair and conversion").getParentWorkSector().getParentWorkSector()
                .addChildWorkSectors("Metal structures").getParentWorkSector()
                .addChildWorkSectors("Other").getParentWorkSector()
                .addChildWorkSectors("Repair and maintenance service");

        manufacturing.addChildWorkSectors("Metalworking")
                .addChildWorkSectors("Construction of metal structures").getParentWorkSector()
                .addChildWorkSectors("Houses and buildings").getParentWorkSector()
                .addChildWorkSectors("Metal products").getParentWorkSector()
                .addChildWorkSectors("Metal works")
                .addChildWorkSectors("CNC-machining").getParentWorkSector()
                .addChildWorkSectors("Forgings, Fasteners").getParentWorkSector()
                .addChildWorkSectors("Gas, Plasma, Laser cutting").getParentWorkSector()
                .addChildWorkSectors("MIG, TIG, Aluminum welding");

        manufacturing.addChildWorkSectors("Plastic and Rubber")
                .addChildWorkSectors("Packaging").getParentWorkSector()
                .addChildWorkSectors("Plastic goods").getParentWorkSector()
                .addChildWorkSectors("Plastic processing technology")
                .addChildWorkSectors("Blowing").getParentWorkSector()
                .addChildWorkSectors("Moulding").getParentWorkSector()
                .addChildWorkSectors("Plastics welding and processing").getParentWorkSector().getParentWorkSector()
                .addChildWorkSectors("Plastic profiles");

        manufacturing.addChildWorkSectors("Printing")
                .addChildWorkSectors("Advertising").getParentWorkSector()
                .addChildWorkSectors("Book/Periodicals printing").getParentWorkSector()
                .addChildWorkSectors("Labelling and packaging printing");

        manufacturing.addChildWorkSectors("Textile and Clothing")
                .addChildWorkSectors("Clothing").getParentWorkSector()
                .addChildWorkSectors("Textile");

        manufacturing.addChildWorkSectors("Wood")
                .addChildWorkSectors("Other (Wood)").getParentWorkSector()
                .addChildWorkSectors("Wooden building materials").getParentWorkSector()
                .addChildWorkSectors("Wooden houses");

        workSectorRepository.save(manufacturing);

        WorkSector other = new WorkSector("Other");
        other.setParentWorkSector(null);

        other.addChildWorkSectors("Creative industries").getParentWorkSector()
                .addChildWorkSectors("Energy technology").getParentWorkSector()
                .addChildWorkSectors("Environment");

        workSectorRepository.save(other);

        WorkSector service = new WorkSector("Service");
        service.setParentWorkSector(null);

        service.addChildWorkSectors("Business services").getParentWorkSector()
                .addChildWorkSectors("Engineering").getParentWorkSector()
                .addChildWorkSectors("Information Technology and Telecommunications")
                .addChildWorkSectors("Data processing, Web portals, E-marketing").getParentWorkSector()
                .addChildWorkSectors("Programming, Consultancy").getParentWorkSector()
                .addChildWorkSectors("Software, Hardware").getParentWorkSector()
                .addChildWorkSectors("Telecommunications");

        service.addChildWorkSectors("Tourism").getParentWorkSector()
                .addChildWorkSectors("Translation services").getParentWorkSector()
                .addChildWorkSectors("Transport and Logistics")
                .addChildWorkSectors("Air").getParentWorkSector()
                .addChildWorkSectors("Rail").getParentWorkSector()
                .addChildWorkSectors("Road").getParentWorkSector()
                .addChildWorkSectors("Water");

        workSectorRepository.save(service);
    }
}
