package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

public class FakultetRacunarstva extends ObrazovnaUstanova implements Diplomski{

    public FakultetRacunarstva(String nazivUstanove, Predmet[] predmeti, Profesor[] profesori, Student[] studenti, Ispit[] ispiti) {
        super(nazivUstanove, predmeti, profesori, studenti, ispiti);
    }

    @Override
    public Student odrediStudentaZaRektorovuNagradu() {
        BigDecimal najveciProsjek = BigDecimal.ZERO;
        Student najuspjesnijiStudent = null;

        for(Student student : getStudenti()){       //prolazimo kroz studente

            Ispit[] ispitiGodina = new Ispit[ispiti.length];        //definiramo nove ispite
            Integer br=0;

            for(Ispit ispit : ispiti){
                    ispitiGodina[br]=new Ispit(ispit.getPred(),ispit.getStud(),ispit.getOcjena(), ispit.getDatumIVrijeme(), ispit.getDvorana());
                    br++;
            }

            BigDecimal projsjek = odrediProsjekOcjenaNaIspitima(ispitiGodina);

            if(projsjek.compareTo(najveciProsjek) > 0 || najuspjesnijiStudent==null || najveciProsjek.equals(najveciProsjek) && student.getDatumRodjenja().compareTo(najuspjesnijiStudent.getDatumRodjenja())>0){
                najuspjesnijiStudent = student;
                najveciProsjek=projsjek;
            }
        }

        return najuspjesnijiStudent;
    }

    @Override
    Student odrediNajuspijesnijegStudentaNaGodini(Integer godina) {

        Integer brojIzvrsnih = 0;
        Student najuspjesnijiStudent = null;

        for(Student student : getStudenti()){
            Ispit[] ispiti = filtrirajIspitePoStudentu(getIspiti(), student);

            Ispit[] ispitiUGodini = new Ispit[ispiti.length];
            Integer br=0;

            for(Ispit ispit : ispiti){
                if(godina.equals(ispit.getDatumIVrijeme().getYear())){          //ako godina ispita i zadana godina se slazu onda se ti ispiti spremaju u ispiti u godini
                    if(ispit.getOcjena()==5) {                                  //provjeravamo li je li ocjena 5 ako je spremamo u novo polje
                        ispitiUGodini[br] = new Ispit(ispit.getPred(), ispit.getStud(), ispit.getOcjena(), ispit.getDatumIVrijeme(), ispit.getDvorana());
                        br++;
                    }
                }
            }

            Integer izvrstan = br;

            if(izvrstan.compareTo(brojIzvrsnih) > 0){
                najuspjesnijiStudent = student;
            }

        }

        return najuspjesnijiStudent;
    }

    @Override
    public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(Ispit[] ispiti, Integer ocjena_diplomskog, Integer ocjena_obrane) {
        Integer zbroj = 0;
        Integer br = 0;
        for (int i = 0; i < ispiti.length; i++) {
            Integer ocjena = ispiti[i].getOcjena();
            zbroj = zbroj + ocjena;
            br++;
        }
        Integer prosjekOcjenaStudenta =(zbroj/br);
        BigDecimal konacna = BigDecimal.valueOf((prosjekOcjenaStudenta*3+ocjena_obrane+ocjena_diplomskog)/5);
        return konacna;
    }
}
