package hr.java.vjezbe.entitet;

import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudentaException;

import java.math.BigDecimal;

public interface Visokoskolska {
    BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(Ispit[] ispiti, Integer ocjena_zavrsnog, Integer ocjena_obrane);
    default BigDecimal odrediProsjekOcjenaNaIspitima(Ispit[] ispiti) throws NemoguceOdreditiProsjekStudentaException{
        BigDecimal prosjecna=BigDecimal.valueOf(0);
        Integer zbroj=0;
        Integer br=0;
        for (Ispit ispit: ispiti){
            Integer grade = ispit.getOcjena();
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
    default Ispit[] filtrirajIspitePoStudentu(Ispit[] ispiti, Student stud){
        Ispit[] noviIspiti = new Ispit[ispiti.length];
        Integer brojac=0,k=0;
        for (Ispit ispit: ispiti) {
            Ispit test = ispit;
            if(stud.equals(ispit.getStud())){
                brojac++;
            }
        }
        for (Ispit ispit: ispiti){
            if(stud.equals(ispit.getStud())){
                noviIspiti[k] = new Ispit(ispit.getPred(), ispit.getStud(), ispit.getOcjena(), ispit.getDatumIVrijeme(), ispit.getDvorana());
                k++;
            }
        }
        return noviIspiti;
    }
}
