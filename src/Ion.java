public class Ion
{
    public double position, velocity, mass, charge, time;
    public int atomicMass, elementaryCharge;

    // value from https://physics.nist.gov/cgi-bin/cuu/Value?ukg
    private final double AMU = 1.66053906660E-27;
    // value from https://physics.nist.gov/cgi-bin/cuu/Value?e
    private final double Qe = 1.602176634E-19;

    public Ion(int m)
    {
        this(m, 1);
    }
    public Ion(int m, int c)
    {
        position = 0;
        velocity = 0;
        time = 0;
        atomicMass = m;
        mass = (double) atomicMass * AMU;
        elementaryCharge = c;
        charge = (double) c * Qe;
    }
}
