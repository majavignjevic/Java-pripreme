package hr.java.vjezbe.entitet;

import hr.java.vjezbe.glavna.Glavna;
import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudentaException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import hr.java.vjezbe.iznimke.PostojiViseNajmladjihStudenataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FakultetRacunarstva extends ObrazovnaUstanova implements Diplomski{
    private static final Logger logger = LoggerFactory.getLogger(FakultetRacunarstva.class);

    public FakultetRacunarstva(String nazivUstanove, List<Predmet> predmeti, List<Profesor> profesori, List<Student> studenti, List<Ispit> ispiti) {
        super(nazivUstanove, predmeti, profesori, studenti, ispiti);
    }

    @Override
    public Student odrediStudentaZaRektorovuNagradu() {
        BigDecimal najveciProsjek = BigDecimal.ZERO;
        Student najuspjesnijiStudent = null;

        for(Student student : getStudenti()){       //prolazimo kroz studente

            List<Ispit> ispitiGodina = new ArrayList<>();        //definiramo nove ispite
            Integer br=0;

            for(Ispit ispit : ispiti){
                    ispitiGodina.add(new Ispit(ispit.getPred(),ispit.getStud(),ispit.getOcjena(), ispit.getDatumIVrijeme(), ispit.getDvorana()));
                    br++;
            }

            try {
                BigDecimal projsjek = odrediProsjekOcjenaNaIspitima(ispitiGodina);

                if(projsjek.compareTo(najveciProsjek) > 0 || najuspjesnijiStudent==null || najveciProsjek.equals(najveciProsjek) && student.getDatumRodjenja().compareTo(najuspjesnijiStudent.getDatumRodjenja())<0){
                    najuspjesnijiStudent = student;
                    najveciProsjek=projsjek;
                }
            }
            catch(NemoguceOdreditiProsjekStudentaException ex1){
                logger.error("poruka: ", ex1);
                System.out.println(ex1.getMessage());
            }
        }

        Student najmladi = null;

        for(Student student: getStudenti()){

            if (najmladi==null || student.getDatumRodjenja().compareTo(najmladi.getDatumRodjenja())<=0) {
                najmladi = student;
            }
        }

        String najmladjiStudenti = "";
        for(Student student: getStudenti()){
            if (student != najmladi || student.getDatumRodjenja().compareTo(najmladi.getDatumRodjenja())==0) {
               najmladjiStudenti += student.getIme() + " " + student.getPrezime() + ", ";
            }
        }

        if(!najmladjiStudenti.isBlank()){
            throw new PostojiViseNajmladjihStudenataException(" nnn " + najmladjiStudenti);
        }


        return najuspjesnijiStudent;
    }

    @Override
    Student odrediNajuspijesnijegStudentaNaGodini(Integer godina) {

        Integer brojIzvrsnih = 0;
        Student najuspjesnijiStudent = null;

        for(Student student : getStudenti()){
            List<Ispit> ispiti = filtrirajIspitePoStudentu(getIspiti(), student);

            List<Ispit> ispitiUGodini = new ArrayList<>();
            Integer br=0;

            for(Ispit ispit : ispiti){
                if(godina.equals(ispit.getDatumIVrijeme().getYear())){          //ako godina ispita i zadana godina se slazu onda se ti ispiti spremaju u ispiti u godini
                    if(ispit.getOcjena().getNumber().equals(5)) {                                  //provjeravamo li je li ocjena 5 ako je spremamo u novo polje
                        ispitiUGodini.add(new Ispit(ispit.getPred(), ispit.getStud(), ispit.getOcjena(), ispit.getDatumIVrijeme(), ispit.getDvorana()));
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
    public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(List<Ispit> ispiti, Integer ocjena_diplomskog, Integer ocjena_obrane) {
        Integer zbroj = 0;
        Integer br = 0;
        for (int i = 0; i < ispiti.size(); i++) {
            Integer ocjena = ispiti.get(i).getOcjena().getNumber();
            zbroj = zbroj + ocjena;
            br++;
        }
        Integer prosjekOcjenaStudenta =(zbroj/br);
        BigDecimal konacna = BigDecimal.valueOf((prosjekOcjenaStudenta*3+ocjena_obrane+ocjena_diplomskog)/5);
        return konacna;
    }
}
