package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

public class VeleucilisteJave extends ObrazovnaUstanova implements Visokoskolska {

    public VeleucilisteJave(String nazivUstanove, Predmet[] predmeti, Profesor[] profesori, Student[] studenti, Ispit[] ispiti) {
        super(nazivUstanove, predmeti, profesori, studenti, ispiti);
    }

    @Override
    public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(Ispit[] ispiti, Integer ocjena_zavrsnog, Integer ocjena_obrane) {
        Integer zbroj = 0;
        Integer br = 0;
        for (int i = 0; i < ispiti.length; i++) {
            Integer ocjena = ispiti[i].getOcjena();
            zbroj = zbroj + ocjena;
            br++;
        }
        Integer prosjekOcjenaStudenta =(zbroj/br);
        BigDecimal konacna = BigDecimal.valueOf((prosjekOcjenaStudenta*2+ocjena_obrane+ocjena_zavrsnog)/4);
        return konacna;
    }

    @Override
    Student odrediNajuspijesnijegStudentaNaGodini(Integer godina) {

        BigDecimal najveciProsjek = BigDecimal.ZERO;
        Student najuspjesnijiStudent = null;

        for(Student student : getStudenti()){
            Ispit[] ispiti = filtrirajIspitePoStudentu(getIspiti(), student);

            Ispit[] ispitiUGodini = new Ispit[ispiti.length];
            Integer br=0;

            for(Ispit ispit : ispiti){
                if(godina.equals(ispit.getDatumIVrijeme().getYear())){          //ako godina ispita i zadana godina se slazu onda se ti ispiti spremaju u ispiti u godini
                    ispitiUGodini[br]=new Ispit(ispit.getPred(),ispit.getStud(),ispit.getOcjena(), ispit.getDatumIVrijeme(), ispit.getDvorana());
                    br++;
                }
            }

            BigDecimal projsjek = odrediProsjekOcjenaNaIspitima(ispitiUGodini);

            if(projsjek.compareTo(najveciProsjek) >= 0){
                najuspjesnijiStudent = student;
            }

        }

        return najuspjesnijiStudent;
    }
}