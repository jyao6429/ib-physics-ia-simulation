public class SimulationData
{
    public Ion ion;
    public double potentialDifference, totalDistance;

    public SimulationData(Ion i, double deltaV, double distance)
    {
        ion = i;
        potentialDifference = deltaV;
        totalDistance = distance;
    }
}
