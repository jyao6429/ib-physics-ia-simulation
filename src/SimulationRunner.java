import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SimulationRunner
{
    public static void main(String[] args) throws IOException
    {
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("raw data.csv")));
        pw.println("mass (amu), final velocity (m/s)");
        int[] masses = {4, 20, 40, 84, 131};
        for (int m : masses)
        {
            Simulation sim = new Simulation(5000, 0.5, m, 1);
            for (int i = 0; i < 10; i++)
            {
                sim.randomize();
                SimulationData data = sim.run();
                pw.printf("%d, %f\n", data.ion.atomicMass, data.ion.velocity);
            }
        }
        pw.close();
    }
}
