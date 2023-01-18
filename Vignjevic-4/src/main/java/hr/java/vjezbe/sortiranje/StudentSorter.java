package hr.java.vjezbe.sortiranje;

import hr.java.vjezbe.entitet.Student;

import java.util.Comparator;

public class StudentSorter implements Comparator <Student>{

    @Override
    public int compare(Student o1, Student o2) {
        int rezultat = o1.getPrezime().compareTo(o2.getPrezime());
        if (rezultat==0){
            rezultat = o1.getIme().compareTo(o2.getIme());
        }
        return rezultat;
    }
}
