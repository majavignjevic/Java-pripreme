package hr.java.vjezbe.entitet;

import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudentaException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public interface Visokoskolska {
    BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(List<Ispit> ispiti, Integer ocjena_zavrsnog, Integer ocjena_obrane);
    default BigDecimal odrediProsjekOcjenaNaIspitima(List<Ispit> ispiti) throws NemoguceOdreditiProsjekStudentaException{
        BigDecimal prosjecna=BigDecimal.valueOf(0);
        Integer zbroj=0;
        Integer br=0;
        for (Ispit ispit: ispiti){
            Integer grade = ispit.getOcjena().getNumber();
            if(grade>1) {
                zbroj = zbroj + grade;
                br++;
            }
            else{
                throw new NemoguceOdreditiProsjekStudentaException("Student "+ ispit.getStud().getIme()+" "
                        +ispit.getStud().getPrezime() +"zbog negativne ocjene na jednom od ispita ima prosjek \"nedovoljan (1)\"!");
            }
        }
        prosjecna = BigDecimal.valueOf(zbroj/br);
        return  prosjecna;
    }
    /*private Ispit[] filtrirajPolozeneIspite(Ispit[] ispiti) {
        Integer br = 0, fil=0;
        for (Ispit ispit: ispiti) {
            Ispit test = ispit;
            if (test.getOcjena() > 1) {
                br++;
            }
        }
        Ispit[] filtrirano = new Ispit[br-1];
        for (Ispit ispit: ispiti) {
            Ispit test = ispit;
            if(test.getOcjena()>1){
                filtrirano[fil]= test;
                fil++;
            }
        }
        return filtrirano;
    }*/
    default List<Ispit> filtrirajIspitePoStudentu(List<Ispit> ispiti, Student stud){
        List<Ispit> noviIspiti = new ArrayList<>();

        long startTime =System.currentTimeMillis();

        ispiti.stream()
                .filter(ispit -> ispit.getStud().equals(stud))
                .forEach(ispit ->  noviIspiti.add(ispit));

        long endTime =System.currentTimeMillis();
        System.out.println("Vrijeme potrebno: " + (endTime - startTime));
        return noviIspiti;
    }
}
