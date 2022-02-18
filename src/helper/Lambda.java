package helper;

import model.Appointment;

@FunctionalInterface
public interface Lambda {
    public boolean compare(Appointment a, Appointment b);
}
