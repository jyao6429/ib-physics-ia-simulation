import java.util.Random;

public class Simulation
{
    private double potentialDifference, totalDistance;
    private int ionMass, ionCharge;

    public Simulation(double deltaV, double distance, int mass, int charge)
    {
        // Error for potentialDifference and totalDistance with Gaussian distribution with std dev of 5%
        Random rand = new Random();
        potentialDifference = deltaV * (1 + 0.05 * rand.nextGaussian());
        totalDistance = distance * (1 + 0.05 * rand.nextGaussian());
        ionMass = mass;
        ionCharge = charge;
    }
    public static void main(String[] args)
    {
        double tempDeltaV;
        double tempDistance;
        int tempMass;
        int tempCharge;
        if (args.length == 4)
        {
            tempDeltaV = Double.parseDouble(args[0]);
            tempDistance = Double.parseDouble(args[1]);
            tempMass = Integer.parseInt(args[2]);
            tempCharge = Integer.parseInt(args[3]);
        }
        else
        {
            // 5 kV
            tempDeltaV = 5000;
            // 10 cm
            tempDistance = 0.5;
            // 4 amu
            tempMass = 4;
            // 1 e+
            tempCharge = 1;

        }
        Simulation sim = new Simulation(tempDeltaV, tempDistance, tempMass, tempCharge);
        SimulationData data = sim.run();
        System.out.printf("Final Velocity: %.4e m/s\n" +
                          "Time Elapsed: %.4e s\n" +
                          "Ion Mass: %d amu\n" +
                          "Ion Charge: %d e+\n" +
                          "Voltage Difference: %.04e V\n" +
                          "Separation Distance: %.04e m", data.ion.velocity, data.ion.time, data.ion.atomicMass, data.ion.elementaryCharge, data.potentialDifference, data.totalDistance);
    }
    public SimulationData run()
    {
        Ion ion = new Ion(ionMass, ionCharge);

        // time interval for simulation
        double dT = 1E-12;

        // Assuming uniform electric field
        double EField = potentialDifference / totalDistance;
        // Calculate acceleration using F = Eq, a = F/m
        double acceleration = (EField * ion.charge) / ion.mass;

        // Keep running until ion is past the total distance
        while (ion.position < totalDistance)
        {
            // Use kinematics equation to calculate the change in velocity and position
            ion.time += dT;
            ion.velocity += acceleration * dT;
            ion.position += ion.velocity * dT + acceleration * dT * dT / 2;
        }
        return new SimulationData(ion, potentialDifference, totalDistance);
    }
}

